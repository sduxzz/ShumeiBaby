package org.sdu.shumei.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.ExitDialogPack;

import android.content.Intent;

/**
 * 退出游戏对话框
 * 
 * @author Craig Lee
 * 
 */
public class ExitDialog extends Scene {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int mSection;
	// 游戏场景
	private Scene mParentScene;
	private BaseActivity mContext;
	private TexturePack mTexturePack;

	// ===========================================================
	// Constructors
	// ===========================================================
	public ExitDialog(Scene mParentScene, BaseActivity mContext,
			TexturePack mTexturePack, int mSection) {
		this.mParentScene = mParentScene;
		this.mContext = mContext;
		this.mTexturePack = mTexturePack;
		this.mSection = mSection;
		init();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private void init() {
		final TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mTexturePack
				.getTexturePackTextureRegionLibrary();
		// 设置背景
		this.setBackgroundEnabled(false);

		Sprite background = new Sprite(0, 0,
				texturePackTextureRegionLibrary
						.get(ExitDialogPack.EXIT_DIALOG_BACKGROUND_ID),
				mContext.getVertexBufferObjectManager());
		this.attachChild(background);

		// 确定按钮
		final ButtonSprite yesBtn = new ButtonSprite(116, 471,
				texturePackTextureRegionLibrary
						.get(ExitDialogPack.EXIT_DIALOG_BTN_YES_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(ExitDialogPack.EXIT_DIALOG_BTN_YES_PRESSED_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						mContext.startActivity(new Intent(mContext,
								GameResultScene.SECTION_LEVELS[mSection - 1]));
						mContext.finish();
					}
				});
		this.attachChild(yesBtn);
		this.registerTouchArea(yesBtn);
		// 取消按钮
		final ButtonSprite noBtn = new ButtonSprite(276, 471,
				texturePackTextureRegionLibrary
						.get(ExitDialogPack.EXIT_DIALOG_BTN_NO_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(ExitDialogPack.EXIT_DIALOG_BTN_NO_PRESSED_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {

					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						mParentScene.clearChildScene();
					}
				});
		this.attachChild(noBtn);
		this.registerTouchArea(noBtn);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
