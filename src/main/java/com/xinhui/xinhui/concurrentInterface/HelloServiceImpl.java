package com.xinhui.xinhui.concurrentInterface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author dongliang.wang
 * @createTime 2019/5/15
 **/
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Transactional(rollbackFor = InterruptedException.class)
    @Override
    public void sayHello(long timeMillis) {
        long time = System.currentTimeMillis() - timeMillis;
        long second = 5000;
        if (time > second) {
            //超过5秒的打印日志输出
            log.warn("time : {}", time);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
