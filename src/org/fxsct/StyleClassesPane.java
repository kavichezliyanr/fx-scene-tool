package org.fxsct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

public class StyleClassesPane {

	private final ObjectProperty<Scene> subjectScene = new SimpleObjectProperty<Scene>();
	private final HashMap<String, List<Node>> styleClassMap = new HashMap<String, List<Node>>();
	private ListView<String> lv;
	public Node getContent() {
		if (lv == null) {
			lv = new ListView<String>();
			subjectScene.addListener(new InvalidationListener() {
				
				@Override
				public void invalidated(Observable paramObservable) {
					refresh();
				}
			});
			refresh();
		}
		return lv;
	}
	
	public ObjectProperty<Scene> subjectSceneProperty() {
		return subjectScene;
	}
	
	public void refresh() {
		styleClassMap.clear();
		if (lv == null || subjectScene.get() == null)
			return;
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.addAll(subjectScene.get().getRoot().getChildrenUnmodifiable());
		Node n;
		while ((n = nodes.poll()) != null) {
			for (String style: n.getStyleClass()) {
				List<Node> nn = styleClassMap.get(style);
				if (nn == null) {
					nn = new ArrayList<Node>();
					styleClassMap.put(style, nn);
					System.out.println("Adding style class: " + style);
				}
				nn.add(n);
				if (n instanceof Parent)
					nodes.addAll(((Parent)n).getChildrenUnmodifiable());
			}
			
		}
		lv.getItems().setAll(styleClassMap.keySet());
	}
	
	

}
