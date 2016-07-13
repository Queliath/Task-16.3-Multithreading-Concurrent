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

    /*
    Создается объект ReentrantReadWriteLock, на который будет ссылаться ссылка
    класса ReadWriteBlock. Этот класс позволет более гибко синхронизировать
    доступ к ресурсу за счет 2ух встроеных Lock'ов (ReadLock и WriteLock).
    Много reader'ов читают данные в параллель и только 1 writer пишет данные.
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void readOperation(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to read");
        /*
        Поток, читающий данные входит в метод и вызывает метод lock() на
        readLock'е, тем самым сообщая о намерении произвести чтение. Если в
        это время проивзодится запись данных (т.е. взята блокировка на
        writeLock'е), то поток блокируется до окончания операции записи.
        Однако если другие потоки так же происводят чтение и вызвали lock()
        на readLock'е и ни один поток не проводит запись, то поток не
        будет блокирован и продолжит выполнение.
         */
        readWriteLock.readLock().lock();
        try {
            logger.debug("Thread " + Thread.currentThread().getName() + " start to read");
            Thread.sleep(1000);
            logger.debug("Thread " + Thread.currentThread().getName() + " end to read");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*
            После окончания операции чтения, поток вызывает метод unlock() на
            readLock'е, тем самым сообщания о завершении своей работы с ресурсом.
             */
            readWriteLock.readLock().unlock();
        }
    }

    public void writeOperation(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to write");
        /*
        Поток, который хочет записсать данные в ресурс при входе в метод вызывает
        lock() на writeLock'е, тем самым блокируя доступ к ресурсу для всех
        читающих потоков и потоков, которые так же хотят записать данные.
         */
        readWriteLock.writeLock().lock();
        try {
            logger.debug("Thread " + Thread.currentThread().getName() + " start to write");
            Thread.sleep(1000);
            logger.debug("Thread " + Thread.currentThread().getName() + " end to write");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*
            После завершения операции записи в ресурс поток вызывает метод
            unlock() на writeLock'е, тем самым снимая блокировку с ожидающих
            потоков.
             */
            readWriteLock.writeLock().unlock();
        }
    }
}
