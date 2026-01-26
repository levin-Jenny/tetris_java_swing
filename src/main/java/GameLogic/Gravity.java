package GameLogic;

public class Gravity implements Runnable {

    private Fieldcopy fieldcopy;
    private int interval = 400;
    private boolean running;
    private Thread thread;
    private long now;
    private long lastFallTime;


    public Gravity() {

    }

    public void setGravityAttributes(Fieldcopy fieldcopy)
    {
        this.fieldcopy = fieldcopy;
    }

    public void upDateInterval(int levels)
    {
        interval = (int)(400 * Math.pow(0.9,levels));
    }


    @Override
    public void run() {
        lastFallTime = System.currentTimeMillis();

        while (running) {
            now = System.currentTimeMillis();

            if (now - lastFallTime >= interval) {

                fieldcopy.moveBlockDown();
                lastFallTime = now;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {}
        }
    }

    public void stop() {
        running = false;
    }

    public void start()
    {
        running = true;
        lastFallTime -= interval;
        thread = new Thread(this, "Gravity-Thread");
        thread.start();
    }
}
