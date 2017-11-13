package tonir.com.dragger2demo;

/**
 * Created by yitu on 2017/11/13.
 * 时间 09:33
 */

public class Cloth {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }
}
