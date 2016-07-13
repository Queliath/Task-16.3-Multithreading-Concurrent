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

    /*
    Создается объект ReentrantLock, на который будет ссылаться ссылка
    интерфейса Lock. Этот класс позволяет ограничить доступ к критической
    секции болеее гибко чем при использовании synchronized. Толкьо один
    поток имеет доступ к разделяемому ресурсу.
     */
    private Lock lock = new ReentrantLock();
    /*
    Создаются 2 объекта Condition, которые представляют альтернативные методы
    стандарным wait/notify/notifyAll. Первый объект будет блокировать потоки,
    которым неоходимо ждать выполнения operation1, второй объект будет выполнять
    обратную логику.
     */
    private Condition operation1Made = lock.newCondition();
    private Condition operation2Made = lock.newCondition();

    /*
    Флаговая переменная показывающая факт выполнения operation1
     */
    private boolean operation1MadeFlag = false;

    public void operation1(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation1");
        /*
        При входе в метод поток вызывает метод lock(), тем самым получая
        блокировку на ресурс, если же ресурс уже используется другим потоком,
        то данный поток будет блокирован до момента освобождения ресурса.
         */
        lock.lock();
        try {
            /*
            Если операция operation1 уже произведена, поток после вызова метода
            await() блокируется до тех пор пока не выполнится operation2 и на
            объекте Condition не вызовится метод signal().
             */
            while(operation1MadeFlag){
                operation2Made.await();
            }
            logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation1");
            Thread.sleep(1000);
            /*
            После выполнения operation1 поток меняет значение фалговой переменной на true.
             */
            operation1MadeFlag = true;
            logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation1");
            /*
            Поток оповещает о выполнении операции operation1 и вызоом метода signal
            на объекте Condition снимает блокировку с потока, заблокированного этим
            же объектом.
             */
            operation1Made.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*
            После пользования ресурсом поток должен снять с него блокировку,
            тем самым открыв доступ к ресурсу уже заблокированным и ожидающим
            потокам.
             */
            lock.unlock();
        }
    }

    public void operation2(){
        logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation2");
        /*
        При входе в метод поток вызывает метод lock(), тем самым получая
        блокировку на ресурс, если же ресурс уже используется другим потоком,
        то данный поток будет блокирован до момента освобождения ресурса.
         */
        lock.lock();
        try {
            /*
            Если операция operation1 еще не произведена, поток после вызова метода
            await() блокируется до тех пор пока не выполнится operation1 и на
            объекте Condition не вызовится метод signal().
             */
            while (!operation1MadeFlag){
                operation1Made.await();
            }
            logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation2");
            Thread.sleep(1000);
            /*
            После выполнения operation2 поток меняет значение фалговой переменной на false.
             */
            operation1MadeFlag = false;
            logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation2");
            /*
            Поток оповещает о выполнении операции operation2 и вызоом метода signal
            на объекте Condition снимает блокировку с потока, заблокированного этим
            же объектом.
             */
            operation2Made.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
