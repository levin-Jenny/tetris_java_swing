package GameLogic;

import UI.UI;



public class Fieldcopy {
    private int[][] field;
    private int[] blockPositionCoords = new int[2];
    private int[][] blockCoords;
    private char blockType;
    private Boolean canHoldBlock = true;

    SavedBlocks savedBlocks;
    Field fieldObj;
    UI ui;
    Gravity gravity;
    GameStats gameStats;




    public void setBlockCoords(int[][] blockCoords) {
        this.blockCoords = blockCoords;
    }



    public Fieldcopy()
    {

    }


    public void setFieldcopyAttributes(SavedBlocks savedBlocks, Field fieldObj, UI ui, Gravity gravity, GameStats gameStats)
    {
        this.savedBlocks = savedBlocks;
        this.fieldObj = fieldObj;
        this.ui = ui;
        this.gravity = gravity;
        this.gameStats = gameStats;
    }

    public void setUpFieldcopy()
    {
        updateFieldCopy();

        getNewBlock();
    }



    public void setBlockType(char blockType) {
        this.blockType = blockType;
    }



    public int[][] getField() {
        return field;
    }

    public void getNewBlock()
    {
        int xCoord = field[0].length / 2;
        blockPositionCoords = new int[]{1, xCoord};
        savedBlocks.newBlock();
    }

    public void holdBlock()
    {
        if (canHoldBlock)
        {
            savedBlocks.holdBlock(blockCoords, blockType, this);
            int xCoord = field[0].length / 2;
            blockPositionCoords = new int[] {1, xCoord};
            writeIntoFieldCopy(blockCoords, blockPositionCoords);
            paintField();
            updateFieldCopy();
            canHoldBlock = false;
            ui.setHoldBlockCoords(savedBlocks.holdBlockCoords,savedBlocks.typeHoldBlockCoords);
        }

    }

    public void paintField()
    {
        ui.paintFieldCopy();
    }


    public boolean checkValidPlacement(int direction)
    {
        return checkValidPlacement(direction, this.blockCoords);
    }


    public void rotateBlockCounterClockwise()
    {
        if(checkValidPlacement(3,Block.rotateBlockCounterClockwise(blockCoords,blockType)))
        {
            blockCoords = Block.rotateBlockCounterClockwise(blockCoords, blockType);
        }
        writeIntoFieldCopy(blockCoords, blockPositionCoords);
        paintField();
        updateFieldCopy();

    }
    public void flipBlock()
    {
        if(checkValidPlacement(3,Block.flipBlock(blockCoords, blockType)))
        {
            blockCoords = Block.flipBlock(blockCoords, blockType);
        }
        writeIntoFieldCopy(blockCoords, blockPositionCoords);
        paintField();
        updateFieldCopy();
    }
    public void rotateBlockClockwise()
    {
        if(checkValidPlacement(3,Block.rotateBlockClockwise(blockCoords, blockType)))
        {
            blockCoords = Block.rotateBlockClockwise(blockCoords, blockType);
        }
        writeIntoFieldCopy(blockCoords, blockPositionCoords);
        paintField();
        updateFieldCopy();
    }

    public boolean checkValidPlacement(int direction, int[][] blockCoords) //direction ==> Down == 0, Right == 1, Left == 2, Nichts == 3
    {
        int[] testBlockCoords = blockPositionCoords.clone();
        switch(direction)
        {
            case 0:
                testBlockCoords[0]++;
                break;
            case 1:
                testBlockCoords[1]++;
                break;
            case 2:
                testBlockCoords[1]--;
                break;
            case 3:
                break;
        }
        try
        {
            for(int[] cord : blockCoords)
            {
                int y = cord[0];
                int x = cord[1];
                y += testBlockCoords[0];
                x += testBlockCoords[1];

                if (field[y][x] != 0)
                    return false;
            }
            return true;
        }
        catch(IndexOutOfBoundsException e)
        {
            return false;
        }

    }

    public void dashDown()
    {
        while(checkValidPlacement(0))
        {
            blockPositionCoords[0]++;
        }
        writeIntoFieldCopy(blockCoords, blockPositionCoords);
        paintField();
        moveBlockDown();
        gameStats.hardDropPoints();
    }


    public void moveBlockDown()
    {


        if(checkValidPlacement(0))
        {
            blockPositionCoords[0]++;
            writeIntoFieldCopy(blockCoords, blockPositionCoords);
            paintField();
            updateFieldCopy();
        }
        else
        {
            placeBlock();
            return;
        }

        updateFieldCopy();

    }

    public void writeIntoFieldCopy(int[][] block, int[] posInField)
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
    }

    public void placeBlock()
    {
        fieldObj.writeIntoField(blockCoords, blockPositionCoords, blockType);
        getNewBlock();
        canHoldBlock = true;

    }

    public void moveRight()
    {
        if(field[0].length - 1 == blockPositionCoords[1])
        {
            return;
        }

        if(checkValidPlacement(1))
        {
            blockPositionCoords[1]++;
            writeIntoFieldCopy(blockCoords, blockPositionCoords);
            paintField();
            updateFieldCopy();
        }

    }

    public void moveLeft()
    {   if (blockPositionCoords[1] == 0)
        {
            return;
        }
        if(checkValidPlacement(2))
        {
            blockPositionCoords[1]--;
            writeIntoFieldCopy(blockCoords, blockPositionCoords);
            paintField();
            updateFieldCopy();
        }

    }

    public void updateFieldCopy()
    {

        int[][] original = fieldObj.getField();
        int rows = original.length;
        int cols = original[0].length;

        this.field = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(original[i], 0, this.field[i], 0, cols);
        }
    }
}
