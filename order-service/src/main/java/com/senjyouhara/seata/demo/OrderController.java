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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author xiaojing
 */
@Log4j2
@RestController
public class OrderController {

	private static final String SUCCESS = "SUCCESS";

	private static final String FAIL = "FAIL";

	private static final String USER_ID = "U100001";

	private static final String COMMODITY_CODE = "C00321";

	private Random random = new Random();

	@Autowired
	private OderApplication.AccountService accountService;

	@Autowired
	private OrderMapper orderMapper;


	@PostMapping(path = "/order/{userId}/{commodityCode}/{orderCount}")
	public String order(@PathVariable("userId") String userId,
	             @PathVariable("commodityCode") String commodityCode,
	             @PathVariable("orderCount") Integer orderCount){
		log.info("Order Service Begin ... xid: " + RootContext.getXID());

		int orderMoney = calculate(commodityCode, orderCount);

		invokerAccountService(orderMoney);

		final Order order = new Order();
		order.userId = userId;
		order.commodityCode = commodityCode;
		order.count = orderCount;
		order.money = orderMoney;


		Integer result = orderMapper.insertEntity(order.userId, order.count, order.money, order.commodityCode);

		if (random.nextBoolean()) {
			throw new RuntimeException("this is a mock Exception");
		}

		log.info("Order Service End ... Created ==========" + result);

		if (result == 1) {
			return SUCCESS;
		}
		return FAIL;
	}

	private int calculate(String commodityId, int orderCount) {
		return 2 * orderCount;
	}

	private void invokerAccountService(int orderMoney) {
		String account = accountService.account(USER_ID, orderMoney);
		log.info("account : {}", account);

	}

	@PostConstruct
	public void init(){
		orderMapper.deleteEntity();
	}

	}
