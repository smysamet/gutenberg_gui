package com.smy.gutenberggui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.smy.gutenberggui.model.User;
import com.smy.gutenberggui.view.MainFrame;

public class GutenBergGUI {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        User user = new User("");
        MainFrame mf = new MainFrame(new User("sad"));
        mf.setVisible(true);
    }
}
