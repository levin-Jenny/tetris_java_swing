import UI.UI;
import GameLogic.Field;
import GameLogic.Fieldcopy;
import GameLogic.Gravity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Tests {
    Gravity gravity;
    Fieldcopy fieldcopy;
    Field field;
    UI ui;

    @BeforeEach
    void setUp() {
        this.field = new Field(5, 4);
        this.fieldcopy = field.getFieldcopy();
        this.gravity = fieldcopy.getGravity();
        this.ui = fieldcopy.getUi();
        gravity.stop();
    }

    @Test
    public void testGridLineClear() {
        int[][] testGrid = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        int[][] testGrid2 = new int[4][5];
        field.setField(testGrid);
        field.updateField();
        assert (Arrays.deepEquals(testGrid, testGrid2));


    }

    @Test
    public void testGridMoveLinesDown() {
        int[][] testGrid = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1}
        };
        int[][] testGrid2 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0}
        };
        field.setField(testGrid);
        field.updateField();
        assert (Arrays.deepEquals(testGrid, testGrid2));
    }


}
