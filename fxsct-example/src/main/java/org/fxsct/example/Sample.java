package org.fxsct.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Sample {

	private static int count = 100; 
	@FXML private Label label;
	@FXML private VBox nodeHolder;
	
	
	@FXML protected void handleButtonAction(ActionEvent event) {
        label.setTranslateX((Math.random() - 0.5) * 100);
        label.setTranslateY((Math.random() - 0.5) * 100);
        label.setText(String.format("Label to [%f, %f]", label.getTranslateX(), label.getTranslateY()));
        System.out.println("Button clicked!!");
    }

	@FXML protected void addNode(ActionEvent event) {
		nodeHolder.getChildren().add(createNextLabel());
	}
	
	@FXML protected void removeNode(ActionEvent event) {
		int rmIndex = getRandomIndex();
		if (rmIndex >= 0) {
			nodeHolder.getChildren().remove(rmIndex);
		}
		
	}
	
	@FXML protected void replaceNode(ActionEvent event) {
		int rmIndex = getRandomIndex();
		if (rmIndex >= 0) {
			nodeHolder.getChildren().set(rmIndex, createNextLabel());
		}
		
	}
	
	@FXML protected void resetNodes(ActionEvent event) {
		Label newLabels[] = new Label[] {
			createNextLabel(),	
			createNextLabel(),	
			createNextLabel(),	
			createNextLabel(),	
			createNextLabel(),	
		};
		nodeHolder.getChildren().setAll(newLabels);
	}
	
	@FXML protected void clearNodes(ActionEvent event) {
		nodeHolder.getChildren().clear();
	}
	
	private int getRandomIndex() {
		if (!nodeHolder.getChildren().isEmpty()) {
			return (int) Math.floor(Math.random()*nodeHolder.getChildren().size());
		} else
			return -1;
		
	}
	
	private Label createNextLabel() {
		return new Label("New Label " + String.valueOf(count++));
	}
}
