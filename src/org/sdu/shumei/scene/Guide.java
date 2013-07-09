package org.sdu.shumei.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.GuidePack;

/**
 * ������������
 * 
 * @author Craig Lee
 * 
 */
public class Guide extends Scene {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Scene mParentScene;
	private BaseActivity mContext;
	private TexturePack mTexturePack;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Guide(Scene mParentScene, BaseActivity mContext,
			TexturePack mTexturePack) {
		this.mParentScene = mParentScene;
		this.mContext = mContext;
		this.mTexturePack = mTexturePack;
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
		// ���ñ���
		this.setBackgroundEnabled(false);

		Sprite background = new Sprite(0, 0,
				texturePackTextureRegionLibrary.get(GuidePack.BACKGROUND_ID),
				mContext.getVertexBufferObjectManager());
		this.attachChild(background);

		// ȷ����ť
		final ButtonSprite knowBtn = new ButtonSprite(172, 865,
				texturePackTextureRegionLibrary
						.get(GuidePack.BTN_KNOW_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(GuidePack.BTN_KNOW_PRESSED_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						mParentScene.clearChildScene();
					}
				});
		this.attachChild(knowBtn);
		this.registerTouchArea(knowBtn);

	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
