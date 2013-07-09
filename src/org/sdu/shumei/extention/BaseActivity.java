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
 * 所有游戏界面的Activity基类
 * 
 * @author Craig Lee
 * 
 */
public abstract class BaseActivity extends SimpleBaseGameActivity {

	// ===========================================================
	// Constants
	// ===========================================================
	/**
	 * 场景
	 */
	protected Scene mScene;
	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * 设置选项
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
		// 声音效果设置
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
	 * 实际屏幕的宽
	 * 
	 * @return
	 */
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}

	/**
	 * 实际屏幕的高
	 * 
	 * @return
	 */
	public int getScreenHeight() {
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	/**
	 * 播放声音
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
	 * 播放音乐
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
	 * 暂停音乐
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
	 * 停止音乐
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
	 * 是否打开音效
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
	 * 是否打开音乐
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
