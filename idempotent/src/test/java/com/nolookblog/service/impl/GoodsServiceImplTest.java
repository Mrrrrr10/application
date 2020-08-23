package com.nolookblog.service.impl;

import com.nolookblog.IdempotentApplication;
import com.nolookblog.pojo.dto.GoodsDTO;
import com.nolookblog.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IdempotentApplication.class)
public class GoodsServiceImplTest {

	@Autowired
	private GoodsService goodsService;

	@Test
	public void test() {
		int goodsId = 1;

		// 根据相同的id查询出商品信息，赋给2个对象
		GoodsDTO goodsDTO1 = goodsService.getById(goodsId);
		GoodsDTO goodsDTO2 = goodsService.getById(goodsId);

		// 打印当前商品信息
		System.out.println(goodsDTO1);
		System.out.println(goodsDTO2);

		// 更新商品信息1
		goodsDTO1.setStatus("2");
		int updateResult1 = goodsService.update(goodsDTO1);
		System.out.println("修改商品信息1" + (updateResult1 == 1 ? "成功" : "失败"));

		// 更新商品信息2
		goodsDTO2.setStatus("2");
		int updateResult2 = goodsService.update(goodsDTO2);
		System.out.println("修改商品信息2" + (updateResult2 == 1 ? "成功" : "失败"));
	}

}