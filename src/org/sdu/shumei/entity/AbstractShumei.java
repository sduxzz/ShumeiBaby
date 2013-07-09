package org.sdu.shumei.entity;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * ��÷����
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
	 * �����ĵ���
	 */
	protected int prop;

	/**
	 * ս����
	 */
	protected int fightingPower;

	/**
	 * ��ײ����3�ξʹӳ������Ƴ�
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
	 * ײ��һ�ε���
	 */
	public void hitGroundWall() {
		this.hitByGroundWallTimes--;
	}

	/**
	 * �Ƿ����ʧ
	 */
	public boolean isDead() {
		return this.hitByGroundWallTimes <= 0;
	}

}
