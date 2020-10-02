package utils;

        import java.util.Random;

public class MyRandom {
    private static MyRandom instance = null;
    private Random random;
    private  MyRandom() {
        random = new Random();
    }

    public static MyRandom getInstance() {
        if (instance == null) instance = new MyRandom();
        return instance;
    }

    public int getRandomInt(int a, int b) {
        return random.nextInt(b - a + 1) + a;
    }

    public double getRandomDouble() {
        return random.nextDouble() * 2 - 1;
    }
}
