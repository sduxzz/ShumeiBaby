package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section1BaseActivity;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * ��һ��ص�2С��
 * 
 * @author Craig Lee
 * 
 */
public class Section1Level2 extends Section1BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private final int mVallainTransientTime = 5;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void initLevelParameter() {
		this.mSection = 1;
		this.mLevel = 2;
		this.mNextLevelClass = Section1Level3.class;
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
		final VillainShumei villain1 = VillainFactory.createVillainShumei(47,
				614, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
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
		final VillainShumei villain2 = VillainFactory.createVillainShumei(177,
				314, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
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
		final VillainShumei villain3 = VillainFactory.createVillainShumei(337,
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

		// ��4������
		final VillainShumei villain4 = VillainFactory.createVillainShumei(432,
				314, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		final Body villain4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain4, mPhysicsWorld);
		villain4.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain4Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain4));
		attachVillainToScene(villain4, villain4Body);

		// ��ӻ���ͳ�Ʊ�ʶ
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {

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
