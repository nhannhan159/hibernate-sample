package com.oddle.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Index controller
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@RequestMapping(value = { "/weather" }, method = RequestMethod.GET)
	public String index() {
		logger.info("Start weather app!");
		return "weather";
	}

}
