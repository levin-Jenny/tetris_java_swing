package GameLogic;

import java.util.ArrayList;
import java.util.Random;

public abstract class BlocksQueue {



    public static char randomChar(Random rand)
    {

        char[] characters = {'i','o','s','z','t','l','j'};
        //char[] characters = {'i'};
        return characters[rand.nextInt(characters.length)];

    }

    public static char getBlock(Random rand)
    {

        return randomChar(rand);
    }
}
