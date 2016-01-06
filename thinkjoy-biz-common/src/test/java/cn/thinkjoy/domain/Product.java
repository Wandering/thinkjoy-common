package cn.thinkjoy.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Product extends CreateBaseDomain<Integer> {

    /** 名字 */
    private String name;
    /** 网址 */
    private String linkUrl;
    /** xx */
    private Double section;
    /** 描述 */
    private String description;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setLinkUrl(String value) {
        this.linkUrl = value;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }
    public void setSection(Double value) {
        this.section = value;
    }

    public Double getSection() {
        return this.section;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id", getId())
			.append("Name",getName())
			.append("LinkUrl",getLinkUrl())
			.append("Section",getSection())
			.append("Creator", getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Description", getDescription())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Product == false) return false;
		if(this == obj) return true;
		Product other = (Product)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

