package com.example.hello.world;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;

public class MainActivity extends SimpleBaseGameActivity {

    static final int CAMERA_WIDTH = 800;
    static final int CAMERA_HEIGHT = 480;

    @Override
    public EngineOptions onCreateEngineOptions() {
        Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
            new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
    }

    @Override
    protected void onCreateResources() {
        // Auto-generated method stub
    }

    @Override
    protected Scene onCreateScene() {
        Scene scene = new Scene();
        scene.setBackground(new Background(0.09804f, 0.6274f, 0));
        Font mFont = FontFactory.create(this.getFontManager(),
        		this.getTextureManager(), 256, 256,
        		Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
        mFont.load();
        Text text = new Text(0, 0, mFont, "FUNKAR!!!", null);
        scene.attachChild(text);
        return scene;
    }
}