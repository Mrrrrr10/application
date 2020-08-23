package com.nolookblog.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_good")
public class GoodsDO {

	/**
	 * id:主键id.
	 */
	private int id;

	/**
	 * status:商品状态：1未下单、2已下单.
	 */
	private String status;

	/**
	 * name:商品名称.
	 */
	private String name;

	/**
	 * version:商品数据版本号.
	 */
	private int version;

	/**
	 * gmtCreate:数据创建时间.
	 */
	private Date gmtCreate;

	/**
	 * gmtUpdate:数据更新时间.
	 */
	private Date gmtUpdate;
}  