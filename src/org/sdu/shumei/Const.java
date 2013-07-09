package org.sdu.shumei;

/**
 * 常量类
 * 
 * @author Craig Lee
 * 
 */
public final class Const {
	/**
	 * 开发模式
	 */
	public static final boolean DEV_MODE = false;

	/**
	 * 图片路径
	 */
	public static final String ASSET_IMAGES_BASE_PATH = "gfx/";

	/**
	 * 声音路径
	 */
	public static final String ASSET_SOUNDS_BASE_PATH = "mfx/";

	/**
	 * 每一关树梅妹的数量
	 */
	public static final int[][] SHUMEI_QUANTITY = {
			{ 4, 5, 5, 6, 6, 7, 7, 5, 8 }, { 5, 6, 7, 7, 8, 8, 8, 9, 9 } };

	/**
	 * 关卡状态
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class LevelStatus {
		/**
		 * 每一大关所有的小关数量
		 */
		public static final int LEVEL_QUANTITY = 9;

		/**
		 * 未解锁
		 */
		public static final int LOCK = 0;
		/**
		 * 解锁未通关
		 */
		public static final int UNLOCK_NOT_PASS = 1;
		/**
		 * 解锁通关
		 */
		public static final int UNLOCK_PASS = 2;

	}

	/**
	 * 游戏界面宽高
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Camera {

		public static final int WIDTH = 540;
		public static final int HEIGHT = 960;

	}

	/**
	 * 碰撞常量
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Collison {
		/**
		 * 碰撞时间间隔
		 */
		public static final long HIT_TIME_STEP = 1500;

		/**
		 * 添加树梅速度
		 */
		public static final long ADD_SHUMEI_TIME_STEP = 350;

		/**
		 * 树梅类
		 */
		public static final int ROLE_SHUMEI = 1;
		/**
		 * 坏蛋类
		 */
		public static final int ROLE_VILLAIN = 2;
		/**
		 * 场景类
		 */
		public static final int ROLE_SCENE = 3;

		/**
		 * 树梅妹
		 */
		public static final int ROLE_SHUMEI_GIRL = 100;
		/**
		 * 树梅哥
		 */
		public static final int ROLE_SHUMEI_BOY = 110;

		/**
		 * 树梅坏蛋
		 */
		public static final int ROLE_VILLAIN_SHUMEI = 200;
		/**
		 * 甲壳虫
		 */
		public static final int ROLE_VILLAIN_BEETLE = 210;

		/**
		 * 虫子
		 */
		public static final int ROLE_VILLAIN_WORM = 200;

		/**
		 * 灯泡
		 */
		public static final int ROLE_SCENE_BULB = 300;

		/**
		 * 筐
		 */
		public static final int ROLE_SCENE_BUCKET = 310;

		/**
		 * 墙
		 */
		public static final int ROLE_SCENE_WALL = 320;

		/**
		 * 地面
		 */
		public static final int ROLE_SCENE_GROUND = 325;

		/**
		 * 树枝
		 */
		public static final int ROLE_SCENE_STICK = 330;

		/**
		 * 树桩
		 */
		public static final int ROLE_SCENE_STUMP = 335;
		/**
		 * 甲壳虫
		 */
		public static final int ROLE_SCENE_FIRE_BUG = 340;

	}

	/**
	 * 树梅战斗力
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class ShumeiFight {
		public static final int SHUMEI_GIRL_LEVEL1 = 200;
		public static final int SHUMEI_GIRL_LEVEL2 = 300;

		public static final int SHUMEI_BOY = 400;
	}

	/**
	 * 坏人生命值
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class VillainLife {
		public static final int SHUMEI_LEVEL1 = 200;
		public static final int SHUMEI_LEVEL2 = 300;
		public static final int SHUMEI_LEVEL3 = 400;
	}

	/**
	 * 坏人携带的积分
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class VillainPoint {
		public static final int LEVEL1 = 200;
		public static final int LEVEL2 = 300;
	}

	/**
	 * 场景中各个物体的碰撞积分
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class SceneEntityPoint {
		/**
		 * 灯泡
		 */
		public static final int BULB = 100;

		/**
		 * 树桩
		 */
		public static final int STUMP = 0;

		/**
		 * 树枝
		 */
		public static final int STICK = 0;

		/**
		 * 甲壳虫
		 */
		public static final int FIRE_BUG = 0;

	}

	/**
	 * 设置常量的Key
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Settings {
		/**
		 * 设置文件名字
		 */
		public static final String SETTINGS_NAME = "settings";

		/**
		 * 声音效果
		 */
		public static final String SOUND_EFFECTS = "sound_effects";

		/**
		 * 背景音乐设置
		 */
		public static final String MUSIC_EFFECTS = "music_effects";
		
		/**
		 * 显示新手引导
		 */
		public static final String SHOW_GUIDE = "show_guide";

		/**
		 * 道具编号，现在游戏角色的形象
		 */
		public static final String ROLE_ID = "role_id";

		/**
		 * 购买的树梅哥的数量
		 */
		public static final String PROP_SHUMEI_BOY_QUANTITY = "shumei_boy_quantity";
		/**
		 * 购买的减速卡的数量
		 */
		public static final String PROP_SLOW_DOWN_CARD_QUANTITY = "slow_down_card_quantity";
		/**
		 * 购买的通关卡的数量
		 */
		public static final String PROP_PASS_CARD_QUANTITY = "pass_card_quantity";

	}

	/**
	 * 道具
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Prop {
		/**
		 * 角色没有购买
		 */
		public static final int STATUS_UNBOUGHT = 0;
		/**
		 * 角色已经购买
		 */
		public static final int STATUS_BOUGHT = 1;
		/**
		 * 已被设置成游戏中的角色
		 */
		public static final int STATUS_CHOSEN = 2;

		/**
		 * 角色价格
		 */
		public static final int ROLE_2_PRICE = 500;
		public static final int ROLE_3_PRICE = 500;
		public static final int ROLE_4_PRICE = 600;
		public static final int ROLE_5_PRICE = 600;
		public static final int ROLE_6_PRICE = 700;
		public static final int ROLE_7_PRICE = 700;

		/**
		 * 道具ID
		 */
		public static final int PROP_SHUMEI_BOY_ID = 11;
		public static final int PROP_SLOW_DOWN_CARD_ID = 12;
		public static final int PROP_PASS_CARD_ID = 13;

		/**
		 * 道具价格
		 */
		public static final int PROP_SHUMEI_BOY_PRICE = 1000;
		public static final int PROP_SLOW_DOWN_CARD_PRICE = 1000;
		public static final int PROP_PASS_CARD_PRICE = 5000;
	}

}
