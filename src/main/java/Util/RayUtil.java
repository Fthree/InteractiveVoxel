package Util;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class RayUtil {
    public static CollisionResult testCenter(Camera cam, Node voxels) {
        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
        CollisionResults result = new CollisionResults();
        voxels.collideWith(ray, result);
        return result.getClosestCollision();
    }
}
