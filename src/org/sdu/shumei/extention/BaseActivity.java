package org.sdu.shumei.extention;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.AudioOptions;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.sdu.shumei.Const;

import android.content.SharedPreferences;

import com.umeng.analytics.MobclickAgent;

/**
 * ������Ϸ�����Activity����
 * 
 * @author Craig Lee
 * 
 */
public abstract class BaseActivity extends SimpleBaseGameActivity {

	// ===========================================================
	// Constants
	// ===========================================================
	/**
	 * ����
	 */
	protected Scene mScene;
	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * ����ѡ��
	 */
	protected SharedPreferences mSettingsPreferences;

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

		final Camera camera = new Camera(0, 0, Const.Camera.WIDTH,
				Const.Camera.HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(
						Const.Camera.WIDTH, Const.Camera.HEIGHT), camera);
		// ����Ч������
		boolean soundSetting = mSettingsPreferences.getBoolean(
				Const.Settings.SOUND_EFFECTS, true);
		boolean musicSetting = mSettingsPreferences.getBoolean(
				Const.Settings.MUSIC_EFFECTS, true);
		final AudioOptions aduioOptions = engineOptions.getAudioOptions();
		aduioOptions.setNeedsSound(soundSetting);
		aduioOptions.setNeedsMusic(musicSetting);
		MobclickAgent.updateOnlineConfig(this);
		MobclickAgent.onError(this);
		return engineOptions;
	}

	@Override
	public synchronized void onResumeGame() {
		super.onResumeGame();
		MobclickAgent.onResume(this);
	}

	@Override
	public synchronized void onPauseGame() {
		super.onPauseGame();
		MobclickAgent.onPause(this);

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

	/**
	 * ʵ����Ļ�Ŀ�
	 * 
	 * @return
	 */
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}

	/**
	 * ʵ����Ļ�ĸ�
	 * 
	 * @return
	 */
	public int getScreenHeight() {
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	/**
	 * ��������
	 * 
	 * @param pSound
	 */
	public void playSound(Sound pSound) {
		if (pSound != null) {
			if (this.mSettingsPreferences.getBoolean(
					Const.Settings.SOUND_EFFECTS, true)) {
				pSound.play();
			}
		}
	}

	/**
	 * ��������
	 * 
	 * @param pMusic
	 */
	public void playMusic(Music pMusic) {
		if (pMusic != null) {
			if (this.mSettingsPreferences.getBoolean(
					Const.Settings.MUSIC_EFFECTS, true)) {
				pMusic.play();
			}
		}
	}

	/**
	 * ��ͣ����
	 * 
	 * @param pMusic
	 */
	public void pauseMusic(Music pMusic) {
		if (pMusic != null) {
			if (this.mSettingsPreferences.getBoolean(
					Const.Settings.MUSIC_EFFECTS, true)) {
				pMusic.pause();
			}
		}

	}

	/**
	 * ֹͣ����
	 * 
	 * @param pMusic
	 */
	public void stopMusic(Music pMusic) {
		if (pMusic != null) {
			if (this.mSettingsPreferences.getBoolean(
					Const.Settings.MUSIC_EFFECTS, true)) {
				pMusic.setLooping(false);
				pMusic.stop();
			}
		}

	}

	/**
	 * �Ƿ����Ч
	 * 
	 * @return
	 */
	public boolean isSoundOn() {
		return mSettingsPreferences.getBoolean(Const.Settings.SOUND_EFFECTS,
				true);
	}
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(0, 0);
	}
	/**
	 * �Ƿ������
	 * 
	 * @return
	 */
	public boolean isMusicOn() {
		return mSettingsPreferences.getBoolean(Const.Settings.MUSIC_EFFECTS,
				true);
	}

	public SharedPreferences getSettingsPreferences() {
		return mSettingsPreferences;
	}

}
