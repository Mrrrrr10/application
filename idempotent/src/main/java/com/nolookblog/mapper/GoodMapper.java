package com.nolookblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nolookblog.pojo.domain.GoodsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Mapper
public interface GoodMapper extends BaseMapper<GoodsDO> {

	/**
	 * 更新商品信息: 使用CAS(compare and set)
	 *
	 * @param goodsDO
	 * @return 影响的行数
	 */
	int updateGoodsUseCAS(GoodsDO goodsDO);

}
