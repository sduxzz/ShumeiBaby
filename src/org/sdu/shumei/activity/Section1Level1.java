package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section1BaseActivity;
import org.sdu.shumei.resource.VillainPack;
import org.sdu.shumei.scene.Guide;
import org.sdu.shumei.utils.L;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * ��һ��ص�1С��
 * 
 * @author Craig Lee
 * 
 */
public class Section1Level1 extends Section1BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================
	private final int mVallainTransientTime = 0;

	// ===========================================================
	// Fields
	// ===========================================================
	// ������������
	private TexturePack mGuideTexturePack;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void createRealResource() {
		super.createRealResource();
		if (mSettingsPreferences.getBoolean(Const.Settings.SHOW_GUIDE, true)) {
			try {
				mGuideTexturePack = new TexturePackLoader(
						this.getTextureManager(), Const.ASSET_IMAGES_BASE_PATH)
						.loadFromAsset(getAssets(), "guide_pack.xml");
				mGuideTexturePack.loadTexture();
			} catch (TexturePackParseException e) {
				L.e(e);
			}
		}

	}

	// ��ʼ�����صĲ���
	@Override
	protected void initLevelParameter() {
		this.mSection = 1;
		this.mLevel = 1;
		this.mNextLevelClass = Section1Level2.class;
		// ����Ĭ�����õ���÷�õĸ���
		int girNum = Const.SHUMEI_QUANTITY[mSection - 1][mLevel - 1];
		// ���Ʊ��ؿ�����÷����
		if (girNum - mShumeiBoyCount > 0) {
			mShumeiGirlCount = girNum - mShumeiBoyCount;
		} else {
			mShumeiGirlCount = 0;
		}
		mVillainCount = 0;
		mShumeiLiveCount = 0;

	}

	@Override
	protected void addVillainToScene() {
		// ��1������
		final VillainShumei villain1 = VillainFactory.createVillainShumei(220,
				314, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		final Body villain1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain1, mPhysicsWorld);
		villain1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));// ������ת����
		villain1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain1));
		attachVillainToScene(villain1, villain1Body);
		// ��2������
		final VillainShumei villain2 = VillainFactory.createVillainShumei(117,
				614, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		final Body villain2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain2, mPhysicsWorld);
		villain2.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain2Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain2));
		attachVillainToScene(villain2, villain2Body);

		// ��3������
		final VillainShumei villain3 = VillainFactory.createVillainShumei(404,
				614, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		final Body villain3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain3, mPhysicsWorld);
		villain3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain3));
		attachVillainToScene(villain3, villain3Body);

		// ��ӻ���ͳ�Ʊ�ʶ
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {
		// ����ָ��
		if (!mScene.hasChildScene()
				&& mSettingsPreferences.getBoolean(Const.Settings.SHOW_GUIDE,
						true)) {
			mSettingsPreferences.edit()
					.putBoolean(Const.Settings.SHOW_GUIDE, false).commit();
			mScene.setChildScene(new Guide(mScene, this, mGuideTexturePack),
					false, true, true);
		}
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
