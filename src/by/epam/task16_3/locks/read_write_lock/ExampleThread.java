package by.epam.task16_3.locks.read_write_lock;

/**
 * Created by Владислав on 12.07.2016.
 */
public class ExampleThread implements Runnable {
    private volatile boolean isRunning = true;

    private Resource resource;
    private boolean isReadingThread;

    public ExampleThread(Resource resource, boolean isReadingThread){
        this.resource = resource;
        this.isReadingThread = isReadingThread;
    }

    @Override
    public void run() {
        if(isReadingThread){
            while (isRunning){
                resource.readOperation();
            }
        }
        else {
            while (isRunning){
                resource.writeOperation();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopRunning(){
        isRunning = false;
    }
}
