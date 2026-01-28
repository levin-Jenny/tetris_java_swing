package GameLogic;

import UI.UI;

public class GameStats {
    int score = 0;
    int linesSum = 0;
    int linesForLevel = 0;
    int levels = 1;

    Gravity gravity;
    UI ui;
    GameModifiers gameModifiers;

    public GameStats()
    {

    }

    public void setGameStatsAttributes(Gravity gravity, UI ui, GameModifiers gameModifiers)
    {
        this.gravity = gravity;
        this.ui = ui;
        this.gameModifiers = gameModifiers;
    }


    public void levelUpCheck()
    {
        if (linesForLevel >= 5)
        {
            levels++;
            linesForLevel -= 5;
            gravity.upDateInterval(levels);
            gameModifiers.setGarbageTime(gravity.getInterval()* 13L);
            ui.setLevel(levels);
        }

    }

    public void lineMade(int linesCount)
    {
        linesForLevel+= linesCount;
        linesSum += linesCount;
        switch(linesCount)
        {
            case 1:
                score += 100*levels;
                break;
            case 2:
                score += 300*levels;
                break;
            case 3:
                score += 500*levels;
                break;
            case 4:
                score += 800*levels;
                break;
        }
        levelUpCheck();
        ui.setScore(score);
    }

    public void hardDropPoints()
    {
        score += 3*levels;
        ui.setScore(score);
    }
}
