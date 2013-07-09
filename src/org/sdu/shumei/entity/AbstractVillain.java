package org.sdu.shumei.entity;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * ���˻���
 * 
 * @author Craig Lee
 * 
 */
public abstract class AbstractVillain extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	/**
	 * ����
	 */
	protected int point;

	/**
	 * ����ֵ
	 */
	protected int life;

	/**
	 * ��͸��ʱ��
	 */
	protected float transientTime;

	// ===========================================================
	// Constructors
	// ===========================================================
	public AbstractVillain(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int point, int life, float transientTime) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		this.point = point;
		this.life = life;
		this.transientTime = transientTime;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public float getTransientTime() {
		return transientTime;
	}

	public void setTransientTime(float transientTime) {
		this.transientTime = transientTime;
	}

	/**
	 * �ж��Ƿ�����
	 * 
	 * @return
	 */
	public boolean isDead() {
		return this.life <= 0;
	}
}
