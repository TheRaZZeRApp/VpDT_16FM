package de.therazzerapp.vpdt_16fm.gui;

import de.therazzerapp.vpdt_16fm.CopyToClipboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since <version>
 */
public class VpDT_Gui {
    private JPanel jPanel;
    private JPanel settingsPanel;
    private JPanel inputPanel;
    private JPanel consoleOutputPanel;
    private JTextArea inputArea;
    private JTextArea consoleOutputArea;
    private JPanel textSettingsPanel;
    private JButton runButton;
    private JButton encodeButton;
    private JButton decodeButton;
    private JPopupMenu popup = new JPopupMenu("Popup");
    private JMenuItem ctcb = new JMenuItem("Copy To Clipboard");
    private JMenuItem clear = new JMenuItem("Clear");

    public static void createFrame(){
        JFrame frame = new JFrame("VpDT_Gui");
        frame.setContentPane(new VpDT_Gui().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,600);
        frame.setTitle("VpDT_16FM b0.0.2");
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(900,300));

        addMenu(frame);
    }

    private static void addMenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        JMenu options = new JMenu("Options");
        JMenu help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(options);
        menuBar.add(help);

        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(open);
        file.add(new JSeparator());
        file.add(save);
        file.add(saveAs);
        file.add(new JSeparator());
        file.add(exit);

        JMenuItem settings = new JMenuItem("Settings");
        options.add(new JSeparator());
        options.add(settings);

        JMenuItem about = new JMenuItem("About");
        help.add(new JSeparator());
        help.add(about);
    }

    public VpDT_Gui() {

        ctcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CopyToClipboard.copyTextToClipboard(inputArea.getText());
            }
        });
        popup.add(ctcb);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputArea.setText("");
            }
        });
        popup.add(clear);

        inputArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
}
