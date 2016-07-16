package de.therazzerapp.vpdt_16fm.gui.ui;

import de.therazzerapp.vpdt_16fm.gui.CopyToClipboard;
import de.therazzerapp.vpdt_16fm.VpDT_16FM;
import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;
import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PDConverter;
import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PDSettings;
import de.therazzerapp.vpdt_16fm.gui.filefilter.TXTFileFilter;
import de.therazzerapp.vpdt_16fm.gui.ConsoleCommander;
import de.therazzerapp.vpdt_16fm.gui.FileReader;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

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
    private JTextPane consoleOutputArea;
    private JPanel textSettingsPanel;
    private JButton runButton;
    private JButton encodeButton;
    private JButton decodeButton;
    private JScrollPane inputScrollPane;
    private JPanel chiperSettings;
    private JCheckBox polyalphabeticDivisionCheckBox;
    private JPanel polydivSettings;
    private JCheckBox transpositionCheckBox;
    private JPanel transposSettings;
    private JSpinner pdR1P;
    private JSpinner pdR1M;
    private JSpinner pdR2P;
    private JSpinner pdR2M;
    private JSpinner pdR3M;
    private JSpinner pdR3P;
    private JButton pdRollerRandom;
    private JButton pdRollerReset;
    private JTextField polyDivPassword;
    private JButton pdPasswordRandomButton;
    private JSpinner pdPasswordRandomSpinner;
    private JCheckBox enhancementsSymbolBox;
    private JTextField enhancementsSymbolField;
    private JLabel enchancementSymbolErrorLabel;
    private JLabel modeLabel;
    private JScrollPane consoleScrollpane;
    private JPopupMenu popup = new JPopupMenu("Popup");
    private JMenuItem ctcb = new JMenuItem("Copy To Clipboard");
    private JMenuItem clear = new JMenuItem("Clear");
    private JFileChooser openDialog = new JFileChooser();
    private boolean encode;

    public JTextPane getConsoleOutputArea() {
        return consoleOutputArea;
    }

    /**
     *
     * @param frame
     */
    public VpDT_Gui(JFrame frame) {
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        consoleOutputArea.setContentType("text/html");

        consoleScrollpane.getViewport().setAutoscrolls(true);

        DefaultCaret caret = (DefaultCaret)consoleOutputArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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

        polyalphabeticDivisionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (polyalphabeticDivisionCheckBox.isSelected()){
                    polydivSettings.setVisible(true);
                } else {
                    polydivSettings.setVisible(false);
                }
            }
        });

        transpositionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (transpositionCheckBox.isSelected()){
                    transposSettings.setVisible(true);
                } else {
                    transposSettings.setVisible(false);
                }
            }
        });

        addMenu(frame);
        addPDUI(frame);

        polydivSettings.setVisible(false);
        transposSettings.setVisible(false);

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polyalphabeticDivisionCheckBox.setEnabled(true);
                transpositionCheckBox.setEnabled(true);
                pdPasswordRandomButton.setEnabled(true);
                pdPasswordRandomSpinner.setEnabled(true);
                pdRollerRandom.setEnabled(true);
                runButton.setEnabled(true);
                encode = true;
                modeLabel.setText("Mode: Encode");
                enhancementsSymbolField.setEnabled(true);
                enhancementsSymbolBox.setEnabled(true);
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polyalphabeticDivisionCheckBox.setEnabled(true);
                transpositionCheckBox.setEnabled(true);
                pdPasswordRandomButton.setEnabled(false);
                pdPasswordRandomSpinner.setEnabled(false);
                pdRollerRandom.setEnabled(false);
                runButton.setEnabled(true);
                encode = false;
                modeLabel.setText("Mode: Decode");
                enhancementsSymbolField.setEnabled(false);
                enhancementsSymbolBox.setEnabled(false);
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!polyalphabeticDivisionCheckBox.isSelected() && !transpositionCheckBox.isSelected()){
                    ConsoleCommander.sendError(consoleOutputArea,"There is no method selected.");
                    return;
                }

                if (polyalphabeticDivisionCheckBox.isSelected() && polyDivPassword.getText().isEmpty()){
                    ConsoleCommander.sendError(consoleOutputArea,"There is password set.");
                    return;
                }

                if (inputArea.getText().isEmpty()){
                    ConsoleCommander.sendError(consoleOutputArea,"There is nothing to encrypt yet.");
                    return;
                }

                if(polyalphabeticDivisionCheckBox.isSelected() && !transpositionCheckBox.isSelected()){
                    int textLength = inputArea.getText().length();
                    Character eS = null;
                    if(!enhancementsSymbolField.getText().isEmpty()){
                        eS = enhancementsSymbolField.getText().charAt(0);
                    }
                    PDSettings pdSettings = new PDSettings((int)pdR1P.getValue(),(int)pdR2P.getValue(),(int)pdR3P.getValue(),(int)pdR1M.getValue(),(int)pdR2M.getValue(),(int)pdR3M.getValue(),eS);
                    if(encode){
                        inputArea.setText(PDConverter.compile(pdSettings,inputArea.getText(),polyDivPassword.getText()));
                        ConsoleCommander.sendInfo(consoleOutputArea,"Text successfully encrypted (Chars: " + textLength + " before / " + inputArea.getText().length() + " after).");
                    } else {
                        inputArea.setText(PDConverter.decompile(pdSettings,inputArea.getText(),polyDivPassword.getText()));
                        ConsoleCommander.sendInfo(consoleOutputArea,"Text successfully decrypted (Chars: " + textLength + " before / " + inputArea.getText().length() + " after).");
                    }

                }




            }
        });


    }

    /**
     * Adds all polyDiv Objects
     * @param frame
     */
    private void addPDUI(JFrame frame){
        SpinnerModel pdPasswordSpinnerModel = new SpinnerNumberModel(10,1,100,1);
        pdPasswordRandomSpinner.setModel(pdPasswordSpinnerModel);

        pdR1P.setModel(new SpinnerNumberModel(3,1,null,1));
        pdR2P.setModel(new SpinnerNumberModel(3,1,null,1));
        pdR3P.setModel(new SpinnerNumberModel(3,1,null,1));
        pdR1M.setModel(new SpinnerNumberModel(3,1,null,1));
        pdR2M.setModel(new SpinnerNumberModel(3,1,null,1));
        pdR3M.setModel(new SpinnerNumberModel(3,1,null,1));

        pdPasswordRandomButton.addActionListener(e -> polyDivPassword.setText(CUtils.generatePassword((int)pdPasswordRandomSpinner.getValue())));

        pdRollerReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdR1P.setValue(3);
                pdR2P.setValue(3);
                pdR3P.setValue(3);
                pdR1M.setValue(3);
                pdR2M.setValue(3);
                pdR3M.setValue(3);
            }
        });

        pdRollerRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                pdR1P.setValue(random.nextInt(CUtils.charSet.length)+2);
                pdR2P.setValue(random.nextInt(CUtils.charSet.length)+2);
                pdR3P.setValue(random.nextInt(CUtils.charSet.length)+2);
                pdR1M.setValue(random.nextInt(CUtils.charSet.length)+2);
                pdR2M.setValue(random.nextInt(CUtils.charSet.length)+2);
                pdR3M.setValue(random.nextInt(CUtils.charSet.length)+2);
            }
        });

        enhancementsSymbolBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enhancementsSymbolBox.isSelected()){
                    enhancementsSymbolField.setEnabled(true);
                } else {
                    enhancementsSymbolField.setEnabled(false);
                }
            }
        });
    }

    /**
     * Adds all menu Objects
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
            ConsoleCommander.sendInfo(consoleOutputArea,"\nFile loaded: <u color=ORANGE>" + "\"" + openDialog.getSelectedFile().getAbsolutePath() +"</u>\" (" + inputArea.getText().length() + " chars).");
        });

        exit.addActionListener(e -> System.exit(0));

        JCheckBoxMenuItem resiszable = new JCheckBoxMenuItem("Resizable");
        JMenuItem settings = new JMenuItem("Settings");
        options.add(resiszable);
        options.add(new JSeparator());
        options.add(settings);

        resiszable.addActionListener(e -> {
            if(resiszable.isSelected()){
                frame.setResizable(true);
            } else {
                frame.setResizable(false);
            }
        });



        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VpDT_16FM.settingsFrame.setVisible(true);
            }
        });

        JMenuItem about = new JMenuItem("About");
        help.add(new JSeparator());
        help.add(about);

        about.addActionListener(e -> VpDT_16FM.aboutFrame.setVisible(true));
    }

    /**
     *
     * @return
     */
    public JPanel getjPanel() {
        return jPanel;
    }

}
