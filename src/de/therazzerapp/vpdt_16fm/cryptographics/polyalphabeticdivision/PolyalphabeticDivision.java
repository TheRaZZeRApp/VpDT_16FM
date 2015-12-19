package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;


import de.therazzerapp.vpdt_16fm.cryptographics.CryptographicUtils;

/**
 * Main poly div class.
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class PolyalphabeticDivision {

    private static String dividend[];
    private static String password;
    private static String sentence;

    private static RollerNormal r1;
    private static RollerNormal r2;
    private static RollerFinal r3;

    private static void update (String newDividend, String divisor){
        password = prepareText2(divisor);
        sentence = newDividend;

        if (divisor.length() > newDividend.length()){
            while (divisor.length() != newDividend.length()){
                newDividend += "x";
            }
        }

        dividend = splitByNumber(divisor,1);

        r1 = new RollerNormal(1,2);
        r2 = new RollerNormal(2,2);
        r3 = new RollerFinal(2,2);
    }

    public static String encode(String newDividend, String divisor){

        update(newDividend, divisor);

        String quotient = "";

        for(int x=0;x != prepareText(sentence).length;x++){
            quotient += (convertText(prepareText(sentence)[x],false));
        }

        return quotient;
    }

    public static String decipher(String newDividend, String divisor){

        update(newDividend, divisor);

        String quotient = "";

        for(int x=0;x != prepareText(sentence).length;x++){
            quotient += (convertText(prepareText(sentence)[x],true));
            dividend = splitByNumber(prepareText(sentence)[x],1);
        }

        return quotient;
    }

    private static String[] prepareText(String dividend){
        //dividend = dividend.replaceAll("[^" +  String.valueOf(CryptographicUtils.rollerLength) +"]", "");
        String[] newText = splitByNumber(dividend,sentence.length());

        String temp = "";
        if (newText.length>1){
            temp = newText[newText.length-1];
        } else {
            return newText;
        }

        while (temp.length() != sentence.length()){
            temp += "x";
        }

        newText[newText.length-1] = temp;

        return newText;
    }

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

    private static String prepareText2(String text){
        text = text.replaceAll("[^a-z0-9_A-Z!\"§$%&/()=.;,?öÖäÄüÜ*-:]", "");
        return text;
    }
    private static char getNewKey(char dividend, char divisor){
        moveWalze1ToKey(dividend);
        moveWalze2ToKey(divisor);
        r1.move(1);
        return r3.getKey();
    }
    private static String convertText(String text, boolean reConv){
        String convertetText= "";
        for(int x=0;x != dividend.length;x++){
            String[] temp = splitByNumber(text,1);
            convertetText += getNewKey(dividend[x].charAt(0),temp[x].charAt(0));
        }
        if (!reConv)
            dividend = splitByNumber(convertetText,1);
        return convertetText;
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
