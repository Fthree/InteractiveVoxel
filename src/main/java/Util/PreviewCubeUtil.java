package Util;

import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class PreviewCubeUtil {
    public static Vector3f calculatePositionOnCube(CollisionResult result, float size) {
        Vector3f cubeSize = new Vector3f(size, size, size).mult(2f);
        return MathUtils.calculateForwardPosition.apply(result.getGeometry().getLocalTranslation(), result.getContactNormal(), cubeSize);
    }

    public static Vector3f calculatePositionInSpace(Camera cam, float putDistance) {
        Vector3f dist = new Vector3f(putDistance, putDistance, putDistance);
        return MathUtils.calculateForwardPosition.apply(cam.getLocation(), cam.getDirection(), dist);
    }
}
