package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Root defaultDir = new Root("C:\\Users\\Tom\\Documents");
        CurrentDir currentDir = new CurrentDir("C:\\Users\\Tom\\Documents");
        CurrentFile currentFile = new CurrentFile();

//        System.out.println(currentDir.getPath() + "\n" + currentDir.seeParent() + "\n" + currentDir.seeName() + currentDir.seeFuncList() + "\n" + currentDir.dirAndFileList());

        ArrayList<String> dirList = new ArrayList<String>();
        ArrayList<String> fileList = new ArrayList<String>();

        File[] FileList;

        Boolean flag = true;
//        String help = "For example to Rename current folder u should write command 'c1 newFolderName'";
        System.out.println("\nWellcome to console");
        final String rAbbr = "r";
        final String dAbbr = "d";
        final String fAbbr = "f";
        final String qAbbr = "q";

        while (flag == true) {
            System.out.println(defaultDir.seeFullName("Root", rAbbr)
                    + defaultDir.seeFuncList()
                    + currentDir.seeFullName("current Dir", dAbbr)
                    + currentDir.seeFuncList()
                    + currentDir.dirAndFileList()
                    + currentFile.seeFullName("current File", fAbbr)
                    + currentFile.seeFuncList()
                    + "\nQuit programm (" + qAbbr + ")");
            System.out.println("Enter command: ");
            String line = in.nextLine().trim();
            String[] parts = line.split("[\\s]+", 3);
//            System.out.println("line: " + line + "\nparts: " + parts[0]);
            String arg;
            switch (parts[0].toLowerCase()) {
                case rAbbr:
                    try {
                        arg = parts[2];
                    } catch (RuntimeException e) {
                        arg = "";
                    }
                    try {
                        defaultDir.funcHandler(defaultDir.getFuncList().get(Integer.parseInt(parts[1])), arg);
                    } catch (RuntimeException e) {
                        System.out.println("Wrong input. Repeat");
                    }
                    break;
                case dAbbr:
                    try {
                        arg = parts[2];
                    } catch (RuntimeException e) {
                        arg = "";
                    }
                    try {
                        arg = (Integer.parseInt(parts[1]) == 3) ? defaultDir.getPath() : arg;
                        System.out.println(currentDir.getFuncList().get(Integer.parseInt(parts[1])));
                        currentDir.funcHandler(currentDir.getFuncList().get(Integer.parseInt(parts[1])), arg);
                        defaultDir.checkPath();
                    } catch (RuntimeException e) {
                        System.out.println("Wrong input. Repeat");
                    }
                    break;
                case fAbbr:
                    try {
                        arg = parts[2];
                    } catch (RuntimeException e) {
                        arg = "";
                    }
                    try {
                        currentFile.funcHandler(currentFile.getFuncList().get(Integer.parseInt(parts[1])), arg);
                    } catch (RuntimeException e) {
                        System.out.println("Wrong input. Repeat");
                    }
                    break;
                case "ds":
                    try {
                        currentDir.setPath(currentDir.takeDirList().get(Integer.parseInt(parts[1])).getPath());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Not full input. Repeat");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Wrong input. Repeat");
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input. Repeat");
                    }
                    break;
                case "df":
                    System.out.println("check");
                    try {
                        System.out.println(currentDir.takeFileList().get(Integer.parseInt(parts[1])).getPath());
                        currentFile.setPath(currentDir.takeFileList().get(Integer.parseInt(parts[1])).getPath());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Not full input. Repeat");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Wrong input. Repeat");
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input. Repeat");
                    }
                    break;
                case qAbbr:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input. Repeat");
            }
        }

    }

}
