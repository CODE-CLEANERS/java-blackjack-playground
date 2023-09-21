package nextstep.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String getInput() throws IOException {
        return br.readLine();
    }
}
