package org.sdu.shumei.entity;

import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.database.dao.ProgressDAO;
import org.sdu.shumei.extention.BaseActivity;

import android.content.Intent;

/**
 * ��Ĺؿ��İ�ť
 * 
 * @author Craig Lee
 * 
 */
public class SectionButton extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	// �ڼ����
	private int mSection;
	// ����
	private Font mNumberFont;
	// �Ƿ����
	private boolean mLock;
	// �������ת���Ľ���
	private Class<?> mLevelChoiceActivity;
	private ProgressDAO progressDAO;

	private Text mScoreText;
	private Text mStarText;

	private VertexBufferObjectManager mVertexBufferObjectManager;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SectionButton(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int mSection, Font mNumberFont,
			Class<?> mLevelChoiceActivity) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		this.mVertexBufferObjectManager = pVertexBufferObjectManager;
		this.mSection = mSection;
		this.mNumberFont = mNumberFont;
		this.mLevelChoiceActivity = mLevelChoiceActivity;
		progressDAO = new ProgressDAO(mContext);
		init();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void changeOnBeginCollision() {

	}

	@Override
	public void changeOnEndCollision() {

	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			if (!mLock) {
				// �������ת
				Intent intent = new Intent(mContext, mLevelChoiceActivity);
				mContext.startActivity(intent);
				return true;
			}
		}
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private void init() {

		int score = progressDAO.getSectionScoreEarned(mSection);
		int totalStar = 3 * Const.LevelStatus.LEVEL_QUANTITY;
		int star = progressDAO.getSectionStarEarned(mSection);
		if (mSection == 1) {
			// ��һ��أ����⴦������
			mLock = false;
			setCurrentTileIndex(0);
			// ������ֺ�������
			mScoreText = new Text(125, 294, mNumberFont, String.valueOf(score),
					mVertexBufferObjectManager);
			mStarText = new Text(125, 370, mNumberFont, star + "/" + totalStar,
					mVertexBufferObjectManager);
			attachChild(mScoreText);
			attachChild(mStarText);
		} else if (mSection == 3) {
			// ���һ�أ������ڴ�
			mLock = true;
			setCurrentTileIndex(0);
		} else {
			int lastScetionStar = progressDAO
					.getSectionStarEarned(mSection - 1);
			if (lastScetionStar < 2 * Const.LevelStatus.LEVEL_QUANTITY) {
				// ���������ʸ�
				mLock = true;
			} else {
				mLock = false;
			}

			if (!mLock) {
				setCurrentTileIndex(0);
				// ������ֺ�������
				mScoreText = new Text(125, 294, mNumberFont,
						String.valueOf(score), mVertexBufferObjectManager);
				mStarText = new Text(125, 370, mNumberFont, star + "/"
						+ totalStar, mVertexBufferObjectManager);
				attachChild(mScoreText);
				attachChild(mStarText);

			} else {
				setCurrentTileIndex(1);
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
