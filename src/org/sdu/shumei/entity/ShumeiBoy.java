package org.sdu.shumei.entity;

import java.util.List;

import org.andengine.audio.sound.Sound;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.adapter.AnimationListenerAdapter;
import org.sdu.shumei.extention.BaseActivity;

/**
 * 树梅哥
 * 
 * @author Craig Lee
 * 
 */
public class ShumeiBoy extends AbstractShumei {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * 被撞的时候音效
	 * 
	 */
	private List<Sound> mHitSoundList = null;
	/**
	 * 该播放那一个碰撞音效了
	 */
	private int mHitSoundTurn = 0;

	private long mLastHitTime;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ShumeiBoy(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int prop, int fightingPower) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager,
				pContext, prop, fightingPower);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void changeOnBeginCollision() {
		// 播放碰撞动画
		this.animate(new long[] { 600 }, new int[] { 1 }, false,
				new AnimationListenerAdapter() {

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// 播放完成恢复到初始状态
						pAnimatedSprite.setCurrentTileIndex(0);
					}
				});

	}

	@Override
	public void changeOnEndCollision() {
		if (System.currentTimeMillis() - mLastHitTime > Const.Collison.HIT_TIME_STEP) {
			// 播放碰撞声音
			if (this.mHitSoundList != null) {
				this.playSound(this.mHitSoundList.get(this.mHitSoundTurn
						% this.mHitSoundList.size()));
			}
			this.mHitSoundTurn++;
			mLastHitTime = System.currentTimeMillis();
		}
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
	public List<Sound> getHitSoundList() {
		return mHitSoundList;
	}

	public void setHitSoundList(List<Sound> mHitSoundList) {
		this.mHitSoundList = mHitSoundList;
	}
}
