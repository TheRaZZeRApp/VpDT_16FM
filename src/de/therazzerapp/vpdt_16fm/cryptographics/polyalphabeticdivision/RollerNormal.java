package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class RollerNormal extends Roller {
    public RollerNormal(int moves, int multiplicand) {
        super(moves, multiplicand);
    }

    @Override
    public void move(int keyPosition) {
        moves=0;

        if (keyPosition == 0){
            return;
        }

        moves += keyPosition*multiplicand;
        position = correctMoves(position += keyPosition*multiplicand);
    }

    @Override
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
