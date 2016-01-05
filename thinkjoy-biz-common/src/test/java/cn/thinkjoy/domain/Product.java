package cn.thinkjoy.domain;

import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Product implements PKEntity<Long> {

    private Long id;
    /** 名字 */
    private String name;
    /** 网址 */
    private String linkUrl;
    /** xx */
    private Double section;
    /** 描述 */
    private String description;

    private Long creator;
    private Long createDate;
    private Long lastModifier;
    private Long lastModDate = Long.valueOf(System.currentTimeMillis());
    private Integer status;

	public Product(){
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Long getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Long lastModDate) {
        this.lastModDate = lastModDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

