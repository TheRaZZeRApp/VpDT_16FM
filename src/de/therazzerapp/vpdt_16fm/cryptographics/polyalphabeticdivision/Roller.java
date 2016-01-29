package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

import de.therazzerapp.vpdt_16fm.cryptographics.CUtils;

/**
 * The main roller class
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public abstract class Roller {
    protected int position;
    protected int moves;
    protected int multiplicand;

    public Roller(int moves, int multiplicand) {
        this.multiplicand = multiplicand;
        move(moves);
    }

    /**
     * Converts a char to an amount of moves the roller has to make to get to the char.
     *
     * @param key
     *          The char the to find
     * @return
     *      The amount of moves the roller has to make
     */
    protected int keyToPosition(char key){
        int i = 0;
        while (key != CUtils.charSet[i]) {
            i++;
        }
        return i;
    }

    /**
     * If the amount of moves is bigger than the main char set is long, the methods cuts them down.<br>
     * Example: If the char set contains 5 chars and the roller has to move about 8 places,<br>
     * the method cuts it down to 3 (8-5)
     *
     * @param newPosition
     *          The amount of moves
     * @return
     *      A cutted amount of moves
     */
    protected int correctMoves(int newPosition){
        int charSetLength = CUtils.charSet.length-1;
        while ((newPosition < 0) || (newPosition > (charSetLength))){
            if(newPosition > (charSetLength)){
                newPosition -= (charSetLength);
            } else {
                newPosition += (charSetLength);
            }
        }
        return newPosition;
    }

    /**
     * Moves the roller by a give amount
     * @param keyPosition
     *          The amount of moves
     */
    public abstract void move(int keyPosition);

    /**
     * Moves the roller to a give char
     * @param key
     *          The char
     */
    public abstract void moveToKey(char key);

    /**
     * Returns the currently selected char.
     * @return
     *      The current char
     */
    public char getCurrentKey(){
        return CUtils.charSet[position];
    }

    /**
     * Returns the current position.
     * @return
     *      The current position
     */
    public int getCurrentPosition() {
        return position;
    }

    /**
     * Returns the moves that had been done since the last move
     * @return
     *      The moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Returns the amount a roller adds to every move
     * @return
     *      The amount of added moves
     */
    public int getMultiplicand() {
        return multiplicand;
    }
}
