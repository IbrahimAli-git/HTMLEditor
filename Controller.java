package com.codegym.task.task32.task3209;

import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void init(){

    }

    public void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {

        View v = new View();
        Controller c = new Controller(v);


        v.setController(c);
        v.init();
        c.init();
    }

}

