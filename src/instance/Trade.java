package instance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {
    private LocalDateTime dateTime;
    private String code;
    private String buyOrSell;
    private String quantity;
    private BigDecimal price;

    public Trade(LocalDateTime dateTime,String code,String buyOrSell,String quantity,BigDecimal price){
        this.dateTime = dateTime;
        this.code = code;
        this.buyOrSell = buyOrSell;
        this.quantity = quantity;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
