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
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.AboutPack;
import org.sdu.shumei.utils.L;

/**
 * 关于界面
 * 
 * @author Craig Lee
 * 
 */
public class About extends BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// 图片
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
					"about_pack.xml");
			this.mTexturePack.loadTexture();
		} catch (TexturePackParseException e) {
			L.e(e);
		}

	}

	@Override
	protected Scene onCreateScene() {
		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = mTexturePack
				.getTexturePackTextureRegionLibrary();
		this.mScene = new Scene();
		mScene.setBackground(new SpriteBackground(new Sprite(0, 0,
				texturePackTextureRegionLibrary.get(AboutPack.BACKGROUND_ID),
				getVertexBufferObjectManager())));

		final ButtonSprite back = new ButtonSprite(0, Const.Camera.HEIGHT
				- texturePackTextureRegionLibrary.get(
						AboutPack.BTN_BACK_NORMAL_ID).getHeight(),
				texturePackTextureRegionLibrary
						.get(AboutPack.BTN_BACK_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(AboutPack.BTN_BACK_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite arg0, float arg1,
							float arg2) {
						finish();
					}
				});
		mScene.attachChild(back);
		mScene.registerTouchArea(back);

		return mScene;
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
