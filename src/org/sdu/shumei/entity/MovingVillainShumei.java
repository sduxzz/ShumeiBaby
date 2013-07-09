package org.sdu.shumei.entity;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * 移动的树梅坏蛋
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
	 * 物理Handler的回调函数
	 * 
	 * @author Craig Lee
	 * 
	 */
	public interface MovingPhysicsCallBack {
		/**
		 * 设置参数
		 * 
		 * @param pPhysicsHandler
		 */
		public void initPhysicsHandler(PhysicsHandler pPhysicsHandler);

		/**
		 * 更新时回调函数
		 */
		public void onUpdate(MovingVillainShumei pMovingVillainShumei,
				PhysicsHandler pPhysicsHandler);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
