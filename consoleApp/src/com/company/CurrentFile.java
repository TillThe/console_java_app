package com.company;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Tom on 06.06.2017.
 */
public class CurrentFile {
    private String path;
    private ArrayList<String> funcList = new ArrayList<String>();

    CurrentFile() {
        fillFuncList();

        setPath("");
    }
    CurrentFile(String path) {
        fillFuncList();

        File file = new File(path);

        if (file.isFile()) {
            this.path = path;
        } else {
            this.path = "";
        }
    }
    public boolean setPath(String path) {
        File file = new File(path);

        if (file.isFile()) {
            this.path = path;
        } else {
            this.path = "";
        }

        return true;
    }
    public String getPath() {
        return path;
    }

    public ArrayList<String> getFuncList() {
        return funcList;
    }

    private void fillFuncList() {
        getFuncList().clear();
        getFuncList().add("Rename file (enter new name)");
        getFuncList().add("Delete file");
    }

    public boolean funcHandler(String funcName, String arg) {
        boolean result = false;
        switch (funcName) {
            case "Rename file (enter new name)":
                result = rename(arg);
                break;
            case "Delete file":
                result = deleteFile();
                break;
            default:
                System.out.println("Repeat input");
                break;
        }
        return result;
    }

    public String seeParent() {
        File dir = new File(path);
        return dir.getParent();
    }
    public String seeFullName(String name, String abbr) {
        return "\n" + name + " (" + abbr + "): '" + getPath() + "'";
    }
    public String seeFuncList() {
        String result = "";

        for (int i = 0; i < funcList.size(); i++) {
            result += "\n    " + i + ". " + funcList.get(i);
        }

//        for (Map.Entry<String, String> e : funcList.entrySet())
//        {
//            System.out.println(e.getValue());
//        }
        return result;
    }

    public boolean deleteFile() {
        File file = new File(path);
        path = "";
        return file.delete();
    }

    public boolean rename(String name) {
        if (seeParent() == null)  return false;

        File file = new File(path);
        File newFile = new File(seeParent() + "\\" + name);

        if (file.renameTo(newFile)) {
            path = seeParent() + "\\" + name;
            return true;
        } else return false;
    }

}
