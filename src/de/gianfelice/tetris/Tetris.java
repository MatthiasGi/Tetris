/*
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/.
 */
package de.gianfelice.tetris;

// --------------------------------- Import(s) ---------------------------------
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Node;
import de.gianfelice.tetris.tetrominos.I;
import de.gianfelice.tetris.tetrominos.J;
import de.gianfelice.tetris.tetrominos.L;
import de.gianfelice.tetris.tetrominos.O;
import de.gianfelice.tetris.tetrominos.S;
import de.gianfelice.tetris.tetrominos.T;
import de.gianfelice.tetris.tetrominos.Tetromino;
import de.gianfelice.tetris.tetrominos.Z;

/**
 * A simple Tetris-game to play with the jMonkeyEngine 3.
 *
 * @author Matthias Gianfelice
 * @version 1.0.0.0
 */
public class Tetris extends SimpleApplication implements ActionListener {

    // ------------------------------ Attribute(s) -----------------------------
    /** Next tetromino to apply. */
    private Tetromino next;

    /** Current falling tetromino. */
    private Tetromino current;

    /** Node to display the next tetromino. */
    private Node nextNode;

    /** The passed time since the last reset. */
    private float time;

    /** The game's field. */
    private Field field;

    /** Id for naming. */
    private long id = 0;

    // ----------------------------- Constructor(s) ----------------------------
    // ------------------------------- Method(s) -------------------------------
    /**
     * Starts the application.
     *
     * @param args Arguments given by the command-line
     */
    public static void main(String[] args) {
        new Tetris().start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void simpleInitApp() {

        // Prepare field
        field = new Field(assetManager);
        rootNode.attachChild(field);

        // Node to display next tetromino
        nextNode = new Node("Next");
        nextNode.setLocalTranslation(12, 15, 0);
        rootNode.attachChild(nextNode);
        next = createTetromino();
        applyNext();

        // Position camera
        flyCam.setEnabled(false);
        cam.getLocation().set(6, 9, 30);
        cam.update();

        // Prepare keys
        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Drop", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener(this, "Rotate", "Left", "Right", "Drop");
        
        // Prepare Music
        AudioNode music = new AudioNode(assetManager, "Sounds/music.wav");
        music.setLooping(true);
        music.play();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void simpleUpdate(float tpf) {
        
        // Read time and react, if one turn is over
        time += tpf;
        if (time < .5f) return;

        // Check, if current tetromino reached the bottom
        if (!current.validTranslate(0, -1)) {
            rootNode.detachChild(current);
            field.save(current);
            field.checkRows();
            applyNext();
            return;
        } else current.move(0, -1, 0);
        
        // Reset timer
        time = 0;

    }

    /**
     * Connects the current falling tetromino with the field and moves the next
     * into falling mode. Also chooses the next tetromino.
     */
    private void applyNext() {
        current = next;
        nextNode.detachAllChildren();
        current.setLocalTranslation(3, 15, 0);
        if (!current.validTranslate(0, 0)) {
            stop();
            return;
        }
        rootNode.attachChild(current);
        next = createTetromino();
        nextNode.attachChild(next);
    }

    /**
     * Internally used to create a random new tetromino.
     * 
     * @return The newly created tetromino
     */
    private Tetromino createTetromino() {
        switch ((int) (Math.random() * 7)) {
            case 0:
                return new I(getName(), assetManager, field);
            case 1:
                return new J(getName(), assetManager, field);
            case 2:
                return new L(getName(), assetManager, field);
            case 3:
                return new O(getName(), assetManager, field);
            case 4:
                return new S(getName(), assetManager, field);
            case 5:
                return new T(getName(), assetManager, field);
            default:
                return new Z(getName(), assetManager, field);
        }
    }
    
    /**
     * Generates the name for a new tetromino.
     * 
     * @return A unique name for a new tetromino
     */
    private String getName() {
        return "Tetromino#" + id++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (isPressed) return;
        switch (name) {
            case "Drop":
                current.drop();
                break;
            case "Left":
                if (current.validTranslate(-1, 0)) current.move(-1, 0, 0);
                break;
            case "Right":
                if (current.validTranslate(1, 0)) current.move(1, 0, 0);
                break;
            case "Rotate":
                if (current.validRotate()) current.rotate();
        }
    }

}
