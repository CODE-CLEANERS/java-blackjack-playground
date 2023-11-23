package hand;

public class Asset {
    private int money;

    public void setMoney(int money) {
        this.money = money;
    }

    private void winMoney(int money){
        this.money += money;
    }

    private void payMoney(int money){
        this.money -= money;
    }

    public int getMoney() {
        return money;
    }

    public void sendMoney(Asset asset, int money){
        this.payMoney(money);
        asset.winMoney(money);
    }
}