package com.company;

import java.io.File;

/**
 * Created by Tom on 06.06.2017.
 */
public class CurrentDir extends Root {
//    private String path;

    CurrentDir() {
        fillFuncList();

        setPath("C://");
    }
    CurrentDir(String path) {
        File dir = new File(path);

        fillFuncList();

        if (dir.isDirectory()) {
            setPath(path);
        } else if(dir.isFile()) {
            setPath(dir.getParent());
        } else {
            setPath("C://");
        }
    }

    private void fillFuncList() {
        getFuncList().clear();
        getFuncList().add("Change folder (enter new path)");
        getFuncList().add("Rename folder (enter new name)");
        getFuncList().add("Create subfolder (enter path)");
        getFuncList().add("Go to root");
        getFuncList().add("Go to parent folder");
        getFuncList().add("Delete folder");
    }

    public boolean funcHandler(String funcName, String arg) {
        boolean result = false;
        switch (funcName) {
            case "Change folder (enter new path)":
                result = setPath(arg);
                break;
            case "Rename folder (enter new name)":
                result = rename(arg);
                break;
            case "Create subfolder (enter path)":
                result = createSubfolder(arg);
                break;
            case "Go to root":
                result = setPath(arg);
                break;
            case "Go to parent folder":
                result = goToParent();
                break;
            case "Delete folder":
                result = deleteFolder();
                break;
            default:
                System.out.println("Repeat input");
                break;
        }
        return result;
    }

}
