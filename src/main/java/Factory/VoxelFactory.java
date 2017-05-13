package Factory;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import Util.CubeUtil;

import java.util.List;

public class VoxelFactory {

    final private CubeUtil cubeUtil;

    public VoxelFactory() {
        cubeUtil = new CubeUtil();
    }

    public void createVoxelCubeSet(AssetManager assetManager, int size, Node voxelsNode) {
        List<Geometry> voxels = cubeUtil.createCubes(assetManager, new Vector3f(size, size, size));
        attachVoxelsToScene(voxelsNode, voxels);
    }

    public Geometry createOutlineCube(AssetManager assetManager, Vector3f position) {
        return cubeUtil.createOutlineCube(assetManager, position);
    }

    private void attachVoxelsToScene(Node root, List<Geometry> voxels) {
        voxels.stream().forEach(voxel -> root.attachChild(voxel));
    }
}
