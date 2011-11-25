/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fxsct;

import java.util.ArrayList;
import java.util.Map.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author ABSW
 */
public class NodePropertyTable {
    TableView<Pair<String, ObservableValue>> tv;
    
    
    public Node getRootNode() {
        if (tv == null) {
            tv = new TableView<Pair<String, ObservableValue>>();
            TableColumn<Pair<String, ObservableValue>,String> propNameCol = new TableColumn<Pair<String, ObservableValue>,String>("Property");
            propNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, ObservableValue>, String>, ObservableValue<String>>(){

                public ObservableValue<String> call(CellDataFeatures<Pair<String, ObservableValue>, String> p) {
                    return new SimpleStringProperty(p.getValue().getKey());
                }
            });
            propNameCol.setPrefWidth(150);
            propNameCol.setResizable(false);
            TableColumn<Pair<String, ObservableValue>,ObservableValue> propValueCol = new TableColumn<Pair<String, ObservableValue>,ObservableValue>("Value");
            propValueCol.setCellValueFactory(new Callback<CellDataFeatures<Pair<String, ObservableValue>, ObservableValue>, ObservableValue<ObservableValue>>(){

                public ObservableValue<ObservableValue> call(CellDataFeatures<Pair<String, ObservableValue>, ObservableValue> p) {
                    return p.getValue().getValue();
                }
            });
            tv.getColumns().addAll(propNameCol, propValueCol);
            tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
        return tv;
    }
    
    public void setNode(Node n) {
    	if (n!= null) {
	        ArrayList<Pair<String, ObservableValue>> props = new ArrayList<Pair<String, ObservableValue>>();
	        for (Entry<String, ObservableValue<?>> e: NodeInfo.getProperties(n).entrySet()) {
	            props.add(new Pair<String, ObservableValue>(e.getKey(), e.getValue()));
	        }
	        tv.getItems().setAll(props);
    	} else
    		tv.getItems().clear();
    }
    
}
