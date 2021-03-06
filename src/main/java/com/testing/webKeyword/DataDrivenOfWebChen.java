package com.testing.webKeyword;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.testing.common.AutoLogger;
import com.testing.common.ExcelWriter;
import com.testing.driverself.GoogleDriver;
import com.testing.driverself.IEDriver;

public class DataDrivenOfWebChen {


	// 成员变量webdriver，让每一个方法需要使用时都用同一个driver对象来进行操作，都在同一个浏览器中进行操作。
	// 避免外部程序对driver进行修改，可以声明为私有对象。
	public  WebDriver driver = null;
	//成员变量ExcelWriter对象，完成每个方法对于结果文件的写入操作。
	private ExcelWriter outExcel;
	//指定写入的行
	private int line;
	//在DataDrivenOfWeb实例化时完成ExcelWriter对象的实例化操作。
	//那么最终的ExcelWriter的保存方法，没有办法在外部进行调用，必须通过DataDrivenOfWeb添加关键字来调用。
	public DataDrivenOfWebChen(String caseFile,String resultFile) {
		
		outExcel=new ExcelWriter(caseFile,resultFile);
	}
	//在外部先完成ExcelWriter的实例化，直接将对象传递进来
	public DataDrivenOfWebChen(ExcelWriter outFile) {
		outExcel=outFile;
		
	}
	
	/**
	 * 不同的浏览器类型都需要完成driver的实例化操作，因此在封装浏览器启动方法时，可以用一个变量来进行浏览器driver对象实例化的类型选择
	 * 每次执行自动化的时候，必然首先要完成openBrowser方法的调用。
	 * 
	 * @param type 浏览器的类型
	 */
	public void openBrowser(String type) {
		switch (type) {
		case "ie":
			IEDriver ie = new IEDriver("webDrivers/IEDriverServer.exe");
			// 注意对成员变量进行实例化操作，之后的方法才可以复用这个实例化好的浏览器
			// 不要再声明一个webdriver局部变量。
			driver = ie.getdriver();
			//隐式等待，设定最长的等待时间为10秒，在10秒内如果进行driver.findelement操作，则会等待元素能够被定位，如果10秒内能被定位了，继续下一步操作
			//如果超过10秒，超时报错。
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		case "chrome":
			try {
				GoogleDriver gg = new GoogleDriver("webDrivers/chromedriver.exe");
				driver = gg.getdriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				outExcel.writeCell(line, 10, "Pass");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				outExcel.writeCell(line, 10, "Fail");
			}
			break;
		// 使用自己封装的driver类和使用selenium原始的实例化方法，等价，只是调用时用到的一些配置的加载。
		case "firefox":
			try {
				System.setProperty("webdriver.gecko.driver", "webDrivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				outExcel.writeCell(line, 10, "Pass");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				AutoLogger.log.error(e,e.fillInStackTrace());
				AutoLogger.log.error("创建火狐driver失败！！");
				outExcel.writeCell(line, 10, "Fail");
			}
			break;
		// 默认输入时用chrome进行启动
		default:
			GoogleDriver ggdefault = new GoogleDriver("webDrivers/chromedriver.exe");
			driver = ggdefault.getdriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}

	}

	/**
	 * 通过调用成员变量，完成get方法的调用，达到访问某个网站的目的
	 * 
	 * @param url 访问网站的url地址
	 */
	public void visitWeb(String url) {
		try {
			driver.get(url);
			AutoLogger.log.info("访问的网站url是"+url);
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}

	/**
	 * 基于name属性定位元素并且输入内容,然后提交。
	 * 
	 * @param NameAttr     待定位元素的name属性
	 * @param inputContent 需要输入的内容
	 */
	public void inputAndSubmitByName(String NameAttr, String inputContent) {
		try {
			WebElement element = driver.findElement(By.name(NameAttr));
			element.sendKeys(inputContent);
			element.submit();
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}

	public void click(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
			AutoLogger.log.info("点击元素"+xpath);
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	public void input(String xpath,String content) {
		try {
			WebElement ele=driver.findElement(By.xpath(xpath));
			//通过clear方法完成输入框中的清理
			ele.clear();
			ele.sendKeys(content);
			AutoLogger.log.info("向"+xpath+"元素输入"+content);
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("对"+xpath+"输入操作失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
			saveScrShot("输入内容");
		}
	}
	
	/**
	 * 通过findelements方法定位符合条件的所有元素，获取其文本内容。
	 * @param xpath
	 */
	public void getAllgoodsType(String xpath) {
		try {
			AutoLogger.log.info("获取所有"+xpath+"元素的文本信息");
			List<WebElement> goodslist= driver.findElements(By.xpath(xpath));
			//遍历每一个元素
			for(WebElement e:goodslist) {
				System.out.println(e.getText());
			}
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
		
	}
	
	
	public String getTitle() {
		try {
			String title = driver.getTitle();
			AutoLogger.log.info("获取到页面标题是"+title);
			outExcel.writeCell(line, 10, "Pass");
			return title;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("获取标题失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
			return "获取标题失败";
		}
	}

	/**
	 * 显式等待，指定一个最长的等待时间，在这个时间内反复地确认预期的事件是否发生了，如果发生了，则结束等待，继续执行，如果超时还未发生，则报错。
	 * 这个方法的实际用途是等待标题编程以cheese开头。
	 * 
	 */
	public void explicitlyWaitTitle() {
		// 设定等待的事件，多少秒会超时。这里是10秒
		try {
			WebDriverWait ewait = new WebDriverWait(driver, 10);
			// 设定等待的事件是什么
			// 匿名内部类的声明
			// expectedcondition就是期望的等待事件，<Boolean>中的Boolean表示预期事件的类型。
			ewait.until(
					// 完成等待事件的定义
					new ExpectedCondition<Boolean>() {
						// apply方法真正指定等待的条件。在等待过程中，会以一定的周期反复地观测事件是否达成。
						// 如果返回为true，则等待成功，结束等待，如果为false，一直等满10秒之后，报错。
						//这里的webdriver参数实际上由webdriverwait对象把webdriverwait实例化时用到的driver参数，作为实参传给形参roy。
						public Boolean apply(WebDriver roy) {
							// 返回一个Boolean类型的数据
							// Boolean这个类型，在expectedcondition<>的尖括号里面定义的类型，同时也是apply方法要返回的类型。
							return roy.getTitle().toLowerCase().startsWith("cheese!");
						}
					});
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("显式等待标题变化失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 等待某个元素定位表达式能够在页面中定位到一个元素
	 * @param xpathExp 等待的元素的定位表达式。
	 */
	public void expllicitlyWaitEle(String xpathExp) {
		try {
			WebDriverWait ewait=new WebDriverWait(driver, 10);
			ewait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpathExp));
				}
			});
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("显式等待元素可被定位失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 显式等待还可以使用selenium已经预定义好的一些等待条件。
	 * @param xpathExp
	 */
	public void explicitlyWaitEleLoc(String xpathExp) {
		try {
			WebDriverWait ewait =new WebDriverWait(driver, 10);
			//等待条件是指定的元素定位方法能够定位到的元素，出现了
			ewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("显式等待元素出现失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	
	/**
	 * 线程休眠，最死板的等待，没有结束等待的条件，固定等待指定的时长。通常用于一些不确定原因的等待。
	 */
	public void halt(String waitTime) {
		try {
			int t=Integer.parseInt(waitTime);
			//线程休眠，让程序停止一段时间，这个时间是固定的，没有任何条件来解除等待。
			//乘以1000转换为等待秒数
			Thread.sleep(t*1000);
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("强制等待异常");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		} 
	}
	/**
	 * 关闭浏览器以及driver进程。
	 */
	public void closeBrowser() {
		try {
			driver.quit();
			AutoLogger.log.info("关闭浏览器");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("关闭浏览器异常");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 封装一个基于输入的用户名密码完成商城登录的方法
	 * @param username
	 * @param password
	 */
	public void shopLogin(String username,String password) {
		click("//a[text()='登录']");
		input("//input[@id='username']", username);
		input("//input[@id='password']", password);
		input("//input[@id='verify_code']", "1");
		click("//a[@name='sbtbutton']");
	}
	
	
	/**
	 * 将鼠标移动到某个元素上悬停。
	 * @param xpath
	 */
	public void hover(String xpath) {
		try {
			Actions act=new Actions(driver);
			//记得在act对象中的方法编辑完之后，加上.perform()方法调用，让动作执行。
			act.moveToElement(driver.findElement(By.xpath(xpath))).perform();
			AutoLogger.log.info("在"+xpath+"元素上悬停");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("悬停操作失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	
	/**
	 * 通过浏览器的标题切换浏览器窗口的方法
	 * @param exTitle 预期的浏览器窗口标题
	 */
	public void switchWindowByTitle(String exTitle) {
		try {
			Set<String> handles= driver.getWindowHandles();
			System.out.println(handles);
			String targetHandle="";
			//遍历所有的句柄，判断这个句柄对应的浏览器窗口标题是否是预期值
			for(String s:handles) {
				//切换到各个窗口句柄，获取其标题，判断是否等于预期值
				if(driver.switchTo().window(s).getTitle().equals(exTitle)) {
					//如果是，则说明找到了需要切换窗口的句柄
					targetHandle=s;
					break;
				}
			}
			AutoLogger.log.info("切换浏览器到"+exTitle+"窗口");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			outExcel.writeCell(line, 10, "Fail");
		}
		//切换到找到的目标句柄中
//		driver.switchTo().window(targetHandle);
	}
	
	
	public void switchIframe(String framename) {
		try {
			driver.switchTo().frame(framename);
			AutoLogger.log.info("切换"+framename+"iframe成功");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("切换到"+framename+"iframe失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	public void switchIframeAsele(String xpath) {
		try {
			WebElement frameElement =driver.findElement(By.xpath(xpath));
			driver.switchTo().frame(frameElement);
			AutoLogger.log.info("切换"+xpath+"iframe成功");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("切换到"+xpath+"iframe失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	/**
	 * 切入iframe之后，调用该方法，切回最外层的html
	 */
	public void switchToRoot() {
		try {
			driver.switchTo().defaultContent();
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("切换到网页最外层失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 通过js滚动到最底端。
	 */
	public void scrollToEnd() {
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			AutoLogger.log.info("执行js滚动条到底成功");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("js语句执行失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	/**
	 * 通过js脚本拼接，实现滚动到指定的y坐标位置。
	 * @param yAxis
	 */
	public void scrollVertical(String yAxis) {
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,"+yAxis+")");
			AutoLogger.log.info("执行js语句滚动到"+yAxis+"位置");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("js语句执行失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 带参的js脚本调用，适用于通过xpath定位到元素然后调用js方法操作。
	 * @param cmd  js脚本，使用arguments[0].click()这种方式进行参数的调用
	 * @param xpath 传递的参数，通过xpath定位元素。
	 */
	public void runJsWithArg(String cmd,String xpath) {
		try {
			//首先通过xpath定位到一个元素
			WebElement ele=driver.findElement(By.xpath(xpath));
			//然后再js执行器执行时，把元素作为参数穿进去，然后在js脚本里通过argument[0]来进行调用。
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript(cmd, ele);
			AutoLogger.log.info("对"+xpath+"元素执行js语句操作");
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("js语句执行失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	public String getJsReturnWithArg(String cmd,String xpath) {
		try {
			WebElement ele=driver.findElement(By.xpath(xpath));
			//然后再js执行器执行时，把元素作为参数穿进去，然后在js脚本里通过argument[0]来进行调用。
			JavascriptExecutor js=(JavascriptExecutor)driver;
			//在执行js脚本时，加上return，将结果进行返回
			String result=js.executeScript("return "+cmd, ele).toString();
			AutoLogger.log.info("对"+xpath+"元素执行js语句操作，返回结果是"+result);
			outExcel.writeCell(line, 10, "Pass");
			return result;
		} catch (Exception e) {
			AutoLogger.log.error("js语句执行失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
			return "js语句执行失败";
		}
	}
	
	/**
	 * 基于select元素中的option元素的文本内容进行选择。
	 * @param xpath
	 * @param visibleText
	 */
	public void selectByText(String xpath,String visibleText) {
		//通过select类的构造方法，完成将元素转换成select对象的过程
		try {
			Select sel=new Select(driver.findElement(By.xpath(xpath))) ;
			sel.selectByVisibleText(visibleText);
			AutoLogger.log.info("操作select元素选择"+visibleText);
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			AutoLogger.log.error("select元素操作失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	/**
	 * 通过select元素中的option选项的value属性值进行选择
	 * @param xpath
	 * @param optionValue
	 */
	public void selectByValue(String xpath,String optionValue) {
		//通过select类的构造方法，完成将元素转换成select对象的过程
		try {
			Select sel=new Select(driver.findElement(By.xpath(xpath))) ;
			sel.selectByValue(optionValue);
			AutoLogger.log.info("操作select元素选择value值"+optionValue);
			outExcel.writeCell(line, 10, "Pass");
		} catch (Exception e) {
			AutoLogger.log.error("select元素操作失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	public void uploadFile(String xpath,String FilePath ) {
		input(xpath, FilePath);
	}
	
	/**
	 * 断言，基于获取到的元素的文本内容，判断用例执行是否成功
	 */
	public void assertEleText(String xpath,String exText) {
		String eleText=driver.findElement(By.xpath(xpath)).getText();
		AutoLogger.log.info("断言元素的文本内容是："+eleText);
		if(eleText.equals(exText)) {
			AutoLogger.log.info("断言文本内容是"+exText+"测试成功");
			outExcel.writeCell(line, 10, "Pass");
		}
		else {
			AutoLogger.log.info("断言文本内容是"+exText+"测试失败");
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 断言页面中是否包含指定的内容。
	 */
	public void assertPageContains(String exText) {
		String pageSource=driver.getPageSource();
		System.out.println(pageSource);
		if(pageSource.contains(exText)) {
			AutoLogger.log.info("测试成功");
			outExcel.writeCell(line, 10, "Pass");
		}
		else{
			AutoLogger.log.info("测试失败");
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 断言某个元素的某个属性的值符合预期
	 * @param xpath
	 * @param exAttr
	 */
	public void assertEleAttr(String xpath,String exAttrkey,String exAttrValue) {
		WebElement ele=driver.findElement(By.xpath(xpath));
		//如果元素获取exAttrkey的值是Value则断言成功
		if(ele.getAttribute(exAttrkey).equals(exAttrValue)) {
			AutoLogger.log.info("测试成功");
			outExcel.writeCell(line, 10, "Pass");
		}
		else{
			AutoLogger.log.info("测试失败");
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	
	/**
	 * 断言，基于获取到的元素的文本内容，判断用例执行是否成功
	 */
 	public void assertEleContainsText(String xpath,String exText) {
		String eleText=driver.findElement(By.xpath(xpath)).getText();
		AutoLogger.log.info("断言元素的文本内容是："+eleText);
		if(eleText.contains(exText)) {
			AutoLogger.log.info("断言文本内容包含"+exText+"测试成功");
			outExcel.writeCell(line, 10, "Pass");
		}
		else {
			AutoLogger.log.info("断言文本内容包含"+exText+"测试失败");
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	/**
	 * 报错时的截图实现，保存的截图文件名格式为方法+当前时间.png
	 * @param method 报错的方法名
	 */
	public void saveScrShot(String method) {
		// 获取当前的执行时间
		Date date = new Date();
		//将时间转换为指定的格式,+号-号这些分隔符可以自己随便替换成自己喜欢的   2020/2/10 22:07  20200210+22-07-33
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		// 将当前时间转换为上一步指定格式的字符串
		String createdate = sdf.format(date);
		// 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
		String scrName = "log/ScrShot/" + method + createdate + ".png";
		
		// 以当前文件名创建一个空的png文件
		File scrShot = new File(scrName);
		// 将截图保存到内存中的临时文件
		File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//将内存中保存的截图复制到创建出来的png文件中。
		try {
			Files.copy(tmp, scrShot);
			AutoLogger.log.info("错误截图保存在"+scrName);
			outExcel.writeCell(line, 10, "Pass");
		} catch (IOException e) {
			e.printStackTrace();
			AutoLogger.log.error("截图失败");
			outExcel.writeCell(line, 10, "Fail");
		}
	}
	
	//保存excel结果文件
	public void saveExcel() {
		outExcel.save();
	}
	
}
