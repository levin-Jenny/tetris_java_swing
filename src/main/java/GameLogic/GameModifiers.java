package GameLogic;

public class GameModifiers {
    boolean garbage;
    Field field;

    public GameModifiers(boolean garbage) {
        this.garbage = garbage;
    }

    public void setGameModifiersAttributes(Field field)
    {
        this.field = field;
    }

    public void garbage()
    {

    }
}
