package Data;

public class BuyUpDownMults {
    private double buy_up_mults;
    private double buy_down_mults;

    public BuyUpDownMults(double buy_up_mults, double buy_down_mults) {
        this.buy_up_mults = buy_up_mults;
        this.buy_down_mults = buy_down_mults;
    }

    public double getBuy_up_mults() {
        return buy_up_mults;
    }

    public void setBuy_up_mults(double buy_up_mults) {
        this.buy_up_mults = buy_up_mults;
    }

    public double getBuy_down_mults() {
        return buy_down_mults;
    }

    public void setBuy_down_mults(double buy_down_mults) {
        this.buy_down_mults = buy_down_mults;
    }
}
