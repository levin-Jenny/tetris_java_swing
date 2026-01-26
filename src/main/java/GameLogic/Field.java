package GameLogic;

import UI.UI;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Field {
    private int x, y;
    private int[][] field;
    Fieldcopy fieldcopy;
    UI ui;
    GameStats gameStats;

/*
    //für Tests getter Setter


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public Fieldcopy getFieldcopy() {
        return fieldcopy;
    }

    public void setFieldcopy(Fieldcopy fieldcopy) {
        this.fieldcopy = fieldcopy;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    //ende getter setter für tests
*/

    public int[][] getField() {
        return field;
    }

    public Field(int x, int y)
    {
        this.x = x;
        this.y = y;
        field = new int[y][x];
    }


    public void setFieldAtrributes(UI ui, Fieldcopy fieldcopy, GameStats gameStats)
    {
        this.ui = ui;
        this.fieldcopy = fieldcopy;
        this.gameStats = gameStats;
    }

    public void failCheck()
    {
        for(int i : field[1])
        {
            if(i != 0)
            {
                 System.exit(1);
            }
        }
    }




    public void writeIntoField(int[][] block, int[] posInField, char blockType)
    {

        for(int[] coord: block)
        {
            int y = coord[0] + posInField[0];
            int x = coord[1] + posInField[1];
            switch(blockType)
            {
                case 'i':
                    field[y][x] = 1;
                    break;
                case 't':
                    field[y][x] = 2;
                    break;
                case 'j':
                    field[y][x] = 3;
                    break;
                case 'l':
                    field[y][x] = 4;
                    break;
                case 's':
                    field[y][x] = 5;
                    break;
                case 'z':
                    field[y][x] = 6;
                    break;
                case 'o':
                    field[y][x] = 7;
                    break;
            }

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
            field[i-1] = field[1].clone();
        }
    }

    public void placeGarbage(int emptyPos)
    {
        moveLinesUp();
        int[] line = new int[9];
        Arrays.fill(line, 8);
        line[emptyPos] = 0;
    }


    public void placeMultipleGarbage(int repeats, int emptyPos)
    {
        for(int i = 0; i < repeats; i++)
        {
            placeGarbage(emptyPos);
        }
    }



}
