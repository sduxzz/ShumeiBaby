package org.sdu.shumei.entity;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * ө���
 * 
 * @author Craig Lee
 * 
 */
public class Firefly extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public Firefly(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
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
}
