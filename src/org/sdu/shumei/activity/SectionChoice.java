package org.sdu.shumei.activity;

import java.util.List;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.options.AudioOptions;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.sdu.shumei.Const;
import org.sdu.shumei.R;
import org.sdu.shumei.entity.SectionButton;
import org.sdu.shumei.entity.SectionButtonFactory;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.SectionChoicePack;
import org.sdu.shumei.utils.L;

import android.graphics.Color;
import android.widget.Toast;

/**
 * 大的关卡的选择界面
 * 
 * @author Craig Lee
 * 
 */
public class SectionChoice extends BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================
	/**
	 * 总的页数
	 */
	private static final int SECTION_NUMBER = 3;

	// ===========================================================
	// Fields
	// ===========================================================
	// 字体
	private Font mNumberFont;
	// 所需的纹理
	private TexturePack mTexturePack;

	/**
	 * 当前所在关卡
	 */
	private int mCurrentSection = 1;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public EngineOptions onCreateEngineOptions() {
		mSettingsPreferences = getSharedPreferences(
				Const.Settings.SETTINGS_NAME, MODE_PRIVATE);
		final SmoothCamera camera = new SmoothCamera(0, 0, Const.Camera.WIDTH,
				Const.Camera.HEIGHT, 1200, 0, 1.0f);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(
						Const.Camera.WIDTH, Const.Camera.HEIGHT), camera);
		// 声音效果设置
		final AudioOptions aduioOptions = engineOptions.getAudioOptions();
		aduioOptions.setNeedsSound(mSettingsPreferences.getBoolean(
				Const.Settings.SOUND_EFFECTS, true));
		aduioOptions.setNeedsMusic(mSettingsPreferences.getBoolean(
				Const.Settings.MUSIC_EFFECTS, true));
		return engineOptions;
	}

	@Override
	protected void onCreateResources() {
		// 字体
		final ITexture fontTexture = new BitmapTextureAtlas(
				this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		FontFactory.setAssetBasePath("font/");
		this.mNumberFont = FontFactory.createFromAsset(this.getFontManager(),
				fontTexture, this.getAssets(), "Wawa.ttf", 60, true,
				Color.rgb(255, 203, 51));
		this.mNumberFont.load();

		try {
			this.mTexturePack = new TexturePackLoader(getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"section_choice_pack.xml");
			this.mTexturePack.loadTexture();
		} catch (TexturePackParseException e) {
			L.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		this.mScene = new Scene();
		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = mTexturePack
				.getTexturePackTextureRegionLibrary();
		final Sprite background = new Sprite(0, 0,
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BACKGROUND_ID),
				getVertexBufferObjectManager());
		this.mScene.setBackground(new SpriteBackground(background));
		this.mScene.setTouchAreaBindingOnActionDownEnabled(true);
		return mScene;
	}

	@Override
	public synchronized void onResumeGame() {
		super.onResumeGame();
		addSectionButtonToScene();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	// 添加关卡按钮
	private void addSectionButtonToScene() {
		this.mScene.detachChildren();

		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = mTexturePack
				.getTexturePackTextureRegionLibrary();
		List<SectionButton> buttons = SectionButtonFactory
				.createSectionButtons(getVertexBufferObjectManager(), this,
						this.mNumberFont, this.mTexturePack);
		for (SectionButton sb : buttons) {
			this.mScene.registerTouchArea(sb);
			this.mScene.attachChild(sb);
		}

		// 返回按钮 HUD
		HUD hud = new HUD();
		// 返回按钮
		final ButtonSprite backBtn = new ButtonSprite(15, 28,
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BUTTON_BACK_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BUTTON_BACK_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						finish();
					}
				});
		// 上一关的按钮
		final ButtonSprite lastSection = new ButtonSprite(20, 400,
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BTN_LAST_SECTION_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BTN_LAST_SECTION_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						if (mCurrentSection == 1) {
							// 到达第一关
							runOnUiThread(new Runnable() {
								public void run() {
									Toast.makeText(
											SectionChoice.this,
											R.string.sectionchoice_firstsection,
											Toast.LENGTH_LONG).show();
								}
							});
						} else {
							mCurrentSection--;
							getEngine().getCamera().offsetCenter(
									-Const.Camera.WIDTH, 0);
						}
					}
				});

		// 下一关的按钮
		final ButtonSprite nextSection = new ButtonSprite(Const.Camera.WIDTH
				- lastSection.getWidth() - 20, 400,
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BTN_NEXT_SECTION_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(SectionChoicePack.BTN_NEXT_SECTION_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						if (mCurrentSection == SECTION_NUMBER) {
							// 到达最后一关
							runOnUiThread(new Runnable() {
								public void run() {
									Toast.makeText(SectionChoice.this,
											R.string.sectionchoice_lastsection,
											Toast.LENGTH_LONG).show();
								}
							});
						} else {
							mCurrentSection++;
							getEngine().getCamera().offsetCenter(
									Const.Camera.WIDTH, 0);
						}
					}
				});

		hud.attachChild(backBtn);
		hud.registerTouchArea(backBtn);

		hud.attachChild(lastSection);
		hud.registerTouchArea(lastSection);

		hud.attachChild(nextSection);
		hud.registerTouchArea(nextSection);

		getEngine().getCamera().setHUD(hud);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
