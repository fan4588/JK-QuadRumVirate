package com.nephew.jk.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
@Api("线程")
public class ThreadController {
    private static Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @GetMapping("test")
    public void test() throws Exception {
        logger.debug("多线程开始");
        //线程结束标识对象
        final CountDownLatch cdl = new CountDownLatch(2);//参数为线程个数
        Thread userThread1 = new Thread(() -> {
            logger.debug("线程开始");
            try {
                System.out.println("执行任务");
            } catch (Exception e) {
                logger.error("出现异常。");
            } finally {
                logger.debug("线程结束");
                cdl.countDown();//此方法是CountDownLatch的线程数-1
            }
        });
        Thread userThread2 = new Thread(() -> {
            logger.debug("线程开始");
            try {
                System.out.println("执行任务");
            } catch (Exception e) {
                logger.error("出现异常。");
            } finally {
                logger.debug("线程结束");
                cdl.countDown();//此方法是CountDownLatch的线程数-1
            }
        });
        try {
            userThread1.start();
            userThread2.start();
            cdl.await();//上面线程都结束才会执行这一步
            logger.debug("全部线程结束");
        } catch (InterruptedException e) {
            logger.error("出现异常", e);
            throw new Exception("出现异常", e);
        }
    }

}
