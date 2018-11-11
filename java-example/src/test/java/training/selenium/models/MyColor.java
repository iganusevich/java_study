package training.selenium.models;

public class MyColor {
    private int red;
    private int green;
    private  int blue;
    private int alpha;

    public boolean isGray(){
        return((this.red == this.green) && (this.green == this.blue));
    }

    public boolean isRed(){
        return (this.green == 0 && this.blue == 0);
    }



    public int getRed() {
        return red;
    }

    public MyColor withRed(int red) {
        this.red = red;
        return this;
    }

    public int getGreen() {
        return green;
    }

    public MyColor withGreen(int green) {
        this.green = green;
        return this;
    }

    public int getBlue() {
        return blue;
    }

    public MyColor withBlue(int blue) {
        this.blue = blue;
        return this;
    }

    public int getAlpha() {
        return alpha;
    }

    public MyColor withAlpha(int alpha) {
        this.alpha = alpha;
        return this;
    }



    

    
}
