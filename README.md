# Dagger2

## 概述

**Dagger2**是Android中比较热门的依赖注入框架，简单来说，就是一个类中需要依赖其他对象时，不需要你亲自为那些需要依赖的对象赋值，为那些对象赋值的操作就交给了[IOC框架](https://zh.wikipedia.org/wiki/%E6%8E%A7%E5%88%B6%E5%8F%8D%E8%BD%AC)

## Dagger2介绍

一般的[IOC框架](https://zh.wikipedia.org/wiki/%E6%8E%A7%E5%88%B6%E5%8F%8D%E8%BD%AC)都是通过反射来实现的，但**Dagger2**作为Android端的IOC框架，为了不影响性能，它是通过apt动态生成代码来实现的。

#### Dagger2主要分为三个模块：

1. 依赖提供方**Module**，负责提供依赖中所需要的对象，类似于工厂类。
2. 依赖需求方实例，它声明依赖对象，类似于业务类。例如在某个**Activity**中需要使用这个对象的时候，只需要在此**Activity**中声明它就行了。
3. 依赖注入组件**Component**，负责将对象注入到依赖需求方，它在实际编码中是一个接口，编译后**Dagger2**会自动为它生成一个实现类。

#### Dagger2有四种基础的注解：

- @Inject **Inject**主要有两个作用，一个是使用在构造函数上，通过标记构造函数让**Dagger2**来使用（**Dagger2**通过**Inject**标记可以在需要这个类实例的时候来找到这个构造函数并把相关实例**new**出来）从而提供依赖，另一个作用就是标记在需要依赖的变量让**Dagger2**为其提供依赖。
- @Provide 用**Provide**来标注一个方法，该方法可以在需要提供依赖时被调用，从而把预先提供好的对象当做依赖给标注了@Injection的变量赋值。**provide**主要用于标注**Module**里的方法
- @Module 用**Module**标注的类是专门用来提供依赖的。有的人可能有些疑惑，看了上面的@Inject，需要在构造函数上标记才能提供依赖，那么如果我们需要提供的类构造函数无法修改怎么办，比如一些**jar**包里的类，我们无法修改源码。这时候就需要使用**Module**了。**Module**可以给不能修改源码的类提供依赖，当然，能用Inject标注的通过**Module**也可以提供依赖
- @Component **Component**一般用来标注接口，被标注了**Component**的接口在编译时会产生相应的类的实例来作为提供依赖方和需要依赖方之间的桥梁，把相关依赖注入到其中。

送张图片说明一下关系：

<img src="https://raw.githubusercontent.com/WnagYo/Dragger2Demo/master/injection.jpg" width="100%" height="100%">

#### 举个栗子：
在app目录下的build.gradle中添加:
 xxxxxxxxxx dependencies {    ...    //引入dagger2    compile 'com.google.dagger:dagger:2.12'    annotationProcessor 'com.google.dagger:dagger-compiler:2.12'}
**使用的类**

```java
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
```



**依赖提供方**

```java
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

```

**依赖注入容器**

```java
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}

```

**依赖需求方**

```java
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

```