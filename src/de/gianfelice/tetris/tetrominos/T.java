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
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import de.gianfelice.tetris.Field;

/**
 * The T tetromino.
 *
 * @author Matthias Gianfelice
 * @version 1.0.0.0
 */
public class T extends Tetromino {

    // ------------------------------ Attribute(s) -----------------------------
    // ----------------------------- Constructor(s) ----------------------------
    /**
     * Creates the tetromino and prepares it.
     * 
     * @param name         The name of the tetromino
     * @param assetManager The app's assetmanager
     * @param field        The field of the game
     */
    public T(String name, AssetManager assetManager, Field field) {
        super(name, assetManager, field, ColorRGBA.Magenta);
        Geometry box = createBox();
        registerBox(box, 0, 1, 3);
        registerBox(box, 1, 1, 2);
        registerBox(box, 2, 1, 2);
        registerBox(box, 3, 0, 2);
        for (int i = 0; i < 3; i++) {
            box = createBox();
            registerBox(box, 0, i, 2);
            registerBox(box, 1, 0, 3-i);
            registerBox(box, 2, i, 3);
            registerBox(box, 3, 1, i+1);
        }
    }

    // ------------------------------- Method(s) -------------------------------
}