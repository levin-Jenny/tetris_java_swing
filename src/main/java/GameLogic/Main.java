package GameLogic;

import UI.UI;
import UI.DrawPanel;

public class Main {
    static void main() {
        new SetUp();
    }
}

class SetUp
{
    public SetUp() {
        //Create Objects
        Field field = new Field(10,22);
        Fieldcopy fieldcopy = new Fieldcopy();
        GameStats gameStats = new GameStats();
        Gravity gravity = new Gravity();
        SavedBlocks savedBlocks = new SavedBlocks();
        GameModifiers gameModifiers = new GameModifiers();
        UI ui = new UI();
        DrawPanel drawPanel = new DrawPanel();

        //Set Attributes of different Objects
        gameModifiers.setGameModifiersAttributes(field, false);
        field.setFieldAtrributes(ui, fieldcopy, gameStats, gravity);
        fieldcopy.setFieldcopyAttributes(savedBlocks, field, ui, gravity, gameStats);
        gameStats.setGameStatsAttributes(gravity, ui, gameModifiers);
        gravity.setGravityAttributes(fieldcopy, gameModifiers, ui);
        savedBlocks.setSavedBlocksAttributes(fieldcopy, true,1, drawPanel, true); //If seedActive,You can set the Seed for the game, else the seed Will be "Random"
        ui.setUIattributes(field,fieldcopy, drawPanel, true); //you can set here if u want darkmode

        //Setup Objects when needed
        savedBlocks.setupSavedBlock();//setup Saved blocks w/ setupSavedBlocks();
        fieldcopy.setUpFieldcopy();     //getNewBlock();, updateFieldCopy(); in Fieldcopy w/ setupFieldcopy
        ui.setUpUI();   //setup UI (to start initComponents();)
        gravity.start();        //Start Gravity
    }
}
