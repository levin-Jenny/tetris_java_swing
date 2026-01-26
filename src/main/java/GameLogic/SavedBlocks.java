package GameLogic;

import UI.UI;

import java.util.Random;

public class SavedBlocks {
    Random random;
    Fieldcopy fieldcopy;
    UI ui;

    int[][] nextBlockCoords;
    char typeNextBlockCoords;
    int[][] holdBlockCoords;
    char typeHoldBlockCoords;



    public SavedBlocks() {

    }

    public void setSavedBlocksAttributes(Fieldcopy fieldcopy, UI ui, int seed)
    {
        if (seed == 0)
        {
            this.random = new Random();
        }
        else
        {this.random = new Random(seed);}

        this.fieldcopy = fieldcopy;
        this.ui = ui;
    }

    public void setupSavedBlock()
    {
        Block.newBlock(this, random);
    }

    public void setNextBlockCoords(int[][] nextBlockCoords) {
        this.nextBlockCoords = nextBlockCoords;
        ui.setNextBlockCoords(nextBlockCoords, typeNextBlockCoords);
    }

    public void setTypeNextBlockCoords(char typeNextBlockCoords) {
        this.typeNextBlockCoords = typeNextBlockCoords;
    }

    public void holdBlock(int[][] blockCoords, char type, Fieldcopy fieldcopy)
    {
        if (holdBlockCoords != null)
        {
            fieldcopy.setBlockCoords(holdBlockCoords);
            fieldcopy.setBlockType(typeHoldBlockCoords);
        }
        else
        {
            fieldcopy.setBlockCoords(nextBlockCoords);
            fieldcopy.setBlockType(typeNextBlockCoords);

            Block.newBlock(this, random);


        }


        holdBlockCoords = blockCoords;
        typeHoldBlockCoords = type;
    }

    public void newBlock()
    {
        fieldcopy.setBlockCoords(nextBlockCoords);
        fieldcopy.setBlockType(typeNextBlockCoords);
        Block.newBlock(this, random);
    }


}
