package training.selenium.models;

public class Product {
    private String name;
    private String regularPrice;
    private String discountPrice;
    private String regularPriceStyle;
    private MyColor regularPriceColor;
    private String discountPriceStyle;
    private MyColor discountPriceColor;
    private float regularPriceSize;
    private float discountPriceSize;

    public boolean isDiscPriceBigger(){
        return (this.discountPriceSize > this.regularPriceSize);
    }

    public boolean isRegPriceCrossed(){
        return ((this.regularPriceStyle.equals("s")) || (this.regularPriceStyle.equals("del")));
    }

    public boolean isDiscPriceStrong(){
        return this.discountPriceStyle.equals("strong");
    }

    public boolean areNamesEqual(Product p){
        return this.name.equals(p.name);
    }

    public boolean areRegPricesEqual(Product p){
        return this.regularPrice.equals(p.regularPrice);
    }

    public boolean areDiscPricesEqual(Product p){
        return this.discountPrice.equals(p.discountPrice);
    }





    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public Product withRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public Product withDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public Product withRegularPriceStyle(String regularPriceStyle) {
        this.regularPriceStyle = regularPriceStyle;
        return this;
    }

    public Product withRegularPriceColor(MyColor regularPriceColor) {
        this.regularPriceColor = regularPriceColor;
        return this;
    }

    public Product withDiscountPriceStyle(String discountPriceStyle) {
        this.discountPriceStyle = discountPriceStyle;
        return this;
    }

    public Product withDiscountPriceColor(MyColor discountPriceColor) {
        this.discountPriceColor = discountPriceColor;
        return this;
    }

    public Product withRegularPriceSize(float regularPriceSize) {
        this.regularPriceSize = regularPriceSize;
        return this;
    }

    public Product withDiscountPriceSize(float discountPriceSize) {
        this.discountPriceSize = discountPriceSize;
        return this;
    }

    public String getName() {

        return name;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public String getRegularPriceStyle() {
        return regularPriceStyle;
    }

    public MyColor getRegularPriceColor() {
        return regularPriceColor;
    }

    public String getDiscountPriceStyle() {
        return discountPriceStyle;
    }

    public MyColor getDiscountPriceColor() {
        return discountPriceColor;
    }

    public float getRegularPriceSize() {
        return regularPriceSize;
    }

    public float getDiscountPriceSize() {
        return discountPriceSize;
    }
}
