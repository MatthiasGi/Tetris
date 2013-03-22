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
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import de.gianfelice.tetris.forms.Stick;

/**
 * A simple Tetris-game to play with the jMonkeyEngine 3.
 *
 * @author Matthias Gianfelice
 * @version 0.0.0.1
 */
public class Tetris extends SimpleApplication {

    // ------------------------------ Attribute(s) -----------------------------
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
        Stick stick = new Stick(assetManager);
        rootNode.attachChild(stick.getForm());
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1, 0, -2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        flyCam.setEnabled(false);
        cam.getLocation().setZ(50);
        cam.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }
}
