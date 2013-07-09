package org.sdu.shumei.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.SceneEntity2Pack;
import org.sdu.shumei.resource.SceneEntityPack;

/**
 * 生产场景实体道具工厂
 * 
 * @author Craig Lee
 * 
 */
public class SceneEntityFactory {

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
	 * 生产灯泡
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @return
	 */
	public static Bulb createBulb(float pX, float pY, TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int pProp) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(pProp);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 2, 1);

		return new Bulb(pX, pY, tiledTextureRegion, pVertexBufferObjectManager,
				pContext, Const.SceneEntityPoint.BULB);
	}

	/**
	 * 生产树桩
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @return
	 */
	public static Stump createStump(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int textureId) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(textureId);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 1, 1);

		return new Stump(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext,
				Const.SceneEntityPoint.STUMP);
	}

	/**
	 * 生产蝴蝶
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @return
	 */
	public static Butterfly createButterfly(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						SceneEntityPack.BUTTERFLY_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 4, 4);
		return new Butterfly(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext);
	}

	/**
	 * 生产雾
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @return
	 */
	public static Sprite createFrog(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						SceneEntity2Pack.FROG_ID);
		return new Sprite(pX, pY, texturePackerTextureRegion,
				pVertexBufferObjectManager);
	}

	/**
	 * 生产萤火虫
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @return
	 */
	public static Firefly createFirefly(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						SceneEntity2Pack.FIREFLY_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 4, 4);
		return new Firefly(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext);
	}

	/**
	 * 生产木板
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @return
	 */
	public static Stick createStick(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						SceneEntity2Pack.STICK_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 1, 1);
		return new Stick(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext,
				Const.SceneEntityPoint.STICK);
	}

	/**
	 * 生产甲壳虫
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @return
	 */
	public static Firebug createFirebug(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		TexturePackerTextureRegion texturePackerTextureRegion = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						SceneEntity2Pack.FIREBUG_ID);
		TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) texturePackerTextureRegion.getTextureX(),
				(int) texturePackerTextureRegion.getTextureY(),
				texturePackerTextureRegion.getSourceWidth(),
				texturePackerTextureRegion.getSourceHeight(), 4, 4);
		return new Firebug(pX, pY, tiledTextureRegion,
				pVertexBufferObjectManager, pContext,
				Const.SceneEntityPoint.FIRE_BUG);

	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
