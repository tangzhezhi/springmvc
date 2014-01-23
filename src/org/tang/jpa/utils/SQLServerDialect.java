package org.tang.jpa.utils;

/**
 * @author xdwang
 * 
 * @ceate 2012-12-19 下午7:53:14
 * 
 * @description SQLServer数据库实现
 * 
 */
public class SQLServerDialect implements Dialect {

	public String getPageSql(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pageSql = new StringBuffer(sql.length() + 100);
		// 其实这里还是有一点问题的，就是排序问题，指定死了，有解决的提供一下，等复习到Hibernate看看Hibernat内部是如何实现的。
		pageSql.append("select * from(select a.*,row_number() over (order by id desc) rownum from( ");
		pageSql.append(sql);
		pageSql.append(") a )b where rownum> " + offset + " and rownum <= " + (offset + limit));
		return pageSql.toString();
	}

}


