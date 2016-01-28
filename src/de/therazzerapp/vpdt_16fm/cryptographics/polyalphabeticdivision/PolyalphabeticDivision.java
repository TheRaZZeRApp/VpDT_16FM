package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;


import de.therazzerapp.vpdt_16fm.cryptographics.CSettings;
import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;

/**
 * Main poly division class.
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class PolyalphabeticDivision {

    private static RollerNormal r1;
    private static RollerNormal r2;
    private static RollerFinal r3;

    private static String[] splittetDividend;
    private static String password;
    private static String sentence;

    /**
     * Encrypts the specified text with a password
     *
     * @param divisor
     *          The password
     * @param dividend
     *          The raw sentence
     * @return
     *      The encrypted text
     */
    public static String compile(String divisor, String dividend) {

        r1 = new RollerNormal(CSettings.r1Position, CSettings.r1Multiplier);
        r2 = new RollerNormal(CSettings.r2Position, CSettings.r2Multiplier);
        r3 = new RollerFinal(CSettings.r3Position, CSettings.r3Multiplier);

        password = CUtils.clearPlaintext(divisor);
        sentence = dividend;

        splittetDividend = CUtils.splitByNumber(divisor,1);

        String text = "";

        for(int x = 0; x != prepareDividend(sentence).length; x++){
            text += (convertText(prepareDividend(sentence)[x],false));
        }
        return text;
    }

    /**
     * Decrypts the specified text with a password
     *
     * @param divisor
     *          The password
     * @param dividend
     *          The raw sentence
     * @return
     *      The decrypted text
     */
    public static String decompile(String divisor, String dividend) {

        r1 = new RollerNormal(CSettings.r1Position, CSettings.r1Multiplier);
        r2 = new RollerNormal(CSettings.r2Position, CSettings.r2Multiplier);
        r3 = new RollerFinal(CSettings.r3Position, CSettings.r3Multiplier);

        password = CUtils.clearPlaintext(divisor);
        sentence = dividend;

        splittetDividend = CUtils.splitByNumber(divisor,1);

        String text = "";

        for(int x = 0; x != prepareDividend(sentence).length; x++){
            text += (convertText(prepareDividend(sentence)[x],true));
            splittetDividend = CUtils.splitByNumber(prepareDividend(sentence)[x],1);
        }
        return text;
    }

    /**
     * Adds a x to the dividend until both are even.
     * Removes every character not contained in the global char set.
     * @param rawDividend
     *          The raw dividend
     * @return
     *      The fixed dividend
     */
    private static String[] prepareDividend(String rawDividend){

        String[] splittedDividend = CUtils.splitByNumber(CUtils.clearPlaintext(rawDividend), password.length());

        if (splittedDividend.length <= 1)
            return splittedDividend;

        String temp = splittedDividend[splittedDividend.length-1];

        while (temp.length() < password.length()){
            temp += "x";
        }

        splittedDividend[splittedDividend.length-1] = temp;

        return splittedDividend;
    }

    /**
     *
     * @param text
     * @param reConv
     * @return
     */
    private static String convertText(String text, boolean reConv){
        String convertetText= "";
        for(int x = 0; x != splittetDividend.length; x++){
            String[] temp = CUtils.splitByNumber(text,1);
            convertetText += getNewKey(splittetDividend[x].charAt(0),temp[x].charAt(0));
        }
        if (!reConv)
            splittetDividend = CUtils.splitByNumber(convertetText,1);
        return convertetText;
    }

    /**
     *
     * @param dividend
     * @param divisor
     * @return
     */
    private static char getNewKey(char dividend, char divisor){
        moveWalze1ToKey(dividend);
        moveWalze2ToKey(divisor);
        r1.move(1);
        return r3.getKey();
    }

    /**
     * Moves Roller 1 to the given char
     * @param key
     */
    private static void moveWalze1ToKey(char key){
        r1.moveToKey(key);
        r2.move(r1.getMoves());
        r3.move(r1.getMoves());
    }

    /**
     * Moves Roller 2 to the given char
     * @param key
     */
    private static void moveWalze2ToKey(char key){
        r2.moveToKey(key);
        r3.moveFinal(r2.getMoves());
    }
}
