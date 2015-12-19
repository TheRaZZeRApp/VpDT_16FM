package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

import de.therazzerapp.vpdt_16fm.cryptographics.CryptographicUtils;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class Roller {
    private int position = 0;
    private int moves;

    public Roller() {
    }

    public char getKey(){
        return CryptographicUtils.charSet[position];
    }

    public int getCurrentPosition() {
        return position;
    }

    public int getMoves() {
        return moves;
    }
}
