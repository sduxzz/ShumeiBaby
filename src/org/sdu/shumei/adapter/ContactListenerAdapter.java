package org.sdu.shumei.adapter;

import org.sdu.shumei.Const;
import org.sdu.shumei.extention.CollisionData;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * ContactListener适配器
 * 
 * @author Craig Lee
 * 
 */
public class ContactListenerAdapter implements ContactListener {

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
	public void beginContact(Contact contact) {
		CollisionData cda = getCollisionData(contact.getFixtureA());
		CollisionData cdb = getCollisionData(contact.getFixtureB());
		
		// 先确定碰撞的两个物体的类型
		if (cda.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact中A是树梅，B是其他碰撞物
			beginContactWithShumei(contact.getFixtureA(), contact.getFixtureB());
		} else if (cdb.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact中B是树梅，A是其他碰撞物
			beginContactWithShumei(contact.getFixtureB(), contact.getFixtureA());
		}
	}

	@Override
	public void endContact(Contact contact) {
		CollisionData cda = getCollisionData(contact.getFixtureA());
		CollisionData cdb = getCollisionData(contact.getFixtureB());
		
		// 先确定碰撞的两个物体的类型
		if (cda.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact中A是树梅，B是其他碰撞物
			endContactWithShumei(contact.getFixtureA(), contact.getFixtureB());
		} else if (cdb.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact中B是树梅，A是其他碰撞物
			endContactWithShumei(contact.getFixtureB(), contact.getFixtureA());
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 碰撞开始，其中有树梅
	 * 
	 * @param pShumei
	 * @param pOther
	 */
	public void beginContactWithShumei(Fixture pShumei, Fixture pOther) {

	}

	/**
	 * 碰撞结束，其中有树梅
	 * 
	 * @param pShumei
	 * @param pOther
	 */
	public void endContactWithShumei(Fixture pShumei, Fixture pOther) {

	}

	/**
	 * 获取碰撞的Body的UserData
	 * 
	 * @param fixture
	 * @return
	 */
	protected CollisionData getCollisionData(Fixture fixture) {
		return (CollisionData) fixture.getBody().getUserData();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
