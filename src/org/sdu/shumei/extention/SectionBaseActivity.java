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
 * 基本游戏场景Activity
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
	 * 物理世界
	 */
	protected PhysicsWorld mPhysicsWorld;
	/**
	 * 场景最上层
	 * 
	 */
	protected Entity mSceneTopLayer;
	/**
	 * 场景第二层
	 * 
	 */
	protected Entity mSceneSecondLayer;
	/**
	 * 场景第三层
	 */
	protected Entity mSceneThirdLayer;
	/**
	 * 当前所在的大关
	 */
	protected int mSection;
	/**
	 * 当前所在的小关
	 */
	protected int mLevel;
	/**
	 * 下一关的界面
	 */
	protected Class<?> mNextLevelClass;
	/**
	 * 树梅妹的纹理
	 */
	protected TexturePack mShumeiGirlTexturePack;
	/**
	 * 场景实体纹理
	 */
	protected TexturePack mSceneEntityTexturePack;
	/**
	 * 树梅坏蛋的纹理
	 */
	protected TexturePack mVillainShumeiTexturePack;
	/**
	 * 粒子系统的纹理
	 */
	protected TexturePack mParticlePack;

	/**
	 * 道具的纹理
	 */
	protected TexturePack mPropPack;

	/**
	 * 树梅标识纹理
	 * 
	 */
	protected TexturePack mShumeiRepresentTexturePack;
	/**
	 * 游戏结果界面纹理
	 */
	protected TexturePack mGameResultTexturePack;

	/**
	 * 退出游戏对话框
	 */
	protected TexturePack mExitDialogTexturePack;

	/**
	 * 树梅妹的个数文字
	 * 
	 */
	protected Text mShumeiGirlCountText;
	/**
	 * 本关树梅妹的个数
	 */
	protected static int mShumeiGirlCount = 0;
	/**
	 * 树梅哥的个数文字
	 * 
	 */
	protected Text mShumeiBoyCountText;
	/**
	 * 树梅哥的个数
	 */
	protected static int mShumeiBoyCount = 0;
	/**
	 * 减速卡的数量
	 */
	protected static int mSlowDownCardCount = 0;
	/**
	 * 终极通关卡的数量
	 */
	protected static int mPassCardCount = 0;

	/**
	 * 坏蛋的个数
	 * 
	 */
	protected static int mVillainCount = 0;

	/**
	 * 坏蛋的个数文字
	 * 
	 */
	protected Text mVallianShumeiCountText;
	/**
	 * 屏幕上活着的树梅个数
	 * 
	 */
	protected static int mShumeiLiveCount = 0;
	/**
	 * 积分字体
	 * 
	 */
	protected StrokeFont mScoreFont;
	/**
	 * 积分文字
	 */
	protected Text mScoreText;
	/**
	 * 积分
	 */
	protected int mScore = 0;
	/**
	 * 坠落音效
	 * 
	 */
	protected Sound mFallDownDou, mFallDownRui, mFallDownMi, mFallDownFa,
			mFallDownSou, mFallDownLa, mHooray;
	/**
	 * 树梅妹的碰撞音效
	 * 
	 */
	protected List<Sound> mShumeiGirlSoundList;
	/**
	 * 星星统计时音效
	 */
	protected List<Sound> mStarCountSoundList;
	/**
	 * 没有树梅可以添加音效
	 * 
	 */
	protected Sound mNoShumeiSound;
	/**
	 * 统计分时的音效
	 * 
	 */
	protected Sound mScoreCountSound;

	/**
	 * 背景音乐
	 * 
	 */
	protected Music mBackgroundMusic;
	/**
	 * 积分计数字体
	 */
	protected Font mScoreCountFont;
	/**
	 * 控制添加树梅速度
	 * 
	 */
	protected long mLastAddShumeiTime;

	/**
	 * 坏人集合
	 */
	protected List<AbstractVillain> mVillainList = new ArrayList<AbstractVillain>();

	/**
	 * 乐谱
	 * 
	 */
	public static int[] mSoundTurns = { 1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 5, 4, 3,
			2, 1 };
	protected int mSoundTurn = 0;

	/**
	 * 物理Body集合
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
		// 加载积分字体
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
			// 加载树梅妹图片
			mShumeiGirlTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "shumei_girl_pack.xml");
			mShumeiGirlTexturePack.loadTexture();

			// 加载树梅坏蛋的图片
			mVillainShumeiTexturePack = new TexturePackLoader(
					getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "villain_pack.xml");
			mVillainShumeiTexturePack.loadTexture();

			// 加载粒子效果纹理
			mParticlePack = new TexturePackLoader(this.getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"particle_pack.xml");
			mParticlePack.loadTexture();

			// 道具纹理
			mPropPack = new TexturePackLoader(this.getTextureManager(),
					Const.ASSET_IMAGES_BASE_PATH).loadFromAsset(getAssets(),
					"prop_pack.xml");
			mPropPack.loadTexture();

			// 树梅标识纹理
			this.mShumeiRepresentTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "shumei_represent_pack.xml");
			this.mShumeiRepresentTexturePack.loadTexture();
			// 游戏结果界面纹理
			mGameResultTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "game_result_pack.xml");
			mGameResultTexturePack.loadTexture();

			// 退出游戏对话框
			this.mExitDialogTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "exit_dialog_pack.xml");
			this.mExitDialogTexturePack.loadTexture();

		} catch (TexturePackParseException e) {
			L.e(e);
			MobclickAgent.reportError(this, e.getMessage());
		}
		// 加载背景音乐
		if (isMusicOn()) {
			loadMusic();
		}
		// 加载声音资源
		if (isSoundOn()) {
			loadSounds();
		}
		this.mPhysicsEditorShapeLibrary = new PhysicsEditorShapeLibrary();
		this.mPhysicsEditorShapeLibrary.open(this, "gfx/sprite_body.xml");
	}

	@Override
	public Scene createRealScene() {
		// 创建场景
		this.mScene = new Scene();
		this.mSceneTopLayer = new Entity();
		this.mSceneSecondLayer = new Entity();
		this.mSceneThirdLayer = new Entity();

		// 根据商店购买的物品初始化
		initShoppedProp();

		// 初始化本关的参数
		initLevelParameter();

		mScene.setOnSceneTouchListener(this);
		// 创建并行运动的背景
		createParallaxBackground();
		// 添加物理世界
		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0,
				SensorManager.GRAVITY_EARTH), false);
		// 设置墙壁
		createPhysicsWorldWalls();
		// 物理世界中处理碰撞
		this.mPhysicsWorld.setContactListener(getPhysicsWorldContactListener());
		// 将物理世界添加到场景中
		mScene.registerUpdateHandler(mPhysicsWorld);
		// 添加图层
		mScene.attachChild(mSceneThirdLayer);
		mScene.attachChild(mSceneSecondLayer);
		mScene.attachChild(mSceneTopLayer);
		// 积分
		this.mScoreText = new Text(115, 5, mScoreFont, String.valueOf(mScore),
				6, getVertexBufferObjectManager());
		mSceneTopLayer.attachChild(mScoreText);
		final Text scoreText = new Text(0, 5, mScoreFont, "Score:",
				getVertexBufferObjectManager());
		mSceneTopLayer.attachChild(scoreText);
		// 添加重玩儿按钮
		addReplayButton(mScene);

		// 向Scene中添加实体道具
		addSceneEntityToScene();
		// 向Scene中添加坏蛋
		addVillainToScene();

		if (mShumeiBoyCount > 0) {
			addShumeiBoyRepresentToScene();
		}
		return mScene;
	}

	/**
	 * 点击屏幕事件
	 */
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// 触摸屏幕添加精灵
		if (this.mPhysicsWorld != null) {
			if (pSceneTouchEvent.isActionDown()) {
				if (System.currentTimeMillis() - mLastAddShumeiTime > Const.Collison.ADD_SHUMEI_TIME_STEP) {
					float x = pSceneTouchEvent.getX();
					float y = pSceneTouchEvent.getY();
					if (mShumeiBoyCount > 0) {
						// 购买了树梅哥
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
		// 暂停背景音乐
		pauseMusic(mBackgroundMusic);
	}

	// 点击返回按钮后
	@Override
	public void onBackPressed() {
		// 更新保存的数量
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
	 * 初始化本关的参数
	 */
	protected abstract void initLevelParameter();

	/**
	 * 创建并行运动的背景
	 */
	protected abstract void createParallaxBackground();

	/**
	 * 向Scene中添加坏蛋
	 */
	protected abstract void addVillainToScene();

	/**
	 * 向Scene中添加实体道具
	 */
	protected abstract void addSceneEntityToScene();

	/**
	 * 获取物理世界的碰撞监听器
	 * 
	 * @return
	 */
	protected ContactListener getPhysicsWorldContactListener() {
		return new ContactListenerAdapter() {
			@Override
			public void beginContactWithShumei(Fixture pShumei, Fixture pOther) {
				// 树梅播放碰撞动画
				AbstractShumei shumeiSprite = (AbstractShumei) getCollisionData(pShumei).sprite;
				shumeiSprite.changeOnBeginCollision();
				// 判断与树梅碰撞的物体类型
				CollisionData otherCollisionData = getCollisionData(pOther);
				switch (otherCollisionData.role) {
				case Const.Collison.ROLE_SCENE_BULB: {
					// 碰到灯泡
					SceneEntity bulb = (SceneEntity) getCollisionData(pOther).sprite;
					bulb.changeOnBeginCollision();// 并且撞击次数加1
					if (bulb.isFirstHit()) {
						mScore += bulb.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				case Const.Collison.ROLE_SCENE_STUMP: {
					// 碰到树桩
					SceneEntity stump = (SceneEntity) getCollisionData(pOther).sprite;
					stump.changeOnBeginCollision();// 并且撞击次数加1
					if (stump.isFirstHit()) {
						mScore += stump.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				case Const.Collison.ROLE_SCENE_STICK: {
					// 碰到木板
					SceneEntity stick = (SceneEntity) getCollisionData(pOther).sprite;
					stick.changeOnBeginCollision();// 并且撞击次数加1
					if (stick.isFirstHit()) {
						mScore += stick.getPoint();
						mScoreText.setText(String.valueOf(mScore));
					}
					break;
				}
				case Const.Collison.ROLE_SCENE_FIRE_BUG: {
					// 碰到木板
					SceneEntity bug = (SceneEntity) getCollisionData(pOther).sprite;
					bug.changeOnBeginCollision();// 并且撞击次数加1
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
				// 判断与树梅碰撞的物体类型
				switch (otherCollisionData.role) {
				case Const.Collison.ROLE_VILLAIN_SHUMEI: {
					// 碰到坏蛋，计算生命值
					VillainShumei vs = (VillainShumei) otherCollisionData.sprite;
					// 更新积分
					mScore += shumeiSprite.getFightingPower();
					mScoreText.setText(String.valueOf(mScore));
					vs.setLife(vs.getLife() - shumeiSprite.getFightingPower());
					if (vs.isDead() && !otherCollisionData.removal) {
						vs.removeAlphaEntityModifer();
						playSmoke(vs.getX(), vs.getY());
						playSound(mHooray);
						// 更新坏蛋数量的显示
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
					// 碰到地面
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
	 * 设置墙壁
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
	 * 移除精灵
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
	 * 添加树梅妹
	 * 
	 * @param pX
	 * @param pY
	 */
	protected void addShumeiGirl(final float pX, final float pY) {
		if (mShumeiGirlCount > 0) {
			// 更改计数文字
			mShumeiGirlCount--;
			mShumeiLiveCount++;
			this.mShumeiGirlCountText.setText(String.valueOf(mShumeiGirlCount));
			// 创建精灵
			final ShumeiGirl girl = ShumeiFactory.createShumeiGirl(pX, pY,
					mShumeiGirlTexturePack, getVertexBufferObjectManager(),
					this, Const.ShumeiFight.SHUMEI_GIRL_LEVEL1);
			// 添加音效列表
			girl.setHitSoundList(mShumeiGirlSoundList);
			// 绑定物理Body
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
			// 添加出场效果
			Sprite ripple = ParticleFactory.createRippleParticle(pX, pY,
					mParticlePack, getVertexBufferObjectManager());
			this.mSceneThirdLayer.attachChild(ripple);
			// 播放声音
			playShumeiFallingEffets();
		} else {
			playSound(mNoShumeiSound);
		}
	}

	/**
	 * 添加树梅哥
	 * 
	 * @param pX
	 * @param pY
	 */
	protected void addShumeiBoy(final float pX, final float pY) {
		// 更改计数文字
		mShumeiBoyCount--;
		mShumeiLiveCount++;
		this.mShumeiBoyCountText.setText(String.valueOf(mShumeiBoyCount));
		// 创建精灵
		final ShumeiBoy boy = ShumeiFactory.createShumeiBoy(pX, pY, mPropPack,
				getVertexBufferObjectManager(), this,
				Const.ShumeiFight.SHUMEI_BOY);

		// 绑定物理Body
		final Body body = this.mPhysicsEditorShapeLibrary.createBody(
				"shumei_boy1", boy, mPhysicsWorld);
		body.setUserData(new CollisionData(Const.Collison.ROLE_SHUMEI_BOY, boy));
		mSceneSecondLayer.attachChild(boy);
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(boy,
				body, true, true));
		// 添加出场效果
		Sprite ripple = ParticleFactory.createRippleParticle(pX, pY,
				mParticlePack, getVertexBufferObjectManager());
		this.mSceneThirdLayer.attachChild(ripple);
		// 播放声音
		playShumeiFallingEffets();
	}

	/**
	 * 重新开始游戏
	 */
	public void restartGame() {
		this.mScore = 0;
		this.mScene.clearChildScene();
		this.mScene.reset();
		this.mEngine.setScene(createRealScene());
	}

	/**
	 * 设置从商店购买的物品
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
	 * 同步物理世界的Static Body和注册了EntityModifer的Sprite的位置
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
	 * 将坏蛋附加到场景中
	 * 
	 * @param v
	 */
	protected void attachVillainToScene(AbstractVillain v, Body body) {
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(v, body,
				true, true));
		attachVillainToScene(v);
	}

	/**
	 * 将坏蛋附加到场景中
	 * 
	 * @param v
	 */
	protected void attachVillainToScene(AbstractVillain v) {
		mSceneSecondLayer.attachChild(v);
		mVillainCount++;
		mVillainList.add(v);
	}

	/**
	 * 使用减速卡，让坏人重现，并且可见时间增加1.5倍
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
	 * 使用终极通关卡，杀死全部坏蛋
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
								// 更新积分
								mScore += v.getPoint();
								mScoreText.setText(String.valueOf(mScore));
								v.setLife(-1);
								v.setAlpha(1);
								playSmoke(v.getX(), v.getY());
								// 更新坏蛋数量的显示
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
	 * 添加重玩儿按钮
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
	 * 添加树梅哥的标识到Scene中
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
	 * 添加树梅妹的标识到Scene中
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
	 * 添加坏蛋的标识到Scene中
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
	 * 添加减速卡标识
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
					// 点击进行减速
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
	 * 添加终极通关卡标识
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
					// 点击进行减速
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
	 * 游戏结束
	 */
	protected void gameOver(boolean isPass) {
		// 更新保存的数量
		mSettingsPreferences
				.edit()
				.putInt(Const.Settings.PROP_SHUMEI_BOY_QUANTITY,
						mShumeiBoyCount).commit();

		// 游戏结束
		if (!mScene.hasChildScene())
			mScene.setChildScene(new GameResultScene(mSection, mLevel, this,
					mNextLevelClass, isPass, mScore, mShumeiGirlCount
							+ mShumeiBoyCount, mGameResultTexturePack,
					mParticlePack, mScoreCountFont, mScoreCountSound,
					mStarCountSoundList), false, true, true);
	}

	/**
	 * 加载游戏音效
	 */
	protected void loadSounds() {
		SoundFactory.setAssetBasePath(Const.ASSET_SOUNDS_BASE_PATH);
		try {
			// 坠落音效
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
			// 欢呼音效
			this.mHooray = SoundFactory.createSoundFromAsset(getSoundManager(),
					this, "hooray.ogg");

			// 树梅妹碰撞音效
			this.mShumeiGirlSoundList = new ArrayList<Sound>();
			this.mShumeiGirlSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "girl_hit_1.ogg"));
			this.mShumeiGirlSoundList.add(SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "girl_hit_2.ogg"));
			// 星星统计音效
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
	 * 加载背景音乐
	 */
	protected void loadMusic() {
		MusicFactory.setAssetBasePath(Const.ASSET_SOUNDS_BASE_PATH);
		// 背景循环音乐
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
	 * 播放坏蛋消失的烟雾
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
	 * 设置背景音乐名称
	 * 
	 * @return
	 */
	protected abstract String setBackgroundMusicName();

	/**
	 * 播放声音音效
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
