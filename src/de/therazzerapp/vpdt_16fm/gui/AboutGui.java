package de.therazzerapp.vpdt_16fm.gui;

import de.therazzerapp.vpdt_16fm.VpDT_16FM;

import javax.swing.*;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.2
 */
public class AboutGui {
    private JPanel aboutPanel;
    private JLabel aboutHeadline;
    private JTextPane creditsTextPane;
    private JLabel buildLable;

    public AboutGui(JFrame frame) {
        buildLable.setText("Version: " + VpDT_16FM.buildNumber);
        creditsTextPane.setContentType("text/html");
        creditsTextPane.setText("Verschlüsselung durch polyalphabetische Division und Transposition von 16 Felder-Matrizen"
            + "<br><br>Copyright by Paul Eduard Koenig (aka. The RaZZeR App)"
            + "<br><br>View code on GitHub:<br><a href=\"https://github.com/TheRaZZeRApp/VpDT_16FM\">https://github.com/TheRaZZeRApp/VpDT_16FM</a>"
            + "<br><br>Visit my website:<br><a href=\"http://therazzerapp.de/\">http://therazzerapp.de/</a>");

    }

    public JPanel getAboutPanel() {
        return aboutPanel;
    }
}
