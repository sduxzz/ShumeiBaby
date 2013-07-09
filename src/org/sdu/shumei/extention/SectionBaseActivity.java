package org.sdu.shumei.extention;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine.EngineLock;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.StrokeFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.math.MathUtils;
import org.andengine.util.modifier.ease.EaseBounceOut;
import org.sdu.shumei.Const;
import org.sdu.shumei.adapter.AnimationListenerAdapter;
import org.sdu.shumei.adapter.ContactListenerAdapter;
import org.sdu.shumei.entity.AbstractShumei;
import org.sdu.shumei.entity.AbstractVillain;
import org.sdu.shumei.entity.ParticleFactory;
import org.sdu.shumei.entity.SceneEntity;
import org.sdu.shumei.entity.ShumeiBoy;
import org.sdu.shumei.entity.ShumeiFactory;
import org.sdu.shumei.entity.ShumeiGirl;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.resource.ParticlePack;
import org.sdu.shumei.resource.ShumeiGirlPack;
import org.sdu.shumei.resource.ShumeiRepresentPack;
import org.sdu.shumei.scene.ExitDialog;
import org.sdu.shumei.scene.GameResultScene;
import org.sdu.shumei.utils.L;

import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.umeng.analytics.MobclickAgent;

/**
 * ������Ϸ����Activity
 * 
 * @author Craig Lee
 * 
 */
public abstract class SectionBaseActivity extends SplashBaseActivity implements
		IOnSceneTouchListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * ��������
	 */
	protected PhysicsWorld mPhysicsWorld;
	/**
	 * �������ϲ�
	 * 
	 */
	protected Entity mSceneTopLayer;
	/**
	 * �����ڶ���
	 * 
	 */
	protected Entity mSceneSecondLayer;
	/**
	 * ����������
	 */
	protected Entity mSceneThirdLayer;
	/**
	 * ��ǰ���ڵĴ��
	 */
	protected int mSection;
	/**
	 * ��ǰ���ڵ�С��
	 */
	protected int mLevel;
	/**
	 * ��һ�صĽ���
	 */
	protected Class<?> mNextLevelClass;
	/**
	 * ��÷�õ�����
	 */
	protected TexturePack mShumeiGirlTexturePack;
	/**
	 * ����ʵ������
	 */
	protected TexturePack mSceneEntityTexturePack;
	/**
	 * ��÷����������
	 */
	protected TexturePack mVillainShumeiTexturePack;
	/**
	 * ����ϵͳ������
	 */
	protected TexturePack mParticlePack;

	/**
	 * ���ߵ�����
	 */
	protected TexturePack mPropPack;

	/**
	 * ��÷��ʶ����
	 * 
	 */
	protected TexturePack mShumeiRepresentTexturePack;
	/**
	 * ��Ϸ�����������
	 */
	protected TexturePack mGameResultTexturePack;

	/**
	 * �˳���Ϸ�Ի���
	 */
	protected TexturePack mExitDialogTexturePack;

	/**
	 * ��÷�õĸ�������
	 * 
	 */
	protected Text mShumeiGirlCountText;
	/**
	 * ������÷�õĸ���
	 */
	protected static int mShumeiGirlCount = 0;
	/**
	 * ��÷��ĸ�������
	 * 
	 */
	protected Text mShumeiBoyCountText;
	/**
	 * ��÷��ĸ���
	 */
	protected static int mShumeiBoyCount = 0;
	/**
	 * ���ٿ�������
	 */
	protected static int mSlowDownCardCount = 0;
	/**
	 * �ռ�ͨ�ؿ�������
	 */
	protected static int mPassCardCount = 0;

	/**
	 * �����ĸ���
	 * 
	 */
	protected static int mVillainCount = 0;

	/**
	 * �����ĸ�������
	 * 
	 */
	protected Text mVallianShumeiCountText;
	/**
	 * ��Ļ�ϻ��ŵ���÷����
	 * 
	 */
	protected static int mShumeiLiveCount = 0;
	/**
	 * ��������
	 * 
	 */
	protected StrokeFont mScoreFont;
	/**
	 * ��������
	 */
	protected Text mScoreText;
	/**
	 * ����
	 */
	protected int mScore = 0;
	/**
	 * ׹����Ч
	 * 
	 */
	protected Sound mFallDownDou, mFallDownRui, mFallDownMi, mFallDownFa,
			mFallDownSou, mFallDownLa, mHooray;
	/**
	 * ��÷�õ���ײ��Ч
	 * 
	 */
	protected List<Sound> mShumeiGirlSoundList;
	/**
	 * ����ͳ��ʱ��Ч
	 */
	protected List<Sound> mStarCountSoundList;
	/**
	 * û����÷���������Ч
	 * 
	 */
	protected Sound mNoShumeiSound;
	/**
	 * ͳ�Ʒ�ʱ����Ч
	 * 
	 */
	protected Sound mScoreCountSound;

	/**
	 * ��������
	 * 
	 */
	protected Music mBackgroundMusic;
	/**
	 * ���ּ�������
	 */
	protected Font mScoreCountFont;
	/**
	 * ���������÷�ٶ�
	 * 
	 */
	protected long mLastAddShumeiTime;

	/**
	 * ���˼���
	 */
	protected List<AbstractVillain> mVillainList = new ArrayList<AbstractVillain>();

	/**
	 * ����
	 * 
	 */
	public static int[] mSoundTurns = { 1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 5, 4, 3,
			2, 1 };
	protected int mSoundTurn = 0;

	/**
	 * ����Body����
	 * 
	 */
	protected PhysicsEditorShapeLibrary mPhysicsEditorShapeLibrary;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void createRealResource() {
		// ���ػ�������
		final ITexture scoreFontTexture = new BitmapTextureAtlas(
				this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		this.mScoreFont = new StrokeFont(this.getFontManager(),
				scoreFontTexture, Typeface.create(Typeface.DEFAULT,
						Typeface.BOLD), 36, true, Color.WHITE, 2, Color.BLACK);
		this.mScoreFont.load();

		final ITexture scoreCountFontTexture = new BitmapTextureAtlas(
				this.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		FontFactory.setAssetBasePath("font/");
		this.mScoreCountFont = FontFactory.createFromAsset(
				this.getFontManager(), scoreCountFontTexture, this.getAssets(),
				"ManateeSolid.ttf", 120, true, Color.WHITE);
		this.mScoreCountFont.load();

		try {
			// ������÷��ͼƬ
			mShumeiGirlTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "shumei_girl_pack.xml");
			mShumeiGirlTexturePack.loadTexture();

			// ������÷������ͼƬ
			mVillainShumeiTexturePack = new TexturePackLoader(
					getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "villain_pack.xml");
			mVillainShumeiTexturePack.loadTexture();

			// ��������Ч������
			mParticlePack = new TexturePackLoader(this.getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"particle_pack.xml");
			mParticlePack.loadTexture();

			// ��������
			mPropPack = new TexturePackLoader(this.getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"prop_pack.xml");
			mPropPack.loadTexture();

			// ��÷��ʶ����
			this.mShumeiRepresentTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "shumei_represent_pack.xml");
			this.mShumeiRepresentTexturePack.loadTexture();
			// ��Ϸ�����������
			mGameResultTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "game_result_pack.xml");
			mGameResultTexturePack.loadTexture();

			// �˳���Ϸ�Ի���
			this.mExitDialogTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "exit_dialog_pack.xml");
			this.mExitDialogTexturePack.loadTexture();

		} catch (TexturePackParseException e) {
			L.e(e);
			MobclickAgent.reportError(this, e.getMessage());
		}
		// ���ر�������
		if (isMusicOn()) {
			loadMusic();
		}
		// ����������Դ
		if (isSoundOn()) {
			loadSounds();
		}
		this.mPhysicsEditorShapeLibrary = new PhysicsEditorShapeLibrary();
		this.mPhysicsEditorShapeLibrary.open(this, "gfx/sprite_body.xml");
	}

	@Override
	public Scene createRealScene() {
		// ��������
		this.mScene = new Scene();
		this.mSceneTopLayer = new Entity();
		this.mSceneSecondLayer = new Entity();
		this.mSceneThirdLayer = new Entity();

		// �����̵깺�����Ʒ��ʼ��
		initShoppedProp();

		// ��ʼ�����صĲ���
		initLevelParameter();

		mScene.setOnSceneTouchListener(this);
		// ���������˶��ı���
		createParallaxBackground();
		// �����������
		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0,
				SensorManager.GRAVITY_EARTH), false);
		// ����ǽ��
		createPhysicsWorldWalls();
		// ���������д�����ײ
		this.mPhysicsWorld.setContactListener(getPhysicsWorldContactListener());
		// ������������ӵ�������
		mScene.registerUpdateHandler(mPhysicsWorld);
		// ���ͼ��
		mScene.attachChild(mSceneThirdLayer);
		mScene.attachChild(mSceneSecondLayer);
		mScene.attachChild(mSceneTopLayer);
		// ����
		this.mScoreText = new Text(115, 5, mScoreFont, String.valueOf(mScore),
				6, getVertexBufferObjectManager());
		mSceneTopLayer.attachChild(mScoreText);
		final Text scoreText = new Text(0, 5, mScoreFont, "Score:",
				getVertexBufferObjectManager());
		mSceneTopLayer.attachChild(scoreText);
		// ����������ť
		addReplayButton(mScene);

		// ��Scene�����ʵ�����
		addSceneEntityToScene();
		// ��Scene����ӻ���
		addVillainToScene();

		if (mShumeiBoyCount > 0) {
			addShumeiBoyRepresentToScene();
		}
		return mScene;
	}

	/**
	 * �����Ļ�¼�
	 */
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// ������Ļ��Ӿ���
		if (this.mPhysicsWorld != null) {
			if (pSceneTouchEvent.isActionDown()) {
				if (System.currentTimeMillis() - mLastAddShumeiTime > Const.Collison.ADD_SHUMEI_TIME_STEP) {
					float x = pSceneTouchEvent.getX();
					float y = pSceneTouchEvent.getY();
					if (mShumeiBoyCount > 0) {
						// ��������÷��
						addShumeiBoy(x, y);
					} else {
						addShumeiGirl(x, y);
					}
					mLastAddShumeiTime = System.currentTimeMillis();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public synchronized void onResumeGame() {
		super.onResumeGame();
		if (mBackgroundMusic != null) {
			if (!mBackgroundMusic.isPlaying())
				playMusic(mBackgroundMusic);
		}

	}

	@Override
	public synchronized void onPauseGame() {
		super.onPauseGame();
		// ��ͣ��������
		pauseMusic(mBackgroundMusic);
	}

	// ������ذ�ť��
	@Override
	public void onBackPressed() {
		// ���±��������
		mSettingsPreferences
				.edit()
				.putInt(Const.Settings.PROP_SHUMEI_BOY_QUANTITY,
						mShumeiBoyCount).commit();
		if (!this.mScene.hasChildScene()) {
			this.mScene.setChildScene(new ExitDialog(mScene, this,
					mExitDialogTexturePack, mSection), false, true, true);
		} else {
			super.onBackPressed();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * ��ʼ�����صĲ���
	 */
	protected abstract void initLevelParameter();

	/**
	 * ���������˶��ı���
	 */
	protected abstract void createParallaxBackground();

	/**
	 * ��Scene����ӻ���
	 */
	protected abstract void addVillainToScene();

	/**
	 * ��Scene�����ʵ�����
	 */
	protected abstract void addSceneEntityToScene();

	/**
	 * ��ȡ�����������ײ������
	 * 
	 * @return
	 */
	protected ContactListener getPhysicsWorldContactListener() {
		return new ContactListenerAdapter() {
			@Override
			public void beginContactWithShumei(Fixture pShumei, Fixture pOther) {
				// ��÷������ײ����
				AbstractShumei shumeiSprite = (AbstractShumei) getCollisionData(pShumei).sprite;
				shumeiSprite.changeOnBeginCollision();
				// �ж�����÷��ײ����������
				CollisionData otherCollisionData = getCollisionData(pOther);
				switch (otherCollisionData.role) {
				case Const.Collison.ROLE_SCENE_BULB: {
					// ��������
					SceneEntity bulb = (SceneEntity) getCollisionData(pOther).sprite;
					bulb.changeOnBeginCollision();// ����ײ��������1
					if (bulb.isFirstHit()) {
						mScore += bulb.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				case Const.Collison.ROLE_SCENE_STUMP: {
					// ������׮
					SceneEntity stump = (SceneEntity) getCollisionData(pOther).sprite;
					stump.changeOnBeginCollision();// ����ײ��������1
					if (stump.isFirstHit()) {
						mScore += stump.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				case Const.Collison.ROLE_SCENE_STICK: {
					// ����ľ��
					SceneEntity stick = (SceneEntity) getCollisionData(pOther).sprite;
					stick.changeOnBeginCollision();// ����ײ��������1
					if (stick.isFirstHit()) {
						mScore += stick.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				case Const.Collison.ROLE_SCENE_FIRE_BUG: {
					// ����ľ��
					SceneEntity bug = (SceneEntity) getCollisionData(pOther).sprite;
					bug.changeOnBeginCollision();// ����ײ��������1
					if (bug.isFirstHit()) {
						mScore += bug.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				default:
					break;
				}
			}

			@Override
			public void endContactWithShumei(Fixture pShumei, Fixture pOther) {
				AbstractShumei shumeiSprite = (AbstractShumei) getCollisionData(pShumei).sprite;
				shumeiSprite.changeOnEndCollision();
				final CollisionData otherCollisionData = getCollisionData(pOther);
				// �ж�����÷��ײ����������
				switch (otherCollisionData.role) {
				case Const.Collison.ROLE_VILLAIN_SHUMEI: {
					// ������������������ֵ
					VillainShumei vs = (VillainShumei) otherCollisionData.sprite;
					// ���»���
					mScore += shumeiSprite.getFightingPower();
					mScoreText.setText(String.valueOf(mScore));
					vs.setLife(vs.getLife() - shumeiSprite.getFightingPower());
					if (vs.isDead() && !otherCollisionData.removal) {
						vs.removeAlphaEntityModifer();
						playSmoke(vs.getX(), vs.getY());
						playSound(mHooray);
						// ���»�����������ʾ
						mVillainCount--;
						mVallianShumeiCountText.setText(String
								.valueOf(mVillainCount));
						otherCollisionData.removal = true;
						detachSpriteFromScene((IShape) otherCollisionData.sprite);
						break;
					}
					vs.changeOnEndCollision();
					break;
				}
				case Const.Collison.ROLE_SCENE_GROUND: {
					// ��������
					shumeiSprite.hitGroundWall();
					if (shumeiSprite.isDead()
							&& !getCollisionData(pShumei).removal) {
						getCollisionData(pShumei).removal = true;
						mShumeiLiveCount--;
						detachSpriteFromScene(shumeiSprite);
					}
					if (mVillainCount <= 0 && mShumeiLiveCount <= 0) {
						stopMusic(mBackgroundMusic);
						gameOver(true);
					} else if (mShumeiGirlCount + mShumeiBoyCount <= 0
							&& mShumeiLiveCount <= 0) {
						stopMusic(mBackgroundMusic);
						gameOver(false);
					}
					break;
				}
				default:
					break;
				}
			}

		};
	}

	/**
	 * ����ǽ��
	 * 
	 */
	protected void createPhysicsWorldWalls() {
		final Rectangle ground = new Rectangle(0, Const.Camera.HEIGHT,
				Const.Camera.WIDTH, 1, getVertexBufferObjectManager());
		final Rectangle left = new Rectangle(-1, 0, 1, Const.Camera.HEIGHT,
				getVertexBufferObjectManager());
		final Rectangle right = new Rectangle(Const.Camera.WIDTH, 0, 1,
				Const.Camera.HEIGHT, getVertexBufferObjectManager());
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,
				0.5f, 0.5f);
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, ground,
				BodyType.StaticBody, wallFixtureDef).setUserData(
				new CollisionData(Const.Collison.ROLE_SCENE_GROUND, ground));
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, left,
				BodyType.StaticBody, wallFixtureDef).setUserData(
				new CollisionData(Const.Collison.ROLE_SCENE_WALL, left));
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, right,
				BodyType.StaticBody, wallFixtureDef).setUserData(
				new CollisionData(Const.Collison.ROLE_SCENE_WALL, right));
		mScene.attachChild(ground);
		mScene.attachChild(left);
		mScene.attachChild(right);
	}

	/**
	 * �Ƴ�����
	 * 
	 * @param sprite
	 */
	protected void detachSpriteFromScene(final IShape sprite) {
		runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				PhysicsConnector pc = mPhysicsWorld
						.getPhysicsConnectorManager()
						.findPhysicsConnectorByShape(sprite);
				mPhysicsWorld.unregisterPhysicsConnector(pc);
				mPhysicsWorld.destroyBody(pc.getBody());
				sprite.setVisible(false);
				mSceneSecondLayer.detachChild(sprite);
				sprite.dispose();
				System.gc();
			}
		});

	}

	/**
	 * �����÷��
	 * 
	 * @param pX
	 * @param pY
	 */
	protected void addShumeiGirl(final float pX, final float pY) {
		if (mShumeiGirlCount > 0) {
			// ���ļ�������
			mShumeiGirlCount--;
			mShumeiLiveCount++;
			this.mShumeiGirlCountText.setText(String.valueOf(mShumeiGirlCount));
			// ��������
			final ShumeiGirl girl = ShumeiFactory.createShumeiGirl(pX, pY,
					mShumeiGirlTexturePack, getVertexBufferObjectManager(),
					this, Const.ShumeiFight.SHUMEI_GIRL_LEVEL1);
			// �����Ч�б�
			girl.setHitSoundList(mShumeiGirlSoundList);
			// ������Body
			final Body girlBody = this.mPhysicsEditorShapeLibrary
					.createBody(
							"shumei_girl"
									+ (mSettingsPreferences.getInt(
											Const.Settings.ROLE_ID,
											ShumeiGirlPack.SHUMEI_GIRL1_ID) + 1)
									+ "_0", girl, this.mPhysicsWorld);
			girlBody.setUserData(new CollisionData(
					Const.Collison.ROLE_SHUMEI_GIRL, girl));
			mSceneSecondLayer.attachChild(girl);

			this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(
					girl, girlBody, true, true));
			// ��ӳ���Ч��
			Sprite ripple = ParticleFactory.createRippleParticle(pX, pY,
					mParticlePack, getVertexBufferObjectManager());
			this.mSceneThirdLayer.attachChild(ripple);
			// ��������
			playShumeiFallingEffets();
		} else {
			playSound(mNoShumeiSound);
		}
	}

	/**
	 * �����÷��
	 * 
	 * @param pX
	 * @param pY
	 */
	protected void addShumeiBoy(final float pX, final float pY) {
		// ���ļ�������
		mShumeiBoyCount--;
		mShumeiLiveCount++;
		this.mShumeiBoyCountText.setText(String.valueOf(mShumeiBoyCount));
		// ��������
		final ShumeiBoy boy = ShumeiFactory.createShumeiBoy(pX, pY, mPropPack,
				getVertexBufferObjectManager(), this,
				Const.ShumeiFight.SHUMEI_BOY);

		// ������Body
		final Body body = this.mPhysicsEditorShapeLibrary.createBody(
				"shumei_boy1", boy, mPhysicsWorld);
		body.setUserData(new CollisionData(Const.Collison.ROLE_SHUMEI_BOY, boy));
		mSceneSecondLayer.attachChild(boy);
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(boy,
				body, true, true));
		// ��ӳ���Ч��
		Sprite ripple = ParticleFactory.createRippleParticle(pX, pY,
				mParticlePack, getVertexBufferObjectManager());
		this.mSceneThirdLayer.attachChild(ripple);
		// ��������
		playShumeiFallingEffets();
	}

	/**
	 * ���¿�ʼ��Ϸ
	 */
	public void restartGame() {
		this.mScore = 0;
		this.mScene.clearChildScene();
		this.mScene.reset();
		this.mEngine.setScene(createRealScene());
	}

	/**
	 * ���ô��̵깺�����Ʒ
	 */
	protected void initShoppedProp() {
		mShumeiBoyCount = mSettingsPreferences.getInt(
				Const.Settings.PROP_SHUMEI_BOY_QUANTITY, 0);
		mSlowDownCardCount = mSettingsPreferences.getInt(
				Const.Settings.PROP_SLOW_DOWN_CARD_QUANTITY, 0);
		mPassCardCount = mSettingsPreferences.getInt(
				Const.Settings.PROP_PASS_CARD_QUANTITY, 0);
	}

	/**
	 * ͬ�����������Static Body��ע����EntityModifer��Sprite��λ��
	 * 
	 * @param pBody
	 * @param pMovingSprite
	 */
	protected void syncBodyAndSpritePosition(Body pBody, Sprite pMovingSprite) {
		pBody.setTransform(
				(pMovingSprite.getX() + pMovingSprite.getWidth() / 2)
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				(pMovingSprite.getY() + pMovingSprite.getHeight() / 2)
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				MathUtils.degToRad(pMovingSprite.getRotation()));
	}

	/**
	 * ���������ӵ�������
	 * 
	 * @param v
	 */
	protected void attachVillainToScene(AbstractVillain v, Body body) {
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(v, body,
				true, true));
		attachVillainToScene(v);
	}

	/**
	 * ���������ӵ�������
	 * 
	 * @param v
	 */
	protected void attachVillainToScene(AbstractVillain v) {
		mSceneSecondLayer.attachChild(v);
		mVillainCount++;
		mVillainList.add(v);
	}

	/**
	 * ʹ�ü��ٿ����û������֣����ҿɼ�ʱ������1.5��
	 */
	protected void villainReappear() {
		for (AbstractVillain v : mVillainList) {
			if (v != null && !v.isDead()) {
				v.setTransientTime(v.getTransientTime() * 1.5f);
				v.changeOnEndCollision();
			}
		}
	}

	/**
	 * ʹ���ռ�ͨ�ؿ���ɱ��ȫ������
	 */
	protected void killAllVillain() {
		this.mScene.registerUpdateHandler(new TimerHandler(0.1f,
				new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						// Unregister this timer handler to ensure it does not
						// run again.
						getEngine().unregisterUpdateHandler(pTimerHandler);
						for (AbstractVillain v : mVillainList) {
							if (v != null && !v.isDead()) {
								// ���»���
								mScore += v.getPoint();
								mScoreText.setText(String.valueOf(mScore));
								v.setLife(-1);
								v.setAlpha(1);
								playSmoke(v.getX(), v.getY());
								// ���»�����������ʾ
								mVillainCount--;
								mVallianShumeiCountText.setText(String
										.valueOf(mVillainCount));
								detachSpriteFromScene(v);
							}
						}
						playSound(mHooray);
						gameOver(true);
					}
				}));
	}

	/**
	 * ����������ť
	 * 
	 * @param mScene
	 */
	private void addReplayButton(Scene mScene) {
		final ButtonSprite replay = new ButtonSprite(Const.Camera.WIDTH - 60,
				0, mShumeiRepresentTexturePack
						.getTexturePackTextureRegionLibrary().get(
								ShumeiRepresentPack.BTN_REPLAY_ID),
				getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite arg0, float arg1,
							float arg2) {
						restartGame();
					}
				});
		mScene.attachChild(replay);
		mScene.registerTouchArea(replay);
	}

	/**
	 * �����÷��ı�ʶ��Scene��
	 */
	protected void addShumeiBoyRepresentToScene() {
		Sprite sprite = new Sprite(235, -10, mShumeiRepresentTexturePack
				.getTexturePackTextureRegionLibrary().get(
						ShumeiRepresentPack.SHUMEI_BOY_ID),
				getVertexBufferObjectManager());
		this.mShumeiBoyCountText = new Text(280, 5, this.mScoreFont,
				String.valueOf(mShumeiBoyCount), 2,
				getVertexBufferObjectManager());
		this.mSceneTopLayer.attachChild(sprite);
		this.mSceneTopLayer.attachChild(mShumeiBoyCountText);
	}

	/**
	 * �����÷�õı�ʶ��Scene��
	 */
	protected void addShumeiGirlRepresentToScene() {
		Sprite sprite = new Sprite(315, -10, mShumeiRepresentTexturePack
				.getTexturePackTextureRegionLibrary().get(
						ShumeiRepresentPack.SHUMEI_GIRL_ID),
				getVertexBufferObjectManager());
		this.mShumeiGirlCountText = new Text(360, 5, mScoreFont,
				String.valueOf(mShumeiGirlCount), 2, new TextOptions(
						HorizontalAlign.RIGHT), getVertexBufferObjectManager());
		this.mSceneTopLayer.attachChild(sprite);
		this.mSceneTopLayer.attachChild(mShumeiGirlCountText);
	}

	/**
	 * ��ӻ����ı�ʶ��Scene��
	 */
	protected void addVallainRepresentToScene() {
		Sprite sprite = new Sprite(395, -10, mShumeiRepresentTexturePack
				.getTexturePackTextureRegionLibrary().get(
						ShumeiRepresentPack.VILLAIN_SHUMEI_ID),
				getVertexBufferObjectManager());

		this.mVallianShumeiCountText = new Text(440, 5, this.mScoreFont,
				String.valueOf(mVillainCount), 2,
				getVertexBufferObjectManager());
		this.mSceneTopLayer.attachChild(sprite);
		this.mSceneTopLayer.attachChild(mVallianShumeiCountText);

	}

	/**
	 * ��Ӽ��ٿ���ʶ
	 */
	protected void addSlowDownCardRepresentToScene() {
		final Sprite sprite = new Sprite(0, Const.Camera.HEIGHT - 70,
				mShumeiRepresentTexturePack
						.getTexturePackTextureRegionLibrary().get(
								ShumeiRepresentPack.SLOW_DOWN_CARD_ID),
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					// ������м���
					if (mSlowDownCardCount > 0) {
						mSlowDownCardCount--;
						mSettingsPreferences
								.edit()
								.putInt(Const.Settings.PROP_SLOW_DOWN_CARD_QUANTITY,
										mSlowDownCardCount).commit();
						villainReappear();
					}
					if (mSlowDownCardCount == 0) {
						this.detachSelf();
					}
				}
				return true;
			}
		};
		sprite.registerEntityModifier(new MoveModifier(3, sprite.getX(), sprite
				.getX(), sprite.getY() - 200, sprite.getY(), EaseBounceOut
				.getInstance()));
		this.mSceneTopLayer.attachChild(sprite);
		this.mScene.registerTouchArea(sprite);

	}

	/**
	 * ����ռ�ͨ�ؿ���ʶ
	 */
	protected void addPassCardRepresentToScene() {
		final Sprite sprite = new Sprite(430, Const.Camera.HEIGHT - 70,
				mShumeiRepresentTexturePack
						.getTexturePackTextureRegionLibrary().get(
								ShumeiRepresentPack.PASS_CARD_ID),
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					// ������м���
					if (mPassCardCount > 0) {
						mPassCardCount--;
						mSettingsPreferences
								.edit()
								.putInt(Const.Settings.PROP_PASS_CARD_QUANTITY,
										mPassCardCount).commit();
						killAllVillain();
					}
					if (mPassCardCount == 0) {
						this.detachSelf();
					}
				}
				return true;
			}
		};
		sprite.registerEntityModifier(new MoveModifier(3, sprite.getX(), sprite
				.getX(), sprite.getY() - 200, sprite.getY(), EaseBounceOut
				.getInstance()));
		this.mSceneTopLayer.attachChild(sprite);
		this.mScene.registerTouchArea(sprite);
	}

	/**
	 * ��Ϸ����
	 */
	protected void gameOver(boolean isPass) {
		// ���±��������
		mSettingsPreferences
				.edit()
				.putInt(Const.Settings.PROP_SHUMEI_BOY_QUANTITY,
						mShumeiBoyCount).commit();

		// ��Ϸ����
		if (!mScene.hasChildScene())
			mScene.setChildScene(new GameResultScene(mSection, mLevel, this,
					mNextLevelClass, isPass, mScore, mShumeiGirlCount
							+ mShumeiBoyCount, mGameResultTexturePack,
					mParticlePack, mScoreCountFont, mScoreCountSound,
					mStarCountSoundList), false, true, true);
	}

	/**
	 * ������Ϸ��Ч
	 */
	protected void loadSounds() {
		SoundFactory.setAssetBasePath(Const.ASSET_SOUNDS_BASE_PATH);
		try {
			// ׹����Ч
			this.mFallDownDou = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down1_dou.ogg");
			this.mFallDownRui = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down1_rui.ogg");
			this.mFallDownMi = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down1_mi.ogg");
			this.mFallDownFa = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down1_fa.ogg");
			this.mFallDownSou = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down1_sou.ogg");
			this.mFallDownLa = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down1_la.ogg");
			// ������Ч
			this.mHooray = SoundFactory.createSoundFromAsset(getSoundManager(),
					this, "hooray.ogg");

			// ��÷����ײ��Ч
			this.mShumeiGirlSoundList = new ArrayList<Sound>();
			this.mShumeiGirlSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "girl_hit_1.ogg"));
			this.mShumeiGirlSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "girl_hit_2.ogg"));
			// ����ͳ����Ч
			this.mStarCountSoundList = new ArrayList<Sound>();
			this.mStarCountSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "star_1.ogg"));
			this.mStarCountSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "star_2.ogg"));
			this.mStarCountSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "star_3.ogg"));

			this.mNoShumeiSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "fall_down_no_shumei.ogg");
			this.mScoreCountSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "score_count.ogg");
			this.mScoreCountSound.setLooping(true);

		} catch (IOException e) {
			L.e(e);
			MobclickAgent.reportError(this, e.getMessage());
		}
	}

	/**
	 * ���ر�������
	 */
	protected void loadMusic() {
		MusicFactory.setAssetBasePath(Const.ASSET_SOUNDS_BASE_PATH);
		// ����ѭ������
		try {
			this.mBackgroundMusic = MusicFactory.createMusicFromAsset(
					getEngine().getMusicManager(), this,
					setBackgroundMusicName());
			this.mBackgroundMusic.setLooping(true);
			playMusic(mBackgroundMusic);
		} catch (IOException e) {
			L.e(e);
			MobclickAgent.reportError(this, e.getMessage());
		}
	}

	/**
	 * ���Ż�����ʧ������
	 * 
	 * @param pX
	 * @param pY
	 */
	protected void playSmoke(float pX, float pY) {
		TexturePackerTextureRegion texturePackerTextureRegion = mParticlePack
				.getTexturePackTextureRegionLibrary()
				.get(ParticlePack.SMOKE_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				mParticlePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 3, 3);
		final AnimatedSprite smoke = new AnimatedSprite(pX, pY,
				tiledTextureRegion, getVertexBufferObjectManager());
		smoke.animate(40, false, new AnimationListenerAdapter() {
			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				final EngineLock engineLocker = getEngine().getEngineLock();
				engineLocker.lock();
				mSceneTopLayer.detachChild(smoke);
				smoke.dispose();
				engineLocker.unlock();
			}
		});
		this.mSceneTopLayer.attachChild(smoke);
	}

	/**
	 * ���ñ�����������
	 * 
	 * @return
	 */
	protected abstract String setBackgroundMusicName();

	/**
	 * ����������Ч
	 * 
	 * @param pX
	 * @param pY
	 */
	protected void playShumeiFallingEffets() {
		int turn = mSoundTurns[this.mSoundTurn % 15];
		switch (turn) {
		case 1:
			this.playSound(mFallDownDou);
			break;
		case 2:
			this.playSound(mFallDownRui);
			break;
		case 3:
			this.playSound(mFallDownMi);
			break;
		case 4:
			this.playSound(mFallDownFa);
			break;
		case 5:
			this.playSound(mFallDownSou);
			break;
		case 6:
			this.playSound(mFallDownLa);
			break;
		}
		this.mSoundTurn++;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
