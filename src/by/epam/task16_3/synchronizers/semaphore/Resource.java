package by.epam.task16_3.synchronizers.semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Resource {
    private static final Logger logger = LogManager.getRootLogger();

    /*
    Создется объект семафора со счетчиком равным 3, оргничивающим кол-во потоков.
    Доступ к общему ресурсу управляется с помощью счетчика. Если он больше нуля,
    то доступ разрешается, а значение счетчика уменьшается.
     */
    private Semaphore semaphore = new Semaphore(3);

    public void useResource() throws InterruptedException {
        logger.debug("Thread " + Thread.currentThread().getName() + " try to use resource");
        /*
        Вызов метода acquire() производит уменьшение значения счетчика и вход
        потока в критическую секцию, однако если значение счетчика равно 0,
        потоку придется ждать открытия доступа к ресурсу семафором (текущий
        поток блокируется, пока другой поток не освободит ресурс).
         */
        semaphore.acquire();
        logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
        Thread.sleep(1000);
        logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
        /*
        Вызов метода release() производит увеличение значения счетчика и выход
        потока из критической секции. Тем самым откывая доступ к ресурсу ожидающим
        потокам.
         */
        semaphore.release();
    }
}
