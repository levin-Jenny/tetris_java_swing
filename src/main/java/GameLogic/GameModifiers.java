package GameLogic;

import java.util.Random;

public class GameModifiers {
    boolean garbageActive;
    private Field field;

    private long garbageTime = 5000;
    private long garbageTimer;


    public GameModifiers() {

    }


    public void setGameModifiersAttributes(Field field, boolean garbageActive)
    {
        this.field = field;
        this.garbageActive = garbageActive;
    }

    public void  setGarbageTime(long garbageTime)
    {
        this.garbageTime = garbageTime;
    }

    public void addToGarbageTime(long intervall)
    {
        garbageTimer += intervall;
        checkGarbageTimer();
    }

    public void checkGarbageTimer()
    {
        if (garbageTime < garbageTimer)
        {
            garbageTimer = 0;
            garbage();
        }
    }

    private void garbage()
    {
        if (garbageActive)
        {
            field.placeMultipleGarbage(1);
        }
    }
}
