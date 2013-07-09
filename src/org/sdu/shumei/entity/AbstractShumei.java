package org.sdu.shumei.entity;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * 树梅基类
 * 
 * @author Craig Lee
 * 
 */
public abstract class AbstractShumei extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * 所穿的道具
	 */
	protected int prop;

	/**
	 * 战斗力
	 */
	protected int fightingPower;

	/**
	 * 碰撞地面3次就从场景中移除
	 */
	protected int hitByGroundWallTimes = 2;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AbstractShumei(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int prop, int fightingPower) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		this.prop = prop;
		this.fightingPower = fightingPower;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getProp() {
		return prop;
	}

	public void setProp(int prop) {
		this.prop = prop;
	}

	public int getFightingPower() {
		return fightingPower;
	}

	public void setFightingPower(int fightingPower) {
		this.fightingPower = fightingPower;
	}

	/**
	 * 撞击一次地面
	 */
	public void hitGroundWall() {
		this.hitByGroundWallTimes--;
	}

	/**
	 * 是否该消失
	 */
	public boolean isDead() {
		return this.hitByGroundWallTimes <= 0;
	}

}
