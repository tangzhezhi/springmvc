package org.tang.jpa.utils;

/**
 * @author xdwang
 * 
 * @ceate 2012-12-19 下午7:45:24
 * 
 * @description 数据库方言接口
 * 
 */

public interface Dialect {

	public static enum Type {
		MYSQL {
			public String getValue() {
				return "mysql";
			}
		},
		MSSQL {
			public String getValue() {
				return "sqlserver";
			}
		},
		ORACLE {
			public String getValue() {
				return "oracle";
			}
		}
	}

	/**
	 * @descrption 获取分页SQL
	 * @author xdwang
	 * @create 2012-12-19下午7:48:44
	 * @param sql
	 *            原始查询SQL
	 * @param offset
	 *            开始记录索引（从零开始）
	 * @param limit
	 *            每页记录大小
	 * @return 返回数据库相关的分页SQL语句
	 */
	public abstract String getPageSql(String sql, int offset, int limit);

}


