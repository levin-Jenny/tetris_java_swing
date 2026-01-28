package GameLogic;

public class Gravity implements Runnable {

    private Fieldcopy fieldcopy;
    private int interval = 400;
    private boolean running;
    private Thread thread;
    private long now;
    private long lastFallTime;
    private GameModifiers gameModifiers;




    public Gravity() {

    }

    public void setGravityAttributes(Fieldcopy fieldcopy, GameModifiers gameModifiers)
    {
        this.fieldcopy = fieldcopy;
        this.gameModifiers = gameModifiers;
    }

    public void upDateInterval(int levels)
    {
        interval = (int)(400 * Math.pow(0.9,levels));
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public void run() {
        lastFallTime = System.currentTimeMillis();

        while (running) {
            now = System.currentTimeMillis();

            if (now - lastFallTime >= interval) {
                gameModifiers.addToGarbageTime(interval);
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
