package com.xinhui.xinhui.concurrentInterface;


/**
 * @author dongliang.wang
 * @createTime 2019/5/15
 **/
public interface HelloService {
    /**
     * test high concurrence
     * @param timeMillis  execute time
     */
    void sayHello(long timeMillis);
}
