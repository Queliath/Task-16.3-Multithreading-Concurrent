package by.epam.task16_3.locks.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private Lock lock = new ReentrantLock();

    public void useResource() throws InterruptedException{
        logger.debug("Thread " + Thread.currentThread().getName() + " try to use resource");
        lock.lock();
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        try {
            Thread.sleep(1000);
            logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
        } finally {
            lock.unlock();
        }
    }
}
