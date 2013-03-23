/*
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/.
 */
package de.gianfelice.tetris;

// --------------------------------- Import(s) ---------------------------------
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import de.gianfelice.tetris.tetrominos.Tetromino;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Inside the field, all tetrominos are collected and displayed.
 *
 * @author Matthias Gianfelice
 * @version 0.0.0.1
 */
public class Field extends Node {

    // ------------------------------ Attribute(s) -----------------------------
    /** The field inside the borders. */
    private Geometry[][] field;

    /** The material for the border-rectangles. */
    private Material mat;

    public static final int HEIGHT = 19;

    public static final int WIDTH = 10;

    // ----------------------------- Constructor(s) ----------------------------
    /**
     * Creates and prepares the field.
     *
     * @param assetManager The app's assetmanager
     */
    public Field(AssetManager assetManager) {
        super("Field");
        field = new Geometry[WIDTH][HEIGHT];
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.LightGray);

        for (int i = 0; i <= HEIGHT; i++) {
            createBox(-1, i - 1);
            createBox(10, i - 1);
            if (i < WIDTH) createBox(i, -1);
        }
    }

    // ------------------------------- Method(s) -------------------------------
    /**
     * Checks, whether a given field is already occupied by another box.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return Whether the field is already occupied
     */
    public boolean isOccupied(int x, int y) {
        if (x < 0 || x >= WIDTH) return true;
        if (y < 0 || y >= HEIGHT) return true;
        return field[x][y] != null;
    }

    /**
     * Saves the currently fallen brick inside the field and displays it
     * properly.
     *
     * @param current The tetromino to save
     */
    public void save(Tetromino current) {
        Geometry[][] boxes = current.getBoxes();
        Point p = current.getPosition();

        for (int x = 0, pX = p.x; x < 4; x++, pX++) {
            for (int y = 0, pY = p.y; y < 4; y++, pY++) {
                if (boxes[x][y] == null) continue;
                field[pX][pY] = boxes[x][y].clone();
                field[pX][pY].setLocalTranslation(pX + .05f, pY + .05f, 0);
                attachChild(field[pX][pY]);
            }
        }
    }

    public void checkRows() {
        for (int y = 0; y < HEIGHT; y++) {
            boolean complete = true;
            for (int x = 0; x < WIDTH; x++) {
                if (field[x][y] != null) continue;
                complete = false;
                break;
            }
            if (complete) removeLine(y--);
        }
    }

    private void removeLine(int y) {
        for (int x = 0; x < WIDTH; x++) detachChild(field[x][y]);
        for (; y < HEIGHT - 1; y++) {
            for (int x = 0; x < WIDTH; x++) {
                field[x][y] = field[x][y + 1];
                if (field[x][y] != null) field[x][y].move(0, -1, 0);
            }
        }
        for (int x = 0; x < WIDTH; x++) field[x][y] = null;
    }

    /**
     * Creates a border-box with the default material and positions it.
     *
     * @param x The x-coordinate of the box
     * @param y The y-coordinate of the box
     */
    private void createBox(int x, int y) {
        Quad quad = new Quad(.9f, .9f);
        Geometry geom = new Geometry(String.format("Field[%d,%d]", x, y), quad);
        geom.setMaterial(mat);
        geom.setLocalTranslation(x + .05f, y + 0.05f, 0);
        attachChild(geom);
    }

}
