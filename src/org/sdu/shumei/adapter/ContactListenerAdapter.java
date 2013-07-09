package org.sdu.shumei.adapter;

import org.sdu.shumei.Const;
import org.sdu.shumei.extention.CollisionData;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * ContactListener������
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
		
		// ��ȷ����ײ���������������
		if (cda.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact��A����÷��B��������ײ��
			beginContactWithShumei(contact.getFixtureA(), contact.getFixtureB());
		} else if (cdb.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact��B����÷��A��������ײ��
			beginContactWithShumei(contact.getFixtureB(), contact.getFixtureA());
		}
	}

	@Override
	public void endContact(Contact contact) {
		CollisionData cda = getCollisionData(contact.getFixtureA());
		CollisionData cdb = getCollisionData(contact.getFixtureB());
		
		// ��ȷ����ײ���������������
		if (cda.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact��A����÷��B��������ײ��
			endContactWithShumei(contact.getFixtureA(), contact.getFixtureB());
		} else if (cdb.role / 100 == Const.Collison.ROLE_SHUMEI) {
			// contact��B����÷��A��������ײ��
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
	 * ��ײ��ʼ����������÷
	 * 
	 * @param pShumei
	 * @param pOther
	 */
	public void beginContactWithShumei(Fixture pShumei, Fixture pOther) {

	}

	/**
	 * ��ײ��������������÷
	 * 
	 * @param pShumei
	 * @param pOther
	 */
	public void endContactWithShumei(Fixture pShumei, Fixture pOther) {

	}

	/**
	 * ��ȡ��ײ��Body��UserData
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
