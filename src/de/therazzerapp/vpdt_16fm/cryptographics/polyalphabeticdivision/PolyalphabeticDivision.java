package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;


import de.therazzerapp.vpdt_16fm.cryptographics.CSettings;
import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;

/**
 * Main poly div class.
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class PolyalphabeticDivision {

    private static RollerNormal r1;
    private static RollerNormal r2;
    private static RollerFinal r3;
    private static String splittedDivisor[];

    private static void update (String divisor){
        r1 = new RollerNormal(CSettings.r1Position,CSettings.r1Multiplier);
        r2 = new RollerNormal(CSettings.r2Position,CSettings.r2Multiplier);
        r3 = new RollerFinal(CSettings.r3Position,CSettings.r3Multiplier);

        splittedDivisor = splitByNumber(divisor,1);
    }

    /**
     * Adds a x to the dividend until both are even.
     * @param dividend
     *              The raw text
     * @param divisor
     *              The password
     * @return
     *      The fixed dividend
     */
    private static String fixDividend(String dividend, String divisor){
        while(dividend.length() < divisor.length()){
            dividend += "x";
        }
        return dividend;
    }

    public static String encode(String dividend, String divisor){
        String cipher = "";

        update(divisor);
        dividend = CUtils.clearPlaintext(dividend);
        divisor = CUtils.clearPlaintext(divisor);
        dividend = fixDividend(dividend, divisor);

        String temp[] = splitByNumber(dividend,divisor.length());

        for(int x=0;x != temp.length;x++){
            cipher += (convertText(temp[x],false));
        }

        return cipher;
    }

    public static String decode(String dividend, String divisor){
        String cipher = "";

        update(divisor);

        String temp[] = splitByNumber(dividend,divisor.length());

        for(int x=0;x != temp.length;x++){
            cipher += (convertText(temp[x],true));
            splittedDivisor = splitByNumber(temp[x],1);
        }

        return cipher;
    }

    /**
     * Splits a String into an array containing Strings by a given value.
     * @param text
     * @param number
     * @return
     */
    private static String[] splitByNumber(String text, int number) {
        int inLength = text.length();
        int arLength = inLength / number;
        int left=inLength%number;
        if(left>0){++arLength;}
        String ar[] = new String[arLength];
        String tempText=text;
        for (int x = 0; x < arLength; ++x) {
            if(tempText.length()>number){
                ar[x]=tempText.substring(0, number);
                tempText=tempText.substring(number);
            }else{
                ar[x]=tempText;
            }
        }
        return ar;
    }

    private static String convertText(String dividend, boolean decode){
        String convertetText= "";
        for(int x=0;x != splittedDivisor.length;x++){
            String[] splittedDividend = splitByNumber(dividend,1);
            convertetText += getNewKey(splittedDivisor[x].charAt(0),splittedDividend[x].charAt(0));
        }
        if (!decode)
            splittedDivisor = splitByNumber(convertetText,1);
        return convertetText;
    }

    private static char getNewKey(char dividend, char divisor){
        moveWalze1ToKey(dividend);
        moveWalze2ToKey(divisor);
        r1.move(1);
        return r3.getKey();
    }

    private static void moveWalze1ToKey(char key){
        r1.moveToKey(key);
        r2.move(r1.getMoves());
        r3.move(r1.getMoves());
    }

    private static void moveWalze2ToKey(char key){
        r2.moveToKey(key);
        r3.moveFinal(r2.getMoves());
    }
}
