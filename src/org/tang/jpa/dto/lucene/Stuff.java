package org.tang.jpa.dto.lucene;

import java.util.Map;
/**
 * 文档库资料对象的基类
 * @author alex
 *
 */
public class Stuff {
	private Map<String,String> attacheData;

	public Map<String,String> getAttacheData() {
		return attacheData;
	}

	public void setAttacheData(Map<String,String> attacheData) {
		this.attacheData = attacheData;
	};
	
}