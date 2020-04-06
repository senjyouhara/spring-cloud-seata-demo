package com.senjyouhara.seata.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


@Data
public class Storage implements Serializable {

	@TableId(type= IdType.AUTO)
	private Long id;
	private String userId;
	private String commodityCode;
	private Long count = 0L;
	private Long money = 0L;
}
