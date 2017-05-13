package Util;

import Options.SelectionOptions;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class MouseUtil {
    public void initCrosshair(AssetManager assetManager, BitmapFont guiFont, AppSettings settings, Node guiNode) {
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+"); // crosshairs
        ch.setLocalTranslation( // center
                settings.getWidth() / 2 - ch.getLineWidth() / 2, settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }

    public void initMouseKeys(InputManager inputManager, ActionListener listener, SelectionOptions option, int buttontype) {
        inputManager.addMapping(option.getType(),
                new MouseButtonTrigger(buttontype));
        inputManager.addListener(listener, option.getType());
    }

    public void initMouseMovement(InputManager inputManager, AnalogListener listener) {
        inputManager.addMapping("RotateX", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("RotateX_Negative", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("RotateY", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("RotateY_Negative", new MouseAxisTrigger(MouseInput.AXIS_Y, false));

        inputManager.addListener(listener, "RotateX", "RotateX_negative", "RotateY", "RotateY_Negative");
    }

    public void initMouseScroll(InputManager inputManager, AnalogListener listener) {
        inputManager.addMapping("Scroll", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        inputManager.addMapping("Scroll_Negative", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addListener(listener, "Scroll", "Scroll_Negative");
    }
}
