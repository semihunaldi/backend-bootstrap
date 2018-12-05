package com.semihunaldi.backendbootstrap.uithymeleaf.controller;

import com.google.common.collect.Lists;
import com.semihunaldi.backendbootstrap.uithymeleaf.model.ChartData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by semihunaldi on 5.12.2018
 */

@Controller
@Scope(value = "request")
public class ChartController extends BaseController {

	@RequestMapping(value = "/chart")
	public String home(Model model, HttpServletRequest request) {
		List<ChartData> chartData = Lists.newArrayList();
		prepareDummyData(chartData);
		model.addAttribute("chartData", chartData);
		return "pages/chart";
	}

	private void prepareDummyData(List<ChartData> chartDataList) {
		Date date = new Date();
		for(int i = 1 ; i < 50 ; i++) {
			Date newDate = addDayToDate(date,i*2);
			chartDataList.add(new ChartData(newDate,new Random().nextInt(100)));
		}
	}

	private Date addDayToDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,day);
		return calendar.getTime();
	}
}
