package org.sdu.shumei.entity;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * 场景中的道具的基类
 * 
 * @author Craig Lee
 * 
 */
public class SceneEntity extends AbstractChangableSprite {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * 被撞击次数
	 */
	protected int hitNumber;

	/**
	 * 积分
	 */
	protected int point;

	// ===========================================================
	// Constructors
	// ===========================================================
	public SceneEntity(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int pPoint) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		this.point = pPoint;
		hitNumber = 0;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void changeOnBeginCollision() {
	}

	@Override
	public void changeOnEndCollision() {
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
	 * 是否被第一次撞击
	 * 
	 * @return
	 */
	public boolean isFirstHit() {
		return hitNumber == 1;
	}

	public int getHitNumber() {
		return hitNumber;
	}

	public void setHitNumber(int hitNumber) {
		this.hitNumber = hitNumber;
	}

	public int getPoint() {
		return point;
	}

}
