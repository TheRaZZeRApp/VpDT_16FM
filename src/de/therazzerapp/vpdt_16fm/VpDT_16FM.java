package de.therazzerapp.vpdt_16fm;

import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PDSettings;
import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;
import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PDConverter;

/**
 * Main class
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class VpDT_16FM {

    public static void main(String[] args) {

        PDSettings pdSettings = new PDSettings(2,3,4,5,6,7);
        String sentence = "Das ist ein kleiner Test";
        String password = CUtils.generatePassword(3);

        String code = PDConverter.compile(pdSettings,sentence,password);
        System.out.println("Plaintext: " + sentence);
        System.out.println("Ciphertext: " + code);
        System.out.println("Decipherd text: " + PDConverter.decompile(pdSettings,code,password));
        System.out.println("Password: " + password);

        //VpDT_Gui.createFrame();

    }

}
