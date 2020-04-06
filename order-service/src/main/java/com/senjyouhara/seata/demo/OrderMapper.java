package com.senjyouhara.seata.demo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper  extends BaseMapper<Order> {

	@Update("update storage_tbl set count = count - #{count} where commodity_code = #{commodityCode}")
	Integer updateEntity(@Param("count") Integer count, @Param("commodityCode") String commodityCode);

	@Delete("TRUNCATE TABLE order_tbl")
	Integer deleteEntity();

	@Insert("insert into order_tbl (user_id, commodity_code, count, money) values (#{userId}, #{commodityCode}, #{count}, #{money})")
	Integer insertEntity(@Param("userId") String userId, @Param("count") Integer count,@Param("money") Integer money, @Param("commodityCode") String commodityCode);

}
