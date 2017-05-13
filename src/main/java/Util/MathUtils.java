package Util;

import com.jme3.math.Vector3f;
import javaslang.Function3;

public class MathUtils {
    public static Function3<Vector3f, Vector3f, Vector3f, Vector3f> calculateForwardPosition = (location, direction, distance) -> location.add(direction.mult(distance));
}
