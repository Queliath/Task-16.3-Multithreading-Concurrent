package by.epam.task16_3.synchronizers.phaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Phaser;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    /*
    Создается объект Phaser со счетчиком равным 5, что означает кол-во
    синхронизируемых потоков в определенных точках (фазах).
     */
    private Phaser phaser = new Phaser(5);

    public void useResource() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        Thread.sleep(1000);

        logger.debug("Thread " + Thread.currentThread().getName() + " come to first point and wait");
        /*
        После выполнения части операции поток вызывает метод arriveAndAwaitAdvance(),
        который блокирует поток до момента вызова этого же метода всеми остальными
        потоками. После чего все потоки продолжают свою работу.
         */
        phaser.arriveAndAwaitAdvance();
        logger.debug("Thread " + Thread.currentThread().getName() + " continue to use resource");

        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " come to second point and continue");
        /*
        После выполнения части операции поток вызывает метод arrive(), тем
        самым сообщая о достижении определенной фазы, при этом поток не будет
        блокирован и продолжит свою работу.
         */
        phaser.arrive();

        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
    }
}
