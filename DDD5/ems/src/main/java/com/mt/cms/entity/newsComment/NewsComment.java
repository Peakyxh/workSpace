package com.mt.cms.entity.newsComment;

import javax.persistence.*;
import java.io.Serializable;
import com.mt.common.core.annotation.DColumn;
import com.mt.common.core.annotation.DEntity;
import com.mt.common.core.web.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import com.mt.common.system.entity.User;

@DEntity(label="新闻评论",comment="",moduleLabel="新闻评论")
@Entity(name="cms_news_comment")
@Table(name = "cms_news_comment" , indexes = { @Index(name = "index_create_id_id", columnList = "create_id_id")  })
@ApiModel(description = "新闻评论:")
public class NewsComment extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@DColumn(index=3,label="内容",comment="评论内容",component="文本",where=false)
	@ApiModelProperty(value = "内容:评论内容")
	@Column(name="content",length=255,nullable=false,unique=false)
	private String content;

	@DColumn(index=4,label="评论的文章id",comment="评论的文章id",component="数字，整数",where=false)
	@ApiModelProperty(value = "评论的文章id:评论的文章id")
	@Column(name="article_id",length=255,nullable=true,unique=false)
	private Integer articleId;

	@DColumn(index=5,label="创建者的id",foreignEntity="User",comment="创建者的id",component="对象选择",where=false)
	@ApiModelProperty(value = "创建者的id:创建者的id")
	@Column(name="create_id_id",length=255,nullable=true,unique=false)
	private Long createIdId;

	@Transient
	private User createId;

	@Transient
	@ApiModelProperty(value = "创建者的id名称:创建者的id")
	@DColumn(index=5,label="创建者的id",foreignEntity="User",comment="创建者的id")
	private String createIdName;

	@DColumn(index=6,label="父评论id",comment="父评论id",component="数字，整数",where=false)
	@ApiModelProperty(value = "父评论id:父评论id")
	@Column(name="parent_id",length=255,nullable=true,unique=false)
	private Integer parentId;


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Long getCreateIdId() {
		return this.createIdId;
	}

	public void setCreateIdId(Long createIdId) {
		this.createIdId = createIdId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public User getCreateId() {
		return this.createId;
	}

	public void setCreateId(User createId) {
		if(createId == null){
		}
		else
		{
		this.createIdId = createId.getEid();
		this.createId = createId;		
		}
}

	public String getCreateIdName() {
		return this.createIdName;
	}

	public void setCreateIdName(String createIdName) {
		this.createIdName = createIdName;
	}



}
