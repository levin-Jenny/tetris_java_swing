package GameLogic;

import UI.UI;

public class Main {
    static void main()
    {
        //Create Objects
        Field field = new Field(10,22);
        Fieldcopy fieldcopy = new Fieldcopy();
        GameStats gameStats = new GameStats();
        Gravity gravity = new Gravity();
        SavedBlocks savedBlocks = new SavedBlocks();
        UI ui = new UI();

        //Set Attributes of different Objects
        field.setFieldAtrributes(ui, fieldcopy, gameStats);
        fieldcopy.setFieldcopyAttributes(savedBlocks, field, ui, gravity, gameStats);
        gameStats.setGameStatsAttributes(gravity, ui);
        gravity.setGravityAttributes(fieldcopy);
        savedBlocks.setSavedBlocksAttributes(fieldcopy, ui, 0); //You can set the Seed for the game, when you set the seed 0, a random seed will be used.
        ui.setUIattributes(field,fieldcopy,true); //you can set here if u want darkmode

        //Setup Objects when needed
        savedBlocks.setupSavedBlock();//setup Saved blocks w/ setupSavedBlocks();
        fieldcopy.setUpFieldcopy();     //getNewBlock();, updateFieldCopy(); in Fieldcopy w/ setupFieldcopy
        ui.setUpUI();   //setup UI (to start initComponents();)
        gravity.start();        //Start Gravity

    }
}
