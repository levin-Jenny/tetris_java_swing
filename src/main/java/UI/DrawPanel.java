package UI;

import GameLogic.Block;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class DrawPanel extends JPanel {
    private int strokeWidht = 3;
    private int cellWidht = 35;
    private int cellHeight = cellWidht;
    private int x = 0;
    private int y = 0;
    private int level = 1;
    private int score = 0;
    private Font font = new Font("Arial", Font.PLAIN, 22);

    private Queue<Character> nextBlockQueue;

    private char holdBlockType;
    private boolean darkMode = false;

//    private fieldType fieldToPrint;
//
//    enum fieldType
//    {
//        Original,Copy
//    }

    public void turnDarkModeOn()
    {
        darkMode = true;
    }

    public void setNextBlockQueue(Queue<Character> nextBlockQueue) {
        this.nextBlockQueue = nextBlockQueue;
    }

    private void setPenColor(Graphics2D g2)
    {
        if (darkMode)
        {
            g2.setColor(Color.WHITE);
        }
        else
        {
            g2.setColor(Color.BLACK);
        }
    }






    private int[][] field;

    public void setDrawPanelfield(int[][] field)
    {
        this.field = field;
    }

    public void paintField(int[][] newField) {
        /*
        * Changes The field to the passed in Field and Paints it after w/ repaint
        *
        * TODO: Save Both fields in this Object and make that you can Choose with an enum value
        *
        * */

        this.field = newField;

        repaint();
    }

    public void updateLevel(int level)
    {
        this.level = level;
    }

    public void updateScore(int score)
    {
        this.score = score;
        repaint();
    }


    public void updateHoldBlock(int[][] holdBlockCoords, char holdBlockType)
    {
        this.holdBlockType = holdBlockType;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(field==null) return;

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );


        paintField(g2);
        paintLevel(g2);
        paintScore(g2);
        paintNextBlock(g2);
        paintHoldBlock(g2);

    }

    protected void drawGrid(Graphics2D g2, int startCoordX, int startCoordY, int height, int width)
    {
        /**
         * Draws a Grid
         *
         * @param g2 is the Graphics Object
         * @param startCoordY is the y part Of the startCoord on the top Left
         * @param startCoordX is the x part Of the startCoord on the top Left
         * @param height how high the grid is in CellHeights
         * @param width how wide the grid is in CellWidths
         * */

        g2.setColor(Color.LIGHT_GRAY); // Always in Gray , the Outline then has to be drawn in the Color setPenColor() returns
        for (int  i = 0; i < width; i++) //Width
        {
            for (int j = 0; j < height; j++) //Height
            {
                g2.drawRect(startCoordX + i*cellWidht, startCoordY + j*cellHeight, cellWidht, cellHeight);
            }
        }

    }

    protected void drawGridContents(Graphics2D g2, int startCoordX, int startCoordY, int[][] field)
    {
        /**
         * Draws a Grid
         *
         * @param g2 is the Graphics Object
         * @param startCoordY is the y part Of the startCoord on the top Left
         * @param startCoordX is the x part Of the startCoord on the top Left
         * @param field Draws all none 0 parts of the Grid in the Right Colors
         * */

        for(int i = 0; i < field.length; i++) // X coord  or Widht
        {
            for (int j = 0; j < field[i].length; j++) // Y coord  or Height
            {
                if (field[i][j] != 0)
                {
                    g2.setColor(Block.getColorByInt(field[i][j]));
                    g2.fillRect(startCoordX + j* cellWidht, startCoordY + i*cellHeight, cellWidht, cellHeight);
                }
            }
        }
    }



    protected void paintLevel(Graphics2D g2)
    {
        setPenColor(g2);
        int rightSideOfField = 200+ (field[0].length+1) *cellWidht;


        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(rightSideOfField, cellHeight, cellWidht*3, cellHeight*2);

        g2.setFont(font);

        g2.drawString("Level:",rightSideOfField+25,cellHeight + 22);

        g2.drawString(String.valueOf(level),rightSideOfField+50,cellHeight + 50);


    }

    protected void paintScore(Graphics2D g2)
    {
        int rightSideOfField =  200+ (field[0].length+1) *cellWidht;

        setPenColor(g2);
        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(rightSideOfField, cellHeight*4, cellWidht*3, cellHeight*2);

        g2.setFont(font);

        g2.drawString("Score:",rightSideOfField+25,cellHeight*4 + 22);

        g2.drawString(String.valueOf(score),rightSideOfField+3,cellHeight*4 + 50);
    }

    protected void paintNextBlock(Graphics2D g2)
    {
        int leftSideOfNextBlocks = 200-4*cellWidht;

        g2.setFont(font);

        g2.drawString("Next:", leftSideOfNextBlocks +25,cellHeight + 22);

        setPenColor(g2);

        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(leftSideOfNextBlocks, cellHeight, cellWidht*3, cellHeight*5);

        g2.drawLine(leftSideOfNextBlocks, cellHeight * 2, leftSideOfNextBlocks + cellWidht*3, cellHeight * 2);

        int startCoordX = leftSideOfNextBlocks;
        int startCoordY = cellHeight * 2;

        for (char block: nextBlockQueue)
        {
            paintNextBlocks(g2, startCoordX, startCoordY, block);
            startCoordY += cellHeight * 4;
        }


    }

    protected void paintNextBlocks(Graphics2D g2, int leftSideOfNextBlocks, int startCoordY, char blockType)
    {

        int[][] nextBlockCoords = Block.getBlockCoordsByType(blockType);




        drawGrid(g2, 200-4*cellWidht,startCoordY, 4, 3);


        g2.setColor(Block.getBlockColorByType(blockType)); //Set The Color matching to the Type



        for(int[] coord: nextBlockCoords)
        {
            y = coord[0];
            x = coord[1];

            g2.fillRect(leftSideOfNextBlocks +(x+1)*cellWidht, startCoordY+(y+1)*cellHeight,cellWidht,cellHeight);
        }


    }

    protected void paintHoldBlock(Graphics2D g2)
    {
        int rightSideOfField =  200+ (field[0].length+1) *cellWidht;


        g2.setFont(font);

        g2.drawString("Hold:",rightSideOfField+25,cellHeight*7 + 22);

        drawGrid(g2,rightSideOfField, cellHeight*8, 4, 3);


        g2.setColor(Block.getBlockColorByType(holdBlockType));


        if (holdBlockType != '\u0000') //Test if null
        {
            int[][] blockCoords = Block.getBlockCoordsByType(holdBlockType); //Just Translate Type To Coords and use Those later
            for(int[] coord: blockCoords)
            {
                y = coord[0];
                x = coord[1];

                g2.fillRect(rightSideOfField +(x+1)*cellWidht, cellHeight*8+(y+1)*cellHeight,cellWidht,cellHeight);
            }
        }



        setPenColor(g2);
        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(rightSideOfField, cellHeight * 7, cellWidht*3, cellHeight*5);

        g2.drawLine(rightSideOfField, cellHeight * 8, rightSideOfField + cellWidht*3, cellHeight * 8);
    }


    protected void paintField(Graphics2D g2)
    {

        drawGridContents(g2, 200,0,field);
        drawGrid(g2,200,0, field.length, field[0].length);

        setPenColor(g2);
        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(200,0,cellWidht*field[0].length,cellHeight*field.length);
    }
}