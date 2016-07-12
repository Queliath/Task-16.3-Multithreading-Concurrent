package by.epam.task16_3.synchronizers.exchanger;

/**
 * Created by Владислав on 12.07.2016.
 */
public class ExampleThread implements Runnable {
    private volatile boolean isRunning = true;

    private Resource resource;

    public ExampleThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                resource.useResource();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning(){
        isRunning = false;
    }
}
