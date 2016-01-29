package de.therazzerapp.vpdt_16fm;

import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;
import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PolyalphabeticDivision;

/**
 * Main class
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class VpDT_16FM {

    public static void main(String[] args) {

        String sentence = "Das ist ein kleiner Test";
        String password = CUtils.generatePassword(3);

        String code = PolyalphabeticDivision.compile(sentence,password);
        System.out.println("Plaintext:" + sentence);
        System.out.println("Ciphertext: " + code);
        System.out.println("Decipherd text: " +PolyalphabeticDivision.decompile(code,password));
        System.out.println("Password:" + password);

    }

}
