package org.sdu.shumei.entity;

import java.util.ArrayList;
import java.util.List;

import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.sdu.shumei.Const;
import org.sdu.shumei.extention.BaseActivity;
import org.sdu.shumei.resource.ShopRoleButtonPack;
import org.sdu.shumei.resource.ShumeiGirlPack;

/**
 * �����̵��е���Ϸ��ɫ����
 * 
 * @author Craig Lee
 * 
 */
public class ShopRoleFactory {

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

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * �����̵��ɫ
	 * 
	 * @return
	 */
	public static List<ShopRole> createShopRoles(TexturePack pTexturePack,
			VertexBufferObjectManager pVertexBufferObjectManager,
			BaseActivity pContext) {
		// ��ɫID
		int[] roleIds = { ShumeiGirlPack.SHUMEI_GIRL2_ID,
				ShumeiGirlPack.SHUMEI_GIRL3_ID, ShumeiGirlPack.SHUMEI_GIRL4_ID,
				ShumeiGirlPack.SHUMEI_GIRL5_ID, ShumeiGirlPack.SHUMEI_GIRL6_ID,
				ShumeiGirlPack.SHUMEI_GIRL7_ID };
		// ��ɫ�۸�
		int[] rolePrices = { Const.Prop.ROLE_2_PRICE, Const.Prop.ROLE_3_PRICE,
				Const.Prop.ROLE_4_PRICE, Const.Prop.ROLE_5_PRICE,
				Const.Prop.ROLE_6_PRICE, Const.Prop.ROLE_7_PRICE };
		int[] textureIds = { ShopRoleButtonPack.ROLE2_ID,
				ShopRoleButtonPack.ROLE3_ID, ShopRoleButtonPack.ROLE4_ID,
				ShopRoleButtonPack.ROLE5_ID, ShopRoleButtonPack.ROLE6_ID,
				ShopRoleButtonPack.ROLE7_ID };

		TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = pTexturePack
				.getTexturePackTextureRegionLibrary();
		// ��ɫ����
		List<TiledTextureRegion> tiledTextureRegionList = new ArrayList<TiledTextureRegion>();
		TexturePackerTextureRegion texturePackerTextureRegion;
		for (int i = 0; i < textureIds.length; i++) {
			texturePackerTextureRegion = texturePackTextureRegionLibrary
					.get(textureIds[i]);
			tiledTextureRegionList.add(TiledTextureRegion.create(
					pTexturePack.getTexture(),
					(int) texturePackerTextureRegion.getTextureX(),
					(int) texturePackerTextureRegion.getTextureY(),
					texturePackerTextureRegion.getSourceWidth(),
					texturePackerTextureRegion.getSourceHeight(), 3, 1));
		}

		List<ShopRole> list = new ArrayList<ShopRole>();
		// ���ƽ�ɫ�ڷ�λ��
		int leftX = 46;
		int rightX = 293;
		int topY = 110;

		ShopRole shopRole = null;
		for (int i = 0; i < roleIds.length; i++) {
			if (i % 2 == 0) {
				// ż��λ�ã������
				shopRole = new ShopRole(leftX, topY,
						tiledTextureRegionList.get(i),
						pVertexBufferObjectManager, pContext, roleIds[i],
						rolePrices[i]);
			} else {
				// ����λ�ã����ұ�
				shopRole = new ShopRole(rightX, topY,
						tiledTextureRegionList.get(i),
						pVertexBufferObjectManager, pContext, roleIds[i],
						rolePrices[i]);
				topY += 262;// �����ƶ�һ��
			}
			list.add(shopRole);
		}
		for (ShopRole sr : list) {
			sr.setShopRoles(list);
		}
		return list;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
}
