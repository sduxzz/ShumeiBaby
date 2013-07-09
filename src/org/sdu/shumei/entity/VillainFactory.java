package org.sdu.shumei.entity;

import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.entity.MovingVillainShumei.MovingPhysicsCallBack;
import org.sdu.shumei.extention.BaseActivity;

/**
 * 生产坏蛋的工厂
 * 
 * @author Craig Lee
 * 
 */
public class VillainFactory {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * 生产树梅坏蛋
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pTextureId
	 *            纹理ID
	 * @param pVertexBufferObjectManager
	 * @param point
	 *            积分
	 * @param life
	 *            生命值
	 * @return
	 */
	public static VillainShumei createVillainShumei(float pX, float pY,
			TexturePack pTexturePack, int pTextureId,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int point, int life, float transientTime) {
		VillainShumei vs = null;

		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(pTextureId);
		TiledTextureRegion tiledTextureRegion = null;

		tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 1, 1);
		vs = new VillainShumei(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext, point, life,
				transientTime);
		return vs;
	}

	/**
	 * 生产移动着的树梅坏蛋
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pTextureId
	 *            纹理ID
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @param point
	 *            积分
	 * @param life
	 *            生命值
	 * @param transientTime
	 * @return
	 */
	public static MovingVillainShumei createMovingVillainShumei(float pX,
			float pY, TexturePack pTexturePack, int pTextureId,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int point, int life, int transientTime,
			MovingPhysicsCallBack pMovingPhysicsCallBack) {
		MovingVillainShumei mvs = null;
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(pTextureId);
		TiledTextureRegion tiledTextureRegion = null;
		tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 1, 1);
		mvs = new MovingVillainShumei(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext, point, life,
				transientTime, pMovingPhysicsCallBack);
		return mvs;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
