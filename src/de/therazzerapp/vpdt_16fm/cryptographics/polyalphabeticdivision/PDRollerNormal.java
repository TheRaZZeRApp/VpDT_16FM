package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class PDRollerNormal extends PDRoller {

    public PDRollerNormal(int moves, int multiplicand) {
        super(moves, multiplicand);
    }

    @Override
    public void move(int keyPosition) {
        if (!isValid(keyPosition)) {
            return;
        }
        moves += keyPosition*multiplicand;
        position = correctMoves(position + keyPosition*multiplicand);
    }

    /**
     * Moves the roller to a give char
     * @param key
     *          The char
     */
    public void moveToKey(char key) {
        moves=0;
        int keyPosition = keyToPosition(key);

        if (keyPosition == 0){
            position = 0;
            return;
        }

        if (position == keyPosition){
            return;
        }

        while (position != keyPosition){
            position++;
            moves++;
            position = correctMoves(position);
        }
    }
}
