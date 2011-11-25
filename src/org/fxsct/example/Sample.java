package org.fxsct.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Sample {

	@FXML private Label label;
	
	@FXML protected void handleButtonAction(ActionEvent event) {
        label.setTranslateX((Math.random() - 0.5) * 100);
        label.setTranslateY((Math.random() - 0.5) * 100);
        label.setText(String.format("Label to [%f, %f]", label.getTranslateX(), label.getTranslateY()));
        System.out.println("Button clicked!!");
    }
}
