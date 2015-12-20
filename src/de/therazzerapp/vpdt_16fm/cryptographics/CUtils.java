package de.therazzerapp.vpdt_16fm.cryptographics;

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
    public static char[] charSet= {'a','b','c','d','x'};
    public static int rollerLength = charSet.length;

    /**
     * Removes every character not contained in the global char set.
     * @param rawPlaintext
     * @return
     */
    public static String clearPlaintext(String rawPlaintext){
        return rawPlaintext.replaceAll("[^" +  String.valueOf(CUtils.charSet) +"]", "");
    }
}
