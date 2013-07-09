package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.extension.physics.box2d.PhysicsConnector;
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
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * 第一大关第8小关
 * 
 * @author Craig Lee
 * 
 */
public class Section1Level8 extends Section1BaseActivity {

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
		this.mLevel = 8;
		this.mNextLevelClass = Section1Level9.class;
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
				155, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2,
				mVallainTransientTime);
		villain1.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - villain1.getWidth(), villain1
								.getY(), villain1.getY(), EaseExponentialInOut
								.getInstance()), new MoveModifier(3,
						Const.Camera.WIDTH - villain1.getWidth(), 0, villain1
								.getY(), villain1.getY(), EaseExponentialInOut
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
		final VillainShumei villain2 = VillainFactory.createVillainShumei(0,
				330, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		villain2.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(4,
						Const.Camera.WIDTH - villain2.getWidth(), 0, villain2
								.getY(), villain2.getY(), EaseLinear
								.getInstance()), new MoveModifier(4, 0,
						Const.Camera.WIDTH - villain2.getWidth(), villain2
								.getY(), villain2.getY(), EaseLinear
								.getInstance()))));
		final Body villain2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain2, mPhysicsWorld);
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
				513, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL1,
				mVallainTransientTime);
		villain3.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(3, 0,
						Const.Camera.WIDTH - villain3.getWidth(), villain3
								.getY(), villain3.getY(), EaseQuadInOut
								.getInstance()), new MoveModifier(3,
						Const.Camera.WIDTH - villain3.getWidth(), 0, villain3
								.getY(), villain3.getY(), EaseQuadInOut
								.getInstance()))));
		final Body villain3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei1_0", villain3, mPhysicsWorld);
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
				673, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
				getVertexBufferObjectManager(), this,
				Const.VillainPoint.LEVEL1, Const.VillainLife.SHUMEI_LEVEL2, 0);
		villain4.registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(new MoveModifier(4,
						Const.Camera.WIDTH - villain4.getWidth(), 0, villain4
								.getY(), villain4.getY(), EaseStrongOut
								.getInstance()), new MoveModifier(4, 0,
						Const.Camera.WIDTH - villain4.getWidth(), villain4
								.getY(), villain4.getY(), EaseStrongOut
								.getInstance()))));
		final Body villain4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"villain_shumei2_0", villain4, mPhysicsWorld);
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
		int topY = 80;
		for (int row = 1; row <= 2; row++) {
			int leftX = -22;
			for (int cols = 1; cols <= 4; cols++) {
				final Bulb bulb = SceneEntityFactory.createBulb(leftX, topY,
						mSceneEntityTexturePack,
						getVertexBufferObjectManager(), this, cols % 3 + 1);
				final Body bulbBody = this.mPhysicsEditorShapeLibrary
						.createBody("bulb" + (cols % 3 + 1) + "_0", bulb,
								mPhysicsWorld);
				bulbBody.setUserData(new CollisionData(
						Const.Collison.ROLE_SCENE_BULB, bulb));
				mSceneSecondLayer.attachChild(bulb);
				leftX += 162;
			}
			topY += 350;
		}

		topY = 250;
		for (int row = 1; row <= 2; row++) {
			int leftX = 65;
			for (int cols = 1; cols <= 3; cols++) {
				final Bulb bulb = SceneEntityFactory.createBulb(leftX, topY,
						mSceneEntityTexturePack,
						getVertexBufferObjectManager(), this, cols);
				final Body bulbBody = this.mPhysicsEditorShapeLibrary
						.createBody("bulb" + cols + "_0", bulb, mPhysicsWorld);
				bulbBody.setUserData(new CollisionData(
						Const.Collison.ROLE_SCENE_BULB, bulb));
				mSceneSecondLayer.attachChild(bulb);
				leftX += 162;
			}
			topY += 350;
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
