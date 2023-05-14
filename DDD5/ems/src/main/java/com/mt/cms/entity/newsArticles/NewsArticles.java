package com.mt.cms.entity.newsArticles;

import javax.persistence.*;
import java.io.Serializable;
import com.mt.common.core.annotation.DColumn;
import com.mt.common.core.annotation.DEntity;
import com.mt.common.core.web.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import com.mt.cms.entity.publisher.Publisher;

@DEntity(label="新闻文章",comment="",moduleLabel="新闻文章列表")
@Entity(name="cms_news_articles")
@Table(name = "cms_news_articles" , indexes = { @Index(name = "index_publisher_name_id", columnList = "publisher_name_id")  })
@ApiModel(description = "新闻文章:")
public class NewsArticles extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@DColumn(index=3,label="新闻标题",comment="新闻标题",component="文本",where=false)
	@ApiModelProperty(value = "新闻标题:新闻标题")
	@Column(name="title",length=255,nullable=false,unique=false)
	private String title;

	@DColumn(index=4,label="新闻概要",comment="新闻概要",component="文本",where=false)
	@ApiModelProperty(value = "新闻概要:新闻概要")
	@Column(name="summary",length=255,nullable=true,unique=false)
	private String summary;

	@DColumn(index=5,label="新闻内容",comment="新闻内容",component="文本",where=false)
	@ApiModelProperty(value = "新闻内容:新闻内容")
	@Column(name="context",length=255,nullable=true,unique=false)
	private String context;

	@DColumn(index=6,label="新闻类别id",comment="新闻类别",component="数字，整数",where=false)
	@ApiModelProperty(value = "新闻类别id:新闻类别")
	@Column(name="category_id",length=255,nullable=true,unique=false)
	private Integer categoryId;

	@DColumn(index=7,label="新闻缩略图",comment="新闻缩略图",component="文本",where=false)
	@ApiModelProperty(value = "新闻缩略图:新闻缩略图")
	@Column(name="thumbnail",length=255,nullable=true,unique=false)
	private String thumbnail;

	@DColumn(index=8,label="评论数",comment="评论数",component="数字，整数",where=false)
	@ApiModelProperty(value = "评论数:评论数")
	@Column(name="comment_num",length=255,nullable=true,unique=false)
	private Integer commentNum;

	@DColumn(index=9,label="浏览数",comment="浏览数",component="数字，整数",where=false)
	@ApiModelProperty(value = "浏览数:浏览数")
	@Column(name="view_num",length=255,nullable=true,unique=false)
	private Integer viewNum;

	@DColumn(index=10,label="点赞数",comment="点赞数",component="数字，整数",where=false)
	@ApiModelProperty(value = "点赞数:点赞数")
	@Column(name="thumb_num",length=255,nullable=true,unique=false)
	private Integer thumbNum;

	@DColumn(index=11,label="收藏数",comment="收藏数",component="数字，整数",where=false)
	@ApiModelProperty(value = "收藏数:收藏数")
	@Column(name="collect_num",length=255,nullable=true,unique=false)
	private Integer collectNum;

	@DColumn(index=12,label="新闻类型",comment="新闻类型1文章，2草稿",component="数字，整数",where=false)
	@ApiModelProperty(value = "新闻类型:新闻类型1文章，2草稿")
	@Column(name="news_type",length=255,nullable=true,unique=false)
	private Integer newsType;

	@DColumn(index=13,label="状态",comment="状态1显示，0不显示",component="文本",where=false)
	@ApiModelProperty(value = "状态:状态1显示，0不显示")
	@Column(name="status",length=255,nullable=true,unique=false)
	private String status;

	@DColumn(index=14,label="是否置顶",comment="是否置顶",component="数字，整数",where=false)
	@ApiModelProperty(value = "是否置顶:是否置顶")
	@Column(name="is_top",length=255,nullable=true,unique=false)
	private Integer isTop;

	@DColumn(index=15,label="作者姓名",foreignEntity="Publisher",comment="作者姓名",component="对象选择",where=false)
	@ApiModelProperty(value = "作者姓名:作者姓名")
	@Column(name="publisher_name_id",length=255,nullable=true,unique=false)
	private Long publisherNameId;

	@Transient
	private Publisher publisherName;

	@Transient
	@ApiModelProperty(value = "作者姓名名称:作者姓名")
	@DColumn(index=15,label="作者姓名",foreignEntity="Publisher",comment="作者姓名")
	private String publisherNameName;


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getViewNum() {
		return this.viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getThumbNum() {
		return this.thumbNum;
	}

	public void setThumbNum(Integer thumbNum) {
		this.thumbNum = thumbNum;
	}

	public Integer getCollectNum() {
		return this.collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getNewsType() {
		return this.newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Long getPublisherNameId() {
		return this.publisherNameId;
	}

	public void setPublisherNameId(Long publisherNameId) {
		this.publisherNameId = publisherNameId;
	}

	public Publisher getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(Publisher publisherName) {
		if(publisherName == null){
		}
		else
		{
		this.publisherNameId = publisherName.getEid();
		this.publisherName = publisherName;		
		}
}

	public String getPublisherNameName() {
		return this.publisherNameName;
	}

	public void setPublisherNameName(String publisherNameName) {
		this.publisherNameName = publisherNameName;
	}



}
