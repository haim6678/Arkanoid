package game;

/**
 * Created by haim on 09-May-16.
 */
public class Counter {
    private int counter;

    /**
     * the constructor.
     */
    public Counter() {
        this.counter = 0;
    }


    /**
     * add number to current count.
     *
     * @param number -the new number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number -the new number
     */
    public void decrease(int number) {
        this.counter -= number;
    }


    /**
     * get current count.
     *
     * @return - the value currently in the counter
     */
    public int getValue() {
        return this.counter;
    }
}
