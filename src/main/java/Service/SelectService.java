package Service;

import Options.SelectionOptions;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import Util.CubeUtil;
import Util.MathUtils;

public class SelectService {
    private CubeUtil cubeUtil;

    public SelectService() {
        cubeUtil = new CubeUtil();
    }

    public void handleSelection(Node voxels, CollisionResult result, SelectionOptions selectionOptions, AssetManager assetManager) {
        if(selectionOptions.equals(SelectionOptions.REMOVE)) {
            voxels.detachChild(result.getGeometry());
        }
        if(selectionOptions.equals(SelectionOptions.ADD)) {
            Vector3f size = new Vector3f(0.1f, 0.1f, 0.1f).mult(2f);
            Vector3f position = MathUtils.calculateForwardPosition.apply(result.getGeometry().getLocalTranslation(), result.getContactNormal(), size);
            voxels.attachChild(cubeUtil.createCube(assetManager, position.x, position.y, position.z));
        }
    }

    public void handleEmptySelection(Node voxels, Camera cam, float distance, SelectionOptions selectionOptions, AssetManager assetManager) {
        if(selectionOptions.equals(SelectionOptions.ADD)) {
            Vector3f dist = new Vector3f(distance, distance, distance);
            Vector3f position = MathUtils.calculateForwardPosition.apply(cam.getLocation(), cam.getDirection(), dist);
            voxels.attachChild(cubeUtil.createCube(assetManager, position.x, position.y, position.z));
        }
    }
}
