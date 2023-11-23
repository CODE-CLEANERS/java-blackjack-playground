package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String in(String message) throws IOException {
        outln(message);
        return br.readLine();
    }

    public static void outln(String message) {
        System.out.println(message);
    }

    public static void emptyln() {
        System.out.println();
    }
}
