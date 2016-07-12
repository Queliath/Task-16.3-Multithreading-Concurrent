package by.epam.task16_3.locks.read_write_lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void readOperation(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to read");
        readWriteLock.readLock().lock();
        try {
            logger.debug("Thread " + Thread.currentThread().getName() + " start to read");
            Thread.sleep(1000);
            logger.debug("Thread " + Thread.currentThread().getName() + " end to read");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void writeOperation(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to write");
        readWriteLock.writeLock().lock();
        try {
            logger.debug("Thread " + Thread.currentThread().getName() + " start to write");
            Thread.sleep(1000);
            logger.debug("Thread " + Thread.currentThread().getName() + " end to write");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
