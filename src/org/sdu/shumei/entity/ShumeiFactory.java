package org.sdu.shumei.entity;

import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.PropPack;
import org.sdu.shumei.resource.ShumeiGirlPack;

/**
 * 生产树梅工厂
 * 
 * @author Craig Lee
 * 
 */
public class ShumeiFactory {

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

	/**
	 * 生产树梅妹
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param fightingPower
	 *            战斗力
	 * @return
	 */
	public static ShumeiGirl createShumeiGirl(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int fightingPower) {
		int prop = pContext.getSettingsPreferences().getInt(
				Const.Settings.ROLE_ID, ShumeiGirlPack.SHUMEI_GIRL1_ID);
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(prop);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 2, 1);
		return new ShumeiGirl(pX - texturePackerTextureRegion.getSourceWidth()
				/ 4, -texturePackerTextureRegion.getSourceHeight(),
				tiledTextureRegion, pVertexBufferObjectManager, pContext, prop,
				fightingPower);
	}

	/**
	 * 生产树梅哥
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @param fightingPower
	 * @return
	 */
	public static ShumeiBoy createShumeiBoy(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int fightingPower) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						PropPack.SHUMEI_BOY_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 2, 1);

		return new ShumeiBoy(pX - texturePackerTextureRegion.getSourceWidth()
				/ 4, -texturePackerTextureRegion.getSourceHeight(),
				tiledTextureRegion, pVertexBufferObjectManager, pContext,
				PropPack.SHUMEI_BOY_ID, fightingPower);
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
