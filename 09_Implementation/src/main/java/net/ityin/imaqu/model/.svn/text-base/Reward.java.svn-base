package net.ityin.imaqu.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_REWARD database table.
 * 
 */
@Entity
@Table(name="T_REWARD")
public class Reward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", unique=true, nullable=false)
	private String id;

	@Column(name="ACTION", nullable=false, length=30)
	private String action;

	@Column(name="REWARD")
	private int reward;

    public Reward() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getReward() {
		return this.reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

}