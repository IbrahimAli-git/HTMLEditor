package com.codegym.task.task32.task3209.action;

import com.codegym.task.task32.task3209.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractAction {
    private View view;

    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public boolean accept(Object sender) {
        return false;
    }
}
