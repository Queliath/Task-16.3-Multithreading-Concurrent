package by.epam.task16_3.synchronizers.phaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private Phaser phaser = new Phaser(5);

    public void useResource() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        Thread.sleep(1000);

        logger.debug("Thread " + Thread.currentThread().getName() + " come to first point and wait");
        phaser.arriveAndAwaitAdvance();
        logger.debug("Thread " + Thread.currentThread().getName() + " continue to use resource");

        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " come to second point and continue");
        phaser.arrive();

        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
    }
}
