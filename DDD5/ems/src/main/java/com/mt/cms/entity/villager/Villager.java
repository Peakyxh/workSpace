package com.mt.cms.entity.villager;

import javax.persistence.*;
import java.io.Serializable;
import com.mt.common.core.annotation.DColumn;
import com.mt.common.core.annotation.DEntity;
import com.mt.common.core.web.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




@DEntity(label="村民信息",comment="",moduleLabel="村民信息")
@Entity(name="cms_villager")
@Table(name = "cms_villager" , indexes = {   })
@ApiModel(description = "村民信息:")
public class Villager extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@DColumn(index=3,label="年龄",comment="年龄",component="数字，整数",where=false)
	@ApiModelProperty(value = "年龄:年龄")
	@Column(name="age",length=255,nullable=false,unique=false)
	private Integer age;

	@DColumn(index=4,label="性别",comment="性别",component="文本",where=false)
	@ApiModelProperty(value = "性别:性别")
	@Column(name="gender",length=255,nullable=true,unique=false)
	private String gender;

	@DColumn(index=5,label="手机号",comment="村民的手机号",component="文本",where=false)
	@ApiModelProperty(value = "手机号:村民的手机号")
	@Column(name="phone_number",length=255,nullable=true,unique=false)
	private String phoneNumber;

	@DColumn(index=6,label="地址",comment="村民的地址",component="文本",where=false)
	@ApiModelProperty(value = "地址:村民的地址")
	@Column(name="address",length=255,nullable=true,unique=false)
	private String address;


	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}
