package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.util.modifier.ease.EaseCubicOut;
import org.andengine.util.modifier.ease.EaseExponentialInOut;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.EaseQuadOut;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.Bulb;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section2BaseActivity;
import org.sdu.shumei.resource.SceneEntity2Pack;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * 第二大关第4小关
 * 
 * @author Craig Lee
 * 
 */
public class Section2Level4 extends Section2BaseActivity {

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
	// 初始化本关的参数
	@Override
	protected void initLevelParameter() {
		this.mSection = 2;
		this.mLevel = 4;
		this.mNextLevelClass = Section2Level5.class;
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
				220, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain1, mPhysicsWorld);
		villain1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(1, 100, 200,
						villain1.getY(), villain1.getY(), EaseLinear
								.getInstance()), new MoveModifier(1, 200, 100,
						villain1.getY(), villain1.getY(), EaseLinear
								.getInstance()))));// 坏蛋旋转动画
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
		final VillainShumei villain2 = VillainFactory.createVillainShumei(0,
				410, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain2, mPhysicsWorld);
		villain2.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0, 120, villain2
						.getY(), villain2.getY(), EaseExponentialInOut
						.getInstance()), new MoveModifier(3, 120, 0, villain2
						.getY(), villain2.getY(), EaseExponentialInOut
						.getInstance()))));// 坏蛋旋转动画
		villain2Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain2));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(villain2,
				villain2Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(villain2Body, villain2);
			}
		});
		attachVillainToScene(villain2);
		// 第3个坏蛋
		final VillainShumei villain3 = VillainFactory.createVillainShumei(0,
				410, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI3_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei3_0", villain3, mPhysicsWorld);
		villain3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(1, 360, 460,
						villain3.getY(), villain3.getY(), EaseQuadOut
								.getInstance()), new MoveModifier(1, 460, 360,
						villain3.getY(), villain3.getY(), EaseQuadOut
								.getInstance()))));// 坏蛋旋转动画
		villain3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain3));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(villain3,
				villain3Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(villain3Body, villain3);
			}
		});
		attachVillainToScene(villain3);
		// 第4个坏蛋
		final VillainShumei villain4 = VillainFactory.createVillainShumei(0,
				600, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		final Body villain4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain4, mPhysicsWorld);
		villain4.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(1, 300, 200,
						villain4.getY(), villain4.getY(), EaseLinear
								.getInstance()), new MoveModifier(1, 200, 300,
						villain4.getY(), villain4.getY(), EaseLinear
								.getInstance()))));// 坏蛋旋转动画
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

		// 添加坏蛋统计标识
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {
		final Bulb bulb1 = SceneEntityFactory.createBulb(0, 150,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntity2Pack.BULB1_ID);
		bulb1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(2, 0,
						Const.Camera.WIDTH - bulb1.getWidth(), bulb1.getY(),
						bulb1.getY(), EaseLinear.getInstance()),
						new MoveModifier(2, Const.Camera.WIDTH
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

		final Bulb bulb2 = SceneEntityFactory.createBulb(0, 340,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntity2Pack.BULB2_ID);
		bulb2.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(2,
						Const.Camera.WIDTH - bulb2.getWidth(), 0, bulb2.getY(),
						bulb2.getY(), EaseCubicOut.getInstance()),
						new MoveModifier(2, 0, Const.Camera.WIDTH
								- bulb2.getWidth(), bulb2.getY(), bulb2.getY(),
								EaseCubicOut.getInstance()))));

		final Body bulb2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb2_0", bulb2, mPhysicsWorld);
		bulb2Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb2));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(bulb2,
				bulb2Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(bulb2Body, bulb2);
			}
		});
		mSceneSecondLayer.attachChild(bulb2);

		final Bulb bulb3 = SceneEntityFactory.createBulb(0, 530,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntity2Pack.BULB3_ID);
		bulb3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - bulb3.getWidth(), bulb3.getY(),
						bulb3.getY(), EaseLinear.getInstance()),
						new MoveModifier(3, Const.Camera.WIDTH
								- bulb3.getWidth(), 0, bulb3.getY(), bulb3
								.getY(), EaseLinear.getInstance()))));

		final Body bulb3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb3_0", bulb3, mPhysicsWorld);
		bulb3Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb3));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(bulb3,
				bulb3Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(bulb3Body, bulb3);
			}
		});
		mSceneSecondLayer.attachChild(bulb3);

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
