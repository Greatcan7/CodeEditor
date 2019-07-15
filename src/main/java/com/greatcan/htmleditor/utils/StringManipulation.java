package com.greatcan.htmleditor.utils;

/**
 * Manipulation with strings
 */
public class StringManipulation {

    /**
     * Getting absolute path in another format
     *
     * @param path
     * @return
     */
    public static String getAbsolutePath(String path) {
        return path.replace("\\", "/");
    }

    /**
     * Getting path without main folder
     * Example: C:/User/Folder/Programmer, FormattedPath: C:/User/Folder
     *
     * @param absolutePath
     * @return
     */
    public static String getFormattedPath(String absolutePath) {
        String path = absolutePath;
        String[] parts = path.split("/");
        String lastWords = parts[parts.length - 1];
        return path.replace("/" + lastWords, "");
    }

    /**
     * Find file extension
     * @param path
     * @return
     */
    public static String getExtension(String path){
        String extension = "";
        for (String item : Config.all_extension) {
            if (path.endsWith(item)){
                System.out.println("End with: " + item);
                extension = item;
            }
        }
        return extension;
    }
}
