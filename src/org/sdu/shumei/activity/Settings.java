package org.sdu.shumei.activity;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.SwitcherButton;
import org.sdu.shumei.entity.SwitcherButtonFactory;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.SettingsPack;
import org.sdu.shumei.utils.L;

/**
 * 设置界面
 * 
 * @author Craig Lee
 * 
 */
public class Settings extends BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// 角色和按钮的图片
	private TexturePack mTexturePack;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onCreateResources() {
		try {
			this.mTexturePack = new TexturePackLoader(getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"settings_pack.xml");
			this.mTexturePack.loadTexture();
		} catch (TexturePackParseException e) {
			L.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		final TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mTexturePack
				.getTexturePackTextureRegionLibrary();
		this.mScene = new Scene();
		// 设置背景
		this.mScene.setBackground(new SpriteBackground(
				new Sprite(0, 0, texturePackTextureRegionLibrary
						.get(SettingsPack.BACKGROUND_ID),
						getVertexBufferObjectManager())));
		// 添加按钮
		addButtonToScene(texturePackTextureRegionLibrary);
		return mScene;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	// 添加按钮
	private void addButtonToScene(
			TexturePackTextureRegionLibrary texturePackTextureRegionLibrary) {
		// 音效开关
		final SwitcherButton soundBtn = SwitcherButtonFactory
				.createSwitcherButton(200, 367, mTexturePack,
						getVertexBufferObjectManager(), this,
						Const.Settings.SOUND_EFFECTS);
		this.mScene.attachChild(soundBtn);
		this.mScene.registerTouchArea(soundBtn);

		// 背景音乐开关
		final SwitcherButton musicBtn = SwitcherButtonFactory
				.createSwitcherButton(200, 625, mTexturePack,
						getVertexBufferObjectManager(), this,
						Const.Settings.MUSIC_EFFECTS);
		this.mScene.attachChild(musicBtn);
		this.mScene.registerTouchArea(musicBtn);

		final ButtonSprite backBtn = new ButtonSprite(0, 767,
				texturePackTextureRegionLibrary
						.get(SettingsPack.BTN_BACK_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(SettingsPack.BTN_BACK_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						finish();
					}
				});
		this.mScene.attachChild(backBtn);
		this.mScene.registerTouchArea(backBtn);

	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
