import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class Main {
    static int n = 11;
    static int l = 5;
    static int p = 3;

    static int[] a = new int[n];
    static int[] b = new int[n];
    static int[] c = new int[n];
    static int[] d = new int[n];

    public static class T extends Thread {
        private int start;
        private int end;
        public T(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public void run() {
            System.out.println(currentThread().getName() + ": " + start + " -> " + end);
            for (int i=start; i<end; i++) {
                c[i] = a[i] + b[i];
            }
        }
    }

    public static void main(String[] args) {

        int chunk = n / p;
        int rest = n % p;

        Random random = new Random();

        for (int i=0; i<n; i++) {
            a[i] = random.nextInt(l);
            b[i] = random.nextInt(l);
        }

        for (int i=0; i<n; i++) {
            d[i] = a[i] + b[i];
        }

        int start = 0;
        int end = chunk;

        List<T> threads = new ArrayList<>();
        for (int i=0; i<p; i++) {
            if (rest > 0) {
                end += 1;
                rest -= 1;
            }

//            System.out.println(start);
//            System.out.println(end);
            threads.add(new T(start, end));
            start = end;
            end += chunk;
        }

        for (T t : threads) {
            t.start();
        }

        for (T t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        System.out.println("Sequential: " + Arrays.toString(d));
    }
}
