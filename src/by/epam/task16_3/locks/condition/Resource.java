package by.epam.task16_3.locks.condition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private final Lock lock = new ReentrantLock();
    private Condition operation1Made = lock.newCondition();
    private Condition operation1NotMade = lock.newCondition();

    private boolean operation1MadeFlag = false;

    public void operation1(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation1");
        lock.lock();
        try {
            while(operation1MadeFlag){
                operation1NotMade.await();
            }
            logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation1");
            Thread.sleep(1000);
            operation1MadeFlag = true;
            logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation1");
            operation1Made.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void operation2(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation2");
        lock.lock();
        try {
            while (!operation1MadeFlag){
                operation1Made.await();
            }
            logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation2");
            Thread.sleep(1000);
            operation1MadeFlag = false;
            logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation2");
            operation1NotMade.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
