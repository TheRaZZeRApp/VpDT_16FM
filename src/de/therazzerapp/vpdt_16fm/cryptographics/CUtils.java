package de.therazzerapp.vpdt_16fm.cryptographics;

import java.util.Random;

/**
 * Class includes various useful methods and global objects.
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class CUtils {

    /**
     * The global chars which can be used inside a text.
     * todo Will be replace by a config file
     *
     */
    public static char[] charSet= {'~','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0','_','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','\'','§','$','%','&','/','(',')','=','?',',',';','.','ö','Ö','ü','Ü','ä','Ä','*','-',':',' ','>'};

    /**
     * Gets added to the dividend's last line until its the same size as the password.
     * todo Will be replaced by something better and config file
     */
    public static char enhancementsSymbol = 'x';

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
     *      An array where each String has the given length
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
     * Password does not contain any spaces
     * @param length
     *          The password length
     * @return
     *      A random generated password
     */
    public static String generatePassword(int length){
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for(int x=0;x != length;x++){
            char temp = charSet[random.nextInt(charSet.length-1)];
            while (temp == ' '){
                temp = charSet[random.nextInt(charSet.length-1)];
            }
            password.append(temp);
        }
        return password.toString();
    }

    /**
     * Replaces the last expression in a string by another.
     *
     * @param text
     *          The raw text
     * @param regex
     *          The expression to be replaced
     * @param replacement
     *          The replacement
     * @return
     *      The edited text
     */
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
}
