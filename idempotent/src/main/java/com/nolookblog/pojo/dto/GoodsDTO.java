package com.nolookblog.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Data
public class GoodsDTO implements Serializable {

	/**
	 * serialVersionUID:序列化ID.
	 */
	private static final long serialVersionUID = 6803791908148880587L;

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
