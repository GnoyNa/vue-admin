package pers.xp.util;

import org.junit.Test;

import java.io.File;
import java.util.UUID;

public class PicUtil {
    public static  String getPicUrl(String imgstr){
        String path = "\\img\\"+UUID.randomUUID().toString()+".jpg";
        Base64ImageConvertor.generateImage(imgstr,new File("C:\\Users\\Administrator\\OneDrive\\oj\\xxq\\src\\main\\webapp"+path)) ;
        return  path;
    }
    @Test
    public  void  test(){
        System.out.println(new File("src/main/webapp/img").getAbsolutePath());
    }
}
