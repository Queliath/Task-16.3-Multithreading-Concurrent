package by.epam.task16_3.synchronizers.count_down_latch;

/**
 * Created by Владислав on 12.07.2016.
 */
public class ExampleThread implements Runnable{
    private Resource resource;

    public ExampleThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            resource.operation1();
            resource.operation2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
