package org.sdu.shumei.database.model;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

/**
 * �������ģ����߹������
 * 
 * @author Craig Lee
 * 
 */
@Table(name = "prop")
public class Prop {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "scoreConsumed", type = "INTEGER")
	private int scoreConsumed;// ���ĵĻ���

	@Column(name = "propId", type = "INTEGER")
	private int propId;// ����ID

	@Column(name = "quantity", type = "INTEGER")
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScoreConsumed() {
		return scoreConsumed;
	}

	public void setScoreConsumed(int scoreConsumed) {
		this.scoreConsumed = scoreConsumed;
	}

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Prop [id=" + id + ", scoreConsumed=" + scoreConsumed
				+ ", propId=" + propId + ", quantity=" + quantity + "]";
	}

}
