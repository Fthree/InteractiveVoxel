package Factory;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import Util.CubeUtil;

import java.util.List;

public class VoxelMapFactory {
    public static void createFlatMap(AssetManager assetManager, Node voxels, int size) {
        CubeUtil cubeUtil = new CubeUtil();
        attachVoxelsToScene(voxels, cubeUtil.createCubes(assetManager, new Vector3f(size, 1f, size)));
    }

    private static void attachVoxelsToScene(Node root, List<Geometry> voxels) {
        voxels.stream().forEach(voxel -> root.attachChild(voxel));
    }
}
