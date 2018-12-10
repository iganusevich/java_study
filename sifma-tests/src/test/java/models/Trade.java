package models;

public class Trade {
    String symbol;
    String eqtype;
    String order;
    String type;
    String amount;
    String price;
    String id;
    boolean confirmed;





    public boolean isConfirmed() {
        return confirmed;
    }

    public Trade withConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public String getId() {
        return id;
    }

    public Trade withId(String id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Trade withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getEqtype() {
        return eqtype;
    }

    public Trade withEqtype(String eqtype) {
        this.eqtype = eqtype;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public Trade withOrder(String order) {
        this.order = order;
        return this;
    }

    public String getType() {
        return type;
    }

    public Trade withType(String type) {
        this.type = type;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public Trade withAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Trade withPrice(String price) {
        this.price = price;
        return this;
    }
}
