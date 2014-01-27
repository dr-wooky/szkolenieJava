package pl.training;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 27.01.14
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class Kostka {

    private Random random = new Random();

    public int rzut() {
        return random.nextInt(6) + 1;
    }

}
