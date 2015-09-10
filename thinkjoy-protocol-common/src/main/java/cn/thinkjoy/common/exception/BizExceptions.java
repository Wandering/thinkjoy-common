package cn.thinkjoy.common.exception;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/9/10 上午11:24<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class BizExceptions {
    /** 不能为空 */
    public static final String REQUIRED_CODE = "0008888";
    /** 特定长度 */
    public static final String LENGTH_CODE = "0007777";
    /** 最大长度 */
    public static final String MAXLENGTH_CODE = "0009999";
    /** 已经存在 */
    public static final String EXISTS_CODE = "0005555";
    /** 不存在 */
    public static final String NOTEXISTS_CODE = "0004444";
    /** 新增失败 */
    public static final String ADDERROR_CODE = "0003333";
    /** 修改失败 */
    public static final String UPDATEERROR_CODE = "0002222";
    /** 删除失败 */
    public static final String DELERROR_CODE = "0001111";
    /** 没有相应的操作权限 */
    public static final String DENY_CODE = "0001112";
    /** 登录验权失败 */
    public static final String AUTHERROR_CODE = "0001113";

}
