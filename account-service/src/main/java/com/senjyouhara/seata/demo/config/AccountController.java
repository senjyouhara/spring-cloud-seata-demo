/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.senjyouhara.seata.demo.config;

import java.util.Random;

import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author xiaojing
 */
@Log4j2
@RestController
public class AccountController {


	private static final String SUCCESS = "SUCCESS";

	private static final String FAIL = "FAIL";

	@Autowired
	private AccountMapper accountMapper;


	private Random random = new Random();

	@PostMapping(value = "/account/{userId}/{money}")
	public String account(@PathVariable("userId") String userId,@PathVariable("money") Integer money) {
		log.info("Account Service ... xid: " + RootContext.getXID());

//		if (random.nextBoolean()) {
//			throw new RuntimeException("this is a mock Exception");
//		}

		Integer result = accountMapper.updateEntity(userId, money);

		log.info("Account Service End ... ");
		if (result == 1) {
			return SUCCESS;
		}
		return FAIL;
	}


	@PostConstruct
	public void init(){
		accountMapper.deleteEntity("U100001");
		accountMapper.insertEntity("U100001",10000);
	}

}
