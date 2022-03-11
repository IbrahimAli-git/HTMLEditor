package com.codegym.task.task32.task3209;

import com.codegym.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init() {
        createNewDocument();
    }

    public void resetDocument(){
        if (document != null){
            document.removeUndoableEditListener(view.getUndoListener());
        }

        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.read(stringReader, document, 0);
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.write(stringWriter, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        view.setController(controller);
        view.init();
        controller.init();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML editor");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int ret = fileChooser.showOpenDialog(view);
        if (ret == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            view.resetUndo();
            try {
                FileReader reader = new FileReader(currentFile);
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.read(reader, document, 0);
                reader.close();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());

        int ret = fileChooser.showSaveDialog(view);

        if (ret == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter writer = new FileWriter(currentFile);
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.write(writer, document, 0, document.getLength());
                writer.close();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null){
            saveDocumentAs();
        } else {
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                HTMLEditorKit kit = new HTMLEditorKit();
                kit.write(fileWriter, document, 0, document.getLength());
                fileWriter.close();
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }
}
