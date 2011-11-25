package org.fxsct.locator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class NodeVisualizerTest extends Application {
	private final NodeLocator nl = new NodeLocator(){{
		strokePaintProperty().set(Color.BLUE);
		strokeTypeProperty().set(StrokeType.OUTSIDE);
	}};
	private final Label label1 = new Label(">>>>>>>>>--1--<<<<<<<<<<<");
	private final Label label2 = new Label(">>>>>>>>>--2--<<<<<<<<<<<");

	private Node currentNode = label1;
	
	private final AnchorPane parent = new AnchorPane(){{
		getChildren().addAll(label1, label2);
		setStyle("-fx-background-color: green");
	}};
	
	private final AnchorPane ap = new AnchorPane(){{
		getChildren().addAll(parent);
	}};
	
	private final Button moveButton = new Button("Move node"){{
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent paramT) {
				AnchorPane.setLeftAnchor(currentNode, Math.random() * 200);
				AnchorPane.setTopAnchor(currentNode, Math.random() * 200);

			}
		});
	}};
	
	private final Button switchButton = new Button("Switch node"){{
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent paramT) {
				if (currentNode == label1)
					currentNode = label2;
				else
					currentNode = label1;
				nl.subjectNodeProperty().set(currentNode);
				
			}
		});
	}};
	
	private final Button moveParentButton = new Button("Move parent"){{
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent paramT) {
				AnchorPane.setLeftAnchor(parent, Math.random() * 200);
				AnchorPane.setTopAnchor(parent, Math.random() * 200);

			}
		});
	}};
	
	private final HBox buttonBox = new HBox(){{
		getChildren().setAll(moveButton, switchButton, moveParentButton);
	}};
	
	private final BorderPane bp = new BorderPane(){{
		setCenter(ap);
		setBottom(buttonBox);
		
	}};
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(bp));
		stage.setWidth(300);
		stage.setHeight(300);
		stage.show();
		nl.subjectNodeProperty().set(label1);
	}

	public static void main(String[] args) {
		launch(args);
	}

}