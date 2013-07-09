package org.sdu.shumei;

/**
 * ������
 * 
 * @author Craig Lee
 * 
 */
public final class Const {
	/**
	 * ����ģʽ
	 */
	public static final boolean DEV_MODE = false;

	/**
	 * ͼƬ·��
	 */
	public static final String ASSET_IMAGES_BASE_PATH = "gfx/";

	/**
	 * ����·��
	 */
	public static final String ASSET_SOUNDS_BASE_PATH = "mfx/";

	/**
	 * ÿһ����÷�õ�����
	 */
	public static final int[][] SHUMEI_QUANTITY = {
			{ 4, 5, 5, 6, 6, 7, 7, 5, 8 }, { 5, 6, 7, 7, 8, 8, 8, 9, 9 } };

	/**
	 * �ؿ�״̬
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class LevelStatus {
		/**
		 * ÿһ������е�С������
		 */
		public static final int LEVEL_QUANTITY = 9;

		/**
		 * δ����
		 */
		public static final int LOCK = 0;
		/**
		 * ����δͨ��
		 */
		public static final int UNLOCK_NOT_PASS = 1;
		/**
		 * ����ͨ��
		 */
		public static final int UNLOCK_PASS = 2;

	}

	/**
	 * ��Ϸ������
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Camera {

		public static final int WIDTH = 540;
		public static final int HEIGHT = 960;

	}

	/**
	 * ��ײ����
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Collison {
		/**
		 * ��ײʱ����
		 */
		public static final long HIT_TIME_STEP = 1500;

		/**
		 * �����÷�ٶ�
		 */
		public static final long ADD_SHUMEI_TIME_STEP = 350;

		/**
		 * ��÷��
		 */
		public static final int ROLE_SHUMEI = 1;
		/**
		 * ������
		 */
		public static final int ROLE_VILLAIN = 2;
		/**
		 * ������
		 */
		public static final int ROLE_SCENE = 3;

		/**
		 * ��÷��
		 */
		public static final int ROLE_SHUMEI_GIRL = 100;
		/**
		 * ��÷��
		 */
		public static final int ROLE_SHUMEI_BOY = 110;

		/**
		 * ��÷����
		 */
		public static final int ROLE_VILLAIN_SHUMEI = 200;
		/**
		 * �׿ǳ�
		 */
		public static final int ROLE_VILLAIN_BEETLE = 210;

		/**
		 * ����
		 */
		public static final int ROLE_VILLAIN_WORM = 200;

		/**
		 * ����
		 */
		public static final int ROLE_SCENE_BULB = 300;

		/**
		 * ��
		 */
		public static final int ROLE_SCENE_BUCKET = 310;

		/**
		 * ǽ
		 */
		public static final int ROLE_SCENE_WALL = 320;

		/**
		 * ����
		 */
		public static final int ROLE_SCENE_GROUND = 325;

		/**
		 * ��֦
		 */
		public static final int ROLE_SCENE_STICK = 330;

		/**
		 * ��׮
		 */
		public static final int ROLE_SCENE_STUMP = 335;
		/**
		 * �׿ǳ�
		 */
		public static final int ROLE_SCENE_FIRE_BUG = 340;

	}

	/**
	 * ��÷ս����
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
	 * ��������ֵ
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
	 * ����Я���Ļ���
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class VillainPoint {
		public static final int LEVEL1 = 200;
		public static final int LEVEL2 = 300;
	}

	/**
	 * �����и����������ײ����
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class SceneEntityPoint {
		/**
		 * ����
		 */
		public static final int BULB = 100;

		/**
		 * ��׮
		 */
		public static final int STUMP = 0;

		/**
		 * ��֦
		 */
		public static final int STICK = 0;

		/**
		 * �׿ǳ�
		 */
		public static final int FIRE_BUG = 0;

	}

	/**
	 * ���ó�����Key
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Settings {
		/**
		 * �����ļ�����
		 */
		public static final String SETTINGS_NAME = "settings";

		/**
		 * ����Ч��
		 */
		public static final String SOUND_EFFECTS = "sound_effects";

		/**
		 * ������������
		 */
		public static final String MUSIC_EFFECTS = "music_effects";
		
		/**
		 * ��ʾ��������
		 */
		public static final String SHOW_GUIDE = "show_guide";

		/**
		 * ���߱�ţ�������Ϸ��ɫ������
		 */
		public static final String ROLE_ID = "role_id";

		/**
		 * �������÷�������
		 */
		public static final String PROP_SHUMEI_BOY_QUANTITY = "shumei_boy_quantity";
		/**
		 * ����ļ��ٿ�������
		 */
		public static final String PROP_SLOW_DOWN_CARD_QUANTITY = "slow_down_card_quantity";
		/**
		 * �����ͨ�ؿ�������
		 */
		public static final String PROP_PASS_CARD_QUANTITY = "pass_card_quantity";

	}

	/**
	 * ����
	 * 
	 * @author Craig Lee
	 * 
	 */
	public static final class Prop {
		/**
		 * ��ɫû�й���
		 */
		public static final int STATUS_UNBOUGHT = 0;
		/**
		 * ��ɫ�Ѿ�����
		 */
		public static final int STATUS_BOUGHT = 1;
		/**
		 * �ѱ����ó���Ϸ�еĽ�ɫ
		 */
		public static final int STATUS_CHOSEN = 2;

		/**
		 * ��ɫ�۸�
		 */
		public static final int ROLE_2_PRICE = 500;
		public static final int ROLE_3_PRICE = 500;
		public static final int ROLE_4_PRICE = 600;
		public static final int ROLE_5_PRICE = 600;
		public static final int ROLE_6_PRICE = 700;
		public static final int ROLE_7_PRICE = 700;

		/**
		 * ����ID
		 */
		public static final int PROP_SHUMEI_BOY_ID = 11;
		public static final int PROP_SLOW_DOWN_CARD_ID = 12;
		public static final int PROP_PASS_CARD_ID = 13;

		/**
		 * ���߼۸�
		 */
		public static final int PROP_SHUMEI_BOY_PRICE = 1000;
		public static final int PROP_SLOW_DOWN_CARD_PRICE = 1000;
		public static final int PROP_PASS_CARD_PRICE = 5000;
	}

}
