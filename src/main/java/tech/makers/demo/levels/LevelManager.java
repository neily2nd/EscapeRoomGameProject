package tech.makers.demo.levels;

import javafx.scene.canvas.GraphicsContext;
import tech.makers.demo.EscapeRoomGame;
import tech.makers.demo.assets.Door;
import tech.makers.demo.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelManager {
    private Level[] levels;
    private int currentLevelIndex;
    private GraphicsContext gc;
    private EscapeRoomGame game;

    public LevelManager(GraphicsContext gc, EscapeRoomGame game) {
        this.gc = gc;
        this.game = game;
        this.currentLevelIndex = 0;
        initializeLevels();
    }

    protected void initializeLevels() {
        // Create players, puzzles, and doors for each level
        Player player1 = new Player(100, 100);
        Door door1 = new Door(600, 400);

        List<Puzzle> puzzleList1 =  new ArrayList<>();
        Puzzle puzzle1 = new Puzzle(300, 300, "What is 2 + 2?", "4");
        puzzleList1.add(puzzle1);
        Level level1 = new Level(player1, puzzleList1, door1);


        Player player2 = new Player(100, 100);
        Door door2 = new Door(600, 400);

        List<Puzzle> puzzleList2 =  new ArrayList<>();
        Puzzle puzzle2 = new Puzzle(300, 300, "What is the capital of France?", "Paris");
        Puzzle puzzle3 = new Puzzle(400, 150, "Say Hi", "Hi");
        puzzleList2.add(puzzle2);
        puzzleList2.add(puzzle3);
        Level level2 = new Level(player2, puzzleList2, door2);

        levels = new Level[]{level1, level2};
    }

    public Level getCurrentLevel() {
        return levels[currentLevelIndex];
    }

    public void loadNextLevel() {
        if (currentLevelIndex < levels.length - 1) {
            currentLevelIndex++;
            game.setupNextLevel(); // Ensure the game setup for the next level
        } else {
            // Handle the end of the game
            System.out.println("You have completed all levels!");
        }
    }

    public void render() {
        getCurrentLevel().render(gc);
    }

    public void update() {
        Level currentLevel = getCurrentLevel();
        currentLevel.update();
        if (currentLevel.isCompleted()) {
            game.completeLevel();
        }
    }

    public void completeLevel() {
        game.completeLevel();
    }

    protected void setLevels(Level[] levels) {
        this.levels = levels;
    }
}