package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public abstract class Roller {
    protected int position = 1;
    protected int moves;
    protected int multipler;

    public Roller(int moves, int multipler) {

        this.multipler = multipler;
        move(moves);
    }

    protected int keyToPosition(char key){
        int i = 0;
        while (key != CUtils.charSet[i]) {
            i++;
        }
        return i;
    }

    protected int correctMoves(int newPosition){
        while ((newPosition < 0) || (newPosition > (CUtils.rollerLength-1))){
            if(newPosition > (CUtils.rollerLength-1)){
                newPosition -= (CUtils.rollerLength-1);
            } else {
                newPosition += (CUtils.rollerLength-1);
            }
        }
        return newPosition;
    }

    public abstract void move(int keyPosition);
    public abstract void moveToKey(char key);

    public char getKey(){
        return CUtils.charSet[position];
    }

    public int getCurrentPosition() {
        return position;
    }

    public int getMoves() {
        return moves;
    }
}
