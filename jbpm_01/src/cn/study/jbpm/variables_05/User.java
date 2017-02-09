package cn.study.jbpm.variables_05;

import java.io.Serializable;

/**
 * 用户对象，要操作的复杂对象
 */
public class User implements Serializable{
	
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
