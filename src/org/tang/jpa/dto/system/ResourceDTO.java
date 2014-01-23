package org.tang.jpa.dto.system;

import java.util.List;


public class ResourceDTO {
	
	private String resourceId; 
	
	private String roleId;
	
	private String resourceName;
	
	private String resourceCode;
	
	private String resourceUrl;
	
	private String resourceParentId;
	
	private String resourceSort;
	
	/**
	 * 资源类型：1是url页面型、2是url方法,这样就把方法也可以进行授权了
	 */
	private String resourceType;
	
	
    public ResourceDTO(){}
	
    public ResourceDTO(String resourceId,String resourceParentId,String resourceName){
        this.resourceParentId=resourceParentId;
        this.resourceId=resourceId;
        this.resourceName = resourceName;
    }
	
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceParentId() {
		return resourceParentId;
	}
	public void setResourceParentId(String resourceParentId) {
		this.resourceParentId = resourceParentId;
	}
	public String getResourceSort() {
		return resourceSort;
	}
	public void setResourceSort(String resourceSort) {
		this.resourceSort = resourceSort;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

}
