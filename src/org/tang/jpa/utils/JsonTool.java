package org.tang.jpa.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.tang.jpa.dto.system.ResourceDTO;

public class JsonTool {
	
	 private static StringBuilder returnStr ;
	
	
	 public static boolean hasChild(List<ResourceDTO> list, ResourceDTO node) { // 判断是否有子节点
			boolean flag = false;
			List<ResourceDTO> l = getChildList(list, node);
			if (l != null && l.size() > 0) {
				flag = true;
			}
			return flag;
		}

		public static List<ResourceDTO> getChildList(List<ResourceDTO> list, ResourceDTO node) { // 得到子节点列表
			List<ResourceDTO> li = new ArrayList<ResourceDTO>();
			Iterator<ResourceDTO> it = list.iterator();
			while (it.hasNext()) {
				ResourceDTO n = (ResourceDTO) it.next();
				if(n != null){
					if(StringUtils.isNotEmpty(n.getResourceParentId())){
						if (n.getResourceParentId().equals(node.getResourceId())) {
							li.add(n);
						}
					}
				}

			}
			return li;
		}
	/**
	 * 根据节点，已得的菜单集合生成菜单树
	 * @param list
	 * @param node
	 * @return
	 */
	public static String generateTree(List<ResourceDTO> list, ResourceDTO node) {
		if(returnStr==null){
			returnStr = new StringBuilder();
		}
		if (hasChild(list, node)) {
			returnStr.append("{menuid:");
			returnStr.append("\"").append(node.getResourceId()).append("\"");
			returnStr.append(",icon:");
			returnStr.append("\"").append("icon-sys").append("\"");
			returnStr.append(",menuname:");
			returnStr.append("\"").append(node.getResourceName()).append("\"");
			returnStr.append(",url:");
			returnStr.append("\"").append(node.getResourceUrl()).append("\"");
			returnStr.append(",menuParentId:");
			returnStr.append("\"").append(node.getResourceParentId()).append("\"");
			returnStr.append(",menus:[");
			List<ResourceDTO> childList = getChildList(list, node);
			Iterator<ResourceDTO> it = childList.iterator();
			while (it.hasNext()) {
				ResourceDTO n = (ResourceDTO) it.next();
				generateTree(list, n);
			}
			returnStr.append("]},");
		} else {
			returnStr.append("{menuid:");
			returnStr.append("\"").append(node.getResourceId()).append("\"");
			returnStr.append(",icon:");
			returnStr.append("\"").append("icon-nav").append("\"");
			returnStr.append(",menuname:");
			returnStr.append("\"").append(node.getResourceName()).append("\"");
			returnStr.append(",url:");
			returnStr.append("\"").append(node.getResourceUrl()).append("\"");
			returnStr.append(",menuParentId:");
			returnStr.append("\"").append(node.getResourceParentId()).append("\"");
			returnStr.append("},");
		}
		return returnStr.toString() ;
	}
	
	
    
    public static String modifyStr(String returnStrs){//修饰一下才能满足Extjs的Json格式   
    	returnStr = null;
        return ("["+returnStrs+"]").replaceAll(",]", "]");   
    }

	public StringBuilder getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(StringBuilder returnStr) {
		this.returnStr = returnStr;
	}
    
}
