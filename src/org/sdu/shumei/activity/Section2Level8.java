package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.entity.Stick;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section2BaseActivity;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * 第二大关第8小关
 * 
 * @author Craig Lee
 * 
 */
public class Section2Level8 extends Section2BaseActivity {

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
	// 初始化本关的参数
	@Override
	protected void initLevelParameter() {
		this.mSection = 2;
		this.mLevel = 8;
		this.mNextLevelClass = Section2Level9.class;
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
		final VillainShumei villain1 = VillainFactory.createVillainShumei(120,
				310, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain1, mPhysicsWorld);
		villain1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new RotationModifier(0.5f, 0, 45),
						new RotationModifier(0.5f, 45, 0),
						new RotationModifier(0.5f, 360, 315),
						new RotationModifier(0.5f, 315, 360))));// 坏蛋旋转动画
		villain1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain1));
		attachVillainToScene(villain1, villain1Body);

		// 第2个坏蛋
		final VillainShumei villain2 = VillainFactory.createVillainShumei(360,
				310, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
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

		// 第3个坏蛋
		final VillainShumei villain3 = VillainFactory.createVillainShumei(120,
				620, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI3_ID,
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

		// 第4个坏蛋
		final VillainShumei villain4 = VillainFactory.createVillainShumei(360,
				620, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
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

		// 添加坏蛋统计标识
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {
		// 木板
		final Stick stick1 = SceneEntityFactory.createStick(60, 180,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body stick1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stick", stick1, mPhysicsWorld);
		stick1Body.setType(BodyType.KinematicBody);
		stick1Body.setAngularVelocity(1f);
		stick1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STICK, stick1));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stick1,
				stick1Body, false, true));
		mSceneSecondLayer.attachChild(stick1);

		final Stick stick2 = SceneEntityFactory.createStick(290, 180,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body stick2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stick", stick2, mPhysicsWorld);
		stick2Body.setType(BodyType.KinematicBody);
		stick2Body.setAngularVelocity(-1f);
		stick2Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STICK, stick2));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stick2,
				stick2Body, false, true));
		mSceneSecondLayer.attachChild(stick2);

		final Stick stick3 = SceneEntityFactory.createStick(60, 510,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body stick3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stick", stick3, mPhysicsWorld);
		stick3Body.setType(BodyType.KinematicBody);
		stick3Body.setAngularVelocity(-1f);
		stick3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STICK, stick3));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stick3,
				stick3Body, false, true));
		mSceneSecondLayer.attachChild(stick3);

		final Stick stick4 = SceneEntityFactory.createStick(290, 510,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body stick4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"stick", stick4, mPhysicsWorld);
		stick4Body.setType(BodyType.KinematicBody);
		stick4Body.setAngularVelocity(1f);
		stick4Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_STICK, stick4));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stick4,
				stick4Body, false, true));
		mSceneSecondLayer.attachChild(stick4);

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
