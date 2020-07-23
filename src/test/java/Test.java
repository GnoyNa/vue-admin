
import pers.xp.util.EncodeUtil;

import java.util.UUID;

public class Test {

    @org.junit.Test
    public  void test(){
        try {
            System.out.println(EncodeUtil.sha1Encode("123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
