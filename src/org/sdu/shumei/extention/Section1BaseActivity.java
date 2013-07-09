package org.sdu.shumei.extention;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.Butterfly;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.resource.Section1Scene1BackgroundPack;
import org.sdu.shumei.utils.L;

/**
 * 第一大关基本游戏场景Activity
 * 
 * @author Craig Lee
 * 
 */
public abstract class Section1BaseActivity extends SectionBaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	protected TexturePack mBackgroundTexturePack;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void createRealResource() {
		super.createRealResource();
		try {
			// 加载场景实体道具图片资源
			mSceneEntityTexturePack = new TexturePackLoader(
					getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(), "scene_entity_pack.xml");
			mSceneEntityTexturePack.loadTexture();
			// 加载背景图片资源
			mBackgroundTexturePack = new TexturePackLoader(
					this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
					.loadFromAsset(getAssets(),
							"section1_scene1_background_pack.xml");
			mBackgroundTexturePack.loadTexture();

		} catch (TexturePackParseException e) {
			L.e(e);
		}
	}

	@Override
	public Scene createRealScene() {
		final Scene scene = super.createRealScene();
		// 向Scene中添加草丛
		final TexturePackerTextureRegion grassTextureRegion = mBackgroundTexturePack
				.getTexturePackTextureRegionLibrary().get(
						Section1Scene1BackgroundPack.SECTION1_SCENE1_GRASS_ID);
		Sprite grass = new Sprite(0, Const.Camera.HEIGHT
				- grassTextureRegion.getHeight(), grassTextureRegion,
				getVertexBufferObjectManager());
		mSceneTopLayer.attachChild(grass);
		this.addShumeiGirlRepresentToScene();
		addButterfly2Scene();
		if (mSlowDownCardCount > 0) {
			addSlowDownCardRepresentToScene();
		}
		if (mPassCardCount > 0) {
			addPassCardRepresentToScene();
		}
		return scene;
	}

	@Override
	protected void createParallaxBackground() {
		// 创建并行运动的背景
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(
				0, 0, 0, 3);
		final VertexBufferObjectManager vertexBufferObjectManager = this
				.getVertexBufferObjectManager();
		TexturePackTextureRegionLibrary backgroundTexturePackTextureRegionLibrary = mBackgroundTexturePack
				.getTexturePackTextureRegionLibrary();
		// 取得天空云彩和树的纹理
		TexturePackerTextureRegion sky = backgroundTexturePackTextureRegionLibrary
				.get(Section1Scene1BackgroundPack.SECTION1_SCENE1_SKY_ID);
		TexturePackerTextureRegion clouds = backgroundTexturePackTextureRegionLibrary
				.get(Section1Scene1BackgroundPack.SECTION1_SCENE1_CLOUDS_ID);
		TexturePackerTextureRegion tree = backgroundTexturePackTextureRegionLibrary
				.get(Section1Scene1BackgroundPack.SECTION1_SCENE1_TREE_ID);
		// 将背景添加到scene中
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f,
				new Sprite(0, 0, sky, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(5.0f,
				new Sprite(0, 0, clouds, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f,
				new Sprite(0, 0, tree, vertexBufferObjectManager)));
		mScene.setBackground(autoParallaxBackground);

	}

	@Override
	protected String setBackgroundMusicName() {
		return "background_forest.ogg";
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * 向场景中添加蝴蝶
	 */
	protected void addButterfly2Scene() {
		final Butterfly butterfly = SceneEntityFactory.createButterfly(0,
				Const.Camera.HEIGHT - 150, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this);
		butterfly.animate(40);
		butterfly.registerEntityModifier(new LoopEntityModifier(
				new MoveModifier(10, -butterfly.getWidth(),
						2 * Const.Camera.WIDTH, butterfly.getY(), butterfly
								.getY())));
		this.mSceneTopLayer.attachChild(butterfly);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
