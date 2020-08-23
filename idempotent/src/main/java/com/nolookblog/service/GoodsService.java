package com.nolookblog.service;

import com.nolookblog.pojo.dto.GoodsDTO;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public interface GoodsService {

	/**
	 * 根据id查询商品
	 *
	 * @param id
	 * @return
	 */
	GoodsDTO getById(int id);

	/**
	 * 更新商品
	 *
	 * @param goodsDTO
	 * @return
	 */
	int update(GoodsDTO goodsDTO);

}
