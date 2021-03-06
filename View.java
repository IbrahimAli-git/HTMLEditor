package com.codegym.task.task32.task3209;


import com.codegym.task.task32.task3209.listeners.FrameListener;
import com.codegym.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.codegym.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public View(){
        try {
            UIManager.setLookAndFeel("View");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }


    public void showAbout(){
        JOptionPane.showMessageDialog(this, "about", "info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0){
            return true;
        } else {
            return false;
        }
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void undo(){
        try {
            undoManager.undo();
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void redo(){
        try {
            undoManager.redo();
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo(){
        return undoManager.canRedo();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "New" : controller.createNewDocument();
                break;
            case "Open" : controller.openDocument();
                break;
            case "Save" : controller.saveDocumentAs();
                break;
            case "Save as..." : controller.saveDocumentAs();
                break;
            case "Exit" : controller.exit();
                break;
            case "About" : showAbout();
                break;
        }
    }

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", htmlScrollPane);

        JScrollPane plainScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Text", plainScrollPane);

        tabbedPane.setPreferredSize(new Dimension(300,300));

        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
            int index = tabbedPane.getSelectedIndex();

            switch (index){
                case 0 : controller.setPlainText(plainTextPane.getText());
                break;
                case 1 : plainTextPane.setText(controller.getPlainText());
                break;
            }
        
        resetUndo();
    }
}