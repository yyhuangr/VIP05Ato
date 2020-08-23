package com.testing.class7;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import com.testing.app.AppDriver;
import com.testing.app.KeywordOfApp;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class QQTestWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//启动服务
		KeywordOfApp app=new KeywordOfApp();
		//设置服务启动的端口，以及等待多久让服务完成启动。
		app.StartAppium("12345", "5");
		//通过脚本启动客户端driver从而打开被测应用。
//		app.runAPP("5.1.1", "127.0.0.1:62001", "com.tencent.mobileqq", ".activity.SplashActivity", "http://127.0.0.1:12345/wd/hub", "5");
//		tapByRelativeCoordinate(app);
		app.killAppium();
		app.runCmd("taskkill /F /IM cmd.exe");
	}

	public static void tapByRelativeCoordinate(KeywordOfApp app) {
		WebElement topTab= app.driver.findElement(By.id("com.tencent.mobileqq:id/ws"));
		//获取元素的起始点
		Point topStartP= topTab.getLocation();
		//获取元素的区域范围，整个元素的宽和高
		Dimension topD=topTab.getSize();
		System.out.println("起始点："+topStartP+"范围："+topD);
		//计算需要点击的位置处于这个元素的什么地方
		int startx=topStartP.getX();
		int starty=topStartP.getY();
		int rangex=topD.getWidth();
		int rangey=topD.getHeight();
		//x轴的十分之一的位置，和y轴的2分之一
		int targetX=startx+rangex/10;
		int targetY=starty+rangey/2;
		//使用appium的touchAction类来完成坐标点击
		TouchAction act=new TouchAction(app.driver);
		//完成对于坐标位置的点击，注意传递的参数是PointOption中的point静态方法完成构造的点位。
		act.tap(PointOption.point(targetX, targetY)).perform();
	}

}
