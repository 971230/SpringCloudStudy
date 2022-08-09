import com.longj.utils.Builder;

/**
 * @Author 龙江锋
 * @Date 2022/8/3 20:08
 * @Version 1.0
 */
public class TempTest {
    public static void main(String[] args) {
        Demo build = Builder.of(Demo::new)
                .with(Demo::test1, 1)
                .with(Demo::test2, 1, "1")
                .with(Demo::test3, 2)
                .build();
    }
}
