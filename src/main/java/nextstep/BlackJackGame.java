package nextstep;

import nextstep.card.Deck;
import nextstep.gamer.Dealer;
import nextstep.gamer.GameUser;
import nextstep.gamer.Player;
import nextstep.gamer.Players;
import nextstep.view.InputView;
import nextstep.view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private BlackjackController controller;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void game() throws IOException {
        outputView.printGetPlayersName();
        
        String input = inputView.getInput();
        String[] names = input.split(",");

        Players players = initPlayers(names);
        controller = new BlackjackController(new Deck(), players, inputView, outputView);

        controller.dealCards(2);
        outputView.printCardsOfUsers(players);

        // ugly한걸 만들기 싫다...
    }

    private static Players initPlayers(String[] names) {
        List<GameUser> users = new ArrayList<>();
        users.add(new Dealer());
        for (String name : names) {
            GameUser gameUser = new Player(name);
            users.add(gameUser);
        }
        return new Players(users);
    }
}
