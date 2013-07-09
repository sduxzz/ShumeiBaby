package org.sdu.shumei.activity;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.util.modifier.ease.EaseElasticOut;
import org.andengine.util.modifier.ease.EaseExponentialOut;
import org.sdu.shumei.Const;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.WelcomePack;
import org.sdu.shumei.utils.L;

import android.content.Intent;

/**
 * ��ʼ��ӭ����
 * 
 * @author Craig Lee
 * 
 */
public class Welcome extends BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ��ɫ�Ͱ�ť��ͼƬ
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
					"welcome_pack.xml");
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
		// ���ñ���
		this.mScene.setBackground(new SpriteBackground(new Sprite(0, 0,
				texturePackTextureRegionLibrary.get(WelcomePack.BACKGROUND_ID),
				getVertexBufferObjectManager())));
		// ��Ӱ�ť
		addButtonToScene(texturePackTextureRegionLibrary);
		// ��ӱ���
		addTitle(texturePackTextureRegionLibrary);
		return mScene;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	// ��Ӱ�ť
	private void addButtonToScene(
			TexturePackTextureRegionLibrary texturePackTextureRegionLibrary) {
		// ��ʼ��Ϸ��ť
		final ButtonSprite start = new ButtonSprite(147, 365,
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_START_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_START_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						Intent intent = new Intent(Welcome.this,
								SectionChoice.class);
						startActivity(intent);
					}
				});
		this.mScene.attachChild(start);
		this.mScene.registerTouchArea(start);
		// ���ð�ť
		final ButtonSprite settings = new ButtonSprite(177, 471,
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_SETTINGS_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_SETTINGS_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						startActivity(new Intent(Welcome.this, Settings.class));
					}
				});
		this.mScene.attachChild(settings);
		this.mScene.registerTouchArea(settings);
		// �̵갴ť
		final ButtonSprite shop = new ButtonSprite(160, 575,
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_SHOP_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_SHOP_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						startActivity(new Intent(Welcome.this, Shop.class));
					}
				});
		this.mScene.attachChild(shop);
		this.mScene.registerTouchArea(shop);
		// ���ڰ�ť
		final ButtonSprite about = new ButtonSprite(177, 673,
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_ABOUT_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(WelcomePack.BUTTON_ABOUT_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						startActivity(new Intent(Welcome.this, About.class));
					}
				});
		this.mScene.attachChild(about);
		this.mScene.registerTouchArea(about);
	}

	// ��ӱ���
	private void addTitle(
			TexturePackTextureRegionLibrary texturePackTextureRegionLibrary) {
		final Sprite shumei = new Sprite(5, 213,
				texturePackTextureRegionLibrary
						.get(WelcomePack.TITLE_SHUMEI_ID),
				getVertexBufferObjectManager());
		shumei.registerEntityModifier(new MoveModifier(1.5f,
				-shumei.getWidth(), shumei.getX(), shumei.getY(),
				shumei.getY(), EaseExponentialOut.getInstance()));

		final Sprite branch = new Sprite(182, 230,
				texturePackTextureRegionLibrary
						.get(WelcomePack.TITLE_BRANCH_ID),
				getVertexBufferObjectManager());
		branch.registerEntityModifier(new MoveModifier(1.5f,
				Const.Camera.WIDTH, branch.getX(), branch.getY(),
				branch.getY(), EaseExponentialOut.getInstance()));

		final Sprite text = new Sprite(33, 106,
				texturePackTextureRegionLibrary.get(WelcomePack.TITLE_TEXT_ID),
				getVertexBufferObjectManager());
		text.registerEntityModifier(new MoveModifier(3f, text.getX(), text
				.getX(), -text.getHeight(), text.getY(), EaseElasticOut
				.getInstance()));

		this.mScene.attachChild(shumei);
		this.mScene.attachChild(branch);
		this.mScene.attachChild(text);

	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
