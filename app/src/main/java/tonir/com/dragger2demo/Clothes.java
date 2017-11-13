package tonir.com.dragger2demo;

/**
 * Created by yitu on 2017/11/13.
 * 时间 10:28
 */

public class Clothes {
    private Cloth cloth;

    public Cloth getCloth() {
        return cloth;
    }

    public Clothes(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public String toString() {
        return cloth.getColor() + "衣服";
    }
}
