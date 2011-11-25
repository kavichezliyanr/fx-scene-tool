/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.fxsct;

import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author ABSW
 */
public class NodeTreeCell extends TreeCell<Node> {

    HBox nodeLayout = new HBox();
    Text javaClass = new Text();
    Text cssId = new Text();
    Text styleList = new Text();
    NodeTreeCell() {
        nodeLayout.getChildren().addAll(javaClass, cssId, styleList);
        setGraphic(nodeLayout);
        javaClass.getStyleClass().add("fxct-text-class-name");
        cssId.getStyleClass().add("fxct-text-css-id");
        styleList.getStyleClass().add("fxct-text-style-class");
        nodeLayout.getStyleClass().add("fxct-node-tree-cell");
    }
    @Override
    protected void updateItem(Node t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty && t != null) {
            javaClass.setText(NodeInfo.getClassName(t));
            if (t.getId() != null && !t.getId().isEmpty())
                cssId.setText("#" + t.getId() + " ");
            else
                cssId.setText(" ");
            styleList.setText(NodeInfo.getStyleClass(t));
        } else {
            javaClass.setText("");
            cssId.setText("");
            styleList.setText("");
        }
    }
    
}
