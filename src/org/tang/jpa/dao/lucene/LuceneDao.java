package org.tang.jpa.dao.lucene;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.tang.jpa.dto.lucene.Stuff;
import org.wltea.analyzer.lucene.IKAnalyzer;


public abstract class LuceneDao {
	private Analyzer analyzer = new IKAnalyzer(true);
	private String indexPath;

	public void add(Stuff stuff) throws Exception {
		createIndex(stuff);
	}

	public void batchAdd(List<Stuff> stuffs) throws Exception {
		createIndexs(stuffs);
	}

	/***
	 * 
	 * 删除方法
	 * 
	 * */

	public void delete(String fieldName, String fieldVaule) {
		try {
			IndexWriter writer = getIndexWrite();
			Query q = new TermQuery(new Term(fieldName, fieldVaule));
			writer.deleteDocuments(q);// 删除指定条件的Document
			writer.commit();// 提交
			writer.close();// 关闭
			System.out.println("删除" + fieldName + "为" + fieldVaule + "的记录成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param fieldMap
	 * @throws Exception
	 */
	public void batchDelete(Map<String, String> fieldMap) throws Exception {
		IndexWriter writer = getIndexWrite();
		for (String fieldName : fieldMap.keySet()) {
			Query q = new TermQuery(
					new Term(fieldName, fieldMap.get(fieldName)));
			writer.deleteDocuments(q);// 删除指定条件的Document
			System.out.println("删除" + fieldName + "为" + fieldMap.get(fieldName)
					+ "的记录成功");
		}
		writer.commit();// 提交
		writer.close();// 关闭
	}

	protected abstract Document getDocument(Stuff stuff) throws Exception;

	/**
	 * 
	 * @param fieldName
	 * @param fieldVaule
	 * @param stuff
	 * @throws Exception
	 */
	public void update(String fieldName, String fieldVaule, Stuff stuff)
			throws Exception {
		try {
			IndexWriter writer = getIndexWrite();
			Document doc = getDocument(stuff);
			writer.updateDocument(new Term(fieldName, fieldVaule), doc);
			writer.commit();
			writer.close();// 关闭
			System.out.println("更新" + fieldName + "为" + fieldVaule + "的记录成功");
		} catch (Exception e) {
			throw e;
		}
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	/**
	 * 设置索引文件的目录
	 * 
	 * @param indexPath
	 */
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	/**
	 * 创建索引
	 * 
	 * @param analyzer
	 * @param indexPath
	 * @param docPath
	 * @throws Exception
	 */
	protected void createIndex(Stuff stuff) throws Exception {
		IndexWriter iwriter = getIndexWrite();
		indexDoc(iwriter, stuff);
		iwriter.commit();
		iwriter.close();
	}

	protected void indexDoc(IndexWriter iwriter, Stuff stuff) throws Exception {
		Document doc = getDocument(stuff);
		iwriter.addDocument(doc);
	}

	/**
	 * 批量创建索引
	 * 
	 * @param analyzer
	 * @param indexPath
	 * @param docPath
	 * @throws Exception
	 */
	protected void createIndexs(List<Stuff> stuffs) throws Exception {
		IndexWriter iwriter = getIndexWrite();
		for (Stuff stuff : stuffs) {
			indexDoc(iwriter, stuff);
		}
		iwriter.close();
	}

	/**
	 * 获取IndexWrite实例
	 * 
	 * @param analyzer
	 * @param indexPath
	 * @return
	 * @throws IOException
	 */
	protected IndexWriter getIndexWrite() throws IOException {
		IndexWriter iwriter;
		Directory directory = FSDirectory.open(new File(indexPath));
		// 配置IndexWriterConfig
		IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		iwriter = new IndexWriter(directory, iwConfig);
		return iwriter;
	}

	/**
	 * 搜索
	 * 
	 * @param searchField
	 *            搜索域
	 * @param indexPath
	 *            索引目录
	 * @param topCount
	 *            返回搜索相似度最高的条数
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Document[] search(String searchField, String searchKeyStr,
			int topCount) throws CorruptIndexException, IOException,
			ParseException {
		Directory directory = FSDirectory.open(new File(indexPath));
		// 搜索过程**********************************
		// 实例化搜索器
		IndexReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

		// 使用QueryParser查询分析器构造Query对象
		QueryParser qp = new QueryParser(Version.LUCENE_47, searchField,
				analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query query = qp.parse(searchKeyStr);

		// 搜索相似度最高的topCount条记录
		TopDocs topDocs = isearcher.search(query, topCount);
		// 输出结果
		Document[] docs=new Document[topDocs.scoreDocs.length];
		for(int i=0;i<docs.length;i++){
			docs[i]=isearcher.doc(topDocs.scoreDocs[i].doc);
		}
		return docs;
	}

	public void displaySearchResult(Document[] docs) {
		System.out.println("开始显示搜索查询结果....\n返回查询条数："+docs.length);
	}

	/**
	 * 为索引文档添加附加的数据,一般为数据库存储相关记录的主键,便于在搜索后查询该文档其它的信息
	 * 
	 * @param attachData
	 * @param doc
	 */
	protected void addAttacheData(Document doc, Map<String, String> attachData) {
		if (attachData != null) {
			Set<String> keys = attachData.keySet();
			for (String key : keys) {
				doc.add(new StringField(key, attachData.get(key),
						Field.Store.YES));
			}
		}
	}

}
