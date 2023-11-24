package blackjack.game;

import blackjack.player.Gambler;
import view.GameInputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerRegistrationStep {

    private final GameStarter gameStarter;

    public PlayerRegistrationStep(GameStarter gameStarter) {
        this.gameStarter = gameStarter;
    }

    public BattingStep proceedBattingStep() {
        BattingStep battingStep = new BattingStep(this.gameStarter);
        battingStep.bat();
        return battingStep;
    }

    protected List<Gambler> addGambler() {
        Optional<String> in = GameInputView.askPlayerName();
        if (!in.isPresent()) {
            return addGambler();
        }

        String[] names = in.get().split(",");
        List<Gambler> gamblers = new ArrayList<>();
        for (String name : names) {
            validateNameAndAddGambler(name);
        }

        return gamblers;
    }

    private void validateNameAndAddGambler(String name) {
        if (name.isEmpty()) {
            return;
        }

        gameStarter.getGamblers().add(new Gambler(name));
    }
}
