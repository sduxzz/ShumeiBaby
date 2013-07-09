package org.sdu.shumei.activity;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.sdu.shumei.Const;
import org.sdu.shumei.entity.Firebug;
import org.sdu.shumei.entity.SceneEntityFactory;
import org.sdu.shumei.entity.VillainFactory;
import org.sdu.shumei.entity.VillainShumei;
import org.sdu.shumei.extention.CollisionData;
import org.sdu.shumei.extention.Section2BaseActivity;
import org.sdu.shumei.resource.VillainPack;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * 第二大关第2小关
 * 
 * @author Craig Lee
 * 
 */
public class Section2Level2 extends Section2BaseActivity {

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
		this.mLevel = 2;
		this.mNextLevelClass = Section2Level3.class;
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
		final VillainShumei villain1 = VillainFactory.createVillainShumei(10,
				395, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
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
		final VillainShumei villain2 = VillainFactory.createVillainShumei(425,
				485, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI2_ID,
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
		final VillainShumei villain3 = VillainFactory.createVillainShumei(286,
				552, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI3_ID,
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
		final VillainShumei villain4 = VillainFactory.createVillainShumei(114,
				710, mVillainShumeiTexturePack, VillainPack.VILLAIN_SHUMEI1_ID,
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
		final Firebug bug1 = SceneEntityFactory.createFirebug(10, 200,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body bug1Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bug1", bug1, mPhysicsWorld);
		bug1.animate(60);
		bug1Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_FIRE_BUG, bug1));
		mSceneSecondLayer.attachChild(bug1);

		final Firebug bug2 = SceneEntityFactory.createFirebug(170, 200,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body bug2Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bug1", bug2, mPhysicsWorld);
		bug2.animate(60);
		bug2Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_FIRE_BUG, bug2));
		mSceneSecondLayer.attachChild(bug2);

		final Firebug bug3 = SceneEntityFactory.createFirebug(320, 200,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body bug3Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bug1", bug3, mPhysicsWorld);
		bug3.animate(60);
		bug3Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_FIRE_BUG, bug3));
		mSceneSecondLayer.attachChild(bug3);

		final Firebug bug4 = SceneEntityFactory.createFirebug(470, 200,
				mSceneEntityTexturePack, getVertexBufferObjectManager(), this);
		final Body bug4Body = this.mPhysicsEditorShapeLibrary.createBody(
				"bug1", bug4, mPhysicsWorld);
		bug4.animate(60);
		bug4Body.setUserData(new CollisionData(
				Const.Collison.ROLE_SCENE_FIRE_BUG, bug4));
		mSceneSecondLayer.attachChild(bug4);

		final Sprite frog = SceneEntityFactory.createFrog(0, 0,
				mSceneEntityTexturePack, getVertexBufferObjectManager());
		frog.registerEntityModifier(new MoveModifier(10, 0, -frog.getWidth(), 0,
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
