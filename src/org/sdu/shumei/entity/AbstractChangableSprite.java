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
 * ��ײʱ��Ҫ�ı�ľ���
 * 
 * @author Craig Lee
 * 
 */
public abstract class AbstractChangableSprite extends AnimatedSprite {

	// ===========================================================
	// Constants
	// ===========================================================
	/**
	 * ����ѡ��
	 */
	protected SharedPreferences mSettingsPreferences;
	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * �����Ļ���
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
	 * ��ײ��ʼʱ�ı�״̬
	 */
	public abstract void changeOnBeginCollision();

	/**
	 * ��ײ����ʱ�ı�״̬
	 */
	public abstract void changeOnEndCollision();

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * �Ƿ����Ч
	 * 
	 * @return
	 */
	public boolean isSoundOn() {
		return this.mSettingsPreferences.getBoolean(
				Const.Settings.SOUND_EFFECTS, true);
	}

	/**
	 * ��������
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
