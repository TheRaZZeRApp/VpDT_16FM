package de.therazzerapp.vpdt_16fm;

import de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision.PolyalphabeticDivision;

/**
 * Main class
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class VpDT_16FM {
    public static void main(String[] args) {

        //test
        String test = "a";
        String encoded = PolyalphabeticDivision.encode(test, "b");
        String decoded = PolyalphabeticDivision.decipher(encoded, "b");
        System.out.println("1:\t" + encoded + "\n2:\t" + decoded);
    }
}
