package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.util.modifier.ease.EaseLinear;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.Firebug;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.entity.Stick;
import org.sdu.shumei.entity.Stump;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section2BaseActivity;
import org.sdu.shumei.resource.SceneEntity2Pack;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * �ڶ���ص�6С��
 * 
 * @author Craig Lee
 * 
 */
public class Section2Level6 extends Section2BaseActivity {

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
	// ��ʼ�����صĲ���
	@Override
	protected void initLevelParameter() {
		this.mSection = 2;
		this.mLevel = 6;
		this.mNextLevelClass = Section2Level7.class;
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
		final VillainShumei villain1 = VillainFactory.createVillainShumei(350,
				320, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
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
		final VillainShumei villain2 = VillainFactory.createVillainShumei(0,
				450, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain2, mPhysicsWorld);
		villain2.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain2Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain2));
		attachVillainToScene(villain2, villain2Body);

		// ��3������
		final VillainShumei villain3 = VillainFactory.createVillainShumei(350,
				440, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI3_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei3_0", villain3, mPhysicsWorld);
		villain3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain3));
		attachVillainToScene(villain3, villain3Body);

		// ��4������
		final VillainShumei villain4 = VillainFactory.createVillainShumei(0,
				600, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
		final Body villain4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain4, mPhysicsWorld);
		villain4.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 10, 450,
						villain4.getY(), villain4.getY(), EaseLinear
								.getInstance()), new MoveModifier(3, 450, 10,
						villain4.getY(), villain4.getY(), EaseLinear
								.getInstance()))));// �����ƶ�����
		villain4Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain4));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(villain4,
				villain4Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(villain4Body, villain4);
			}
		});
		attachVillainToScene(villain4);

		// ��ӻ���ͳ�Ʊ�ʶ
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {
		// ��ת����׮
		final Stump stump1 = SceneEntityFactory.createStump(350, 200,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntity2Pack.STUMP_ID);
		final Body stump1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stump", stump1, mPhysicsWorld);
		stump1Body.setType(BodyType.KinematicBody);
		stump1Body.setAngularVelocity(1f);
		stump1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STUMP, stump1));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stump1,
				stump1Body, false, true));
		mSceneSecondLayer.attachChild(stump1);

		// ľ��
		final Stick stick = SceneEntityFactory.createStick(20, 350,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body stickBody = this.mPhysicsEditorShapeLibrary.createBody(
				"stick", stick, mPhysicsWorld);
		stickBody.setType(BodyType.KinematicBody);
		stickBody.setAngularVelocity(1f);
		stickBody.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STICK, stick));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stick,
				stickBody, false, true));
		mSceneSecondLayer.attachChild(stick);

		// �׿ǳ�
		final Firebug bug1 = SceneEntityFactory.createFirebug(270, 410,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body bug1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bug1", bug1, mPhysicsWorld);
		bug1.animate(60);
		bug1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_FIRE_BUG, bug1));
		mSceneSecondLayer.attachChild(bug1);

		final Sprite frog = SceneEntityFactory.createFrog(0, 0,
				mSceneEntityTexturePack, getVertexBufferObjectManager());
		frog.registerEntityModifier(new MoveModifier(8, 0, -frog.getWidth(), 0,
				0));
		mSceneTopLayer.attachChild(frog);
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
