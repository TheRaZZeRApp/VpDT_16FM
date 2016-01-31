package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

/**
 * The final roller moves backwards
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class RollerFinal extends Roller {
    public RollerFinal(int moves, int multiplicand) {
        super(moves, multiplicand);
    }

    @Override
    public void move(int keyPosition) {
        moves=0;

        if (keyPosition == 0){
            return;
        }

        moves += keyPosition*multiplicand*multiplicand;
        position = correctMoves(position -= keyPosition*multiplicand*multiplicand);
    }

    /**
     * This method is empty, use moveFinal instead.
     * @param key
     */
    @Override
    public void moveToKey(char key) {
        //empty
    }

    /**
     * The final move from this roller goes backwards
     * @param keyPosition
     *          The amount of moves
     */
    public void moveFinal(int keyPosition){
        moves=0;

        if (keyPosition == 0){
            return;
        }

        moves += keyPosition;
        position = correctMoves(position -= keyPosition);
    }
}
