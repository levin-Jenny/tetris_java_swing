package GameLogic;

import UI.UI;

public class Gravity implements Runnable {

    private Fieldcopy fieldcopy;
    private int interval = 400;
    private boolean running;
    private Thread thread;
    private long now;
    private long lastFallTime;
    private GameModifiers gameModifiers;
    private UI ui;




    public Gravity() {

    }

    public void setGravityAttributes(Fieldcopy fieldcopy, GameModifiers gameModifiers, UI ui)
    {
        this.fieldcopy = fieldcopy;
        this.gameModifiers = gameModifiers;
        this.ui = ui;
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
        ui.setRunning(false);
    }

    public void start()
    {
        running = true;
        ui.setRunning(true);
        lastFallTime -= interval;
        thread = new Thread(this, "Gravity-Thread");
        thread.start();
    }
}
