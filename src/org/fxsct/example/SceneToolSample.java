/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fxsct.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.fxsct.SceneTool;

/**
 *
 * @author ABSW
 */
public class SceneToolSample extends Application {
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        
        stage.setScene(new Scene(root));
        stage.setTitle("Scenetool Sample");
        stage.show();
        stage.getScene().onMouseMovedProperty().set(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent ev) {
				System.out.println(String.format("Mouse Moved to %f,%f", ev.getX(), ev.getY() ));
				// TODO Auto-generated method stub
				
			}
		});
        
        SceneTool st = new SceneTool(stage);
//        st.show();
//        st.debug();
    }
}
