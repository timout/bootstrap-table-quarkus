package timout.quarkus.bootstrap.table;

import java.util.Random;

public class Util {

    private Util() {
        //
    }

    public static String generatingRandomAlphabeticString(int size) {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static long generateRank() {
        Random random = new Random();
        return random.nextLong();
    }
}
