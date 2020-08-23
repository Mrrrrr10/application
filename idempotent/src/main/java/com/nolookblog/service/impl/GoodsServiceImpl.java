package com.nolookblog.service.impl;

import com.nolookblog.mapper.GoodMapper;
import com.nolookblog.pojo.domain.GoodsDO;
import com.nolookblog.pojo.dto.GoodsDTO;
import com.nolookblog.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodMapper goodMapper;

	/**
	 * 根据id查询商品
	 *
	 * @param id
	 * @return
	 */
	@Override
	public GoodsDTO getById(int id) {
		GoodsDO goodsDO = goodMapper.selectById(id);
		GoodsDTO goodsDTO = new GoodsDTO();
		BeanUtils.copyProperties(goodsDO, goodsDTO);

		return goodsDTO;
	}

	/**
	 * 更新商品
	 *
	 * @param goodsDTO
	 * @return
	 */
	@Override
	public int update(GoodsDTO goodsDTO) {
		GoodsDO goodsDO = new GoodsDO();
		BeanUtils.copyProperties(goodsDTO, goodsDO);

		return goodMapper.updateGoodsUseCAS(goodsDO);
	}
}
