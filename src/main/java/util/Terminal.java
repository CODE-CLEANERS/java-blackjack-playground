package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String in(String message) throws IOException {
        out(message);
        return br.readLine();
    }

    public static void out(String message) {
        System.out.println(message);
    }

    public static void outln() {
        System.out.println();
    }
}
