/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: cmc
 * $Id:  UserDatagroup.java 2014-10-27 10:37:55 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class UserDatagroup extends CreateBaseDomain<Long>{
    private Integer datagroupId;
    private Integer userId;

	public UserDatagroup(){
	}
    public void setDatagroupId(Integer value) {
        this.datagroupId = value;
    }

    public Integer getDatagroupId() {
        return this.datagroupId;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Status",getStatus())
			.append("DatagroupId",getDatagroupId())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserDatagroup == false) return false;
		if(this == obj) return true;
		UserDatagroup other = (UserDatagroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

