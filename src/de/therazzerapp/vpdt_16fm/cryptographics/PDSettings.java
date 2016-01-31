package de.therazzerapp.vpdt_16fm.cryptographics;

/**
 * Contains the starting position of every roller and there multiplicand.
 *
 * @author The RaZZeR App <rezzer101@googlemail.com; e-mail@therazzerapp.de>
 * @since 0.0.2
 */
public class PDSettings {
    private int r1Position;
    private int r2Position;
    private int r3Position;
    private int r1Multiplicand;
    private int r2Multiplicand;
    private int r3Multiplicand;

    /**
     * Sets the starting position of every roller and there multiplicand.
     *
     * @param r1Position
     *          Roller 1 starting position
     * @param r2Position
     *          Roller 2 starting position
     * @param r3Position
     *          Roller 3 starting position
     * @param r1Multiplicand
     *          Roller 1 multiplicand
     * @param r2Multiplicand
     *          Roller 2 multiplicand
     * @param r3Multiplicand
     *          Roller 3 multiplicand
     */
    public PDSettings(int r1Position, int r2Position, int r3Position, int r1Multiplicand, int r2Multiplicand, int r3Multiplicand) {
        this.r1Position = r1Position;
        this.r2Position = r2Position;
        this.r3Position = r3Position;
        this.r1Multiplicand = r1Multiplicand;
        this.r2Multiplicand = r2Multiplicand;
        this.r3Multiplicand = r3Multiplicand;
    }

    /**
     * Sets every amount to one.
     */
    public void restetSettings(){
        r1Position = 1;
        r2Position = 1;
        r3Position = 1;
        r1Multiplicand = 1;
        r2Multiplicand = 1;
        r3Multiplicand = 1;
    }

    /**
     * Returns the starting position of roller 1
     * @return
     *      The starting position
     */
    public int getR1Position() {
        return r1Position;
    }

    /**
     * Returns the starting position of roller 2
     * @return
     *      The starting position
     */
    public int getR2Position() {
        return r2Position;
    }

    /**
     * Returns the starting position of roller 3
     * @return
     *      The starting position
     */
    public int getR3Position() {
        return r3Position;
    }

    /**
     * Returns the multiplicand of roller 1
     * @return
     *      The multiplicand
     */
    public int getR1Multiplicand() {
        return r1Multiplicand;
    }

    /**
     * Returns the multiplicand of roller 2
     * @return
     *      The multiplicand
     */
    public int getR2Multiplicand() {
        return r2Multiplicand;
    }

    /**
     * Returns the multiplicand of roller 3
     * @return
     *      The multiplicand
     */
    public int getR3Multiplicand() {
        return r3Multiplicand;
    }
}
