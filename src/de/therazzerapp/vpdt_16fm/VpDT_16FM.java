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

        String password = "password";
        String sentence = "Das ist ein Test!";

        String code = PolyalphabeticDivision.compile(password,sentence);
        System.out.println("Test:" + code);
        System.out.println("Test2: " + PolyalphabeticDivision.decompile(password,code));
        System.out.println("Test3:" + CUtils.generatePassword(50));

    }

}
