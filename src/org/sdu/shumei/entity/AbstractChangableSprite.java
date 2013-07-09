package org.sdu.shumei.entity;

import org.andengine.audio.sound.Sound;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.extention.BaseActivity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 碰撞时需要改变的精灵
 * 
 * @author Craig Lee
 * 
 */
public abstract class AbstractChangableSprite extends AnimatedSprite {

	// ===========================================================
	// Constants
	// ===========================================================
	/**
	 * 设置选项
	 */
	protected SharedPreferences mSettingsPreferences;
	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * 上下文环境
	 */
	protected BaseActivity mContext;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AbstractChangableSprite(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.mContext = pContext;
		this.mSettingsPreferences = this.mContext.getSharedPreferences(
				Const.Settings.SETTINGS_NAME, Context.MODE_PRIVATE);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 碰撞开始时改变状态
	 */
	public abstract void changeOnBeginCollision();

	/**
	 * 碰撞结束时改变状态
	 */
	public abstract void changeOnEndCollision();

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * 是否打开音效
	 * 
	 * @return
	 */
	public boolean isSoundOn() {
		return this.mSettingsPreferences.getBoolean(
				Const.Settings.SOUND_EFFECTS, true);
	}

	/**
	 * 播放声音
	 * 
	 * @param pSound
	 */
	public void playSound(Sound pSound) {
		if (this.mSettingsPreferences.getBoolean(Const.Settings.SOUND_EFFECTS,
				true)) {
			pSound.play();
		}
	}
}
