package com.greatcan.htmleditor.controllers;

import com.greatcan.htmleditor.sidebar.DragAndDrop;
import com.greatcan.htmleditor.sidebar.SidebarContent;
import com.greatcan.htmleditor.tabmenu.CreateTab;
import com.greatcan.htmleditor.tabmenu.MenuEventHandling;
import com.greatcan.htmleditor.utils.Config;
import com.greatcan.htmleditor.utils.StringManipulation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The class responsible for main interface in program
 */
public class InterfaceController implements Initializable {

    private static final String TAG = "InterfaceController";

    @FXML
    private TreeView viewDirectories;
    @FXML
    private MenuItem itemOpenFolder;
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuEventHandling menuEventHandling = new MenuEventHandling(viewDirectories);
        DragAndDrop dragAndDrop = new DragAndDrop(viewDirectories);

        itemOpenFolder.setOnAction(menuEventHandling.itemOpenFolder());
        viewDirectories.addEventHandler(MouseEvent.MOUSE_CLICKED, sidebarItemClick());

        viewDirectories.setOnDragOver(dragAndDrop.setDragOver());
        viewDirectories.setOnDragDropped(dragAndDrop.setDragDropped());
        viewDirectories.setOnDragExited(dragAndDrop.setDragExited());

        viewDirectories.setRoot(new SidebarContent(new File("C:/Users/Sebii/Documents")));
    }

    /**
     * Function while clicking in the TreeView item
     *
     * @return
     */
    public EventHandler<MouseEvent> sidebarItemClick() {
        return event -> {
            Node node = event.getPickResult().getIntersectedNode();
            // Accept clicks only on node cells, and not on empty spaces of the TreeView
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                try {
                    //Name of clicked item
                    String name = (String) ((TreeItem) viewDirectories.getSelectionModel().getSelectedItem()).getValue();

                    //Getting clicked path from the main tree
                    StringBuilder pathBuilder = new StringBuilder();
                    for (TreeItem<String> item = (TreeItem<String>) viewDirectories.getSelectionModel().getSelectedItem();
                         item != null; item = item.getParent()) {

                        pathBuilder.insert(0, item.getValue());
                        pathBuilder.insert(0, "/");
                    }

                    //Getting full clicked path
                    String absolutePath = StringManipulation.getAbsolutePath(Config.absolutePath);
                    String formattedPath = StringManipulation.getFormattedPath(absolutePath);
                    String clickedPath = formattedPath + pathBuilder.toString();

                    System.out.println(TAG + ", sidebarItemClick: Clicked path: " + clickedPath);

                    if (!StringManipulation.getExtension(clickedPath).equals("")){
                        CreateTab createTab = new CreateTab(tabPane);
                        createTab.createTab(name, clickedPath);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
