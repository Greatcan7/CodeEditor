package com.greatcan.htmleditor.tabmenu;

import com.greatcan.htmleditor.sidebar.OpenFolder;
import com.greatcan.htmleditor.utils.Config;
import com.greatcan.htmleditor.utils.StringManipulation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Handling of menu items in TabMenu
 */
public class MenuEventHandling {

    private OpenFolder openFolder;

    public MenuEventHandling(TreeView viewDirectories) {
        openFolder = new OpenFolder(viewDirectories);
    }

    /**
     * Function: OpenFolder
     *
     * @return
     */
    public EventHandler<ActionEvent> itemOpenFolder() {
        return event -> {
            openFolder.showDirectoryChooser();
        };
    }

}
