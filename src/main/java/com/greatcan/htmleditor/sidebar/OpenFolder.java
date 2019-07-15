package com.greatcan.htmleditor.sidebar;

import com.greatcan.htmleditor.utils.Config;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;

/**
 * Class that opens the selected folder
 */
public class OpenFolder {

    private static final String TAG = "OpenFolder";

    private TreeView viewDirectories;

    //For getting primary objects
    public OpenFolder(TreeView viewDirectories) {
        this.viewDirectories = viewDirectories;
    }

    /**
     * Open directory chooser
     */
    public void showDirectoryChooser() {
        try {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setInitialDirectory(new File(System.getProperty("user.home")));
            File choice = dc.showDialog(Config.primaryStage);
            if (choice == null || !choice.isDirectory()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Could not open directory");
                alert.setContentText("The file is invalid.");
                alert.showAndWait();
            } else {
                getContentFromFolder(choice);
            }
        } catch (Exception e) {
            System.out.println(TAG + ", showDirectoryChooser: " + e.getMessage());
        }
    }

    public void getContentFromFolder(File file) {
        viewDirectories.setRoot(new SidebarContent(file));
        Config.absolutePath = file.getAbsolutePath();
    }
}
