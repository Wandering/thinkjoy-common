/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: ehr
 * $Id:  UserData.java 2014-10-03 16:39:54 $
 */



package cn.thinkjoy.common.managerui.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class UserData extends CreateBaseDomain{
    private Integer status;
    private Integer dataModelId;
    private Integer dataId;
    private Integer userId;

	public UserData(){
	}
    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }
    public void setDataModelId(Integer value) {
        this.dataModelId = value;
    }

    public Integer getDataModelId() {
        return this.dataModelId;
    }
    public void setDataId(Integer value) {
        this.dataId = value;
    }

    public Integer getDataId() {
        return this.dataId;
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
			.append("DataModelId",getDataModelId())
			.append("DataId",getDataId())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserData == false) return false;
		if(this == obj) return true;
		UserData other = (UserData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

