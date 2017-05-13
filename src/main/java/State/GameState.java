package State;

import Factory.VoxelFactory;
import Factory.VoxelMapFactory;
import Options.SelectionOptions;
import Service.ListenerService;
import Util.MathUtils;
import Util.MouseUtil;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapFont;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class GameState extends AbstractAppState {
    private SimpleApplication app;

    private Node voxelPreview;
    private Node voxels;
    private VoxelFactory voxelFactory;
    private Geometry outlineCube;
    private BitmapFont guiFont;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication)app;
        this.guiFont = this.app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");

        voxels = new Node("Voxels");
        voxelPreview = new Node("VoxelPreview");

        startGame();
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if(enabled) {
            startGame();
        } else {
            this.app.getRootNode().detachAllChildren();
        }
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);

    }

    private void startGame() {
        this.app.getRootNode().attachChild(voxelPreview);
        this.app.getRootNode().attachChild(voxels);
        voxelFactory = new VoxelFactory();

        setDefaults();
        setupMap();
        setupOutlineCube();
        initMouse();
    }

    private void setDefaults() {
        this.app.getFlyByCamera().setMoveSpeed(6f);
        this.app.setDisplayStatView(false);
        this.app.getCamera().setLocation(new Vector3f(0f, 1.1f, 0f));
    }

    private void setupMap() {
        VoxelMapFactory.createFlatMap(this.app.getAssetManager(), voxels, 50);
    }

    private void initMouse() {
        MouseUtil mouseUtil = new MouseUtil();
        mouseUtil.initCrosshair(this.app.getAssetManager(), guiFont, this.app.getContext().getSettings(), this.app.getGuiNode());

        ListenerService listenerService = new ListenerService();

        //Setup mouse click listeners
        ActionListener mouseClickListener = listenerService.getMouseClickListener(this.app.getCamera(), voxels, this.app.getAssetManager());
        mouseUtil.initMouseKeys(
                this.app.getInputManager(),
                mouseClickListener,
                SelectionOptions.ADD,
                MouseInput.BUTTON_LEFT
        );
        mouseUtil.initMouseKeys(
                this.app.getInputManager(),
                mouseClickListener,
                SelectionOptions.REMOVE,
                MouseInput.BUTTON_RIGHT
        );

        //Setup mouseAxis listener for movement
        AnalogListener mouseAxisListener = listenerService.getMouseAxisListener(this.app.getCamera(), voxels, outlineCube);
        mouseUtil.initMouseMovement(
                this.app.getInputManager(),
                mouseAxisListener
        );
    }

    private void setupOutlineCube() {
        //Create a new outline cube anywhere
        outlineCube = voxelFactory.createOutlineCube(this.app.getAssetManager(), MathUtils.calculateForwardPosition.apply(this.app.getCamera().getLocation(), this.app.getCamera().getDirection(), new Vector3f()));
        //Ensure it isn't showing right from the beginning
        outlineCube.setCullHint(Spatial.CullHint.Always);
        //Attach to preview node
        voxelPreview.attachChild(outlineCube);
    }

    private void setupCollision() {

    }
}
