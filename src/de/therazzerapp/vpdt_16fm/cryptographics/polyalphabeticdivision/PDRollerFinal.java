package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

/**
 * The final roller moves backwards
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.1
 */
public class PDRollerFinal extends PDRoller {

    public PDRollerFinal(int moves, int multiplicand) {
        super(moves, multiplicand);
    }

    @Override
    public void move(int keyPosition) {
        if (!isValid(keyPosition)) {
            return;
        }
        moves += keyPosition*multiplicand*multiplicand;
        position = correctMoves(position - keyPosition*multiplicand*multiplicand);
    }

    /**
     * The final move from this roller goes backwards
     * @param keyPosition
     *          The amount of moves
     */
    public void moveFinal(int keyPosition){
        if (!isValid(keyPosition)) {
            return;
        }
        moves += keyPosition;
        position = correctMoves(position - keyPosition);
    }

}
