package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.util.math.MathUtils;
import org.sdu.shumei.Const;
import org.sdu.shumei.adapter.UpdateHandlerAdapter;
import org.sdu.shumei.entity.Bulb;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section1BaseActivity;
import org.sdu.shumei.resource.SceneEntityPack;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * 第一大关第6小关
 * 
 * @author Craig Lee
 * 
 */
public class Section1Level6 extends Section1BaseActivity {

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
		this.mLevel = 6;
		this.mNextLevelClass = Section1Level7.class;
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
		final VillainShumei villain1 = VillainFactory.createVillainShumei(
				Const.Camera.WIDTH / 2, 230, mVillainShumeiTexturePack,
				VillainPack.VILLAIN_SHUMEI1_ID, getVertexBufferObjectManager(),
				this, Const.VillainPoint.LEVEL1,
				Const.VillainLife.SHUMEI_LEVEL1, 0);
		final Body villain1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain1, mPhysicsWorld);
		villain1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain1));
		villain1.registerUpdateHandler(new UpdateHandlerAdapter() {
			private long theta = 0;

			@Override
			public void onUpdate(float pSecondsElapsed) {
				theta++;
				villain1.setX(Const.Camera.WIDTH / 2 - villain1.getWidth() / 2
						+ 220 * (float) Math.sin(MathUtils.degToRad(theta)));
				villain1.setY(Const.Camera.HEIGHT / 2 - villain1.getHeight()
						+ 20 + 220
						* (float) Math.cos(MathUtils.degToRad(theta)));
			}
		});
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(villain1,
				villain1Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(villain1Body, villain1);
			}
		});
		attachVillainToScene(villain1);

		// 第2个坏蛋
		final VillainShumei villain2 = VillainFactory.createVillainShumei(120,
				480, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
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
		final VillainShumei villain3 = VillainFactory.createVillainShumei(236,
				280, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
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

		// 第4个坏蛋
		final VillainShumei villain4 = VillainFactory.createVillainShumei(350,
				490, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
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
		final VillainShumei villain5 = VillainFactory.createVillainShumei(440,
				180, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		final Body villain5Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain5, mPhysicsWorld);
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
		final Bulb bulb1 = SceneEntityFactory.createBulb(0, 120,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.BULB1_ID);
		final Body bulbBody = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb1_0", bulb1, mPhysicsWorld);
		bulbBody.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb1));
		mSceneSecondLayer.attachChild(bulb1);

		final Bulb bulb2 = SceneEntityFactory.createBulb(Const.Camera.WIDTH / 2
				- bulb1.getWidth() / 2, 120, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.BULB2_ID);
		final Body bulb2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb2_0", bulb2, mPhysicsWorld);
		bulb2Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb2));
		mSceneSecondLayer.attachChild(bulb2);

		final Bulb bulb3 = SceneEntityFactory.createBulb(Const.Camera.WIDTH
				- bulb2.getWidth(), 120, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.BULB3_ID);
		final Body bulb3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb3_0", bulb3, mPhysicsWorld);
		bulb3Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb3));
		mSceneSecondLayer.attachChild(bulb3);

		final Bulb bulb4 = SceneEntityFactory.createBulb(
				(bulb1.getX() + bulb2.getX()) / 2, 420,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.BULB3_ID);
		final Body bulb4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb3_0", bulb4, mPhysicsWorld);
		bulb4Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb4));
		mSceneSecondLayer.attachChild(bulb4);

		final Bulb bulb5 = SceneEntityFactory.createBulb(
				(bulb3.getX() + bulb2.getX()) / 2, 420,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.BULB1_ID);
		final Body bulb5Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb1_0", bulb5, mPhysicsWorld);
		bulb5Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb5));
		mSceneSecondLayer.attachChild(bulb5);
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
