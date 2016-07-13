package by.epam.task16_3.synchronizers.cyclic_barrier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    /*
    Создается объект CyclicBarrier со счетчиком равным 5, что означает кол-во
    синхонизируемых потоков в определенной точке. Потоки достигшие точки синхронизации
    будут ожидать прибытия других потоков.
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public void operation1() throws InterruptedException, BrokenBarrierException {
        logger.debug("Thread " + Thread.currentThread().getName() + " start the operation1");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end the operation1");
        /*
        После выполенения операции operation1 поток вызывает метод await()
        и уменьшает счетчик на единицу - это и есть точка синхронизации.
        После достижения этой точки всеми потоками, счетчик обнулится
        и потоки продолжают свою работу. До тех пор потоки будут блокированы.
         */
        cyclicBarrier.await();
    }

    public void operation2() throws InterruptedException{
        logger.debug("Thread " + Thread.currentThread().getName() + " start the operation2");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end the operation2");
    }
}
