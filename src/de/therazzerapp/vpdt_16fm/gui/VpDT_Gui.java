package de.therazzerapp.vpdt_16fm.gui;

import de.therazzerapp.vpdt_16fm.CopyToClipboard;
import de.therazzerapp.vpdt_16fm.FileReader;
import de.therazzerapp.vpdt_16fm.VpDT_16FM;
import de.therazzerapp.vpdt_16fm.filefilter.TXTFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.2
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
    private JScrollPane inputScrollPane;
    private JPopupMenu popup = new JPopupMenu("Popup");
    private JMenuItem ctcb = new JMenuItem("Copy To Clipboard");
    private JMenuItem clear = new JMenuItem("Clear");
    private JFileChooser openDialog = new JFileChooser();

    public JTextArea getConsoleOutputArea() {
        return consoleOutputArea;
    }

    /**
     *
     * @param frame
     */
    public VpDT_Gui(JFrame frame) {

        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        addMenu(frame);

        ctcb.addActionListener(e -> CopyToClipboard.copyTextToClipboard(inputArea.getText()));
        popup.add(ctcb);

        clear.addActionListener(e -> inputArea.setText(""));
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
                if (e.getButton() == MouseEvent.BUTTON3){
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    /**
     *
     * @param frame
     */
    private void addMenu(JFrame frame){
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

        TXTFileFilter txtFileFilter = new TXTFileFilter();
        openDialog.setDialogTitle("Coose a TXT");
        openDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        openDialog.addChoosableFileFilter(txtFileFilter);
        openDialog.setFileFilter(txtFileFilter);
        openDialog.setAcceptAllFileFilterUsed(false);

        open.addActionListener(e -> {
            openDialog.showOpenDialog(frame);
            inputArea.setText(FileReader.getFileContent(openDialog.getSelectedFile()));
            consoleOutputArea.append("\n[Info] The following file was read: " + "\"" + openDialog.getSelectedFile().getAbsolutePath() +"\" (" + inputArea.getText().length() + " character)");
        });

        exit.addActionListener(e -> System.exit(0));

        JMenuItem settings = new JMenuItem("Settings");
        options.add(new JSeparator());
        options.add(settings);

        JMenuItem about = new JMenuItem("About");
        help.add(new JSeparator());
        help.add(about);

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VpDT_16FM.aboutFrame.setVisible(true);
            }
        });
    }

    /**
     *
     * @return
     */
    public JPanel getjPanel() {
        return jPanel;
    }
}
