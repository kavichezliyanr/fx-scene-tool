package org.fxsct.locator;


import org.fxsct.util.BindingsTrace;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BoundsInfoTest extends Application {

	
	Label label = new Label("OOO>>>---<<<OOO");
	
	AnchorPane parent = new AnchorPane(){{
		getChildren().addAll(label);
		resize(200, 200);
	}};
	
	AnchorPane grandParent = new AnchorPane(){{
		getChildren().addAll(parent);
		resize(400,400);
	}};

	private final Button moveNodeButton = new Button("Move Node"){{
		setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				AnchorPane.setLeftAnchor(label, Math.random()*100);
				AnchorPane.setTopAnchor(label, Math.random()*100);
			}
		});
	}};
	
	private final Button moveParentButton = new Button("Move Parent"){{
		setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				AnchorPane.setLeftAnchor(parent, Math.random()*200);
				AnchorPane.setTopAnchor(parent, Math.random()*200);
			}
		});
	}};
	
	HBox buttonBox = new HBox() {{
		getChildren().addAll(moveNodeButton, moveParentButton);
	}};
	
	BorderPane borderPane = new BorderPane() {{
		setCenter(grandParent);
		setBottom(buttonBox);
	}};
	
	BoundsInfo inf = new BoundsInfo();
	
	@Override
	public void start(Stage stage) throws Exception {

		inf.subjectNodeProperty().set(label);
		stage.setScene(new Scene(borderPane));
		stage.show();
//		BindingsTrace.invalidate("boundsInLocal", inf.localBoundsProperty(), System.out);
//		BindingsTrace.invalidate("boundsInParent", inf.layoutBoundsProperty(), System.out);
//		BindingsTrace.invalidate("offsetY", inf.offsetYProperty(), System.out);
//		BindingsTrace.invalidate("layoutBounds", label.layoutBoundsProperty(), System.out);

	}
	
	public static void main(String[] args) {
		launch(args);
	}


}