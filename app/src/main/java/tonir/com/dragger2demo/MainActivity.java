package tonir.com.dragger2demo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Inject
    Cloth cloth;
    @Inject
    Shoe shoe;
    @Inject
    Clothes clothes;
    @Inject
    @Named("red")
    Cloth redCloth;
    @Inject
    @Named("blue")
    Cloth blueCloth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_main);
        DaggerMainComponent
                .builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);
//        tv.setText("我现在有" + cloth + "和" + shoe + "和" + clothes);
        tv.setText("我现在有" + redCloth + "和" + blueCloth);
    }
}
