package org.sdu.shumei.database.dao;

import java.util.List;

import org.sdu.shumei.Const;
import org.sdu.shumei.database.DBHelper;
import org.sdu.shumei.database.model.Progress;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class ProgressDAO extends BaseDaoImpl<Progress> {

	public ProgressDAO(Context context) {
		super(new DBHelper(context));
	}

	/**
	 * �ж�һ���Ƿ����
	 * 
	 * @param section
	 * @param level
	 * @return
	 */
	public boolean isLock(int section, int level) {
		if (level > Const.LevelStatus.LEVEL_QUANTITY) {
			return true;
		}
		List<Progress> progressList = this
				.find(null,
						"section = ? and level = ?",
						new String[] { String.valueOf(section),
								String.valueOf(level) }, null, null, null, null);
		if (progressList.size() > 0) {
			Progress p = progressList.get(0);
			if (p.getStatus() == Const.LevelStatus.LOCK)
				return true;
			else
				return false;
		}
		return true;
	}

	/**
	 * һ����ؿ��õ��ķ���
	 * 
	 * @return
	 */
	public int getSectionScoreEarned(int section) {
		int sum = 0;
		List<Progress> pros = this.find(null, "section = ?",
				new String[] { String.valueOf(section) }, null, null, null,
				null);
		for (Progress p : pros) {
			sum += p.getScore();
		}
		return sum;
	}

	/**
	 * һ����ؿ��õ���������
	 * 
	 * @param section
	 * @return
	 */
	public int getSectionStarEarned(int section) {
		int sum = 0;
		List<Progress> pros = this.find(null, "section = ?",
				new String[] { String.valueOf(section) }, null, null, null,
				null);
		for (Progress p : pros) {
			sum += p.getStar();
		}
		return sum;
	}

	/**
	 * ���йؿ����ܷ���
	 * 
	 * @return
	 */
	public int getScoreEarned() {
		int sum = 0;
		List<Progress> pros = this.find();
		for (Progress p : pros) {
			sum += p.getScore();
		}
		return sum;
	}

	/**
	 * ��ȥ�ùؿ���������ķ������������¿�ʼ��ĳ��
	 * 
	 * @param section
	 * @param level
	 * @return
	 */
	public int getScoreEarned(int section, int level) {
		int sum = 0;
		List<Progress> pros = this.find(null, "section = ?",
				new String[] { String.valueOf(section) }, null, null, null,
				null);
		for (Progress p : pros) {
			if (p.getLevel() != level)
				sum += p.getScore();
		}
		return sum;
	}

	/**
	 * �������
	 * 
	 * @param progress
	 * @return
	 */
	public void saveProgress(Progress progress) {
		List<Progress> pros = this.find(
				null,
				"section = ? and level = ?",
				new String[] { String.valueOf(progress.getSection()),
						String.valueOf(progress.getLevel()) }, null, null,
				null, null);
		if (pros.size() > 0) {
			// ����֮ǰ�ļ�¼������
			Progress oldProgress = pros.get(0);
			// �µļ�¼���ϵļ�¼�ã���洢
			if (progress.getScore() >= oldProgress.getScore()
					|| progress.getStar() >= oldProgress.getStar()) {

				oldProgress.setScore(Math.max(oldProgress.getScore(),
						progress.getScore()));
				oldProgress.setStar(Math.max(oldProgress.getStar(),
						progress.getStar()));
				oldProgress.setStatus(progress.getStatus());
				this.update(oldProgress);
			}
		} else {
			// �����µ�
			this.insert(progress);
		}
	}

}
