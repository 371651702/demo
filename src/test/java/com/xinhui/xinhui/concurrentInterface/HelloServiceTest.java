package com.xinhui.xinhui.concurrentInterface;

import com.xinhui.xinhui.XinhuiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XinhuiApplication.class})
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        //模拟1000个线程并发
        CountDownLatchUtil countDownLatchUtil = new CountDownLatchUtil(1000);

        countDownLatchUtil.latch(() -> helloService.sayHello(currentTimeMillis));
    }
}