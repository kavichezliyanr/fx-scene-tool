/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fxsct;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;

/**
 * 
 * @author ABSW
 */
class NodeTreeItem extends TreeItem<Node> {
	private boolean hasInitializedChildren = false;

	NodeTreeItem(Node node) {
		super(node);

//		setExpanded(false);
		// expandedProperty().addListener(new InvalidationListener() {
		//
		// public void invalidated(Observable o) {
		// if (isExpanded() && getParent() != null && !hasInitializedChildren)
		// initChildren();
		// }
		// });
	}

	@Override
	public ObservableList<TreeItem<Node>> getChildren() {
		if (!hasInitializedChildren) {
			initChildren();
		}
		return FXCollections.unmodifiableObservableList(super.getChildren());
	}

	@Override
	public boolean isLeaf() {
		return !(getValue() instanceof Parent);
	}

	private List<TreeItem<Node>> buildChildren() {
		ArrayList<TreeItem<Node>> newKids = new ArrayList<TreeItem<Node>>();
		for (Node n : ((Parent) getValue()).getChildrenUnmodifiable()) {
			System.out.println("buildChildren() "
					+ NodeInfo.getBreadCrumbString(n));
			newKids.add(new NodeTreeItem(n));
		}
		return newKids;
	}

	ListChangeListener<Node> nodeChangeListener = new ListChangeListener<Node>() {

		public void onChanged(Change<? extends Node> change) {
			List<TreeItem<Node>> children = NodeTreeItem.super
					.getChildren();
			while (change.next()) {
				int replaceSize = Math.min(change.getAddedSize(),
						change.getRemovedSize());
				for (int i = 0; i < replaceSize; i++) {
					int absIndex = change.getFrom() + i;
					((NodeTreeItem) (children.get(absIndex))).dispose();
					Node n = change.getAddedSubList().get(i);
					System.out
							.println("initChildren/onChanged(): Replacing node "
									+ NodeInfo.getBreadCrumbString(n));
					children.set(absIndex, new NodeTreeItem(n));
				}
				if (change.getAddedSize() > change.getRemovedSize()) {
					for (int i = 0; i < change.getAddedSize()
							- change.getAddedSize(); i++) {
						int absIndex = change.getFrom() + replaceSize + i;
						Node n = change.getAddedSubList().get(replaceSize + i);
						System.out
								.println("initChildren/onChanged(): Adding node "
										+ NodeInfo.getBreadCrumbString(n));
						children.add(absIndex, new NodeTreeItem(n));
					}
				} else if (change.getAddedSize() < change.getRemovedSize()) {
					int startIndex = change.getFrom() + replaceSize;
					int removeSize = change.getRemovedSize() - replaceSize;
					try {
						List<TreeItem<Node>> removedItems = children.subList(
								startIndex, startIndex + removeSize);
						for (TreeItem<Node> item : removedItems) {
							((NodeTreeItem) item).dispose();
						}
						children.removeAll(removedItems);
					} catch (IndexOutOfBoundsException ex) {
						System.out.println(String.format("NodeTreeItem Error: Tried to remove children from %d to %d but the list only had %d items", startIndex, removeSize, children.size()));
					}
				}
			}
		}
	};

	private void initChildren() {
		hasInitializedChildren = true;
		if (getValue() instanceof Parent) {
			Parent me = (Parent) getValue();
			super.getChildren().setAll(buildChildren());
			me.getChildrenUnmodifiable().addListener(nodeChangeListener);
		}
	}

	private void dispose() {
		// Parent me = (Parent) getValue();
		// me.getChildrenUnmodifiable().removeListener(nodeInvalidationListener);
		// final ObservableList<TreeItem<Node>> children =
		// NodeTreeItem.super.getChildren();
		// children.clear();
		// isChildrenInitialized = false;
	}

	@Override
	public boolean equals(Object paramObject) {
		if (paramObject == null) {
			return false;
		}
		if (paramObject instanceof NodeTreeItem) {
			NodeTreeItem localTreeItem = (NodeTreeItem) paramObject;
			if (isLeaf() != localTreeItem.isLeaf()) {
				return false;
			}
			if (isExpanded() != localTreeItem.isExpanded()) {
				return false;
			}
			if ((getValue() != localTreeItem.getValue())
					&& (((getValue() == null) || (!(getValue()
							.equals(localTreeItem.getValue())))))) {
				return false;
			}
			if ((getGraphic() != localTreeItem.getGraphic())
					&& (((getGraphic() == null) || (!(getGraphic()
							.equals(localTreeItem.getGraphic())))))) {
				return false;
			}
			if ((getParent() != localTreeItem.getParent())
					&& (((getParent() == null) || (!(getParent()
							.equals(localTreeItem.getParent())))))) {
				return false;
			}

			return true;
		} else
			return false;

	}
}
