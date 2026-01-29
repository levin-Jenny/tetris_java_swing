package UI;

import GameLogic.Field;
import GameLogic.Fieldcopy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UI extends JFrame {
    private Field field;
    private Fieldcopy fieldcopy;
    private DrawPanel drawPanel;
    private boolean darkmode = false;


//    ArrayList<Field> fields = new ArrayList<Field>();
//    ArrayList<Fieldcopy> fieldCopies = new ArrayList<Fieldcopy>();


    public UI()
    {
    }

    public void setUpUI()
    {
        initComponents();
    }

    public void setUIattributes(Field field, Fieldcopy fieldcopy, DrawPanel drawPanel,  boolean darkmode)
    {
        this.field = field;
        this.fieldcopy = fieldcopy;
        this.darkmode = darkmode;
        this.drawPanel = drawPanel;
    }

    public void turnDarkModeOn()
    {
        darkmode = true;
        drawPanel.turnDarkModeOn();
    }

    public void paintFieldCopy() {
        drawPanel.paintField(fieldcopy.getField());
    }

    public void paintField() {
        drawPanel.paintField(field.getField());
    }


    public void setHoldBlockCoords(int[][] holdBlockCoords, char nextBlockType)
    {
        drawPanel.updateHoldBlock(holdBlockCoords, nextBlockType);
    }

    public void setLevel(int level) {
        drawPanel.updateLevel(level);
    }

    public void setScore(int score) {
        drawPanel.updateScore(score);
    }

    private void initComponents() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 1500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        drawPanel.setDrawPanelfield(field.getField());


        if(darkmode)
        {
            turnDarkModeOn();
            drawPanel.setBackground(Color.black);
        }

        add(drawPanel);


        InputMap inputMap = drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = drawPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "RotateClockwise");
        actionMap.put("RotateClockwise", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.rotateBlockClockwise();
                    }
                }
        );
        inputMap.put(KeyStroke.getKeyStroke( "A"), "RotateCounterClockwise");
        actionMap.put("RotateCounterClockwise", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.rotateBlockCounterClockwise();
                    }
                }
        );

        inputMap.put(KeyStroke.getKeyStroke("S"), "FlipBlock");
        actionMap.put("FlipBlock", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.flipBlock();
                    }
                }
        );

        inputMap.put(KeyStroke.getKeyStroke("C"), "HoldBlock");
        actionMap.put("HoldBlock", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.holdBlock();
                    }
                }
        );


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Links");
        actionMap.put("Links", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.moveLeft();
                    }
                }
        );

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Right");
        actionMap.put("Right", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.moveRight();
                    }
                }
        );

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Down");
        actionMap.put("Down", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.moveBlockDown();
                    }
                }
        );

        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "DashDown");
        actionMap.put("DashDown", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fieldcopy.dashDown();
                    }
                }
        );


    }


}



