package org.fxsct;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Window;
import javafx.util.Callback;

public class NodeBrowser {
	
	private final ObjectProperty<Window> subjectStage = new SimpleObjectProperty<Window>();

    private final TreeView<Node> nodeTree = new TreeView<Node>() {

        {
            this.setEditable(false);
            this.setCellFactory(new Callback<TreeView<Node>, TreeCell<Node>>() {

                public TreeCell<Node> call(TreeView<Node> p) {
                    return new NodeTreeCell();
                }
            });
            
        }
    };
    private final ObjectProperty<Node> subjectNode = new SimpleObjectProperty<Node>();
    
    public ObjectProperty<Node> selectedNodeProperty() {
        return subjectNode;
    }
    
    public NodeBrowser() {
    	subjectStage.addListener(stageListener);
        nodeTree.getSelectionModel().getSelectedItems().addListener(new InvalidationListener() {

            public void invalidated(Observable o) {
                ObservableList<TreeItem<Node>> selected = nodeTree.getSelectionModel().getSelectedItems();
                if (selected.isEmpty()) {
                    subjectNode.set(null);
                } else {
                    subjectNode.set(selected.get(0).getValue());
                }
            }
        });
    }

    public Parent getViewNode() {
        return nodeTree;
    }
    
    private final InvalidationListener stageListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable paramObservable) {
			if (subjectStage.get() == null || 
					subjectStage.get().getScene() == null ||
					subjectStage.get().getScene().getRoot() == null)
				nodeTree.setRoot(null);
			else
				nodeTree.setRoot(new NodeTreeItem(subjectStage.get().getScene().getRoot()));
		}
    };

 
    Node getSelectedNode() {
        return subjectNode.get();
    }
    
    public ObjectProperty<Window> subjectStageProperty() {
    	return subjectStage;
    }

	public void scrollTo(Node node) {
		// TODO Auto-generated method stub
		
	}

}
