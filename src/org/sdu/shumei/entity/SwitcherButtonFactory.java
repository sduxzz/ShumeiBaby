package org.sdu.shumei.entity;

import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.SettingsPack;

/**
 * 生产开关按钮的工场
 * 
 * @author Craig Lee
 * 
 */
public class SwitcherButtonFactory {

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
	 * 生产开关按钮
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @param pSettingName
	 * @return
	 */
	public static SwitcherButton createSwitcherButton(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, String pSettingName) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						SettingsPack.BTN_SWITCH_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 2, 1);

		return new SwitcherButton(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext, pSettingName);

	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
