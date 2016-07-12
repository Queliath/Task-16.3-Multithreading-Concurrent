package by.epam.task16_3.synchronizers.count_down_latch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private CountDownLatch countDownLatch = new CountDownLatch(5);

    public void operation1() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start the operation1");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end the operation1");
        countDownLatch.countDown();
        countDownLatch.await();
    }

    public void operation2() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start the operation2");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end the operation2");
    }
}
