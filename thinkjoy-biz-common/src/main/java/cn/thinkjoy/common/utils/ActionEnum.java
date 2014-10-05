package cn.thinkjoy.common.utils;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * 功能按钮枚举
 * <p/>
 * 创建时间: 14-10-2 下午12:58<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public enum ActionEnum {
    ADD("add", "新增"),
    EDIT("edit", "修改"),
    //VIEW("add", "新增"),
    DEL("del", "删除"),
    IMPORT("import", "导入数据"),
    EXPORT("import", "导出数据"),
    EXPORTTPL("exportTpl", "导出摸板");

    private String action;
    private String name;

    private ActionEnum(String action, String name){
        this.action = action;
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
