package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

import de.therazzerapp.vpdt_16fm.cryptographics.CryptographicUtils;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public abstract class Roller {
    protected int position = 0;
    protected int moves;
    protected int multipler;

    public Roller(int moves, int multipler) {
        this.multipler = multipler;
    }

    protected int keyToPosition(char key){
        int i;
        for(i = 0; key != CryptographicUtils.charSet[i]; i++){}
        return i;
    }

    protected int correctMoves(int newPosition){
        while ((newPosition < 0) || (newPosition > (CryptographicUtils.rollerLength-1))){
            if(newPosition > (CryptographicUtils.rollerLength-1)){
                newPosition -= (CryptographicUtils.rollerLength-1);
            } else {
                newPosition += (CryptographicUtils.rollerLength-1);
            }
        }
        return position;
    }

    public abstract void move(int keyPosition);
    public abstract void moveToKey(char key);

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
