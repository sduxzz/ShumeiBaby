package org.sdu.shumei.database.model;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

/**
 * ͨ�����
 * 
 * @author Craig Lee
 * 
 */
@Table(name = "progress")
public class Progress {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "section", type = "INTEGER")
	private int section;// �ڼ����

	@Column(name = "level", type = "INTEGER")
	private int level;// �ڼ�С��

	@Column(name = "status", type = "INTEGER")
	private int status;// �ؿ�״̬

	@Column(name = "star", type = "INTEGER")
	private int star;// ��õ�������
	@Column(name = "score", type = "INTEGER")
	private int score;// ��õķ���

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Progress [id=" + id + ", section=" + section + ", level="
				+ level + ", status=" + status + ", star=" + star + ", score="
				+ score + "]";
	}

}
