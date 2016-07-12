package by.epam.task16_3.synchronizers.exchanger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    private Exchanger<Integer> exchanger = new Exchanger<>();

    public void useResource() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        Thread.sleep(1000);

        Random random = new Random();
        int valueToExchange = random.nextInt(10);
        logger.debug("Thread " + Thread.currentThread().getName() + " ready to exchange value " + valueToExchange);

        int valueFromExchange = exchanger.exchange(valueToExchange);
        logger.debug("Thread " + Thread.currentThread().getName() + " get the exchange value " + valueFromExchange);

        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
    }
}
