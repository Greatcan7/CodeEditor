package com.greatcan.htmleditor.tabmenu;

import com.greatcan.htmleditor.tabmenu.tabcontent.CustomTextArea;
import com.greatcan.htmleditor.utils.StringManipulation;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Responsible for creating a content tab during click on the selected file
 */
public class CreateTab {

    //Main tab
    private TabPane tabPane;

    public CreateTab(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    /**
     * Creating tab
     * @param tabName
     */
    public void createTab(String tabName, String clickedPath){
        try {
            //Create tab
            Tab tab = new Tab(tabName);

            //Add Node to tab
            tab.setContent(getExtensionFromPath(clickedPath));

            //Add tab to TabPane
            tabPane.getTabs().add(tab);

            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node getExtensionFromPath(String path){
        String extension = StringManipulation.getExtension(path);
        switch (extension){
            case ".txt":
            case ".html":
            case ".css":
            case ".js":
                return new CustomTextArea().getNode();
            case ".jpg":
            case ".png":
                return new ImageView();
            default:
                return new Label("No supported file");
        }
    }
}
