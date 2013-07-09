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
 * 商店中的游戏角色形象
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

	// 角色ID
	private int roleId;

	private PropDAO propDAO;

	private int status;
	// 价格
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
				// 已经被购买过了，再次点击即为设置此形象为游戏角色形象、
				// 更新setting
				Editor editor = mSettingsPreferences.edit();
				editor.putInt(Const.Settings.ROLE_ID, roleId);
				editor.commit();
				this.setCurrentTileIndex(2);
				// 更新其他的状态
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
				// 未购买，点击即为要购买
				// 计算积分
				if (price <= Shop.leftScore) {
					// 可用积分大于价格，则能购买
					status = Const.Prop.STATUS_BOUGHT;
					setCurrentTileIndex(1);
					// 存到数据库
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
	// 初始化状态
	public void initStatus() {
		if (propDAO.isBought(roleId)) {
			// 被购买了
			if (this.roleId == mSettingsPreferences.getInt(
					Const.Settings.ROLE_ID, ShumeiGirlPack.SHUMEI_GIRL1_ID)) {
				// 被设置成游戏角色
				setStatus(Const.Prop.STATUS_CHOSEN);
				this.setCurrentTileIndex(2);
			} else {
				setStatus(Const.Prop.STATUS_BOUGHT);
				this.setCurrentTileIndex(1);
			}
		} else {
			// 没有购买
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
