package UI;

import GameLogic.Block;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
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
        repaint();
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


        paintGrid(g2);
        paintLevel(g2);
        paintScore(g2);
        paintNextBlock(g2);
        paintHoldBlock(g2);

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

        int[][] nextBlockCoords = Block.getBlockCoordsByType(nextBlockQueue.peek());


        int rightSideOfField =  200+ (field[0].length+1) *cellWidht;

        int xMiniGrid = 0;
        int yMiniGrid = 0;



        g2.setFont(font);

        g2.drawString("Next:",rightSideOfField+25,cellHeight*7 + 22);

        g2.setColor(Color.lightGray);
        for (int i = 0; i < 4; i++)
        {
            xMiniGrid = 0;
            for (int j = 0; j < 3; j++)
            {
                g2.drawRect(rightSideOfField+cellWidht*xMiniGrid,cellHeight*8+cellHeight*yMiniGrid,cellWidht,cellHeight);
                xMiniGrid++;
            }
            yMiniGrid++;
        }

        g2.setColor(Block.getBlockColorByType(nextBlockQueue.peek())); //Set The Color matching to the Type



        for(int[] coord: nextBlockCoords)
        {
            y = coord[0];
            x = coord[1];

            g2.fillRect(rightSideOfField +(x+1)*cellWidht, cellHeight*8+(y+1)*cellHeight,cellWidht,cellHeight);
        }


        setPenColor(g2);
        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(rightSideOfField, cellHeight * 7, cellWidht*3, cellHeight*5);

        g2.drawLine(rightSideOfField, cellHeight * 8, rightSideOfField + cellWidht*3, cellHeight * 8);
    }

    protected void paintHoldBlock(Graphics2D g2)
    {
        int rightSideOfField =  200+ (field[0].length+1) *cellWidht;

        int xMiniGrid = 0;
        int yMiniGrid = 0;



        g2.setFont(font);

        g2.drawString("Hold:",rightSideOfField+25,cellHeight*13 + 22);

        g2.setColor(Color.lightGray);
        for (int i = 0; i < 4; i++)
        {
            xMiniGrid = 0;
            for (int j = 0; j < 3; j++)
            {
                g2.drawRect(rightSideOfField+cellWidht*xMiniGrid,cellHeight*14+cellHeight*yMiniGrid,cellWidht,cellHeight);
                xMiniGrid++;
            }
            yMiniGrid++;
        }

        g2.setColor(Block.getBlockColorByType(holdBlockType));


        if (holdBlockType != '\u0000') //Test if null
        {
            int[][] blockCoords = Block.getBlockCoordsByType(holdBlockType); //Just Translate Type To Coords and use Those later
            for(int[] coord: blockCoords)
            {
                y = coord[0];
                x = coord[1];

                g2.fillRect(rightSideOfField +(x+1)*cellWidht, cellHeight*14+(y+1)*cellHeight,cellWidht,cellHeight);
            }
        }



        setPenColor(g2);
        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(rightSideOfField, cellHeight * 13, cellWidht*3, cellHeight*5);

        g2.drawLine(rightSideOfField, cellHeight * 14, rightSideOfField + cellWidht*3, cellHeight * 14);
    }


    protected void paintGrid(Graphics2D g2)
    {


        y = 0;
        for(int[] line: field)
        {
            x = 0;
            for (int i = 0; i < line.length; i++)
            {
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawRect(x*cellWidht+200,y*cellHeight,cellWidht,cellHeight);
                if (line[x] != 0)
                {
                    switch (line[x])
                    {
                        case 1:
                            g2.setColor(Color.BLUE);
                            break;
                        case 2:
                            g2.setColor(Color.GREEN);
                            break;
                        case 3:
                            g2.setColor(Color.YELLOW);
                            break;
                        case 4:
                            g2.setColor(Color.ORANGE);
                            break;
                        case 5:
                            g2.setColor(Color.magenta);
                            break;
                        case 6:
                            g2.setColor(Color.CYAN);
                            break;
                        case 7:
                            g2.setColor(Color.RED);
                            break;
                    }
                    g2.fillRect(x*cellWidht+200,y*cellHeight,cellWidht,cellHeight);
                }
                x++;
            }
            y++;
        }


        setPenColor(g2);
        g2.setStroke(new BasicStroke(strokeWidht));

        g2.drawRect(200,0,cellWidht*field[0].length,cellHeight*field.length);
    }
}