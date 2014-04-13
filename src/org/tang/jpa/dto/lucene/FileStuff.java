package org.tang.jpa.dto.lucene;
/**
 * 文件资料
 * @author alex
 *
 */
public class FileStuff extends Stuff{
	private String filePath;
	private String contentFieldName;
	public String getContentFieldName() {
		return contentFieldName;
	}

	public void setContentFieldName(String contentFieldName) {
		this.contentFieldName = contentFieldName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
