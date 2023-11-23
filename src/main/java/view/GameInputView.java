package view;

import java.io.IOException;
import java.util.Optional;

public class GameInputView {

    private final static String ASK_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private final static String INVALID_INPUT_MESSAGE = "입력이 바르지 않습니다";
    private final static String ASK_BAT_AMOUNT_FORMAT = "%s의 배팅 금액은?";
    private final static String AKS_HIT_FORMAT = "%s는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";
    private final static String YES = "y";
    private final static String NO = "n";


    public static Optional<String> askPlayerName(){
        String names;
        try {
            names = Terminal.in(ASK_PLAYER_MESSAGE);
        } catch (IOException e) {
            names = null;
            Terminal.outln(INVALID_INPUT_MESSAGE);
        }

        return Optional.ofNullable(names);
    }

    public static Optional<Integer> askBatAmount(String name) {
        Integer batAmount;

        try {
            Terminal.emptyln();
            String in = Terminal.in(String.format(ASK_BAT_AMOUNT_FORMAT, name));
            batAmount = Integer.parseInt(in);
        } catch (IOException | NumberFormatException e) {
            batAmount = null;
            Terminal.outln(INVALID_INPUT_MESSAGE);
        }

        return Optional.ofNullable(batAmount);
    }

    public static Optional<Boolean> askHit(String name) {
        Boolean isHit = null;

        try {
            String in = Terminal.in(String.format(AKS_HIT_FORMAT, name));

            if (in.equalsIgnoreCase(YES)) {
                isHit = true;
            }

            if (in.equalsIgnoreCase(NO)) {
                isHit = false;
            }
        } catch (IOException e) {
            Terminal.outln(INVALID_INPUT_MESSAGE);
        }

        return Optional.ofNullable(isHit);
    }
}
