package com.mt.cms.entity.newsCategory;

import javax.persistence.*;
import java.io.Serializable;
import com.mt.common.core.annotation.DColumn;
import com.mt.common.core.annotation.DEntity;
import com.mt.common.core.web.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




@DEntity(label="新闻类别",comment="",moduleLabel="新闻类别")
@Entity(name="cms_news_category")
@Table(name = "cms_news_category" , indexes = {   })
@ApiModel(description = "新闻类别:")
public class NewsCategory extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@DColumn(index=3,label="状态",comment="状态1是显示，0是不显示",component="文本",where=false)
	@ApiModelProperty(value = "状态:状态1是显示，0是不显示")
	@Column(name="status",length=255,nullable=true,unique=false)
	private String status;


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
