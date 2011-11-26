package org.fxsct.locator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BoundsLocatorTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		final BoundsLocator bl = new BoundsLocator();
		AnchorPane ap = new AnchorPane();
		Button b = new Button("Click Me");
		ap.getChildren().addAll(bl.getLocatorNode(), b);
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent paramT) {
				bl.boundsProperty().set(new BoundingBox(Math.random()*200,Math.random()*200,Math.random()*200,Math.random()*200));
				
			}
		});
		ap.resize(300, 300);
		stage.setScene(new Scene(ap));
		stage.show();
	}
}