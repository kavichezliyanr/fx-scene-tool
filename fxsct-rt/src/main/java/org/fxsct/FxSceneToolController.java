/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fxsct;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 *
 * @author ABSW
 */
public class FxSceneToolController extends Controller implements Initializable {

    NodeToolController nodeTool;
    
    @FXML private VBox root;
    
//    @FXML
//    private Tab nodeToolTab;
//    @FXML
//    private Tab stylesheetTab;
    
	private final BooleanProperty trackMouse = new SimpleBooleanProperty(false);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nodeTool = SceneToolFactory.createNodeToolController();
        nodeTool.trackMouseProperty().bind(trackMouse);
        root.getChildren().setAll(nodeTool.getView());
    }

    ObservableList<Window> getStages() {
        return nodeTool.getStages();
    }

	public BooleanProperty trackMouseProperty() {
		return trackMouse ;
	}
}
