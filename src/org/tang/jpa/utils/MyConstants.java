package org.tang.jpa.utils;

public enum MyConstants {
	
	SUCCESS("操作成功"), FAIL("操作失败"),
	ADDSUCCESS("添加成功"), ADDFAIL("添加失败"),
	MODIFYSUCCESS("修改成功"), MODIFYFAIL("修改失败"),
	DELSUCCESS("删除成功"), DELFAIL("删除失败"),
	AUTHSUCCESS("授权成功"), AUTHFAIL("授权失败"),
	EXIST("已经存成"),
	HADAPPROVAL("已经审核,不能修改或者删除"), 
	APPROVALSUCCESS("审核通过"), 
	APPROVALFAIL("审核不成功"), 
	MALE("男"), FEMALE("女");
	
	private final String name;

	private MyConstants(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
