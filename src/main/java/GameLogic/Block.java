package GameLogic;

import java.awt.*;

public abstract class Block {

    private static final int[][] I_BLOCK = {{0, 0}, {-1, 0}, {1, 0}, {2, 0}};

    private static final int[][] T_BLOCK = {{0, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static final int[][] O_BLOCK = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};

    private static final int[][] S_BLOCK = {{0, 0}, {1, 0}, {0, -1}, {1, 1}};

    private static final int[][] Z_BLOCK = {{0, 0}, {1, 0}, {0, 1}, {1, -1}};

    private static final int[][] J_BLOCK = {{0, 0}, {1, 0}, {-1, -1}, {-1, 0}};

    private static final int[][] L_BLOCK = {{0, 0}, {1, 0}, {-1, 1}, {-1, 0}};


    public static int[][] getBlockCoordsByType(char type) {
        return switch (type) {
            case 'i' -> I_BLOCK;
            case 't' -> T_BLOCK;
            case 'o' -> O_BLOCK;
            case 's' -> S_BLOCK;
            case 'z' -> Z_BLOCK;
            case 'j' -> J_BLOCK;
            case 'l' -> L_BLOCK;
            default -> throw new IllegalArgumentException("Invalid Block type");
        };
    }


    public static Color getBlockColorByType(char type) {
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

    public static Color getColorByInt(int type)
    {
        return switch (type)
        {
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 3 -> Color.YELLOW;
            case 4 -> Color.ORANGE;
            case 5 -> Color.MAGENTA;
            case 6 -> Color.CYAN;
            case 7 -> Color.RED;
            case 8 -> Color.lightGray;
            default -> null;
        };
    }

    public static int getIntByType(char type) {
        return switch (type) {
            case 'i' -> 1;
            case 't' -> 2;
            case 'j' -> 3;
            case 'l' -> 4;
            case 's' -> 5;
            case 'z' -> 6;
            case 'o' -> 7;
            default -> throw new IllegalArgumentException("Invalid Int type");
        };
    }

    private enum RotationKinds {
        clockwise,
        counterclockwise,
        flip
    }

    public static int[][] rotateBlock(RotationKinds direction, char blockType, int[][] blockCoords) {
        int[][] copyBlockCoords = new int[4][2];

        for (int i = 0; i < 4; i++) {
            copyBlockCoords[i] = blockCoords[i].clone();
        }
        if (blockType == 'o') { //o block shouldn't be rotated as it else would just shift around
            return copyBlockCoords;
        }

        switch (direction) {
            case clockwise:
                for (int[] singlePoint : copyBlockCoords) {
                    int x = singlePoint[1];
                    int y = singlePoint[0];

                    singlePoint[0] = x * -1;
                    singlePoint[1] = y;
                }
                break;
            case counterclockwise:
                for (int[] singlePoint : copyBlockCoords) {
                    int x = singlePoint[1];
                    int y = singlePoint[0];

                    singlePoint[0] = x;
                    singlePoint[1] = y * -1;
                }
                break;
            case flip:
                for (int[] singlePoint : copyBlockCoords) {
                    int x = singlePoint[1];
                    int y = singlePoint[0];

                    singlePoint[0] = y * -1;
                    singlePoint[1] = x * -1;
                }
                break;

        }
        return copyBlockCoords;
    }

    public static int[][] rotateBlockClockwise(int[][] blockCoords, char blockType) {

        return rotateBlock(RotationKinds.clockwise, blockType, blockCoords);

    }

    public static int[][] rotateBlockCounterClockwise(int[][] blockCoords, char blockType) {
        return rotateBlock(RotationKinds.counterclockwise, blockType, blockCoords);

    }

    public static int[][] flipBlock(int[][] blockCoords, char blockType) {
        return rotateBlock(RotationKinds.flip, blockType, blockCoords);

    }
}
