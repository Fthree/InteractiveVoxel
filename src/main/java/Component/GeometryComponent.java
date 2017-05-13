package Component;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeometryComponent {
    private Geometry geometry;
    private Node attachedNode;
}
