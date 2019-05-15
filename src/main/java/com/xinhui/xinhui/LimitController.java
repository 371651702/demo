package com.xinhui.xinhui;


import com.revinate.guava.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author dongliang.wang
 * @createTime 2019/4/25
 **/
@RestController
public class LimitController {

    private RateLimiter limiter = RateLimiter.create(10);

    @PostMapping("/spike")
    public String spike(){
        System.out.println("等待时间: " + limiter.acquire());
        return "成功";
    }

    public static void main(String[] args) {
//        File file = new File("E:\\BOC");
//        findDirectory(file);
        for (int i = 0; i < 5; i++) {
            new Thread().start();
        }


    }

    private static void findDirectory(File file) {
        File[] f1 = file.listFiles();

        for (File f : f1) {
            if (!f.isFile()){
                findDirectory(f);
            }
            if (f.isFile()){
                System.out.println(f.getName());
            }
        }


    }


}
