package org.sdu.shumei.extention;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * ����������
 * 
 * @author Craig Lee
 * 
 */
public abstract class SplashBaseActivity extends BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// ��������Loding�����ͼ��
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private ITextureRegion mLodingTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onCreateResources() {
		// ֻ����LodingͼƬ
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(),
				1024, 1024, TextureOptions.BILINEAR);
		this.mLodingTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mBitmapTextureAtlas, getAssets(),
						"splash.png", 0, 0);
		this.mBitmapTextureAtlas.load();
	}

	@Override
	protected Scene onCreateScene() {
		// ����һ�������Scene
		final Scene mSplashScene = new Scene();
		mSplashScene.setBackground(new SpriteBackground(new Sprite(0, 0,
				mLodingTextureRegion, getVertexBufferObjectManager())));

		mSplashScene.registerUpdateHandler(new TimerHandler(0.1f,
				new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						// Unregister this timer handler to ensure it does not
						// run again.
						getEngine().unregisterUpdateHandler(pTimerHandler);
						// then create our resources
						createRealResource();
						// then create the real scene
						mScene = createRealScene();
						mEngine.setScene(mScene);
					}
				}));
		return mSplashScene;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * ������ʼ������Դ
	 */
	public abstract void createRealResource();

	/**
	 * ���������ĳ���
	 * 
	 * @return
	 */
	public abstract Scene createRealScene();
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
