package tonir.com.dragger2demo;

import dagger.Component;

/**
 * Created by yitu on 2017/11/13.
 * 时间 09:41
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
