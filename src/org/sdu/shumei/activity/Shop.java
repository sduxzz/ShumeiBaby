package org.sdu.shumei.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.options.AudioOptions;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.sdu.shumei.Const;
import org.sdu.shumei.R;
import org.sdu.shumei.adapter.UpdateHandlerAdapter;
import org.sdu.shumei.database.dao.ProgressDAO;
import org.sdu.shumei.database.dao.PropDAO;
import org.sdu.shumei.database.model.Prop;
import org.sdu.shumei.entity.ShopRole;
import org.sdu.shumei.entity.ShopRoleFactory;
import org.sdu.shumei.extention.SplashBaseActivity;
import org.sdu.shumei.resource.ShopBackgroundPack;
import org.sdu.shumei.resource.ShopRoleButtonPack;
import org.sdu.shumei.utils.L;

import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.widget.Toast;

/**
 * �̵�
 * 
 * @author Craig Lee
 * 
 */
public class Shop extends SplashBaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * ��ǰ�����ѵĻ���
	 */
	public static int leftScore;

	// ��ɫ�Ͱ�ť��ͼƬ
	private TexturePack mRoleButtonTexturePack;
	// ����ͼƬ
	private TexturePack mBackgroundTexturePack;
	// ��������
	private Music mBackgroundMusic;
	// ���û��ֵ�����
	private Font mPointFont;
	// �������÷�������
	private int mShumeiBoyQuantity = 0;
	private Text mShumeiBoyQuantityText = null;
	// ����ļ��ٿ�����
	private int mSlowDownCardQuantity = 0;
	private Text mSlowDownCardQuantityText = null;
	// ������ռ�ͨ�ؿ�
	private int mPassCardQuantity = 0;
	private Text mPassCardQuantityText = null;

	// ��ǰ���û���
	private Text mLeftScoreLeftText = null;
	private Text mLeftScoreLeftWordText = null;

	private Text mRightScoreRightText = null;
	private Text mRightScoreRightWordText = null;

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
		// ����Ч������
		final AudioOptions aduioOptions = engineOptions.getAudioOptions();
		aduioOptions.setNeedsSound(mSettingsPreferences.getBoolean(
				Const.Settings.SOUND_EFFECTS, true));
		aduioOptions.setNeedsMusic(mSettingsPreferences.getBoolean(
				Const.Settings.MUSIC_EFFECTS, true));
		return engineOptions;
	}

	@Override
	public void createRealResource() {
		// ����
		final ITexture pointFontTexture = new BitmapTextureAtlas(
				this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		FontFactory.setAssetBasePath("font/");
		this.mPointFont = FontFactory.createFromAsset(this.getFontManager(),
				pointFontTexture, this.getAssets(), "Wawa.ttf", 40, true,
				Color.WHITE);
		this.mPointFont.load();

		try {
			this.mRoleButtonTexturePack = new TexturePackLoader(
					getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "shop_role_button_pack.xml");
			this.mRoleButtonTexturePack.loadTexture();

			this.mBackgroundTexturePack = new TexturePackLoader(
					getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "shop_background_pack.xml");
			this.mBackgroundTexturePack.loadTexture();
		} catch (TexturePackParseException e) {
			L.e(e);
		}
		if (isMusicOn()) {
			loadMusic();
		}
	}

	@Override
	public Scene createRealScene() {
		// ��ʼ�����û���
		initLeftScore();
		this.mScene = new Scene();
		// ���ñ���
		final Sprite background = new Sprite(0, 0, mBackgroundTexturePack
				.getTexturePackTextureRegionLibrary().get(
						ShopBackgroundPack.BACKGROUND_ID),
				getVertexBufferObjectManager());
		this.mScene.attachChild(background);
		// �ѵ��߼��ص�������
		addPropToScene();
		// �Ѱ�ť��ӵ�������
		addButtonToScene();

		// ��ʼ��ͳ�����ֵ�����
		int leftScoreTextY = 28;
		mLeftScoreLeftWordText = new Text(115, leftScoreTextY, mPointFont,
				"���֣�", getVertexBufferObjectManager());
		mLeftScoreLeftText = new Text(mLeftScoreLeftWordText.getX()
				+ mLeftScoreLeftWordText.getWidth(), leftScoreTextY,
				mPointFont, String.valueOf(leftScore),
				getVertexBufferObjectManager());
		mRightScoreRightWordText = new Text(Const.Camera.WIDTH + 199,
				leftScoreTextY, mPointFont, "���֣�",
				getVertexBufferObjectManager());
		mRightScoreRightText = new Text(mRightScoreRightWordText.getX()
				+ mRightScoreRightWordText.getWidth(), leftScoreTextY,
				mPointFont, String.valueOf(leftScore),
				getVertexBufferObjectManager());
		this.mScene.attachChild(mLeftScoreLeftWordText);
		this.mScene.attachChild(mLeftScoreLeftText);
		this.mScene.attachChild(mRightScoreRightWordText);
		this.mScene.attachChild(mRightScoreRightText);

		int textX = Const.Camera.WIDTH + 363;
		int textY = 315;
		mShumeiBoyQuantityText = new Text(textX, textY, mPointFont,
				String.valueOf(mShumeiBoyQuantity), 2,
				getVertexBufferObjectManager());
		mSlowDownCardQuantityText = new Text(textX, textY + 248, mPointFont,
				String.valueOf(mSlowDownCardQuantity), 2,
				getVertexBufferObjectManager());
		mPassCardQuantityText = new Text(textX, textY + 2 * 248, mPointFont,
				String.valueOf(mPassCardQuantity), 2,
				getVertexBufferObjectManager());
		this.mScene.attachChild(mShumeiBoyQuantityText);
		this.mScene.attachChild(mSlowDownCardQuantityText);
		this.mScene.attachChild(mPassCardQuantityText);

		// ���¿��û���
		this.mScene.registerUpdateHandler(new UpdateHandlerAdapter() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				mLeftScoreLeftText.setText(String.valueOf(leftScore));
				mRightScoreRightText.setText(String.valueOf(leftScore));
			}
		});
		return this.mScene;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	// ��ʼ�����û���
	private void initLeftScore() {
		final ProgressDAO progressDAO = new ProgressDAO(this);
		final PropDAO propDAO = new PropDAO(this);
		leftScore = progressDAO.getScoreEarned() - propDAO.getScoreConsumed();
	}

	// �ѵ��߼��ص�������
	private void addPropToScene() {
		// �����÷��ɫ
		List<ShopRole> shopRoles = ShopRoleFactory.createShopRoles(
				mRoleButtonTexturePack, getVertexBufferObjectManager(), this);
		for (ShopRole sr : shopRoles) {
			this.mScene.attachChild(sr);
			this.mScene.registerTouchArea(sr);
		}

	}

	// �Ѱ�ť��ӵ�������
	private void addButtonToScene() {
		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mRoleButtonTexturePack
				.getTexturePackTextureRegionLibrary();
		// ������߽���İ�ť
		TexturePackerTextureRegion propTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(ShopRoleButtonPack.BUTTON_PROP_NORMAL_ID);
		final ButtonSprite prop = new ButtonSprite(Const.Camera.WIDTH
				- propTexturePackerTextureRegion.getSourceWidth(), 0,
				propTexturePackerTextureRegion,
				texturePackTextureRegionLibrary
						.get(ShopRoleButtonPack.BUTTON_PROP_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// �ƶ�Camera������һ��
						getEngine().getCamera().offsetCenter(
								Const.Camera.WIDTH, 0);
					}
				});

		this.mScene.attachChild(prop);
		this.mScene.registerTouchArea(prop);

		// �����ɫ����İ�ť
		final ButtonSprite role = new ButtonSprite(Const.Camera.WIDTH, 0,
				texturePackTextureRegionLibrary
						.get(ShopRoleButtonPack.BUTTON_ROLE_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(ShopRoleButtonPack.BUTTON_ROLE_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// �ƶ�Camera������һ��
						getEngine().getCamera().offsetCenter(
								-Const.Camera.WIDTH, 0);
					}
				});
		this.mScene.attachChild(role);
		this.mScene.registerTouchArea(role);

		// �����Ӽ����������İ�ť
		createAddAndMinusButton(texturePackTextureRegionLibrary);

		// ����ť
		TexturePackerTextureRegion buyTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(ShopRoleButtonPack.BUTTON_BUY_NORMAL_ID);
		final ButtonSprite buy = new ButtonSprite(2 * Const.Camera.WIDTH
				- buyTexturePackerTextureRegion.getSourceWidth(),
				Const.Camera.HEIGHT
						- buyTexturePackerTextureRegion.getSourceHeight(),
				buyTexturePackerTextureRegion,
				texturePackTextureRegionLibrary
						.get(ShopRoleButtonPack.BUTTON_BUY_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// ȷ�Ϲ�����Ʒ
						buyChosenProp();
					}
				});
		this.mScene.attachChild(buy);
		this.mScene.registerTouchArea(buy);

		// ���ذ�ť HUD
		HUD hud = new HUD();
		TexturePackerTextureRegion backTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(ShopRoleButtonPack.BUTTON_BACK_NORMAL_ID);
		final ButtonSprite back = new ButtonSprite(0, Const.Camera.HEIGHT
				- backTexturePackerTextureRegion.getSourceHeight(),
				backTexturePackerTextureRegion,
				texturePackTextureRegionLibrary
						.get(ShopRoleButtonPack.BUTTON_BACK_PRESSED_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// ������Ϸ������ʾ�������
						finish();
					}
				});
		hud.attachChild(back);
		hud.registerTouchArea(back);
		getEngine().getCamera().setHUD(hud);
	}

	// �����Ӽ����������İ�ť
	private void createAddAndMinusButton(
			TexturePackTextureRegionLibrary texturePackTextureRegionLibrary) {
		TexturePackerTextureRegion plusTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(ShopRoleButtonPack.BUTTON_PLUS_ID);
		TexturePackerTextureRegion minusTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(ShopRoleButtonPack.BUTTON_MINUS_ID);
		// ���ư�ťλ��
		int leftX = Const.Camera.WIDTH + 270;
		int rightX = Const.Camera.WIDTH + 430;
		int topY = 309;

		List<ButtonSprite> list = new ArrayList<ButtonSprite>();
		ButtonSprite buttonSprite = null;
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				// ���Ű�ť
				buttonSprite = new ButtonSprite(leftX, topY,
						minusTexturePackerTextureRegion,
						getVertexBufferObjectManager());
			} else {
				// �ӺŰ�ť
				buttonSprite = new ButtonSprite(rightX, topY,
						plusTexturePackerTextureRegion,
						getVertexBufferObjectManager());
				// �����ƶ�һ��
				topY += 246;
			}
			this.mScene.attachChild(buttonSprite);
			this.mScene.registerTouchArea(buttonSprite);
			list.add(buttonSprite);
		}
		list.get(0).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (mShumeiBoyQuantity > 0) {
					mShumeiBoyQuantity--;
					mShumeiBoyQuantityText.setText(String
							.valueOf(mShumeiBoyQuantity));
				}
			}
		});
		list.get(1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				int toCostScore = Const.Prop.PROP_SHUMEI_BOY_PRICE
						* (mShumeiBoyQuantity + 1)
						+ Const.Prop.PROP_SLOW_DOWN_CARD_PRICE
						* mSlowDownCardQuantity
						+ Const.Prop.PROP_PASS_CARD_PRICE * mPassCardQuantity;
				if (toCostScore <= leftScore) {
					mShumeiBoyQuantity++;
					mShumeiBoyQuantityText.setText(String
							.valueOf(mShumeiBoyQuantity));
				} else {
					// ��ʾ���ֲ���
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Shop.this, R.string.shop_lackPoint,
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});
		list.get(2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (mSlowDownCardQuantity > 0) {
					mSlowDownCardQuantity--;
					mSlowDownCardQuantityText.setText(String
							.valueOf(mSlowDownCardQuantity));
				}
			}
		});
		list.get(3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				int toCostScore = Const.Prop.PROP_SLOW_DOWN_CARD_PRICE
						* (mSlowDownCardQuantity + 1)
						+ Const.Prop.PROP_SHUMEI_BOY_PRICE * mShumeiBoyQuantity
						+ Const.Prop.PROP_PASS_CARD_PRICE * mPassCardQuantity;
				if (toCostScore <= leftScore) {
					mSlowDownCardQuantity++;
					mSlowDownCardQuantityText.setText(String
							.valueOf(mSlowDownCardQuantity));
				} else {
					// ��ʾ���ֲ���
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Shop.this, R.string.shop_lackPoint,
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});
		list.get(4).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (mPassCardQuantity > 0) {
					mPassCardQuantity--;
					mPassCardQuantityText.setText(String
							.valueOf(mPassCardQuantity));
				}
			}
		});
		list.get(5).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				int toCostScore = Const.Prop.PROP_PASS_CARD_PRICE
						* (mPassCardQuantity + 1)
						+ Const.Prop.PROP_SHUMEI_BOY_PRICE * mShumeiBoyQuantity
						+ Const.Prop.PROP_SLOW_DOWN_CARD_PRICE
						* mSlowDownCardQuantity;
				if (toCostScore <= leftScore) {
					mPassCardQuantity++;
					mPassCardQuantityText.setText(String
							.valueOf(mPassCardQuantity));
				} else {
					// ��ʾ���ֲ���
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Shop.this, R.string.shop_lackPoint,
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});

	}

	// ȷ�Ϲ������
	private void buyChosenProp() {
		// �������ݿ�
		final PropDAO propDAO = new PropDAO(this);
		if (mShumeiBoyQuantity > 0) {
			// ��������÷��
			Prop prop = new Prop();
			prop.setPropId(Const.Prop.PROP_SHUMEI_BOY_ID);
			prop.setQuantity(mShumeiBoyQuantity);
			prop.setScoreConsumed(mShumeiBoyQuantity
					* Const.Prop.PROP_SHUMEI_BOY_PRICE);
			propDAO.insert(prop);
			leftScore -= prop.getScoreConsumed();
			Editor editor = mSettingsPreferences.edit();
			editor.putInt(
					Const.Settings.PROP_SHUMEI_BOY_QUANTITY,
					mShumeiBoyQuantity
							+ mSettingsPreferences.getInt(
									Const.Settings.PROP_SHUMEI_BOY_QUANTITY, 0));
			editor.commit();

		}
		if (mSlowDownCardQuantity > 0) {
			// �����˼��ٿ�
			Prop prop = new Prop();
			prop.setPropId(Const.Prop.PROP_SLOW_DOWN_CARD_ID);
			prop.setQuantity(mSlowDownCardQuantity);
			prop.setScoreConsumed(mSlowDownCardQuantity
					* Const.Prop.PROP_SLOW_DOWN_CARD_PRICE);
			propDAO.insert(prop);
			leftScore -= prop.getScoreConsumed();
			Editor editor = mSettingsPreferences.edit();
			editor.putInt(
					Const.Settings.PROP_SLOW_DOWN_CARD_QUANTITY,
					mSlowDownCardQuantity
							+ mSettingsPreferences
									.getInt(Const.Settings.PROP_SLOW_DOWN_CARD_QUANTITY,
											0));
			editor.commit();
		}
		if (mPassCardQuantity > 0) {
			// �������ռ�ͨ�ؿ�
			Prop prop = new Prop();
			prop.setPropId(Const.Prop.PROP_PASS_CARD_ID);
			prop.setQuantity(mPassCardQuantity);
			prop.setScoreConsumed(mPassCardQuantity
					* Const.Prop.PROP_PASS_CARD_PRICE);
			propDAO.insert(prop);
			leftScore -= prop.getScoreConsumed();
			Editor editor = mSettingsPreferences.edit();
			editor.putInt(
					Const.Settings.PROP_PASS_CARD_QUANTITY,
					mPassCardQuantity
							+ mSettingsPreferences.getInt(
									Const.Settings.PROP_PASS_CARD_QUANTITY, 0));
			editor.commit();
		}

		if (mShumeiBoyQuantity + mSlowDownCardQuantity + mPassCardQuantity > 0) {
			// ������
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(Shop.this, R.string.shop_buyPropSuccess,
							Toast.LENGTH_SHORT).show();
				}
			});
			finish();
		} else {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(Shop.this, R.string.shop_buyNothing,
							Toast.LENGTH_SHORT).show();
				}
			});
		}

	}

	// ���ر�������
	private void loadMusic() {
		// ���ر�������
		MusicFactory.setAssetBasePath(Const.ASSET_SOUNDS_BASE_PATH);
		try {
			// TODO
			this.mBackgroundMusic = MusicFactory.createMusicFromAsset(
					getMusicManager(), this, "background_forest.ogg");
			this.mBackgroundMusic.setLooping(true);
			playMusic(mBackgroundMusic);
		} catch (IOException e) {
			L.e(e);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
