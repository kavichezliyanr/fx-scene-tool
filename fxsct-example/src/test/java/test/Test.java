package test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;

public class Test implements Initializable{

	private int count = 100;
	@FXML private StackPane mainContent;
	private TreeItem<String> testNode;
	
	

	@FXML protected void addNode(ActionEvent event) {
		int index = getRandomIndex();
		
		TreeItem<String> newNode = createNextItem();
		if (index < 0)
			index = 0;
		testNode.getChildren().add(index, newNode);
	}
	
	@FXML protected void removeNode(ActionEvent event) {
	}
	
	@FXML protected void replaceNode(ActionEvent event) {
		
	}
	
	@FXML protected void resetNodes(ActionEvent event) {
		count = 100;
		testNode.getChildren().setAll(
				createNextItem(),
				createNextItem(),
				createNextItem(),
				createNextItem(),
				createNextItem()
				);
		
	}
	
	@FXML protected void clearNodes(ActionEvent event) {
		testNode.getChildren().clear();
	}
	
	private int getRandomIndex() {
		if (!testNode.getChildren().isEmpty()) {
			return (int) Math.floor(Math.random()*testNode.getChildren().size());
		} else
			return -1;
		
	}
	
	private TreeItem<String> createNextItem() {
		return new TreeItem<String>("Item " + String.valueOf(count++));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 TreeItem<String> root = new TreeItem<String>("Root Node");
		 root.setExpanded(true);
		 testNode = new TreeItem<String>("TestItem");
		 root.getChildren().addAll(
		     new TreeItem<String>("Item 1"),
		     new TreeItem<String>("Item 2"),
		     testNode,
		     new TreeItem<String>("Item 3")
		 );
		 TreeView<String> treeView = new TreeView<String>(root);
		 
		 mainContent.getChildren().add(treeView);
		// TODO Auto-generated method stub
		
	}
}
