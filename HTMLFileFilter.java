package com.codegym.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {


    public HTMLFileFilter() {
    }

    @Override
    public boolean accept(File f) {
        String html = ".html";
        String htm = ".htm";
        String fileName = f.getName().substring(f.getName().lastIndexOf("."));

        if (f.isDirectory()){
            return true;
        } else if(fileName.equalsIgnoreCase(html) || fileName.equalsIgnoreCase(htm)){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML and HTM files";
    }
}
