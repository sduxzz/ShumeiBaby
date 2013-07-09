package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.util.modifier.ease.EaseCubicOut;
import org.andengine.util.modifier.ease.EaseExponentialInOut;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.EaseQuadInOut;
import org.andengine.util.modifier.ease.EaseStrongOut;
import org.sdu.shumei.Const;
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
 * 第一大关第7小关
 * 
 * @author Craig Lee
 * 
 */
public class Section1Level7 extends Section1BaseActivity {

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
	@Override
	protected void initLevelParameter() {
		this.mSection = 1;
		this.mLevel = 7;
		this.mNextLevelClass = Section1Level8.class;
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
				123, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1, 0);
		villain1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - villain1.getWidth(), villain1
								.getY(), villain1.getY(), EaseLinear
								.getInstance()), new MoveModifier(3,
						Const.Camera.WIDTH - villain1.getWidth(), 0, villain1
								.getY(), villain1.getY(), EaseLinear
								.getInstance()))));
		final Body villain1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain1, mPhysicsWorld);
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
				273, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
		villain2.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(4,
						Const.Camera.WIDTH - villain2.getWidth(), 0, villain2
								.getY(), villain2.getY(), EaseCubicOut
								.getInstance()), new MoveModifier(4, 0,
						Const.Camera.WIDTH - villain2.getWidth(), villain2
								.getY(), villain2.getY(), EaseCubicOut
								.getInstance()))));
		final Body villain2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain2, mPhysicsWorld);
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
				423, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI3_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1, 0);
		villain3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - villain3.getWidth(), villain3
								.getY(), villain3.getY(), EaseExponentialInOut
								.getInstance()), new MoveModifier(3,
						Const.Camera.WIDTH - villain3.getWidth(), 0, villain3
								.getY(), villain3.getY(), EaseExponentialInOut
								.getInstance()))));
		final Body villain3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei3_0", villain3, mPhysicsWorld);
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
				573, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1, 0);
		villain4.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(4,
						Const.Camera.WIDTH - villain4.getWidth(), 0, villain4
								.getY(), villain4.getY(), EaseQuadInOut
								.getInstance()), new MoveModifier(4, 0,
						Const.Camera.WIDTH - villain4.getWidth(), villain4
								.getY(), villain4.getY(), EaseQuadInOut
								.getInstance()))));
		final Body villain4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain4, mPhysicsWorld);
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

		// 第5个坏蛋
		final VillainShumei villain5 = VillainFactory.createVillainShumei(0,
				723, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
		villain5.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - villain5.getWidth(), villain5
								.getY(), villain5.getY(), EaseStrongOut
								.getInstance()), new MoveModifier(3,
						Const.Camera.WIDTH - villain5.getWidth(), 0, villain5
								.getY(), villain5.getY(), EaseStrongOut
								.getInstance()))));
		final Body villain5Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain5, mPhysicsWorld);
		villain5Body.setUserData(new CollisionData(
				Const.Collison.ROLE_VILLAIN_SHUMEI, villain5));
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(villain5,
				villain5Body, true, true) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				syncBodyAndSpritePosition(villain5Body, villain5);
			}
		});
		attachVillainToScene(villain5);
		// 添加坏蛋统计标识
		addVallainRepresentToScene();
	}

	@Override
	protected void addSceneEntityToScene() {
		final Bulb bulb1 = SceneEntityFactory.createBulb(
				Const.Camera.WIDTH / 2 - 30, 50, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.BULB1_ID);
		final Body bulbBody = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb1_0", bulb1, mPhysicsWorld);
		bulbBody.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb1));
		mSceneSecondLayer.attachChild(bulb1);

		final Bulb bulb2 = SceneEntityFactory.createBulb(0, 200,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.BULB2_ID);
		final Body bulb2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb2_0", bulb2, mPhysicsWorld);
		bulb2Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb2));
		mSceneSecondLayer.attachChild(bulb2);

		final Bulb bulb3 = SceneEntityFactory.createBulb(Const.Camera.WIDTH
				- bulb2.getWidth(), 350, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.BULB3_ID);
		final Body bulb3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb3_0", bulb3, mPhysicsWorld);
		bulb3Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb3));
		mSceneSecondLayer.attachChild(bulb3);

		final Bulb bulb4 = SceneEntityFactory.createBulb(0, 500,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this,
				SceneEntityPack.BULB1_ID);
		final Body bulb4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb1_0", bulb4, mPhysicsWorld);
		bulb4Body.setUserData(new CollisionData(Const.Collison.ROLE_SCENE_BULB,
				bulb4));
		mSceneSecondLayer.attachChild(bulb4);

		final Bulb bulb5 = SceneEntityFactory.createBulb(Const.Camera.WIDTH
				- bulb4.getWidth(), 650, mSceneEntityTexturePack,
				getVertexBufferObjectManager(), this, SceneEntityPack.BULB2_ID);
		final Body bulb5Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bulb2_0", bulb5, mPhysicsWorld);
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
