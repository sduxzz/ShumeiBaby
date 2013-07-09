package org.sdu.shumei.activity;

import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
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
import org.sdu.shumei.entity.LevelButton;
import org.sdu.shumei.entity.LevelButtonFactory;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.Section2LevelChoicePack;
import org.sdu.shumei.utils.L;

import android.graphics.Color;

/**
 * 第三大关中的小关选择界面
 * 
 * @author Craig Lee
 * 
 */
public class Section3LevelChoice extends BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// 字体
	private Font mLevelTextFont;
	// 所需的纹理
	private TexturePack mTexturePack;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onCreateResources() {
		// 字体
		final ITexture fontTexture = new BitmapTextureAtlas(
				this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		FontFactory.setAssetBasePath("font/");
		this.mLevelTextFont = FontFactory.createFromAsset(
				this.getFontManager(), fontTexture, this.getAssets(),
				"Wawa.ttf", 90, true, Color.WHITE);
		this.mLevelTextFont.load();

		try {
			this.mTexturePack = new TexturePackLoader(getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"section2_level_choice_pack.xml");
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
						.get(Section2LevelChoicePack.BACKGROUND_2_ID),
				getVertexBufferObjectManager());
		this.mScene.setBackground(new SpriteBackground(background));
		return mScene;
	}

	@Override
	public synchronized void onResumeGame() {
		super.onResumeGame();
		addLevelButtonToScene();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	// 添加关卡按钮
	/**
	 * 
	 */
	private void addLevelButtonToScene() {
		this.mScene.detachChildren();
		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = mTexturePack
				.getTexturePackTextureRegionLibrary();
		// 返回按钮
		final ButtonSprite backBtn = new ButtonSprite(15, 28,
				texturePackTextureRegionLibrary
						.get(Section2LevelChoicePack.BUTTON_BACK2_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(Section2LevelChoicePack.BUTTON_BACK2_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						finish();
					}
				});
		this.mScene.registerTouchArea(backBtn);
		this.mScene.attachChild(backBtn);

		List<LevelButton> levelButtons = LevelButtonFactory
				.createSection2LevelButtons(mTexturePack, mLevelTextFont,
						getVertexBufferObjectManager(), this);
		for (LevelButton lv : levelButtons) {
			mScene.registerTouchArea(lv);
			mScene.attachChild(lv);
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
