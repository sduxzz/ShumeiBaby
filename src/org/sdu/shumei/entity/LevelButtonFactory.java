package org.sdu.shumei.entity;

import java.util.ArrayList;
import java.util.List;

import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.activity.Section1Level1;
import org.sdu.shumei.activity.Section1Level2;
import org.sdu.shumei.activity.Section1Level3;
import org.sdu.shumei.activity.Section1Level4;
import org.sdu.shumei.activity.Section1Level5;
import org.sdu.shumei.activity.Section1Level6;
import org.sdu.shumei.activity.Section1Level7;
import org.sdu.shumei.activity.Section1Level8;
import org.sdu.shumei.activity.Section1Level9;
import org.sdu.shumei.activity.Section2Level1;
import org.sdu.shumei.activity.Section2Level4;
import org.sdu.shumei.activity.Section2Level2;
import org.sdu.shumei.activity.Section2Level3;
import org.sdu.shumei.activity.Section2Level5;
import org.sdu.shumei.activity.Section2Level6;
import org.sdu.shumei.activity.Section2Level7;
import org.sdu.shumei.activity.Section2Level8;
import org.sdu.shumei.activity.Section2Level9;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.Section1LevelChoicePack;
import org.sdu.shumei.resource.Section2LevelChoicePack;

/**
 * 生产小关按钮
 * 
 * @author Craig Lee
 * 
 */
public class LevelButtonFactory {

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
	 * 创建第一大关的小关按钮
	 * 
	 * @param pTexturePack
	 * @param pFont
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @return
	 */
	public static List<LevelButton> createSection1LevelButtons(
			TexturePack pTexturePack, Font pFont,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		int section = 1;
		List<LevelButton> listButtons = new ArrayList<LevelButton>();
		LevelButton levelButton = null;

		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = pTexturePack
				.getTexturePackTextureRegionLibrary();
		// 按钮背景的纹理
		TexturePackerTextureRegion levelButtonTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(Section1LevelChoicePack.BUTTON_LEVEL1_ID);
		TiledTextureRegion levelButtonTiledTextureRegion = TiledTextureRegion
				.create(pTexturePack.getTexture(),
						(int) levelButtonTexturePackerTextureRegion
								.getTextureX(),
						(int) levelButtonTexturePackerTextureRegion
								.getTextureY(),
						levelButtonTexturePackerTextureRegion.getSourceWidth(),
						levelButtonTexturePackerTextureRegion.getSourceHeight(),
						2, 1);
		// 星星的纹理
		TexturePackerTextureRegion starsTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(Section1LevelChoicePack.STARS_ID);
		TiledTextureRegion starsTiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) starsTexturePackerTextureRegion.getTextureX(),
				(int) starsTexturePackerTextureRegion.getTextureY(),
				starsTexturePackerTextureRegion.getSourceWidth(),
				starsTexturePackerTextureRegion.getSourceHeight(), 1, 4);
		// 跳转到的界面
		Class<?>[] nextGameLevel = { Section1Level1.class,
				Section1Level2.class, Section1Level3.class,
				Section1Level4.class, Section1Level5.class,
				Section1Level6.class, Section1Level7.class,
				Section1Level8.class, Section1Level9.class };

		int leftX = 42;
		int middleX = 200;
		int rightX = 360;
		int topY = 133;

		for (int i = 0; i < Const.LevelStatus.LEVEL_QUANTITY; i++) {
			if (i % 3 == 0) {
				// 放置在左边的按钮
				levelButton = new LevelButton(leftX, topY,
						levelButtonTiledTextureRegion,
						pVertexBufferObjectManager, pContext, section, i + 1,
						nextGameLevel[i], starsTiledTextureRegion, pFont);
			} else if (i % 3 == 1) {
				// 放置在中间的按钮
				levelButton = new LevelButton(middleX, topY,
						levelButtonTiledTextureRegion,
						pVertexBufferObjectManager, pContext, section, i + 1,
						nextGameLevel[i], starsTiledTextureRegion, pFont);
			} else {
				// 放置在右边的按钮
				levelButton = new LevelButton(rightX, topY,
						levelButtonTiledTextureRegion,
						pVertexBufferObjectManager, pContext, section, i + 1,
						nextGameLevel[i], starsTiledTextureRegion, pFont);
				// 向下移动一行
				topY += 203;
			}
			listButtons.add(levelButton);
		}

		return listButtons;
	}

	/**
	 * 创建第二大关的小关按钮
	 * 
	 * @param pTexturePack
	 * @param pFont
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @return
	 */
	public static List<LevelButton> createSection2LevelButtons(
			TexturePack pTexturePack, Font pFont,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		int section = 2;
		List<LevelButton> listButtons = new ArrayList<LevelButton>();
		LevelButton levelButton = null;

		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = pTexturePack
				.getTexturePackTextureRegionLibrary();
		// 按钮背景的纹理
		TexturePackerTextureRegion levelButtonTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(Section2LevelChoicePack.BUTTON_LEVEL2_ID);
		TiledTextureRegion levelButtonTiledTextureRegion = TiledTextureRegion
				.create(pTexturePack.getTexture(),
						(int) levelButtonTexturePackerTextureRegion
								.getTextureX(),
						(int) levelButtonTexturePackerTextureRegion
								.getTextureY(),
						levelButtonTexturePackerTextureRegion.getSourceWidth(),
						levelButtonTexturePackerTextureRegion.getSourceHeight(),
						2, 1);
		// 星星的纹理
		TexturePackerTextureRegion starsTexturePackerTextureRegion = texturePackTextureRegionLibrary
				.get(Section2LevelChoicePack.STARS_ID);
		TiledTextureRegion starsTiledTextureRegion = TiledTextureRegion.create(
				pTexturePack.getTexture(),
				(int) starsTexturePackerTextureRegion.getTextureX(),
				(int) starsTexturePackerTextureRegion.getTextureY(),
				starsTexturePackerTextureRegion.getSourceWidth(),
				starsTexturePackerTextureRegion.getSourceHeight(), 1, 4);
		// 跳转到的界面
		Class<?>[] nextGameLevel = { Section2Level1.class,
				Section2Level2.class, Section2Level3.class,
				Section2Level4.class, Section2Level5.class,
				Section2Level6.class, Section2Level7.class,
				Section2Level8.class, Section2Level9.class };

		int leftX = 42;
		int middleX = 200;
		int rightX = 360;
		int topY = 133;

		for (int i = 0; i < Const.LevelStatus.LEVEL_QUANTITY; i++) {
			if (i % 3 == 0) {
				// 放置在左边的按钮
				levelButton = new LevelButton(leftX, topY,
						levelButtonTiledTextureRegion,
						pVertexBufferObjectManager, pContext, section, i + 1,
						nextGameLevel[i], starsTiledTextureRegion, pFont);
			} else if (i % 3 == 1) {
				// 放置在中间的按钮
				levelButton = new LevelButton(middleX, topY,
						levelButtonTiledTextureRegion,
						pVertexBufferObjectManager, pContext, section, i + 1,
						nextGameLevel[i], starsTiledTextureRegion, pFont);
			} else {
				// 放置在右边的按钮
				levelButton = new LevelButton(rightX, topY,
						levelButtonTiledTextureRegion,
						pVertexBufferObjectManager, pContext, section, i + 1,
						nextGameLevel[i], starsTiledTextureRegion, pFont);
				// 向下移动一行
				topY += 203;
			}
			listButtons.add(levelButton);
		}
		return listButtons;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
