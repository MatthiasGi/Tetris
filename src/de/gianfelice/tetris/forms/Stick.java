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
import java.util.ArrayList;
import java.util.List;


/**
 * TODO: Descriptive Text 
 *
 * @author  Matthias
 * @version 0.0.0.1
 */
public class Stick extends Form {

    // ------------------------------ Attribute(s) -----------------------------    
    private Node node;

    // ----------------------------- Constructor(s) ----------------------------
    public Stick(AssetManager assetManager) {
        super(assetManager);
        
        node = new Node("Stick");
        
        for (int i = 0; i < 4; i++) {
            Box box = new Box(Vector3f.ZERO, .45f, .45f, .45f);
            Geometry geom = new Geometry("Box#" + i, box);
            geom.setMaterial(getMaterial());
            node.attachChild(geom);
            geom.move(0, i - 1.5f, 0);
        }
    }

    // ------------------------------- Method(s) -------------------------------
    @Override
    public Node getForm() {
        return node;
    }

    @Override
    public ColorRGBA getColor() {
        return ColorRGBA.Cyan;
    }

}
