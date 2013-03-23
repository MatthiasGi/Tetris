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
import de.gianfelice.tetris.Field;

/**
 * The O tetromino.
 *
 * @author Matthias Gianfelice
 * @version 1.0.0.0
 */
public class O extends Tetromino {

    // ------------------------------ Attribute(s) -----------------------------
    // ----------------------------- Constructor(s) ----------------------------
    /**
     * Creates the tetromino and prepares it.
     * 
     * @param name         The name of the tetromino
     * @param assetManager The app's assetmanager
     * @param field        The field of the game
     */
    public O(String name, AssetManager assetManager, Field field) {
        super(name, assetManager, field, ColorRGBA.Yellow);
        registerBox(createBox(), 0, 1, 2);
        registerBox(createBox(), 0, 2, 2);
        registerBox(createBox(), 0, 1, 3);
        registerBox(createBox(), 0, 2, 3);
    }

    // ------------------------------- Method(s) -------------------------------
}