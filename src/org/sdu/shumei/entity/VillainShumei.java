package org.sdu.shumei.entity;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;

/**
 * Ê÷Ã·»µµ°
 * 
 * @author Craig Lee
 * 
 */
public class VillainShumei extends AbstractVillain {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private IEntityModifier alphaEntityModifer;

	// ===========================================================
	// Constructors
	// ===========================================================

	public VillainShumei(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int point, int life, float transientTime) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager,
				pContext, point, life, transientTime);
		if (this.transientTime > 0) {
			alphaEntityModifer = new AlphaModifier(transientTime, 1, 0);
			this.registerEntityModifier(alphaEntityModifer);
		}

	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void changeOnBeginCollision() {

	}

	@Override
	public void changeOnEndCollision() {
		removeAlphaEntityModifer();
		if (this.transientTime > 0) {
			alphaEntityModifer = new AlphaModifier(transientTime, 1, 0);
			this.registerEntityModifier(alphaEntityModifer);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * ÒÆ³ý±äÍ¸Ã÷µÄ¶¯»­
	 */
	public void removeAlphaEntityModifer() {
		if (this.transientTime > 0) {
			this.unregisterEntityModifier(alphaEntityModifer);
			this.setAlpha(1);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
