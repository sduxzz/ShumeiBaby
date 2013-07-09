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
import org.sdu.shumei.activity.Section1LevelChoice;
import org.sdu.shumei.activity.Section2LevelChoice;
import org.sdu.shumei.activity.Section3LevelChoice;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.SectionChoicePack;

/**
 * 生产大关的按钮
 * 
 * @author Craig Lee
 * 
 */
public class SectionButtonFactory {

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
	 * 创建大关选择的按钮
	 * 
	 * @param pVertexBufferObjectManager
	 * @param pContext
	 * @param mSection
	 * @param mNumberFont
	 * @param pTexturePack
	 * @return
	 */
	public static List<SectionButton> createSectionButtons(
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, Font mNumberFont, TexturePack pTexturePack) {
		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = pTexturePack
				.getTexturePackTextureRegionLibrary();
		List<SectionButton> list = new ArrayList<SectionButton>();
		SectionButton sb = null;

		Class<?>[] nextClasses = { Section1LevelChoice.class,
				Section2LevelChoice.class, Section3LevelChoice.class };
		int[] texture = { SectionChoicePack.SECTION1_ID,
				SectionChoicePack.SECTION2_ID, SectionChoicePack.SECTION3_ID };
		int topY = 180;
		int leftX = 117;
		for (int i = 0; i < nextClasses.length; i++) {
			TexturePackerTextureRegion sectionButtonTexturePackerTextureRegion = texturePackTextureRegionLibrary
					.get(texture[i]);

			TiledTextureRegion sectionButtonTiledTextureRegion = null;
			if (i == 0 || i == nextClasses.length - 1) {
				sectionButtonTiledTextureRegion = TiledTextureRegion.create(
						pTexturePack.getTexture(),
						(int) sectionButtonTexturePackerTextureRegion
								.getTextureX(),
						(int) sectionButtonTexturePackerTextureRegion
								.getTextureY(),
						sectionButtonTexturePackerTextureRegion
								.getSourceWidth(),
						sectionButtonTexturePackerTextureRegion
								.getSourceHeight(), 1, 1);
			} else {
				sectionButtonTiledTextureRegion = TiledTextureRegion.create(
						pTexturePack.getTexture(),
						(int) sectionButtonTexturePackerTextureRegion
								.getTextureX(),
						(int) sectionButtonTexturePackerTextureRegion
								.getTextureY(),
						sectionButtonTexturePackerTextureRegion
								.getSourceWidth(),
						sectionButtonTexturePackerTextureRegion
								.getSourceHeight(), 2, 1);
			}
			sb = new SectionButton(leftX, topY,
					sectionButtonTiledTextureRegion,
					pVertexBufferObjectManager, pContext, i + 1, mNumberFont,
					nextClasses[i]);
			list.add(sb);
			leftX += Const.Camera.WIDTH;
		}

		return list;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
