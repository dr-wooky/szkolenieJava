package pl.training;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 27.01.14
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class KostkaRzut {

    public static void main(String[] args) {
        Kostka kostka = new Kostka();
        for (int i=0; i<5; i++) {
            System.out.println(kostka.rzut());
        }
    }
}
