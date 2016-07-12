package by.epam.task16_3.locks.lock;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Main {
    public static void main(String[] args){
        Resource resource = new Resource();

        ExampleThread exampleThread1 = new ExampleThread(resource);
        ExampleThread exampleThread2 = new ExampleThread(resource);
        ExampleThread exampleThread3 = new ExampleThread(resource);
        ExampleThread exampleThread4 = new ExampleThread(resource);
        ExampleThread exampleThread5 = new ExampleThread(resource);

        new Thread(exampleThread1).start();
        new Thread(exampleThread2).start();
        new Thread(exampleThread3).start();
        new Thread(exampleThread4).start();
        new Thread(exampleThread5).start();

        try {
            Thread.sleep(10000);
            exampleThread1.stopRunning();
            exampleThread2.stopRunning();
            exampleThread3.stopRunning();
            exampleThread4.stopRunning();
            exampleThread5.stopRunning();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
