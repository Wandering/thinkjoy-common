package cn.thinkjoy.common.mybatis.core.mybatis.paging;

/**
 * 分页导航生成工具类
 * 
 * @author shadow
 * 
 */
public class PageUtils {

	private static final long show_ = 10;

	private static final long sep_ = show_ / 2;

	private static final long split_ = sep_ / 2;

	public static String build(long offset, long limit, long total) {
		return build(offset, limit, total, "pageClick");
	}

	public static String build(long offset, long limit, long total, String func) {

		long count = total % limit == 0 ? total / limit : total / limit + 1;

		StringBuffer buffer = new StringBuffer();

		buffer.append("<span class='count_result'>共 " + count + " 页 " + total + " 条记录 </span>");

		// 判断是否显示上页
		if (offset > 1) {
			long prev = offset - 1;
			buffer.append(getNormalPart("上页", prev, limit, func));
		}

		// 页数不超过限制的情况
		if (count <= show_)
			buffer.append(getPart(1, count, offset, limit, func));

		// 页数大于限制的情况

		if (count > show_) {
			if (offset <= sep_) {
				buffer.append(getPart(1, sep_ + split_, offset, limit, func));
				buffer.append(getEllipsis("...")).append(getNormalPart(String.valueOf(count), count, limit, func));
			} else if (offset > (count - sep_)) {
				buffer.append(getNormalPart(String.valueOf(1), 1, limit, func)).append(getEllipsis("..."));
				buffer.append(getPart(count - sep_ - 1, count, offset, limit, func));
			} else {
				buffer.append(getNormalPart(String.valueOf(1), 1, limit, func)).append(getEllipsis("..."));
				buffer.append(getPart(offset - split_ - 1, offset + split_ + 1, offset, limit, func));
				buffer.append(getEllipsis("...")).append(getNormalPart(String.valueOf(count), count, limit, func));
			}
		}

		// 判断是否显示下页
		if (offset < count) {
			long next = offset + 1;
			buffer.append(getNormalPart("下页", next, limit, func));
		}

		return buffer.toString();

	}

	// 一般按钮
	private static StringBuffer getNormalPart(String content, long offset, long limit, String func) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<a href='javascript:void(0);' ").append("onclick='").append(func).append("(").append(offset)
				.append(",").append(limit).append(");'").append("'>").append(content).append("</a>");
		return buffer;
	}

	// 拼接中间部分
	private static StringBuffer getPart(long begin, long end, long offset, long limit, String func) {
		StringBuffer buffer = new StringBuffer();
		for (long i = begin; i <= end; i++) {
			if (offset == i)
				buffer.append(getSelectedPart(String.valueOf(i), i));
			else
				buffer.append(getNormalPart(String.valueOf(i), i, limit, func));
		}
		return buffer;
	}

	// 选中按钮
	private static StringBuffer getSelectedPart(String content, long value) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<a href='javascript:void(0);'").append(" class='current'>").append(content).append("</a>");
		return buffer;
	}

	// 省略部分
	private static StringBuffer getEllipsis(String content) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<a href='javascript:void(0);'>").append(content).append("</a>");
		return buffer;
	}

}
