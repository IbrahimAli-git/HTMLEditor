package com.codegym.task.task32.task3209.action;

import com.codegym.task.task32.task3209.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RedoAction extends AbstractAction {
    private View view;


    public RedoAction(View view) {
        this.view = view;
    }

    public RedoAction(String name, View view) {
        super(name);
        this.view = view;
    }

    public RedoAction(String name, Icon icon, View view) {
        super(name, icon);
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender);
    }
}
