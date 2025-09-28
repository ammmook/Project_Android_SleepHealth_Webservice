package com.springmvc.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.bean.Sleep;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SleepController {

	@RequestMapping(value = "/user/addWithSleep", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseObj do_registerWithSleep(@RequestBody Map<String, Object> map) {
	    String message = "";
	    try {
	        // รับข้อมูลจาก client (แอป Android)
	        String username = map.get("username").toString();

	        // สร้างข้อมูลการนอน
	        List<Sleep> sleeps = new ArrayList<>();
	        List<Map<String, Object>> sleepData = (List<Map<String, Object>>) map.get("sleeps");

	        // วนลูปสร้าง Sleep สำหรับแต่ละรายการใน sleepData
	        for (Map<String, Object> sleep : sleepData) {
	            Sleep sleepObj = new Sleep();
	            
	            sleepObj.setUsername(username);  // เก็บ username ใน sleepObj
	            sleepObj.setDate(sleep.get("sleep_date").toString());
	            sleepObj.setBedTime(sleep.get("sleep_bed").toString());
	            sleepObj.setWakeUpTime(sleep.get("sleep_wakeup").toString());
	            sleepObj.setDuration(((Double) sleep.get("sleep_duration")).intValue()); 
	            sleepObj.setQuality(((Double) sleep.get("sleep_quality")).intValue());   
	            sleepObj.setDebt(((Double) sleep.get("sleep_debt")).intValue());
	            
	            sleeps.add(sleepObj);  // เพิ่มข้อมูลการนอนใน list
	        }

	        // บันทึกข้อมูลการนอนทั้งหมด
	        SleepManager sm = new SleepManager();
	        message = sm.insertSleep(sleeps);  // บันทึกหลายข้อมูลในการนอน
	        return new ResponseObj(200, sleeps);  // ส่งกลับข้อมูลการนอนที่บันทึก
	    } catch (Exception e) {
	        e.printStackTrace();
	        message = "Please try again....";
	        return new ResponseObj(500, message);
	    }
	}

	@RequestMapping(value = "/user/getSleepData", method = RequestMethod.GET)
	public @ResponseBody ResponseObj getSleepData(HttpServletRequest request, 
	                                             @RequestParam("username") String username) {
	    List<Sleep> sleeps = null;
	    try {
	        SleepManager sm = new SleepManager();
	        sleeps = sm.getSleepDataByUsername(username);

	        if (sleeps != null && !sleeps.isEmpty()) {
	            return new ResponseObj(200, sleeps);
	        } else {
	            return new ResponseObj(404, "No sleep data found for the user.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseObj(500, "Error while fetching sleep data.");
	    }
	}
	
	@RequestMapping(value = "/user/getSleepDataByDate", method = RequestMethod.GET)
	public @ResponseBody ResponseObj getSleepDataByDate(HttpServletRequest request, 
	                                                     @RequestParam("date") String date) {
	    List<Sleep> sleeps = null;
	    try {
	        SleepManager sm = new SleepManager();
	        sleeps = sm.getSleepDataByDate(date);

	        if (sleeps != null && !sleeps.isEmpty()) {
	            return new ResponseObj(200, sleeps);
	        } else {
	            return new ResponseObj(404, "No sleep data found for the date.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseObj(500, "Error while fetching sleep data.");
	    }
	}
	
	@RequestMapping(value = "/user/deleteSleepData", method = RequestMethod.GET)
	public @ResponseBody ResponseObj deleteSleepData(HttpServletRequest request, 
	                                                 @RequestParam("username") String username, 
	                                                 @RequestParam("date") String date) {
	    String message = "";
	    try {
	        // ลบข้อมูลการนอนตามวันที่และชื่อผู้ใช้
	        SleepManager sm = new SleepManager();
	        boolean isDeleted = sm.deleteSleepbyUsernameandDate(username, date);  // ส่งชื่อผู้ใช้และวันที่ไปลบข้อมูล

	        if (isDeleted) {
	            return new ResponseObj(200, "Data deleted successfully.");
	        } else {
	            return new ResponseObj(404, "No sleep data found for the specified date and user.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        message = "Error occurred while deleting data.";
	        return new ResponseObj(500, message);
	    }
	}


}
