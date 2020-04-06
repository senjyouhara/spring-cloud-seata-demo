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

package com.senjyouhara.seata.demo;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author xiaojing
 */
@RestController
public class StorageController {

	@Autowired
	private StorageMapper storageMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageController.class);

	private static final String SUCCESS = "SUCCESS";

	private static final String FAIL = "FAIL";

	@GetMapping(value = "/storage/{commodityCode}/{count}", produces = "application/json")
	public String echo(@PathVariable String commodityCode, @PathVariable Integer count) {
		LOGGER.info("Storage Service Begin ... xid: " + RootContext.getXID());
		Integer result = storageMapper.updateEntity(count, commodityCode);
		LOGGER.info("Storage Service End ... ");

		 Random random = new Random();
		if (random.nextBoolean()) {
			throw new RuntimeException("this is a mock Exception");
		}

		if (result == 1) {
			return SUCCESS;
		}
		return FAIL;
	}

	@PostConstruct
	public void init(){
		storageMapper.deleteEntity("C00321");
		storageMapper.insertEntity(1000,"C00321");
	}

}
