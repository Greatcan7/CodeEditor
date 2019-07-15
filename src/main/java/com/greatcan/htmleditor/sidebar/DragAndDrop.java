package com.greatcan.htmleditor.sidebar;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;

/**
 * Responsible for drag and drop file into sidebar
 */
public class DragAndDrop {

    private static final String TAG = "DragAndDrop";

    //Sidebar
    private TreeView treeView;

    public DragAndDrop(TreeView treeView) {
        this.treeView = treeView;
    }

    public EventHandler<DragEvent> setDragOver() {
        return event -> {
            final Dragboard db = event.getDragboard();

            final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                    || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                    || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");

            if (db.hasFiles()) {
                treeView.getStyleClass().add("dragOver");

                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        };
    }

    public EventHandler<DragEvent> setDragExited(){
        return event -> {

            treeView.getStyleClass().clear();
            event.consume();
        };
    }

    public EventHandler<DragEvent> setDragDropped(){
        return event -> {
            final Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                // Only get the first file from the list
                final File file = db.getFiles().get(0);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(file.getAbsolutePath());
                        try {
                            //if(!viewDirectories.getChildren().isEmpty()){
                            //    viewDirectories.getChildren().remove(0);
                            //}
                            OpenFolder openFolder = new OpenFolder(treeView);
                            openFolder.getContentFromFolder(new File(file.getAbsolutePath()));

                        } catch (Exception ex) {
                            System.out.println(TAG + ", setDragDropped: " + ex.getMessage());
                        }
                    }
                });
            }
            event.setDropCompleted(success);
            event.consume();
        };
    }
}
