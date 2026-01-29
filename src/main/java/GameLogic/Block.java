package GameLogic;

import java.awt.*;
import java.util.Random;

public abstract class Block {


    public static int[][] getBlockCoordsByType(char type)
    {
        int[][] blockCoords = new int[4][2];

        switch(type)
        {
            case 'i':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {-1,0};
                blockCoords[2] = new int[] {1,0};
                blockCoords[3] = new int[] {2,0};
                break;
            case 't':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {1,0};
                blockCoords[2] = new int[] {0,-1};
                blockCoords[3] = new int[] {0,1};
                break;
            case 'o':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {1,0};
                blockCoords[2] = new int[] {0,1};
                blockCoords[3] = new int[] {1,1};
                break;
            case 's':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {1,0};
                blockCoords[2] = new int[] {0,-1};
                blockCoords[3] = new int[] {1,1};
                break;
            case 'z':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {1,0};
                blockCoords[2] = new int[] {0,1};
                blockCoords[3] = new int[] {1,-1};
                break;
            case 'j':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {1,0};
                blockCoords[2] = new int[] {-1,-1};
                blockCoords[3] = new int[] {-1,0};
                break;
            case 'l':
                blockCoords[0] = new int[] {0,0};
                blockCoords[1] = new int[] {1,0};
                blockCoords[2] = new int[] {-1,1};
                blockCoords[3] = new int[] {-1,0};
                break;
            default:
                throw new IllegalArgumentException("Invalid Block type");
        }

        return blockCoords;
    }


    public static Color getBlockColorByType(char type)
    {
        return switch (type) {
            case 'i' -> Color.BLUE;
            case 't' -> Color.GREEN;
            case 'j' -> Color.YELLOW;
            case 'l' -> Color.ORANGE;
            case 's' -> Color.magenta;
            case 'z' -> Color.CYAN;
            case 'o' -> Color.RED;
            default -> null;
        };
    }




    public static int[][] rotateBlockClockwise(int[][] blockCoords, char blockType)
    {

        int[][] copyBlockCoords = new int[4][2];

        for (int i = 0; i < 4; i++)
        {
            copyBlockCoords[i] = blockCoords[i].clone();
        }

        switch(blockType)
        {
            case 'i':
            case 't':
            case 'j':
            case 'l':
            case 's':
            case 'z':
                for(int[] singlePoint: copyBlockCoords) {
                    int x = singlePoint[1];
                    int y = singlePoint[0];

                    singlePoint[0] = x * -1;
                    singlePoint[1] = y;
                }
                break;
            case 'o':
                break;


        }
        return copyBlockCoords;
    }

    public static int[][] rotateBlockCounterClockwise(int[][] blockCoords, char blockType)
    {
        int[][] copyBlockCoords = new int[4][2];

        for (int i = 0; i < 4; i++)
        {
            copyBlockCoords[i] = blockCoords[i].clone();
        }

        switch(blockType)
        {
            case 'i':
            case 't':
            case 'j':
            case 'l':
            case 's':
            case 'z':
                for(int[] singlePoint: copyBlockCoords) {
                    int x = singlePoint[1];
                    int y = singlePoint[0];

                    singlePoint[0] = x;
                    singlePoint[1] = y * -1;
                }
                break;
            case 'o':
                break;


        }
        return copyBlockCoords;
    }

    public static int[][] flipBlock(int[][] blockCoords, char blockType)
    {
        int[][] copyBlockCoords = new int[4][2];

        for (int i = 0; i < 4; i++)
        {
            copyBlockCoords[i] = blockCoords[i].clone();
        }
        switch(blockType)
        {
            case 'i':
            case 't':
            case 'j':
            case 'l':
            case 's':
            case 'z':
                for(int[] singlePoint: copyBlockCoords) {
                    int x = singlePoint[1];
                    int y = singlePoint[0];

                    singlePoint[0] = y * -1;
                    singlePoint[1] = x * -1;
                }
                break;
            case 'o':
                break;
        }
        return copyBlockCoords;
    }



}
