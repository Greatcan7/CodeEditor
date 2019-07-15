package com.greatcan.htmleditor.tabmenu.tabcontent;

import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

public class CustomTextArea {

    public Node getNode() {
        TextArea textArea = new TextArea();
        textArea.setText("Hello, World!");

        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.K)) {
                    //showContextMenu();

                }
            }
        });

        textArea.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                TextAreaSkin skin = (TextAreaSkin) textArea.getSkin();
                int insertionPoint = skin.getInsertionPoint(event.getX(), event.getY());
                textArea.positionCaret(insertionPoint);
                showContextMenu(textArea, event);

            }
        });

        return textArea;
    }

    private void showContextMenu(TextArea textArea, ContextMenuEvent event) {
        // Create ContextMenu
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Menu Item 1");
        contextMenu.getItems().addAll(item1);

        contextMenu.show(textArea, event.getScreenX(), event.getScreenY());
    }
}
