import java.util.Base64;

/**
 * @Author 龙江锋
 * @Date 2022/8/3 20:55
 * @Version 1.0
 */
public class TempTest {
    @org.junit.jupiter.api.Test
    public void test(){
        String str = "你和我重逢在灿烂的季节";
        //Base64不只是可以对字符串进行编码，任何byte[]数据都可以，编码结果可以是byte[]，也可以是字符串
        String encodeStr = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println("Base64编码后的字符串："+encodeStr);

        System.out.println("解码后的字符串："+new String(Base64.getDecoder().decode(encodeStr)));
    }
}
