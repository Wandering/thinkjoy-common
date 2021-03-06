package cn.thinkjoy.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Role extends CreateBaseDomain<Integer> {
    /** 角色名称 */
    private String name;
    /** 描述 */
    private String description;

    public Role(){
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id",getId())
                .append("Name",getName())
                .append("Creator",getCreator())
                .append("CreateDate",getCreateDate())
                .append("LastModifier",getLastModifier())
                .append("LastModDate",getLastModDate())
                .append("Description",getDescription())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof Role == false) return false;
        if(this == obj) return true;
        Role other = (Role)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}
