package GameLogic;

import UI.*;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class SavedBlocks {
    private Random random;
    private Fieldcopy fieldcopy;
    private UI ui;
    private DrawPanel drawPanel;
    Queue<Character> nextBlocksTypes = new ArrayDeque<>(); // I dont save the whole Block i just save the type so I will just Generate it Again
    int[][] nextBlockBufferCoords;
    char typeNextBlockBufferCoords;

    char typeHoldBlock; // I dont save the whole Block i just save the type so I will just Generate it Again

    private boolean moreHoldBlocks;



    public SavedBlocks() {

    }

    public void setSavedBlocksAttributes(Fieldcopy fieldcopy, UI ui, boolean seedActive, int seed, DrawPanel drawPanel, boolean moreHoldBlocks)
    {
        if (!seedActive)
        {
            this.random = new Random();
        }
        else
        {
            this.random = new Random(seed);
        }

        this.drawPanel = drawPanel;
        this.moreHoldBlocks = moreHoldBlocks;

        this.fieldcopy = fieldcopy;
        this.ui = ui;
    }

    public void addNextBlockToQueue()
    {
        nextBlocksTypes.add(BlocksQueue.getBlock(random));
    }

    public void setupSavedBlock()
    {

        if(moreHoldBlocks)
        {
            for (int i = 0; i < 4; i++)
            {
                addNextBlockToQueue();
            }
        }
        else
        {
            addNextBlockToQueue();
        }
        drawPanel.setNextBlockQueue(nextBlocksTypes);
    }

    public void setUpdateDrawPanelBlocksQueue(int[][] nextBlockBufferCoords) {
        this.nextBlockBufferCoords = nextBlockBufferCoords;
        //Todo make queue in drawpanel
        //ui.setNextBlockCoords(nextBlocksCoords.peek(), nextBlocksTypes.peek());
    }

    public void setTypeNextBlockBufferCoords(char typeNextBlockBufferCoords) {
        this.typeNextBlockBufferCoords = typeNextBlockBufferCoords;
    }

    public void holdBlock(char type, Fieldcopy fieldcopy)
    {

        if (typeHoldBlock != '\u0000') //Checks if is not Equivalent to null for char
        {
            fieldcopy.setBlockCoords(Block.getBlockCoordsByType(typeHoldBlock));
            fieldcopy.setBlockType(typeHoldBlock);
        }
        else
        {
            fieldcopy.setBlockCoords(Block.getBlockCoordsByType(nextBlocksTypes.peek()));
            fieldcopy.setBlockType(nextBlocksTypes.poll());

            addNextBlockToQueue();


        }


        typeHoldBlock = type;
    }

    public void newBlock()
    {
        fieldcopy.setBlockCoords(Block.getBlockCoordsByType(nextBlocksTypes.peek()));
        fieldcopy.setBlockType(nextBlocksTypes.poll());
        addNextBlockToQueue();
    }


}
