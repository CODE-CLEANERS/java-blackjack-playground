package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InputView {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String getInput(){
        try {
            return br.readLine();
        } catch (IOException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public String[] getName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] userNames = getInput().split(",");
        try{
            validate(userNames);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getName();
        }
        return userNames;
    }
    private void validate(String[] userNames){
        if (userNames.length == 0 || isDuplicatedNames(userNames) || isContainsBlank(userNames)){
            throw new IllegalArgumentException("[Error] 잘못된 입력");
        }
    }

    private boolean isContainsBlank(String[] userNames) {
        return Arrays.stream(userNames)
                .anyMatch(s -> s.trim().isEmpty());
    }

    private boolean isDuplicatedNames(String[] userNames) {
        return Arrays.stream(userNames)
                .distinct()
                .count() == userNames.length;
    }

    private int getMoney(String name){
        System.out.println(name + "의 배팅 금액은?");
        String input = getInput();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            System.out.println("[ERROR] 잘못된 입력");
            return getMoney(name);
        }
    }

    public boolean askUserForCardDecision(String name){
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = getInput();
        try {
            checkInput(input);
            return name.equals("y");
        } catch (IllegalArgumentException e){
            System.out.println("[ERROR] 잘못된 입력");
            return askUserForCardDecision(name);
        }
    }

    private void checkInput(String input) {
        input = input.trim().toLowerCase();
        if (input.isEmpty() || isNotValid(input)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotValid(String input) {
        return !(input.equals("y") || input.equals("n"));
    }
}