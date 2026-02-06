package GameLogic;

import UI.UI;


import java.util.Arrays;
import java.util.Random;

public class Field {
    private final int x;
    private final int y;
    private int[][] field;
    private Fieldcopy fieldcopy;
    private UI ui;
    private GameStats gameStats;
    private Random random;
    private Gravity gravity;


    public int[][] getField() {
        return field;
    }

    public Field(int x, int y)
    {
        this.x = x;
        this.y = y;
        field = new int[y][x];
        this.random = new Random();
    }


    public void setFieldAtrributes(UI ui, Fieldcopy fieldcopy, GameStats gameStats, Gravity gravity)
    {
        this.ui = ui;
        this.fieldcopy = fieldcopy;
        this.gameStats = gameStats;
        this.gravity = gravity;
    }

    public void failCheck()
    {
        for(int i : field[1])
        {
            if(i != 0)
            {
                 gravity.stop();
            }
        }
    }




    public void writeIntoField(int[][] block, int[] posInField, char blockType)
    {
        int intForColor = Block.getIntByType(blockType);
        for(int[] coord: block)
        {
            int y = coord[0] + posInField[0];
            int x = coord[1] + posInField[1];
            field[y][x] = intForColor;
        }
        updateField();
        failCheck();

        fieldcopy.updateFieldCopy();
        ui.paintField();
    }

    public boolean checkLineComplete(int[] line)
    {
        for (int i: line)
        {
            if (i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public void moveLinesDown(int index)
    {
        for (int i = index; i > 0;  i--)
        {
            field[i] = field[i-1].clone();
        }
    }

    public void updateField()
    {
        int counter = 0;
        for (int i = 0; i< field.length; i++)
        {
            if (checkLineComplete(field[i]))
            {
                counter++;
                field[i] = new int[x];
                moveLinesDown(i);
            }

        }
        if(counter > 0)
        {
            gameStats.lineMade(counter);
        }
    }

    public void moveLinesUp()
    {
        for(int i = 1; i < field.length; i++)
        {
            field[i-1] = field[i].clone();
        }
    }

    public void placeGarbage(int emptyPos)
    {
        moveLinesUp();
        int[] line = new int[x];
        Arrays.fill(line, 8);
        line[emptyPos] = 0;
        field[y - 1] = line;

    }

    public void placeMultipleGarbage(int repeats)
    {
        placeMultipleGarbage(repeats, random.nextInt(x-1));
    }


    public void placeMultipleGarbage(int repeats, int emptyPos)
    {
        for(int i = 0; i < repeats; i++)
        {
            placeGarbage(emptyPos);
        }
    }

}
