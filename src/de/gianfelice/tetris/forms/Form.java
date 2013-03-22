/*
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * 
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/.
 */

package de.gianfelice.tetris.forms;

// --------------------------------- Import(s) ---------------------------------
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;


/**
 * Abstract class which provides a convenient way of working with different
 * forms onscreen.
 *
 * @author  Matthias Gianfelice
 * @version 0.1.0.0
 */
public abstract class Form {

    // ------------------------------ Attribute(s) -----------------------------
    /** Should provide the {@link AssetManager} of the application. */
    protected AssetManager assetManager;

    // ----------------------------- Constructor(s) ----------------------------
    /**
     * Creates the form and saves the {@link AssetManager} for later use.
     * 
     * @param assetManager The assetmanager of the application
     */
    public Form(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    // ------------------------------- Method(s) -------------------------------
    /**
     * Returns the node which itselfs contains all boxes of the form.
     * 
     * @return The node of the form
     */
    public abstract Node getForm();

}
