package org.sdu.shumei.entity;

import java.util.List;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.database.dao.ProgressDAO;
import org.sdu.shumei.database.model.Progress;
import org.sdu.shumei.extention.BaseActivity;

import android.content.Intent;

/**
 * С�ؿ���ť
 * 
 * @author Craig Lee
 * 
 */
public class LevelButton extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// �ڼ����
	private int mSection;
	// �ڼ�С�صİ�ť
	private int mLevel;
	// �������ת���Ľ���
	private Class<?> mInGameActivity;

	private ProgressDAO progressDAO;

	private AnimatedSprite mStarSprite;

	private Text mLevelNumberText;
	// �Ƿ����
	private boolean mLock;

	// ===========================================================
	// Constructors
	// ===========================================================
	public LevelButton(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int mSection, int mLevel,
			Class<?> mInGameActivity,
			ITiledTextureRegion mStarTiledTextureRegion, Font mLevelNumberFont) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		this.mSection = mSection;
		this.mLevel = mLevel;
		this.mInGameActivity = mInGameActivity;
		progressDAO = new ProgressDAO(mContext);
		mLevelNumberText = new Text(45, 15, mLevelNumberFont,
				String.valueOf(mLevel), pVertexBufferObjectManager);
		mStarSprite = new AnimatedSprite(25, 93, mStarTiledTextureRegion,
				pVertexBufferObjectManager);
		initButton();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			if (!mLock) {
				// �������ת
				Intent intent = new Intent(mContext, mInGameActivity);
				mContext.startActivity(intent);
			}
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

	// ��ʼ�������ť
	private void initButton() {
		// ��ѯ���ݿ�����״̬
		List<Progress> resultList = progressDAO
				.find(null,
						"section = ? and level = ?",
						new String[] { String.valueOf(mSection),
								String.valueOf(mLevel) }, null, null, null,
						null);
		if (mLevel == 1) {
			// ��һС�����⴦��
			mLock = false;
			// ��������
			setCurrentTileIndex(1);
			if (resultList.size() == 0) {
				mStarSprite.setCurrentTileIndex(0);
			} else {
				mStarSprite.setCurrentTileIndex(resultList.get(0).getStar());
			}
			this.attachChild(mStarSprite);
			this.attachChild(mLevelNumberText);
		} else {
			if (resultList.size() == 0) {
				// û�д˹صļ�¼����δ����
				mLock = true;
				// δ��������
				setCurrentTileIndex(0);
			} else {
				if (resultList.get(0).getStatus() == Const.LevelStatus.LOCK) {
					mLock = true;
					// δ��������
					setCurrentTileIndex(0);
				} else {
					// ��������
					mLock = false;
					setCurrentTileIndex(1);
					mStarSprite
							.setCurrentTileIndex(resultList.get(0).getStar());
					this.attachChild(mStarSprite);
					this.attachChild(mLevelNumberText);
				}
			}
		}

	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
