package tech.makers.demo.levelManagement.levels.LevelThree.interactions;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import tech.makers.demo.levelManagement.Interaction;

public class EddieInteraction3 extends Interaction {
    private final String defaultMessage = "I have your new MacBook credentials!";
    private final String username = "newUser"; // Example username
    private final String password = "newPass123"; // Example password
    public Image idleSpriteSheet;
    public Image[] idleRightFrames;
    public Image[] idleUpFrames;
    public Image[] idleLeftFrames;
    public Image[] idleDownFrames;
    public String direction = "down";
    public int currentFrame = 0;
    public long lastFrameTime = 0;
    public static final int FRAME_DURATION = 150;

    public EddieInteraction3(double x, double y, String imagePath) {
        super(x, y, imagePath);

        this.idleSpriteSheet = new Image(getClass().getResource(imagePath).toExternalForm());
        this.idleRightFrames = loadFrames(idleSpriteSheet, 0);
        this.idleUpFrames = loadFrames(idleSpriteSheet, 6);
        this.idleLeftFrames = loadFrames(idleSpriteSheet, 12);
        this.idleDownFrames = loadFrames(idleSpriteSheet, 18);
    }

    public Image[] loadFrames(Image spriteSheet, int startFrame) {
        Image[] frames = new Image[6];
        int spriteWidth = 16;
        int spriteHeight = 32;
        for (int i = 0; i < 6; i++) {
            int x = (startFrame + i) * spriteWidth;
            int y = 0;
            frames[i] = new WritableImage(spriteSheet.getPixelReader(), x, y, spriteWidth, spriteHeight);
        }
        return frames;
    }

    @Override
    public void render(GraphicsContext gc) {
        Image[] currentFrames;
        switch (direction) {
            case "up":
                currentFrames = idleUpFrames;
                break;
            case "down":
                currentFrames = idleDownFrames;
                break;
            case "left":
                currentFrames = idleLeftFrames;
                break;
            case "right":
                currentFrames = idleRightFrames;
                break;
            default:
                currentFrames = idleDownFrames;
        }
        Image currentImage = currentFrames[currentFrame];
        gc.drawImage(currentImage, getX(), getY(), 25, 50);
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= FRAME_DURATION) {
            currentFrame = (currentFrame + 1) % 6;
            lastFrameTime = currentTime;
        }
    }

    @Override
    public void interact() {
        showAlert("Eddie: Your new username is " + username + " and password is " + password);
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tip");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
