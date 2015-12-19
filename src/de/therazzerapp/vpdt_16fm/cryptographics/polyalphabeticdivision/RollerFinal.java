package de.therazzerapp.vpdt_16fm.cryptographics.polyalphabeticdivision;

/**
 * <description>
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since <version>
 */
public class RollerFinal extends Roller {
    public RollerFinal(int moves, int multipler) {
        super(moves, multipler);
    }

    @Override
    public void move(int keyPosition) {
        moves=0;

        if (keyPosition == 0){
            return;
        }

        for(int x=(keyPosition*multipler*multipler);x != 0;x--){
            position--;
            moves++;
        }
        position = correctMoves(position);
    }

    @Override
    public void moveToKey(char key) {
        //empty
    }

    public void moveFinal(int keyPosition){
        moves=0;

        if (keyPosition == 0){
            return;
        }

        for(int x=(keyPosition);x != 0;x--){
            position--;
            moves++;
        }
        position = correctMoves(position);
    }
}
