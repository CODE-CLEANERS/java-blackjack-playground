package nextstep.gamer;

public class Dealer extends GameUser {
    private static final String DEALER_NAME = "Dealer";

    public Dealer() {
        super(DEALER_NAME);
    }
    @Override
    public CardSumStatus statusCheck() {
        return CardSumStatus.getNumberStatusForDealer(this.getCardSum());
    }
}
// 1. 상태를 체크한다. 플레이어/딜러는 상태를 리턴하고, 그것을 바탕으로 더 받을 수 있는지 판단한다
// 2. 더 받을 수 있다면,(UNDER) Y/N 을 입력하라고 한다.
// 3. N을 입력하면 그대로 종료 <- 이 상태를 나타내야 한다. 종료가 아닌 녀석들만 반복해야하는데...    //, Y를 입력하면 다음 페이즈로 넘어간다.

// 종료의 상태를 나타내려면 어떻게 할 것인가? ->
// 1. Enum 에서 뎁스를 깊게 하는 건 좋은 게 아닐 듯 하다...상태를 더 늘려서 체크문을 늘린다는 것은 거부감이 든다.
// 2. 새로운 Enum을 만들어서 시작/종료 만 나타낸다? 아니면 플레이어의 필드만?
