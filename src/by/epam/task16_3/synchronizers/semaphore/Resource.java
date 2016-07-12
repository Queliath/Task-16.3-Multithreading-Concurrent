package by.epam.task16_3.synchronizers.semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private Semaphore semaphore = new Semaphore(3, true);

    public void useResource() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " try to use resource");
        semaphore.acquire();
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
        semaphore.release();
    }
}
