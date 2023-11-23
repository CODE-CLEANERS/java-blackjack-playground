package card;

public class Card {
    private final Number number;
    private final Shape shape;

    public Card(Shape shape, Number number) {
        this.number = number;
        this.shape = shape;
    }

    public String getCardInfo(){
        return number.getName() + shape.getName();
    }

    public Number getNumber() {
        return number;
    }
}