package tonir.com.dragger2demo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yitu on 2017/11/13.
 * 时间 09:36
 */

@Module
class MainModule {
    @Provides
    Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    Clothes getClothes(Cloth cloth) {
        return new Clothes(cloth);
    }

    @Provides
    @Named("red")
    Cloth getRedCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    @Named("blue")
    Cloth getBlueCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }
}
