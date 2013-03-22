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
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;


/**
 * TODO: Descriptive Text 
 *
 * @author  Matthias
 * @version 0.0.0.1
 */
public abstract class Form {

    // ------------------------------ Attribute(s) -----------------------------
    protected AssetManager assetManager;
    private static Material mat;

    // ----------------------------- Constructor(s) ----------------------------
    public Form(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    // ------------------------------- Method(s) -------------------------------
    public abstract Node getForm();
    public abstract ColorRGBA getColor();
    
    public Material getMaterial() {
        if (mat == null) {
            mat = new Material(assetManager,
                    "Common/MatDefs/Light/Lighting.j3md");
            mat.setBoolean("UseMaterialColors", true);
            mat.setColor("Ambient", getColor());
            mat.setColor("Diffuse", getColor());
        }
        return mat;
    }

}
