package org.sdu.shumei.entity;

import java.util.List;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.R;
import org.sdu.shumei.activity.Shop;
import org.sdu.shumei.database.dao.PropDAO;
import org.sdu.shumei.database.model.Prop;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.ShumeiGirlPack;

import android.content.SharedPreferences.Editor;
import android.widget.Toast;

/**
 * �̵��е���Ϸ��ɫ����
 * 
 * @author Craig Lee
 * 
 */
public class ShopRole extends AbstractChangableSprite {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ��ɫID
	private int roleId;

	private PropDAO propDAO;

	private int status;
	// �۸�
	private int price;

	private List<ShopRole> shopRoles;

	// ===========================================================
	// Constructors
	// ===========================================================
	public ShopRole(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext, int roleId, int price) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pContext);
		propDAO = new PropDAO(mContext);
		this.roleId = roleId;
		this.price = price;
		initStatus();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			if (status == Const.Prop.STATUS_BOUGHT) {
				// �Ѿ���������ˣ��ٴε����Ϊ���ô�����Ϊ��Ϸ��ɫ����
				// ����setting
				Editor editor = mSettingsPreferences.edit();
				editor.putInt(Const.Settings.ROLE_ID, roleId);
				editor.commit();
				this.setCurrentTileIndex(2);
				// ����������״̬
				for (ShopRole sr : shopRoles) {
					if (sr.getRoleId() != roleId) {
						sr.initStatus();
					}
				}
				mContext.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(mContext, R.string.shop_setRoleSccess,
								Toast.LENGTH_SHORT).show();
					}
				});
			} else if (status == Const.Prop.STATUS_UNBOUGHT) {
				// δ���򣬵����ΪҪ����
				// �������
				if (price <= Shop.leftScore) {
					// ���û��ִ��ڼ۸����ܹ���
					status = Const.Prop.STATUS_BOUGHT;
					setCurrentTileIndex(1);
					// �浽���ݿ�
					Prop prop = new Prop();
					prop.setPropId(roleId);
					prop.setQuantity(1);
					prop.setScoreConsumed(price);
					propDAO.insert(prop);

					Shop.leftScore -= price;
					mContext.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(mContext,
									R.string.shop_buyRoleSuccess,
									Toast.LENGTH_SHORT).show();
						}
					});

				} else {
					mContext.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(mContext, R.string.shop_lackPoint,
									Toast.LENGTH_SHORT).show();
						}
					});
				}
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
	// ��ʼ��״̬
	public void initStatus() {
		if (propDAO.isBought(roleId)) {
			// ��������
			if (this.roleId == mSettingsPreferences.getInt(
					Const.Settings.ROLE_ID, ShumeiGirlPack.SHUMEI_GIRL1_ID)) {
				// �����ó���Ϸ��ɫ
				setStatus(Const.Prop.STATUS_CHOSEN);
				this.setCurrentTileIndex(2);
			} else {
				setStatus(Const.Prop.STATUS_BOUGHT);
				this.setCurrentTileIndex(1);
			}
		} else {
			// û�й���
			setStatus(Const.Prop.STATUS_UNBOUGHT);
			this.setCurrentTileIndex(0);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getRoleId() {
		return roleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ShopRole> getShopRoles() {
		return shopRoles;
	}

	public void setShopRoles(List<ShopRole> shopRoles) {
		this.shopRoles = shopRoles;
	}
}
