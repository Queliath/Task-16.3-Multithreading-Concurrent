package by.epam.task16_3.synchronizers.count_down_latch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    /*
    Создается объект CountDownLatch со счетчиком равным 5, который позволяет
    одному или нескольким потокам ожидать до тех пор, пока не завершится
    определенное количество операций, выполняющихся в других потоках.
     */
    private CountDownLatch countDownLatch = new CountDownLatch(5);

    public void operation1() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start the operation1");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end the operation1");
        /*
        Поток после выполнения операции operation1 вызывает метод countDown(),
        тем самым уменьая счетчик на единицу.
         */
        countDownLatch.countDown();
        /*
        После этого поток вызывает метод await(), что приводит к ожиданию потоком
        обнуления счетчика countDownLatch'а (что будет означать выполнения всеми 5-ю
        потоками операции operation1), после этого все потоки продолжают свою работу.
         */
        countDownLatch.await();
    }

    public void operation2() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start the operation2");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end the operation2");
    }
}
