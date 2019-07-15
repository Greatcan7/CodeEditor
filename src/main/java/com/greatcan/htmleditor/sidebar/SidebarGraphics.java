package com.greatcan.htmleditor.sidebar;

import com.greatcan.htmleditor.utils.StringManipulation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Responsible for setting graphics for the TreeView item
 */
public class SidebarGraphics {

    /**
     * Return the graphics om accordance with the extension
     * @param value
     * @return
     * @throws Exception
     */
    public Node getGraphics(String value) throws Exception{
        Node node;
        String extension = StringManipulation.getExtension(value);
        switch (extension){
            case ".txt":
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/txt_file.png")));
                break;
            case ".pdf":
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/pdf_file.png")));
                break;
            case ".html":
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/html_file.png")));
                break;
            case ".css":
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/css_file.png")));
                break;
            case ".js":
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/js_file.png")));
                break;
            case ".jpg":
            case ".svg":
            case ".png":
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/image.png")));
                break;
            default:
                node = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("img/sidebar/file.png")));
                break;
        }
        return node;
    }
}
