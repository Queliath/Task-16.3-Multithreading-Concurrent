package by.epam.task16_3.synchronizers.exchanger;

/**
 * Created by Владислав on 12.07.2016.
 */
public class Main {
    public static void main(String[] args){
        Resource resource = new Resource();

        ExampleThread exampleThread1 = new ExampleThread(resource);
        ExampleThread exampleThread2 = new ExampleThread(resource);

        new Thread(exampleThread1).start();
        new Thread(exampleThread2).start();

        try {
            Thread.sleep(10000);
            exampleThread1.stopRunning();
            exampleThread2.stopRunning();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
