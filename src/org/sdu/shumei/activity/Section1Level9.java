package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.util.modifier.ease.EaseLinear;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.Bulb;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.entity.Stump;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section1BaseActivity;
import org.sdu.shumei.resource.SceneEntityPack;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * 第一大关第9小关
 * 
 * @author Craig Lee
 * 
 */
public class Section1Level9 extends Section1BaseActivity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private final int mVallainTransientTime = 4;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void initLevelParameter() {
		this.mSection = 1;
		this.mLevel = 9;
		this.mNextLevelClass = null;
		// 本关默认设置的树梅妹的个数
		int girNum = Const.SHUMEI_QUANTITY[mSection - 1][mLevel - 1];
		// 控制本关卡的树梅数量
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
		// 第1个坏蛋
		final VillainShumei villain1 = VillainFactory.createVillainShumei(0,
				400, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		villain1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - villain1.getWidth(), villain1
								.getY(), villain1.getY(), EaseLinear
								.getInstance()), new MoveModifier(3,
						Const.Camera.WIDTH - villain1.getWidth(), 0, villain1
								.getY(), villain1.getY(), EaseLinear
								.getInstance()))));
		final Body villain1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain1, mPhysicsWorld);
		villain1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain1));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(villain1,
				villain1Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(villain1Body, villain1);
			}
		});
		attachVillainToScene(villain1);

		// 第2个坏蛋
		final VillainShumei villain2 = VillainFactory.createVillainShumei(260,
				244, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
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

		// 第3个坏蛋
		final VillainShumei villain3 = VillainFactory.createVillainShumei(10,
				614, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
		final Body villain3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain3, mPhysicsWorld);
		villain3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain3));
		attachVillainToScene(villain3, villain3Body);

		// 第4个坏蛋
		final VillainShumei villain4 = VillainFactory.createVillainShumei(
				Const.Camera.WIDTH / 2 - 15, 614, mVillainShumeiTexturePack,
				VillainPack.VILLAIN_SHUMEI2_ID, getVertexBufferObjectManager(),
				this, Const.VillainPoint.LEVEL1,
				Const.VillainLife.SHUMEI_LEVEL2, mVallainTransientTime);
		final Body villain4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain4, mPhysicsWorld);
		villain4.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));
		villain4Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain4));
		attachVillainToScene(villain4, villain4Body);

		// 第5个坏蛋
		final VillainShumei villain5 = VillainFactory.createVillainShumei(
				Const.Camera.WIDTH - villain4.getWidth() - 15, 614,
				mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
		final Body villain5Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain5, mPhysicsWorld);
		villain5.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));// 坏蛋旋转动画
		villain5Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain5));
		attachVillainToScene(villain5, villain5Body);

		// 添加坏蛋统计标识
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {
		// 旋转的树桩
		final Stump stump1 = SceneEntityFactory.createStump(244, 137,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.STUMP_ID);
		final Body stump1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stump", stump1, mPhysicsWorld);
		stump1Body.setType(BodyType.KinematicBody);
		stump1Body.setAngularVelocity(1f);
		stump1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STUMP, stump1));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stump1,
				stump1Body, false, true));
		mSceneSecondLayer.attachChild(stump1);

		final Stump stump2 = SceneEntityFactory.createStump(stump1.getHeight()
				- stump1.getWidth(), 500, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.STUMP_ID);
		final Body stump2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stump", stump2, mPhysicsWorld);
		stump2Body.setType(BodyType.KinematicBody);
		stump2Body.setAngularVelocity(1f);
		stump2Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STUMP, stump2));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stump2,
				stump2Body, false, true));
		mSceneSecondLayer.attachChild(stump2);

		final Stump stump3 = SceneEntityFactory.createStump(Const.Camera.WIDTH
				- stump2.getHeight(), 500, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.STUMP_ID);
		final Body stump3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stump", stump3, mPhysicsWorld);
		stump3Body.setType(BodyType.KinematicBody);
		stump3Body.setAngularVelocity(-1f);
		stump3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STUMP, stump3));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stump3,
				stump3Body, false, true));
		mSceneSecondLayer.attachChild(stump3);

		// 灯泡
		final Bulb bulb1 = SceneEntityFactory.createBulb(0, 330,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.BULB1_ID);
		bulb1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - bulb1.getWidth(), bulb1.getY(),
						bulb1.getY(), EaseLinear.getInstance()),
						new MoveModifier(3, Const.Camera.WIDTH
								- bulb1.getWidth(), 0, bulb1.getY(), bulb1
								.getY(), EaseLinear.getInstance()))));
		final Body bulbBody = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb1_0", bulb1, mPhysicsWorld);
		bulbBody.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb1));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(bulb1,
				bulbBody, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(bulbBody, bulb1);
			}
		});
		mSceneSecondLayer.attachChild(bulb1);

		final Bulb bulb2 = SceneEntityFactory.createBulb(
				Const.Camera.WIDTH / 2 - 30, 500, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.BULB2_ID);
		final Body bulb2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb2_0", bulb2, mPhysicsWorld);
		bulb2Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb2));
		mSceneSecondLayer.attachChild(bulb2);

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
