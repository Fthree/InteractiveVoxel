package Entity;

import Component.GeometryComponent;
import lombok.Data;

@Data
public class BasicBlock implements Voxel {
    public GeometryComponent geometryComponent;
}
