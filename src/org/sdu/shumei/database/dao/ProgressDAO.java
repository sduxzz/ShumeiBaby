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
	 * 判断一关是否解锁
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
	 * 一个大关卡得到的分数
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
	 * 一个大关卡得到的星星数
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
	 * 所有关卡的总分数
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
	 * 除去该关卡以外的所的分数，用于重新开始玩某关
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
	 * 保存进程
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
			// 存在之前的记录，更新
			Progress oldProgress = pros.get(0);
			// 新的记录比老的记录好，则存储
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
			// 插入新的
			this.insert(progress);
		}
	}

}
