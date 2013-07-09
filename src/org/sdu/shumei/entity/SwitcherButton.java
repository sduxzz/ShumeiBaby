package org.sdu.shumei.entity;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.utils.L;

/**
 * 设置界面的开关按钮
 * 
 * @author Craig Lee
 * 
 */
public class SwitcherButton extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	/**
	 * 开关状态，true为开，false为关
	 */
	private boolean mStatus;

	/**
	 * 设置名称
	 */
	private String mSettingName;

	// ===========================================================
	// Constructors
	// ===========================================================
	public SwitcherButton(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, String pSettingName) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		mSettingName = pSettingName;
		mStatus = mContext.getSettingsPreferences().getBoolean(mSettingName,
				true);
		judgeStatus();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			mStatus = !mStatus;
			mContext.getSettingsPreferences().edit()
					.putBoolean(mSettingName, mStatus).commit();
			judgeStatus();
		}
		return true;
	}

	@Override
	public void changeOnBeginCollision() {

	}

	@Override
	public void changeOnEndCollision() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void judgeStatus() {
		L.i("init switcher button");
		if (mStatus) {
			setCurrentTileIndex(1);
		} else {
			setCurrentTileIndex(0);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
