package javaTester;

import java.util.Random;

public class Topic_06_Random {
    // Java Build-in (Cung cap san - lay ra su dung)
    // Java Libraries (Do 1 ca nhan/to chuc tu viet)
    // Java Faker -> generate data random co nghia

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextBoolean());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());

        System.out.println(rand.nextInt(999));
    }

}
