package org.sdu.shumei.entity;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * �ƶ�����÷����
 * 
 * @author Craig Lee
 * 
 */
public class MovingVillainShumei extends VillainShumei {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected PhysicsHandler mPhysicsHandler;
	protected MovingPhysicsCallBack mMovingPhysicsCallBack;

	// ===========================================================
	// Constructors
	// ===========================================================
	public MovingVillainShumei(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int point, int life, int transientTime,
			MovingPhysicsCallBack pMovingPhysicsCallBack) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager,
				pContext, point, life, transientTime);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mMovingPhysicsCallBack = pMovingPhysicsCallBack;
		this.mMovingPhysicsCallBack.initPhysicsHandler(mPhysicsHandler);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		this.mMovingPhysicsCallBack.onUpdate(this, this.mPhysicsHandler);
		super.onManagedUpdate(pSecondsElapsed);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	/**
	 * ����Handler�Ļص�����
	 * 
	 * @author Craig Lee
	 * 
	 */
	public interface MovingPhysicsCallBack {
		/**
		 * ���ò���
		 * 
		 * @param pPhysicsHandler
		 */
		public void initPhysicsHandler(PhysicsHandler pPhysicsHandler);

		/**
		 * ����ʱ�ص�����
		 */
		public void onUpdate(MovingVillainShumei pMovingVillainShumei,
				PhysicsHandler pPhysicsHandler);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
