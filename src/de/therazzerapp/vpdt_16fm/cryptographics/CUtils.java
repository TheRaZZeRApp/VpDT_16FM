package de.therazzerapp.vpdt_16fm.cryptographics;

import java.util.Random;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class CUtils {

    /**
     * The global chars which can be used inside a text.
     *
     */
    public static char[] charSet= {'~','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0','_','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','\'','§','$','%','&','/','(',')','=','?',',',';','.','ö','Ö','ü','Ü','ä','Ä','*','-',':',' ','>'};
    public static int rollerLength = charSet.length;

    /**
     * Removes every character not contained in the global char set.
     * @param rawPlaintext
     *          The raw text
     * @return
     *      The text without any character not contained in the global char set.
     */
    public static String clearPlaintext(String rawPlaintext){
        return rawPlaintext.replaceAll("[^" +  String.valueOf(CUtils.charSet) +"]", "");
    }

    /**
     * Splits a String into an array containing Strings by a given value.
     * @param text
     *          The raw text
     * @param number
     *          The distances by which the string is cut
     * @return
     *      An array =)
     */
    public static String[] splitByNumber(String text, int number) {
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

    /**
     * Generates a password containing all characters from the global char set.
     *
     * @param length
     *          The password length
     * @return
     *      A random generated password
     */
    public static String generatePassword(int length){
        Random random = new Random();
        String temp = "";
        for(int x=0;x != length;x++){
            temp += charSet[random.nextInt(charSet.length-1)];
        }
        return temp;
    }

    /**
     *
     * @param text
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
}
