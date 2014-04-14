package org.tang.jpa.dao.lucene;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.tika.Tika;
import org.tang.jpa.dto.lucene.FileStuff;
import org.tang.jpa.dto.lucene.Stuff;
/**
 * 文档库访问之文件对象实现
 * @author alex
 *
 */
public class LuceneDaoFileImpl extends LuceneDao{
	private static String contentFieldName = "content";
	private static Tika tika = new Tika();

	protected void indexDoc(IndexWriter iwriter, Stuff stuff) throws Exception {
		FileStuff fileStuff=(FileStuff)stuff;
		File file=new File(fileStuff.getFilePath());
		if(file.isDirectory()){
			indexDocByFileDir(iwriter,new File(fileStuff.getFilePath()),stuff.getAttacheData());
		}else{
			super.indexDoc(iwriter,stuff);
		}
	}
	
	/**
	 * 根据指定存放内容的目录创建索引
	 * 
	 * @param iwriter
	 * @param file
	 * @throws IOException
	 */
	private void indexDocByFileDir(IndexWriter iwriter, File file,Map<String,String> attachData) throws IOException {
		if (file.canRead()){
			if (file.isDirectory()) {
				String[] files = file.list();
				if (files != null)
					for (int i = 0; i < files.length; i++)
						indexDocByFileDir(iwriter, new File(file, files[i]),attachData);
			} else {
				Document doc = getDocument(file,attachData);
				iwriter.addDocument(doc);
			}
		}
	}
	
	protected Document getDocument(File file,Map<String,String> attachData) throws IOException {
		Document doc = new Document();
		addAttacheData(doc,attachData );
		// 此处添加文件内容时，需要根据tika获取Reader对象
		doc.add(new TextField(contentFieldName, tika.parse(file)));
		doc.add(new StringField("fileName", file.getName(),
				Field.Store.YES));
		doc.add(new StringField("path", file.getAbsolutePath(),
				Field.Store.YES));
		return doc;
	}
	
	public void displaySearchResult(Document[] docs) {
		super.displaySearchResult(docs);
		for (int i = 0; i < docs.length; i++) {
			System.out.println("内容：" + docs[i].toString());
			System.out.println(docs[i].get("fileName") + "["
					+ docs[i].get("path") + "]");
		}
	}
	@Override
	protected Document getDocument(Stuff stuff) throws IOException {
		FileStuff fileStuff=(FileStuff)stuff;
		File file=new File(fileStuff.getFilePath());
		Document doc = new Document();
		addAttacheData(doc,stuff.getAttacheData() );
		// 此处添加文件内容时，需要根据tika获取Reader对象
		doc.add(new TextField(contentFieldName, tika.parse(file)));
		doc.add(new StringField("fileName", file.getName(),
				Field.Store.YES));
		doc.add(new StringField("path", file.getAbsolutePath(),
				Field.Store.YES));
		return doc;
	}

}
