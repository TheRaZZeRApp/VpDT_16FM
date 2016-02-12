package de.therazzerapp.vpdt_16fm;

import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PDSettings;
import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;
import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PDConverter;
import de.therazzerapp.vpdt_16fm.gui.VpDT_Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Main class
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class VpDT_16FM {

    public static void main(String[] args) {

        /*

        PDSettings pdSettings = new PDSettings(2,3,4,5,6,7);
        String sentence = "Das ist ein kleiner Test";
        String password = CUtils.generatePassword(1);

        String code = PDConverter.compile(pdSettings,sentence,password);
        System.out.println("Plaintext: " + sentence);
        System.out.println("Ciphertext: " + code);
        System.out.println("Decipherd text: " + PDConverter.decompile(pdSettings,code,password));
        System.out.println("Password: " + password);
        System.out.println("Symbol: " + CUtils.enhancementsSymbol);

        */
        JFrame frame = new JFrame("VpDT_Gui");
        frame.setContentPane(new VpDT_Gui(frame).getjPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,600);
        frame.setTitle("VpDT_16FM b0.0.2");
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(900,300));

    }

}
