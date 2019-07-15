package com.greatcan.htmleditor.sidebar;

import java.io.File;
import java.util.*;

import com.greatcan.htmleditor.utils.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SidebarContent extends TreeItem<String> {

    //Hidden file. Don't need to show it
    private static final String protectedFile = "desktop.ini";

    private final File file;

    public SidebarContent(File file) {
        super(file.toString());
        this.file = file;
        Config.absolutePath = file.getAbsolutePath();

        String object = this.getValue();

        SidebarGraphics sidebarGraphics = new SidebarGraphics();
        try {
            this.setGraphic(sidebarGraphics.getGraphics(object));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.isDirectory()) {
            this.setGraphic((new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/folder.png")))));
        }

        //set the value (which is what is displayed in the tree)
        String fullPath = file.getAbsolutePath();
        if (!fullPath.endsWith(File.separator)) {
            String value = file.toString();
            int indexOf = value.lastIndexOf(File.separator);
            if (indexOf > 0) {
                this.setValue(value.substring(indexOf + 1));
            } else {
                this.setValue(value);
            }
        }
    }

    public File getFile() {
        return (this.file);
    }

    @Override
    public ObservableList<TreeItem<String>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;

            ObservableList<SidebarContent> list = buildChildren(this);
            super.getChildren().addAll(sortingData(list));
        }
        return super.getChildren();
    }

    /**
     * Sorting data by directories, subdirectories, files
     * @param list
     * @return
     */
    private ObservableList<SidebarContent> sortingData(ObservableList<SidebarContent> list) {
        //List with directories
        ObservableList<SidebarContent> aDirs = FXCollections.observableArrayList();

        //List with files
        ObservableList<SidebarContent> aFiles = FXCollections.observableArrayList();

        //Main list
        ObservableList<SidebarContent> output = FXCollections.observableArrayList();

        for (SidebarContent item : list) {
            if (item.getFile().isDirectory()) {
                aDirs.add(item);
            } else {
                aFiles.add(item);
            }
        }

        //Add directories and files to main list
        output.addAll(aDirs);
        output.addAll(aFiles);

        return output;
    }

    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            isLeaf = this.file.isFile();
        }

        return isLeaf;
    }

    /**
     * Get all directories and files from chosen path
     * @param treeItem
     * @return
     */
    private ObservableList<SidebarContent> buildChildren(SidebarContent treeItem) {
        File f = treeItem.getFile();
        if (f != null && f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                ObservableList<SidebarContent> children = FXCollections.observableArrayList();
                for (File childFile : files) {
                    String path = childFile.toString();
                    if (!path.endsWith(protectedFile)) {
                        children.add(new SidebarContent(childFile));
                    }
                }
                return children;
            }
        }

        return FXCollections.emptyObservableList();
    }

    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;
    private boolean isLeaf;
}