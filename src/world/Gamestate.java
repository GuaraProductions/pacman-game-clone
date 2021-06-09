package world;

public enum Gamestate {
    pause   (0),
    ingame (1),
    cutcene (2),
    gameover(-1),
	win(111);

    private int state;
    Gamestate(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}
