package com.company;

import com.sun.xml.internal.ws.util.QNameMap;

import java.io.File;
import java.util.*;

/**
 * Created by Tom on 06.06.2017.
 */
public class Root {
    private String path;
    private ArrayList<String> funcList = new ArrayList<String>();
//    Map funcList = new HashMap<String, String>();

    Root() {
        fillFuncList();

        path = "C://";
    }
    Root(String path) {
        File dir = new File(path);

        fillFuncList();

        if (dir.isDirectory()) {
            this.path = path;
        } else if(dir.isFile()) {
            this.path = dir.getParent();
        } else {
            this.path = "C://";
        }
    }

    public ArrayList<String> getFuncList() {
        return funcList;
    }
    private void fillFuncList() {
        funcList.add("Change root (enter new path)");
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

    public boolean funcHandler(String funcName, String arg) {
        boolean result = false;
        switch (funcName) {
            case "Change root (enter new path)":
                result = setPath(arg);
                break;
            default:
                System.out.println("Repeat input");
                break;
        }
        return result;
    }

    public String seeFullName(String name, String abbr) {
        return "\n" + name + " (" + abbr + "): '" + getPath() + "'";
    }

    public boolean setPath(String path) {
        File dir = new File(path);

        if (dir.isDirectory()) {
            this.path = path;
        } else if(dir.isFile()) {
            this.path = dir.getParent();
        } else {
            return false;
        }
        return true;
    }

    public String getPath() {
        return path;
    }

    public String checkPath() {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            path = seeParent();
            File dir1 = new File(path);
            if (!dir1.isDirectory()) {
                path = "C://";
            }
        }
        return path;
    }

    public String seeName() {
        File dir = new File(path);
        return dir.getName();
    }

    public String seeParent() {
        File dir = new File(path);
        return dir.getParent();
    }

    public boolean goToParent() {
        String oldPath = path;
        path = seeParent();
        return oldPath.compareTo(path) != 0;
    }

    public boolean createSubfolder(String name) {
        File dir = new File(path + "/" + name);
        return dir.mkdirs();
    }

    public boolean deleteFolder() {
        File dir = new File(path);
        path = seeParent();
        return dir.delete();
    }

    public boolean rename(String name) {
        if (seeParent() == null)  return false;

        File dir = new File(path);
        File newDir = new File(seeParent() + "\\" + name);

        if (dir.renameTo(newDir)) {
            path = seeParent() + "\\" + name;
            return true;
        } else return false;
    }

    public ArrayList<File> takeDirList() {
        ArrayList<File> dirList = new ArrayList<File>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()){
                if (item.isDirectory()) {
                    dirList.add(item);
                }
            }
        }
        return dirList;
    }

    public ArrayList<File> takeFileList() {
        ArrayList<File> dirList = new ArrayList<File>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()){
                if (!item.isDirectory()) {
                    dirList.add(item);
                }
            }
        }
        return dirList;
    }

    public String dirAndFileList() {
        String result = "\n  Subfolders (-s-):";
        for (int i = 0; i < takeDirList().size(); i++) {
            result += "\n    " + i + ". " + takeDirList().get(i).getName();
        }
        result += "\n  Files (-f-):";
        for (int i = 0; i < takeFileList().size(); i++) {
            result += "\n    " + i + ". " + takeFileList().get(i).getName();
        }
        return result;
    }

}
