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
 * The Z tetromino.
 *
 * @author Matthias Gianfelice
 * @version 0.1.0.0
 */
public class Z extends Tetromino {

    // ------------------------------ Attribute(s) -----------------------------
    // ----------------------------- Constructor(s) ----------------------------
    /**
     * Creates the tetromino and prepares it.
     * 
     * @param name         The name of the tetromino
     * @param assetManager The app's assetmanager
     * @param field        The field of the game
     */
    public Z(String name, AssetManager assetManager, Field field) {
        super(name, assetManager, field, ColorRGBA.Red);
        for (int i = 0; i < 2; i++) {
            Geometry box = createBox();
            registerBox(box, 0, i, 3);
            registerBox(box, 1, 2, 3-i);
            box = createBox();
            registerBox(box, 0, i+1, 2);
            registerBox(box, 1, 1, 2-i);
        }
    }

    // ------------------------------- Method(s) -------------------------------
}