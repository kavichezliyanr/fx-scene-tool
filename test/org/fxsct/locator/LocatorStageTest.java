package org.fxsct.locator;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LocatorStageTest extends Application {
	final LocatorStage locStage = new LocatorStage();
	private Stage primary;
	private Popup secondary;

	private final Button b = new Button("Click Me!"){{
		setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (locStage.parentWindowProperty().get() == null)
					locStage.parentWindowProperty().set(primary);
				else if (locStage.parentWindowProperty().get() == primary)
					locStage.parentWindowProperty().set(secondary);
				else 
					locStage.parentWindowProperty().set(null);
			}
		});
	}};

	@Override
	public void start(Stage s) throws Exception {
		primary = s;
		StackPane sp = new StackPane();
		sp.getChildren().add(b);
		primary.setScene(new Scene(sp));
		primary.show();
		
		secondary = new Popup();
		secondary.getContent().setAll(new Label("Stage 2"));
		secondary.show(primary);
		Circle c1 = new Circle(5, Color.TRANSPARENT);
		c1.setStroke(Color.RED);
		Circle c2 = new Circle(10, Color.TRANSPARENT);
		c2.setStroke(Color.RED);
		Circle c3 = new Circle(15, Color.TRANSPARENT);
		c3.setStroke(Color.RED);
//		DoubleBinding x = Bindings.when(Bindings.isNotNull(locStage.parentWindowProperty()))
//				.then(Bindings.selectDouble(locStage.parentWindowProperty(), "x"))
//				.otherwise(Screen.getPrimary().getBounds().getWidth()/2);
//		DoubleBinding y = Bindings.when(Bindings.isNotNull(locStage.parentWindowProperty()))
//				.then(Bindings.selectDouble(locStage.parentWindowProperty(), "y"))
//				.otherwise(Screen.getPrimary().getBounds().getHeight()/2);
		ReadOnlyDoubleProperty x = primary.xProperty();
		ReadOnlyDoubleProperty y = primary.yProperty();

		c1.centerXProperty().bind(x);
		c1.centerYProperty().bind(y);
		c2.centerXProperty().bind(x);
		c2.centerYProperty().bind(y);
		c3.centerXProperty().bind(x);
		c3.centerYProperty().bind(y);
		locStage.getContent().addAll(c1, c2, c3);
		locStage.parentWindowProperty().set(secondary);
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}