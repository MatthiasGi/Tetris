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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;


/**
 * The basic stick, straight, long or I of the classic Tetris-game.
 *
 * @author  Matthias Gianfelice
 * @version 0.1.0.0
 */
public class Stick extends Form {

    // ------------------------------ Attribute(s) -----------------------------
    /** The node, containing all included boxes. */
    private Node node = new Node("Stick");
    
    /** The color of the form itself. */
    public static final ColorRGBA color = ColorRGBA.Cyan;
    
    /** The material, static to save resources. */
    private static Material mat;

    // ----------------------------- Constructor(s) ----------------------------
    /**
     * {@inheritDoc}
     */
    public Stick(AssetManager assetManager) {
        super(assetManager);
        
        for (int i = 0; i < 4; i++) {
            Box box = new Box(Vector3f.ZERO, .45f, .45f, .45f);
            Geometry geom = new Geometry("Box#" + i, box);
            geom.setMaterial(getMaterial());
            node.attachChild(geom);
            geom.move(0, i - 1.5f, 0);
        }
    }

    // ------------------------------- Method(s) -------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public Node getForm() {
        return node;
    }
    
    /**
     * Creates the material of the form if not yet done.
     * 
     * @return The material of the form
     */
    public final Material getMaterial() {
        if (mat == null) {
            mat = new Material(assetManager,
                    "Common/MatDefs/Light/Lighting.j3md");
            mat.setBoolean("UseMaterialColors", true);
            mat.setColor("Ambient", color);
            mat.setColor("Diffuse", color);
        }
        
        return mat;
    }

}
