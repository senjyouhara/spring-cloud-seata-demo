package com.senjyouhara.seata.demo.config;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {


	//	  `id` int(11) NOT NULL AUTO_INCREMENT,
//  `user_id` varchar(255) DEFAULT NULL,
//  `money` int(11) DEFAULT 0,
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String userId;
	private Integer money;


}
