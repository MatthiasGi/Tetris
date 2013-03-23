/*
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/.
 */
package de.gianfelice.tetris.tetrominos;

// --------------------------------- Import(s) ---------------------------------
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import de.gianfelice.tetris.Field;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A tetromino is the shape that falls and consists out of 4 boxes.
 *
 * @author Matthias Gianfelice
 * @version 0.1.0.0
 */
public abstract class Tetromino extends Node {

    // ------------------------------ Attribute(s) -----------------------------
    /** The shared material by all boxes. */
    private Material mat;

    /** The field of the game for collision-checking. */
    private Field field;

    /** The different rotation states of the tetromino. */
    private List<Geometry[][]> boxes;

    /** The current state. */
    private int state = 0;

    /** ID for naming. */
    private int id = 0;

    // ----------------------------- Constructor(s) ----------------------------
    /**
     * Creates the tetromino and prepares it for the game.
     *
     * @param name         The name for the tetromino
     * @param assetManager The app's assetmanager
     * @param field        The field of the game
     * @param color        A color for the contained boxes
     */
    public Tetromino(String name, AssetManager assetManager, Field field,
            ColorRGBA color) {
        super(name);
        this.field = field;
        boxes = new ArrayList<>();
        boxes.add(new Geometry[4][4]);
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
    }

    // ------------------------------- Method(s) -------------------------------
    /**
     * Checks, whether a new offset would be a valid position for the whole
     * tetromino.
     *
     * @param offsetX The new x-offset
     * @param offsetY The new y-offset
     * @return Whether the new position would be valid
     */
    public boolean validTranslate(int offsetX, int offsetY) {
        Point p = getPosition();
        p.x += offsetX;
        p.y += offsetY;
        return valid(boxes.get(state), p);
    }

    /**
     * Checks, whether rotating the tetromino would be valid.
     *
     * @return Whether rotating would be a valid action
     */
    public boolean validRotate() {
        return valid(boxes.get(getNextState()), getPosition());
    }

    /**
     * Changes the current state to the next one.
     */
    public void rotate() {
        state = getNextState();
        update();
    }

    /**
     * Returns the boxes that are assigned to the current state.
     * 
     * @return Current active boxes
     */
    public Geometry[][] getBoxes() {
        return boxes.get(state);
    }

    /**
     * Drops the tetromino to the bottom.
     */
    public void drop() {
        int n = 0;
        while (validTranslate(0, n - 1)) n--;
        move(0, n, 0);
    }

    /**
     * Calculates the position of the tetromino upon the field by fetching the
     * local translation.
     *
     * @return The position of the tetromino
     */
    public Point getPosition() {
        Point p = new Point();
        p.x = (int) getLocalTranslation().getX();
        p.y = (int) getLocalTranslation().getY();
        return p;
    }

    /**
     * Creates, prepares and attaches a box. Note, that it isn't yet registred
     * and therefore not aligned.
     *
     * @return The newly created and prepared box.
     * @see #registerBox(Geometry, int, int, int)
     */
    protected Geometry createBox() {
        Quad box = new Quad(.9f, .9f);
        Geometry geom = new Geometry(getName() + "#" + id++, box);
        geom.setMaterial(mat);
        attachChild(geom);
        return geom;
    }

    /**
     * Registres a box and binds a position to a given state. So that changing
     * the state will also align the box.
     *
     * @param geom  The box which must be created by {@link #createBox()}
     * @param state The state to bind the box to
     * @param x     The corresponding x-coordinate
     * @param y     The corresponding y-coordinate
     * @throws IllegalArgumentException  If the box was not created via
     *                                   {@link #createBox()} or the given
     *                                   coordinates do not fit inside a 4x4
     * @throws IndexOutOfBoundsException The state must be inside the valid
     *                                   range of 0 and the current size
     */
    protected void registerBox(Geometry geom, int state, int x, int y) {
        if (!hasChild(geom)) throw new IllegalArgumentException(
                    "Box is not created by this tetromino!");
        if (state < 0 || boxes.size() < state)
            throw new IndexOutOfBoundsException("State doesn't fit in!");
        if (x < 0 || y < 0 || x >= 4 || y >= 4)
            throw new IllegalArgumentException("The tertromino is a 4x4!");

        if (boxes.size() == state) boxes.add(new Geometry[4][4]);
        boxes.get(state)[x][y] = geom;
        if (this.state == state) update();
    }

    /**
     * Rotating the tetromino just changes the state. This method gets the next
     * state on the list.
     *
     * @return Next rotating-state on the list
     */
    private int getNextState() {
        int n = state + 1;
        if (n >= boxes.size()) return 0;
        return n;
    }
    
    /**
     * Validates whether a new array of boxes would fit with a given offset.
     * 
     * @param boxes The array with boxes
     * @param p     The offset
     * @return Whether the new position would be valid
     */
    private boolean valid(Geometry[][] boxes, Point p) {
        for (int x = 0, pX = p.x; x < 4; x++, pX++) {
            for (int y = 0, pY = p.y; y < 4; y++, pY++) {
                if (boxes[x][y] == null) continue;
                if (field.isOccupied(pX, pY)) return false;
            }
        }
        return true;
    }
    
    /**
     * Updates the position of each contained box.
     */
    private void update() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Geometry box = boxes.get(state)[x][y];
                if (box == null) continue;
                box.setLocalTranslation(x + .05f, y + .05f, 0);
            }
        }
    }

}
