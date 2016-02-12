package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;

import java.util.Random;

/**
 * Main poly division class.
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class PDConverter {

    private static PDRollerNormal r1;
    private static PDRollerNormal r2;
    private static PDRollerFinal r3;

    /**
     * The dividend that has to be shift through every compile.
     * Previous line is the password for the next line.
     */
    private static String[] splittetDividend;

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
    public static String compile(PDSettings pdSettings, String dividend, String divisor) {

        //Will be replaced to add a non static amount of rollers
        r1 = new PDRollerNormal(pdSettings.getR1Position(), pdSettings.getR1Multiplicand());
        r2 = new PDRollerNormal(pdSettings.getR2Position(), pdSettings.getR2Multiplicand());
        r3 = new PDRollerFinal(pdSettings.getR3Position(), pdSettings.getR3Multiplicand());

        Random expanderAmount = new Random(10);
        int expander = expanderAmount.nextInt();

        for (int i = 0;i < expander; i++){
            dividend += CUtils.enhancementsSymbol;
        }

        String password = CUtils.clearPlaintext(divisor);

        splittetDividend = CUtils.splitByNumber(divisor,1);

        StringBuilder ciphertext = new StringBuilder();

        for(int x = 0; x != prepareDividend(dividend,password).length; x++){
            ciphertext.append(convertText(prepareDividend(dividend,password)[x],false));
        }
        return ciphertext.toString();
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
    public static String decompile(PDSettings pdSettings, String dividend, String divisor) {

        //Will be replaced to add a non static amount of rollers
        r1 = new PDRollerNormal(pdSettings.getR1Position(), pdSettings.getR1Multiplicand());
        r2 = new PDRollerNormal(pdSettings.getR2Position(), pdSettings.getR2Multiplicand());
        r3 = new PDRollerFinal(pdSettings.getR3Position(), pdSettings.getR3Multiplicand());

        String password = CUtils.clearPlaintext(divisor);

        splittetDividend = CUtils.splitByNumber(divisor,1);

        StringBuilder plaintext = new StringBuilder();
        for(int x = 0; x != prepareDividend(dividend,password).length; x++){
            plaintext.append(convertText(prepareDividend(dividend,password)[x],true));
            splittetDividend = CUtils.splitByNumber(prepareDividend(dividend,password)[x],1);
        }
        return plaintext.toString();
    }

    /**
     * Adds a x to the dividend until both are even.
     * Removes every character not contained in the global char set.
     * @param rawDividend
     *          The raw dividend
     * @return
     *      The fixed dividend
     */
    private static String[] prepareDividend(String rawDividend, String password){

        String[] splittedDividend = CUtils.splitByNumber(CUtils.clearPlaintext(rawDividend), password.length());

        if (splittedDividend.length < 1)
            return splittedDividend;

        StringBuilder fixedDividend = new StringBuilder(splittedDividend[splittedDividend.length-1]);

        while (fixedDividend.length() < password.length()){
            fixedDividend.append(CUtils.enhancementsSymbol);
        }

        splittedDividend[splittedDividend.length-1] = fixedDividend.toString();

        return splittedDividend;
    }

    /**
     * Converts the raw text line by line
     *
     * @param text
     *          The raw text
     * @param decompile
     *          - false if its a compile
     *          - true if its a decompile
     * @return
     *      The compiled text
     */
    private static String convertText(String text, boolean decompile){
        StringBuilder cipher = new StringBuilder();
        for(int x = 0; x < splittetDividend.length; x++){
            String[] temp = CUtils.splitByNumber(text,1);
            cipher.append(getNewKey(splittetDividend[x].charAt(0),temp[x].charAt(0)));
        }
        if (!decompile)
            splittetDividend = CUtils.splitByNumber(cipher.toString(),1);
        return cipher.toString();
    }

    /**
     * Divides a char by another one.
     *
     * @param dividend
     *          Raw text char
     * @param divisor
     *          Password char
     * @return
     *      Compiled char
     */
    private static char getNewKey(char dividend, char divisor){
        moveWalze1ToKey(dividend);
        moveWalze2ToKey(divisor);
        r1.move(1); //Damit aa nicht zu bb wird
        return r3.getCurrentKey();
    }

    /**
     * Moves Roller 1 to the given char
     * @param key
     *          The char the roller gets move to
     */
    private static void moveWalze1ToKey(char key){
        r1.moveToKey(key);
        r2.move(r1.getMoves());
        r3.move(r1.getMoves());
    }

    /**
     * Moves Roller 2 to the given char
     * @param key
     *          The char the roller gets move to
     */
    private static void moveWalze2ToKey(char key){
        r2.moveToKey(key);
        r3.moveFinal(r2.getMoves());
    }
}
