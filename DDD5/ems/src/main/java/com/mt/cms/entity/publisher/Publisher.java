package com.mt.cms.entity.publisher;

import javax.persistence.*;
import java.io.Serializable;
import com.mt.common.core.annotation.DColumn;
import com.mt.common.core.annotation.DEntity;
import com.mt.common.core.web.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




@DEntity(label="发布者信息",comment="",moduleLabel="新闻发布者信息")
@Entity(name="cms_publisher")
@Table(name = "cms_publisher" , indexes = {   })
@ApiModel(description = "发布者信息:")
public class Publisher extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@DColumn(index=3,label="联系电话",comment="发布人联系电话",component="文本",where=false)
	@ApiModelProperty(value = "联系电话:发布人联系电话")
	@Column(name="phone",length=255,nullable=true,unique=false)
	private String phone;

	@DColumn(index=4,label="地址",comment="发布人地址",component="文本",where=false)
	@ApiModelProperty(value = "地址:发布人地址")
	@Column(name="address",length=255,nullable=true,unique=false)
	private String address;

	@DColumn(index=5,label="新闻条数",comment="发布人发布的新闻条数",component="数字，整数",where=false)
	@ApiModelProperty(value = "新闻条数:发布人发布的新闻条数")
	@Column(name="count",length=255,nullable=true,unique=false)
	private Integer count;


	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}



}
