package de.therazzerapp.vpdt_16fm;

import de.therazzerapp.vpdt_16fm.gui.ui.AboutGui;
import de.therazzerapp.vpdt_16fm.gui.ConsoleCommander;
import de.therazzerapp.vpdt_16fm.gui.ui.SettingsGui;
import de.therazzerapp.vpdt_16fm.gui.ui.VpDT_Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Main class
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class VpDT_16FM {

    public static String buildNumber = "b0.0.2";
    public static JFrame frame = new JFrame("VpDT_Gui");
    public static JFrame aboutFrame = new JFrame("About");
    public static JFrame settingsFrame = new JFrame("Settings");
    public static SettingsGui settingsGui = new SettingsGui(settingsFrame);
    public static AboutGui aboutGui = new AboutGui(aboutFrame);
    public static VpDT_Gui vpDT_gui = new VpDT_Gui(frame);

    public static void main(String[] args) {

        frame.setContentPane(vpDT_gui.getjPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,600);
        frame.setTitle("VpDT_16FM " + buildNumber);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(900,550));
        frame.setResizable(false);

        aboutFrame.setContentPane(aboutGui.getAboutPanel());
        aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        aboutFrame.pack();
        aboutFrame.setVisible(false);
        aboutFrame.setSize(400,300);
        aboutFrame.setTitle("About VpDT_16FM " + buildNumber);
        aboutFrame.setLocationRelativeTo(null);
        aboutFrame.setMinimumSize(new Dimension(400, 300));
        aboutFrame.setResizable(false);

        settingsFrame.setContentPane(settingsGui.getSettingsPanel());
        settingsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        settingsFrame.pack();
        settingsFrame.setVisible(false);
        settingsFrame.setSize(600,300);
        settingsFrame.setTitle("Settings");
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setMinimumSize(new Dimension(600, 300));
        settingsFrame.setResizable(false);

        ConsoleCommander.sendInfo(vpDT_gui.getConsoleOutputArea(),"VpDT_16FM " +buildNumber+" started successfully.");
    }

}
