package cn.thinkjoy.common.domain;

/**
 * 搜索操作
 * 
 * @author xjli
 * 
 */
public class SearchField {
	/* 搜索字段名称 */
	private String field;

	/* 操作符 == > < */
	private String op;

	/* 值 */
	private String data;

	public SearchField(String field,String op,String data)
	{
		this.field = field;
		this.op = op;
		this.data = data;

	}

	public SearchField()
	{

	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
