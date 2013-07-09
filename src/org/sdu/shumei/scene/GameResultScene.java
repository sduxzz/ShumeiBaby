package org.sdu.shumei.scene;

import java.util.List;
import java.util.Random;

import org.andengine.audio.sound.Sound;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.RectangleParticleEmitter;
import org.andengine.entity.particle.initializer.AccelerationParticleInitializer;
import org.andengine.entity.particle.initializer.AlphaParticleInitializer;
import org.andengine.entity.particle.initializer.BlendFunctionParticleInitializer;
import org.andengine.entity.particle.initializer.ColorParticleInitializer;
import org.andengine.entity.particle.initializer.RotationParticleInitializer;
import org.andengine.entity.particle.initializer.ScaleParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.entity.particle.modifier.OffCameraExpireParticleModifier;
import org.andengine.entity.particle.modifier.RotationParticleModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.font.IFont;
import org.andengine.util.modifier.ease.EaseBounceOut;
import org.sdu.shumei.Const;
import org.sdu.shumei.R;
import org.sdu.shumei.activity.Section1LevelChoice;
import org.sdu.shumei.activity.Section2LevelChoice;
import org.sdu.shumei.activity.Shop;
import org.sdu.shumei.database.dao.ProgressDAO;
import org.sdu.shumei.database.model.Progress;
import org.sdu.shumei.extention.SectionBaseActivity;
import org.sdu.shumei.resource.GameResultPack;
import org.sdu.shumei.resource.ParticlePack;
import org.sdu.shumei.utils.L;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.GLES20;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

/**
 * ��Ϸ���ͼ��
 * 
 * @author Craig Lee
 * 
 */
public class GameResultScene extends Scene {

	// ===========================================================
	// Constants
	// ===========================================================
	/**
	 * ��÷����
	 */
	public static final int[] SHUMEI_PARTICLES = {
			ParticlePack.SHUMEI_GIRL1_ID, ParticlePack.SHUMEI_GIRL2_ID,
			ParticlePack.SHUMEI_GIRL3_ID, ParticlePack.SHUMEI_GIRL4_ID,
			ParticlePack.SHUMEI_GIRL5_ID };

	public static final Class<?>[] SECTION_LEVELS = {
			Section1LevelChoice.class, Section2LevelChoice.class };

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * ����ѡ��
	 */
	private SharedPreferences mSettingsPreferences;
	private int mSection;
	private int mLevel;
	// ��ǰ���ڵ�Activity
	private SectionBaseActivity mContext;
	private Class<?> mNextLevel;
	// �Ƿ�ͨ��
	private boolean mIsPass;
	// ��õĻ���
	private int mEarnedScore;
	// ʣ�����÷����������������
	private int mShumeiCount;
	// ��ͼ�����������
	private TexturePack mTexturePack;
	private TexturePack mParticleTexturePack;
	// ��������
	private IFont mScoreCountFont;
	private Text mScoreCount;
	// ���ּ�����Ч
	private Sound mScoreCountSound;
	// �������ǵ���Ч
	private List<Sound> mStarCountSoundList;
	// ֮ǰ�ۼƵĻ���
	private int mBaseScore;
	private ProgressDAO progressDAO;
	// �����ϵİ�ť
	private ButtonSprite mShopButton, mMainMenuButton, mReplayButton,
			mNextButton;
	private Random mRandom;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GameResultScene(int section, int level,
			SectionBaseActivity mContext, Class<?> mNextLevel, boolean mIsPass,
			int mEarnedScore, int mShumeiCount, TexturePack mTexturePack,
			TexturePack mParticleTexturePack, IFont mScoreCountFont,
			Sound mScoreCountSound, List<Sound> mStarCoundSoundList) {
		this.mSection = section;
		this.mLevel = level;
		this.mContext = mContext;
		this.mNextLevel = mNextLevel;
		this.mIsPass = mIsPass;
		this.mEarnedScore = mEarnedScore;
		this.mShumeiCount = mShumeiCount;
		this.mTexturePack = mTexturePack;
		this.mParticleTexturePack = mParticleTexturePack;
		this.mScoreCountFont = mScoreCountFont;
		this.mScoreCountSound = mScoreCountSound;
		this.mStarCountSoundList = mStarCoundSoundList;
		this.mSettingsPreferences = mContext.getSharedPreferences(
				Const.Settings.SETTINGS_NAME, Context.MODE_PRIVATE);
		this.mRandom = new Random();
		progressDAO = new ProgressDAO(mContext);
		init();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ��ʼ��
	private void init() {
		final TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mTexturePack
				.getTexturePackTextureRegionLibrary();
		// ���ñ���
		this.setBackgroundEnabled(false);
		Sprite grayBackground = new Sprite(0, 0,
				texturePackTextureRegionLibrary
						.get(GameResultPack.BACKGROUND_ID),
				mContext.getVertexBufferObjectManager());
		this.attachChild(grayBackground);
		// �������ϵͳ
		createFallingParticleSystem();
		// ��Ӱ�ť
		addButtons();
		if (this.mIsPass) {
			// ͨ�ؽ���
			final ProgressDAO progressDAO = new ProgressDAO(mContext);
			this.mBaseScore = progressDAO.getScoreEarned(mSection, mLevel);
			// ������ǵ�λ��
			addDarkStar();
			// ���ͨ����ʾ����
			Sprite passText = new Sprite(41, 170,
					texturePackTextureRegionLibrary
							.get(GameResultPack.TEXT_PASSED_ID),
					mContext.getVertexBufferObjectManager());
			passText.registerEntityModifier(new MoveModifier(1.5f, passText
					.getX(), passText.getX(), -passText.getHeight(), passText
					.getY(), EaseBounceOut.getInstance()));
			this.attachChild(passText);

			this.mScoreCount = new Text(130, 440, mScoreCountFont,
					String.valueOf(this.mBaseScore), 6,
					mContext.getVertexBufferObjectManager());
			this.attachChild(mScoreCount);
			// ��ʼ����
			countScoreAndStar();

			// �������ݿ�����
			saveProgressToDB(Const.LevelStatus.UNLOCK_PASS, mEarnedScore,
					calculateStar(mSection, mLevel, mShumeiCount));
		} else {
			// δͨ�ؽ���
			// �������ݿ�����
			saveProgressToDB(Const.LevelStatus.UNLOCK_NOT_PASS, 0, 0);

			Sprite vallain = new Sprite(54, 230,
					texturePackTextureRegionLibrary
							.get(GameResultPack.FAIL_VALLAIN_ID),
					mContext.getVertexBufferObjectManager());
			this.attachChild(vallain);
			// ���δͨ����ʾ����
			Sprite failText = new Sprite(41, 170,
					texturePackTextureRegionLibrary
							.get(GameResultPack.TEXT_FAIL_ID),
					mContext.getVertexBufferObjectManager());
			failText.registerEntityModifier(new MoveModifier(1.5f, failText
					.getX(), failText.getX(), -failText.getHeight(), failText
					.getY(), EaseBounceOut.getInstance()));

			this.attachChild(failText);
			enableButtonTouchArea();
		}
	}

	// ��Ӱ�ť
	private void addButtons() {
		this.setTouchAreaBindingOnActionDownEnabled(true);
		final TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mTexturePack
				.getTexturePackTextureRegionLibrary();
		// �̵갴ť
		mShopButton = new ButtonSprite(0, 0,
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_SHOP_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_SHOP_NORMAL_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// �����̵�
						Intent intent = new Intent(mContext, Shop.class);
						mContext.startActivity(intent);
					}
				});
		this.attachChild(mShopButton);
		// ���˵���ť
		mMainMenuButton = new ButtonSprite(54, 612,
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_MAIN_MENU_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_MAIN_MENU_PRESSED_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_MAIN_MENU_DISABLED_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// �������ڴ�ص����˵�
						// mContext.startActivity(new Intent(mContext,
						// SECTION_LEVELS[mSection - 1]));
						mContext.finish();
					}
				});
		this.attachChild(mMainMenuButton);

		// �����水ť
		mReplayButton = new ButtonSprite(205, 612,
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_REPLAY_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_REPLAY_PRESSED_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_REPLAY_DISABLED_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						mContext.restartGame();
					}
				});
		this.attachChild(mReplayButton);

		// �����һ�صİ�ť
		mNextButton = new ButtonSprite(355, 612,
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_NEXT_NORMAL_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_NEXT_PRESSED_ID),
				texturePackTextureRegionLibrary
						.get(GameResultPack.BUTTON_NEXT_DISABLED_ID),
				mContext.getVertexBufferObjectManager(), new OnClickListener() {
					@Override
					public void onClick(ButtonSprite pButtonSprite,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						// ������һ��
						mContext.startActivity(new Intent(mContext, mNextLevel));
						mContext.finish();
					}
				});
		this.attachChild(mNextButton);

		this.registerTouchArea(mShopButton);
		this.registerTouchArea(mMainMenuButton);
		this.registerTouchArea(mReplayButton);
		this.registerTouchArea(mNextButton);

		mShopButton.setEnabled(false);
		mMainMenuButton.setEnabled(false);
		mReplayButton.setEnabled(false);
		mNextButton.setEnabled(false);
	}

	/**
	 * ע�ᰴť��������
	 */
	private void enableButtonTouchArea() {
		mShopButton.setEnabled(true);
		mMainMenuButton.setEnabled(true);
		mReplayButton.setEnabled(true);
		if (!progressDAO.isLock(mSection, mLevel + 1) && mNextLevel != null)
			mNextButton.setEnabled(true);
		if (mNextLevel == null && mIsPass) {
			// ��һ��ؽ���
			mContext.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(mContext, R.string.gameresult_endofsection,
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	// ������ǵ�λ��
	private void addDarkStar() {
		final TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mTexturePack
				.getTexturePackTextureRegionLibrary();
		Sprite star1 = new Sprite(43, 290,
				texturePackTextureRegionLibrary
						.get(GameResultPack.STAR_DARK_ID),
				mContext.getVertexBufferObjectManager());
		this.attachChild(star1);
		Sprite star2 = new Sprite(197, 290,
				texturePackTextureRegionLibrary
						.get(GameResultPack.STAR_DARK_ID),
				mContext.getVertexBufferObjectManager());
		this.attachChild(star2);
		Sprite star3 = new Sprite(359, 290,
				texturePackTextureRegionLibrary
						.get(GameResultPack.STAR_DARK_ID),
				mContext.getVertexBufferObjectManager());
		this.attachChild(star3);
	}

	// ��������������
	private void countScoreAndStar() {
		final Runnable starRunnable = new Runnable() {
			@Override
			public void run() {
				final int star = calculateStar(mSection, mLevel, mShumeiCount);
				Sprite sprite = null;
				TexturePackerTextureRegion texturePackerTextureRegion = mTexturePack
						.getTexturePackTextureRegionLibrary().get(
								GameResultPack.STAR_LIGHT_ID);
				int pX;
				try {
					for (int i = 0; i < star; i++) {

						if (i == 0) {
							pX = 43;
							Thread.sleep(400);
						} else if (i == 1)
							pX = 197;
						else
							pX = 359;
						sprite = new Sprite(pX, 290,
								texturePackerTextureRegion,
								mContext.getVertexBufferObjectManager());
						attachChild(sprite);
						if (mStarCountSoundList != null)
							playSound(mStarCountSoundList.get(i));
						Thread.sleep(700);

					}
				} catch (InterruptedException e) {
					L.e(e);
					MobclickAgent.reportError(mContext, e.getMessage());
				}
				enableButtonTouchArea();
			}
		};

		final Runnable scoreRunnable = new Runnable() {
			@Override
			public void run() {
				int step = 20;
				playSound(mScoreCountSound);
				int count = mEarnedScore;
				while (count >= step) {
					count -= step;
					mBaseScore += step;
					mScoreCount.setText(String.valueOf(mBaseScore));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						L.e(e);
						MobclickAgent.reportError(mContext, e.getMessage());
					}
				}
				stopSound(mScoreCountSound);
				new Thread(starRunnable).start();
			}
		};
		Thread scoreThread = new Thread(scoreRunnable);
		scoreThread.start();
	}

	/**
	 * �������ϵͳ
	 */
	private void createFallingParticleSystem() {
		int firstParticle = this.mRandom.nextInt(SHUMEI_PARTICLES.length);
		int secondParticle = (firstParticle + 1) % SHUMEI_PARTICLES.length;
		final TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = this.mParticleTexturePack
				.getTexturePackTextureRegionLibrary();

		final RectangleParticleEmitter rectangleParticleEmitter = new RectangleParticleEmitter(
				Const.Camera.WIDTH / 2 - 50, 120, Const.Camera.WIDTH - 200, 1);
		// ��һ������ϵͳ
		final SpriteParticleSystem particleSystem1 = new SpriteParticleSystem(
				rectangleParticleEmitter, 2, 4, 6,
				texturePackTextureRegionLibrary
						.get(SHUMEI_PARTICLES[firstParticle]),
				mContext.getVertexBufferObjectManager());
		particleSystem1
				.addParticleInitializer(new ColorParticleInitializer<Sprite>(0,
						0, 0, 1, 1, 1));
		particleSystem1
				.addParticleInitializer(new AlphaParticleInitializer<Sprite>(0f));
		particleSystem1
				.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(
						GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
		particleSystem1
				.addParticleInitializer(new VelocityParticleInitializer<Sprite>(
						0, 0, 50, 140));
		particleSystem1
				.addParticleInitializer(new AccelerationParticleInitializer<Sprite>(
						0, 0, 60, 150));
		particleSystem1
				.addParticleInitializer(new ScaleParticleInitializer<Sprite>(
						0.5f, 1.5f));
		particleSystem1
				.addParticleInitializer(new RotationParticleInitializer<Sprite>(
						0, 360));
		particleSystem1
				.addParticleInitializer(new ExpireParticleInitializer<Sprite>(
						2.5f));
		particleSystem1
				.addParticleModifier(new OffCameraExpireParticleModifier<Sprite>(
						mContext.getEngine().getCamera()));
		particleSystem1.addParticleModifier(new AlphaParticleModifier<Sprite>(
				0, 1.5f, 0f, 0.7f));
		particleSystem1.addParticleModifier(new AlphaParticleModifier<Sprite>(
				1.5f, 3, 0.7f, 0f));
		particleSystem1
				.addParticleModifier(new RotationParticleModifier<Sprite>(0, 3,
						-360, 0));
		this.attachChild(particleSystem1);

		// �ڶ�������ϵͳ
		final SpriteParticleSystem particleSystem2 = new SpriteParticleSystem(
				rectangleParticleEmitter, 2, 4, 6,
				texturePackTextureRegionLibrary
						.get(SHUMEI_PARTICLES[secondParticle]),
				mContext.getVertexBufferObjectManager());
		particleSystem2
				.addParticleInitializer(new ColorParticleInitializer<Sprite>(0,
						0, 0, 1, 1, 1));
		particleSystem2
				.addParticleInitializer(new AlphaParticleInitializer<Sprite>(0f));
		particleSystem2
				.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(
						GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
		particleSystem2
				.addParticleInitializer(new VelocityParticleInitializer<Sprite>(
						0, 0, 50, 140));
		particleSystem2
				.addParticleInitializer(new AccelerationParticleInitializer<Sprite>(
						0, 0, 60, 150));
		particleSystem2
				.addParticleInitializer(new ScaleParticleInitializer<Sprite>(
						0.5f, 1.5f));
		particleSystem2
				.addParticleInitializer(new RotationParticleInitializer<Sprite>(
						0, 360));
		particleSystem2
				.addParticleInitializer(new ExpireParticleInitializer<Sprite>(
						2.5f));
		particleSystem2
				.addParticleModifier(new OffCameraExpireParticleModifier<Sprite>(
						mContext.getEngine().getCamera()));
		particleSystem2.addParticleModifier(new AlphaParticleModifier<Sprite>(
				0, 1.5f, 0f, 0.7f));
		particleSystem2.addParticleModifier(new AlphaParticleModifier<Sprite>(
				1.5f, 3, 0.7f, 0));
		particleSystem2
				.addParticleModifier(new RotationParticleModifier<Sprite>(0, 3,
						-360, 0));
		this.attachChild(particleSystem2);
	}

	// �������ݿ�����
	private void saveProgressToDB(int status, int score, int star) {
		Progress progress = new Progress();
		progress.setSection(mSection);
		progress.setLevel(mLevel);
		progress.setStatus(status);
		progress.setScore(score);
		progress.setStar(star);
		progressDAO.saveProgress(progress);
		if (mLevel < Const.LevelStatus.LEVEL_QUANTITY && mIsPass) {
			// ������һ��
			Progress nextProgress = new Progress();
			nextProgress.setSection(mSection);
			nextProgress.setLevel(mLevel + 1);
			nextProgress.setStatus(Const.LevelStatus.UNLOCK_NOT_PASS);
			nextProgress.setScore(0);
			nextProgress.setStar(0);
			progressDAO.saveProgress(nextProgress);
		}
	}

	/**
	 * ��������
	 * 
	 * @param pSound
	 */
	private void playSound(Sound pSound) {
		if (pSound != null) {
			if (this.mSettingsPreferences.getBoolean(
					Const.Settings.SOUND_EFFECTS, true)) {
				pSound.play();
			}
		}
	}

	/**
	 * ֹͣ����
	 * 
	 * @param pSound
	 */
	private void stopSound(Sound pSound) {
		if (pSound != null) {
			if (this.mSettingsPreferences.getBoolean(
					Const.Settings.SOUND_EFFECTS, true)) {
				pSound.setLooping(false);
				pSound.stop();
			}
		}
	}

	/**
	 * ����ĳ��ʣ����÷��������ö�������
	 * 
	 * @param section
	 * @param level
	 * @param shumeiLeft
	 * @return
	 */
	private int calculateStar(int section, int level, int shumeiLeft) {
		int shumeiQuantity = Const.SHUMEI_QUANTITY[section - 1][level - 1];
		if (shumeiLeft >= (int) (shumeiQuantity * 0.75))
			return 3;
		else if (shumeiLeft >= (int) (shumeiQuantity * 0.5))
			return 2;
		else if (shumeiLeft >= (int) (shumeiQuantity * 0.25))
			return 1;
		else
			return 0;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
