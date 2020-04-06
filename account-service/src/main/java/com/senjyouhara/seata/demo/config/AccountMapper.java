package com.senjyouhara.seata.demo.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper extends BaseMapper<Account> {

	@Delete("delete from account_tbl where user_id = #{userId}")
	Integer deleteEntity(@Param("userId") String userId);

	@Insert("insert into account_tbl(user_id, money) values (#{userId}, #{money})")
	Integer insertEntity(@Param("userId") String userId,@Param("money") Integer money);

	@Update("update account_tbl set money = money - #{money} where user_id = #{userId}")
	Integer updateEntity(@Param("userId") String userId,@Param("money") Integer money);

}
