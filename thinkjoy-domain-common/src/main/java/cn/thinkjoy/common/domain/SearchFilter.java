package cn.thinkjoy.common.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * 搜索条件集合
 * 
 * @author xjli
 * 
 */
public class SearchFilter {

	/**
	 * 多个条件的并列关系 and or
	 */
	private String groupOp;

	/**
	 * 搜索条件集合
	 */
	private List<SearchField> rules = new ArrayList<>();

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<SearchField> getRules() {
		return rules;
	}

	public void setRules(List<SearchField> rules) {
		this.rules = rules;
	}

}
