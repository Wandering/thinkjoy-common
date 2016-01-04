package cn.thinkjoy.domain;

import cn.thinkjoy.common.mybatis.core.mybatis.domain.PKEntity;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/27 下午8:34<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class Role implements PKEntity<Long>{
    private String name;
    private String description;
    private Long creator;
    private Long createDate;
    private Long lastModifier;
    private Long lastModDate = Long.valueOf(System.currentTimeMillis());
    private Integer status;

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Role() {
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
}
