package Service;

import Options.SelectionOptions;
import Util.PreviewCubeUtil;
import Util.RayUtil;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class ListenerService {
    private static SelectService selectService;

    public ListenerService() {
        selectService = new SelectService();
    }

    public ActionListener getMouseClickListener(Camera cam, Node voxels, AssetManager assetManager) {
        return (name, keyPressed, tpf) -> {
            //RMB
            if(name.equals(SelectionOptions.REMOVE.getType()) && !keyPressed) {
                CollisionResult result = RayUtil.testCenter(cam, voxels);
                if(result != null) {
                    selectService.handleSelection(voxels, result, SelectionOptions.REMOVE, assetManager);
                }
            }

            //LMB
            if(name.equals(SelectionOptions.ADD.getType()) && !keyPressed) {
                CollisionResult result = RayUtil.testCenter(cam, voxels);
                if(result != null){
                    selectService.handleSelection(voxels, result, SelectionOptions.ADD, assetManager);
                }
            }
        };
    }

    public AnalogListener getMouseAxisListener(Camera cam, Node voxels, Geometry outlineCube) {
        return (name, value, tpf) -> {
            if("RotateX".equals(name) || "RotateX_negative".equals(name) || "RotateY".equals(name) || "RotateY_Negative".equals(name)) {
                CollisionResult result = RayUtil.testCenter(cam, voxels);
                if(result != null) {
                    outlineCube.setCullHint(Spatial.CullHint.Dynamic);
                    outlineCube.setLocalTranslation(PreviewCubeUtil.calculatePositionOnCube(result, 0.1f));
                }
            }
        };
    }
}
