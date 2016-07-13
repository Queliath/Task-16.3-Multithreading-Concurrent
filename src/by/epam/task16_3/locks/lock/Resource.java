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

    /*
    Создается объект ReentrantLock, на который будет ссылаться ссылка
    интерфейса Lock. Этот класс позволяет ограничить доступ к критической
    секции болеее гибко чем при использовании synchronized. Толкьо один
    поток имеет доступ к разделяемому ресурсу.
     */
    private Lock lock = new ReentrantLock();

    public void useResource() throws InterruptedException{
        logger.debug("Thread " + Thread.currentThread().getName() + " try to use resource");
        /*
        При входе в метод поток вызывает метод lock(), тем самым получая
        блокировку на ресурс, если же ресурс уже используется другим потоком,
        то данный поток будет блокирован до момента освобождения ресурса.
         */
        lock.lock();
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        try {
            Thread.sleep(1000);
            logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
        } finally {
            /*
            После пользования ресурсом поток должен снять с него блокировку,
            тем самым открыв доступ к ресурсу уже заблокированным и ожидающим
            потокам.
             */
            lock.unlock();
        }
    }
}
