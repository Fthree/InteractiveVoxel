package Util;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

public class CubeUtil {
    public Geometry createCube(AssetManager assetManager, float posX, float posY, float posZ) {
        Box cubeMesh = new Box(0.1f, 0.1f, 0.1f);
        Geometry cubeGeo = new Geometry("Simple Box", cubeMesh);
        cubeGeo.setLocalTranslation(new Vector3f(posX, posY, posZ));
        Material cubeMat = new Material(assetManager,
        "Common/MatDefs/Misc/Unshaded.j3md");
        cubeMat.setColor("Color", ColorRGBA.randomColor());
        cubeGeo.setMaterial(cubeMat);
        return cubeGeo;
    }

    public Geometry createOutlineCube(AssetManager assetManager, Vector3f position) {
        Box cubeMesh = new Box(0.1f, 0.1f, 0.1f);
        Geometry cubeGeo = new Geometry("Outline Box", cubeMesh);
        cubeGeo.setLocalTranslation(position);
        Material cubeMat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        cubeMat.getAdditionalRenderState().setWireframe(true);
        cubeMat.setColor("Color", ColorRGBA.White);
        cubeGeo.setMaterial(cubeMat);
        return cubeGeo;
    }

    public List<Geometry> createCubes(AssetManager assetManager, Vector3f size) {
        List<Geometry> ret = new ArrayList<>();

        for (int i = 0; i != size.z; i++) { //Z
            for (int j = 0; j != size.y; j++) { //Y
                for (int k = 0; k != size.x; k++) { //X
                    Geometry current = this.createCube(assetManager, 0.2f * k, 0.2f * j, 0.2f * i);
                    ret.add(current);
                }
            }
        }
        return ret;
    }
}
