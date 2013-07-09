package org.sdu.shumei.entity;

import java.util.Random;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ColorModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.sdu.shumei.resource.ParticlePack;

import android.opengl.GLES20;

/**
 * 生产粒子工场
 * 
 * @author Craig Lee
 * 
 */
public class ParticleFactory {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**
	 * 常用颜色
	 */
	public static final Color[] COLORS = { Color.RED, Color.GREEN, Color.BLUE,
			Color.YELLOW, Color.WHITE };

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
	 * 创建波纹式效果
	 * 
	 * @param pX
	 * @param pY
	 * @param pTexturePack
	 * @param pVertexBufferObjectManager
	 */
	public static Sprite createRippleParticle(float pX, float pY,
			TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		final Random random = new Random();
		int choice = random.nextInt(COLORS.length);
		TexturePackerTextureRegion texture = pTexturePack
				.getTexturePackTextureRegionLibrary().get(
						ParticlePack.CIRCLE_ID);
		final Sprite circle = new Sprite(pX - texture.getSourceWidth() / 2,
				-texture.getSourceHeight() / 2, texture,
				pVertexBufferObjectManager);
		circle.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE);
		final ParallelEntityModifier parallelEntityModifier = new ParallelEntityModifier(
				new ScaleModifier(1f, 0.5f, 1.7f),
				new AlphaModifier(1f, 1f, 0f), new ColorModifier(1f,
						COLORS[choice], COLORS[choice]));
		circle.registerEntityModifier(parallelEntityModifier);
		return circle;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
