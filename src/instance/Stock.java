package instance;

public class Stock {

    private String code;
    private String name;
    private String market;
    private String sharesIssued;

    public Stock(String code,String name,String market,String sharesIssued){
        this.code=code;
        this.name=name;
        this.market=market;
        this.sharesIssued=sharesIssued;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSharesIssued() {
        return sharesIssued;
    }

    public void setSharesIssued(String sharesIssued) {
        this.sharesIssued = sharesIssued;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
