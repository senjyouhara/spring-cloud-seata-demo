package com.senjyouhara.seata.demo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageMapper extends BaseMapper<Storage> {

	@Update("update storage_tbl set count = count - #{count} where commodity_code = #{commodityCode}")
	Integer updateEntity(@Param("count") Integer count, @Param("commodityCode") String commodityCode);

	@Update("delete from storage_tbl where commodity_code = #{commodityCode}")
	Integer deleteEntity(@Param("commodityCode") String commodityCode);

	@Update("insert into storage_tbl(commodity_code, count) values (#{commodityCode} , #{count})")
	Integer insertEntity(@Param("count") Integer count, @Param("commodityCode") String commodityCode);
}
