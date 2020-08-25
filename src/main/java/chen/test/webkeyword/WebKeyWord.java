package chen.test.webkeyword;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import chen.test.common.AutoLogger;
import chen.test.driverself.FirefDriver;
import chen.test.driverself.GoogleDriver;
import chen.test.driverself.IeDriver;

public class WebKeyWord {
	
	public WebDriver driver=null;
	//该方法是根据 浏览器的类型（ie、chrome、firefox），打开不同的浏览器
	public void openBrowser(String type) {
		switch(type) {
		case "ie":
			//创建chen.test.driverself.IeDriver.java类的实例
			IeDriver ie=new IeDriver("webDrivers/IEDriverServer.exe");
			//通过IeDriver.java类获取driver实例
			 driver=ie.getDriver();
			 //设置隐式等待，10秒钟
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 break;
		case "chrome":
			//创建chen.test.driverself.GoogleDriver.java类的实例
			GoogleDriver gd=new GoogleDriver("webDrivers/chromedriver.exe");
			driver=gd.getDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		case "firefox":
			//创建chen.test.driverself.FirefDriver.java类的实例
			FirefDriver ff=new FirefDriver("", "webDrivers/geckodriver.exe");
			driver=ff.getDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		default:// 默认输入时用chrome进行启动
			//创建chen.test.driverself.GoogleDriver.java类的实例
			GoogleDriver gdd=new GoogleDriver("webDrivers/chromedriver.exe");
			driver=gdd.getDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}
	}
	
	
	//打开一个url地址
	public void visitWeb(String url) {
		try {
			driver.get(url);
			AutoLogger.log.info("访问的网站url是："+url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("visitWeb");
		}
	}
	
	//基于name属性定位元素，并输入内容，提交
	public void inputAndSubmitByName(String NameAtrr,String InputContent) {
		
		try {
			WebElement el=driver.findElement(By.name(NameAtrr));
			el.sendKeys(InputContent);
			el.submit();
			AutoLogger.log.info("你输入的元素： "+NameAtrr+" 里面的内容是："+InputContent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("你输入的元素： "+NameAtrr+" 里面的内容是："+InputContent+"提交失败！");
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	
	//点击 "登陆" 的超链接
	public void click(String xpath) {
		
		try {
			driver.findElement(By.xpath(xpath)).click();
			AutoLogger.log.info("点击元素"+xpath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("点击"+xpath+"元素操作失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("click");
		}
	}
	//输入框  的方法（例如：获取登陆用户名、密码、验证码的输入框）
	public void  input(String xpath,String content) {
		try {
			WebElement le=driver.findElement(By.xpath(xpath)) ;
			AutoLogger.log.info("向"+xpath+"元素输入"+content);
			//清理输入框
			le.clear();
			le.sendKeys(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("对"+xpath+"输入操作失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
//			saveScrShot("输入内容");
			saveScrShot("input");
		}
	}
	//获取货架上面的所有商品类型
	/*
	 * 通过findelements 方法定位符合条件的所有元素，获取其文本内容。
	 */
	public void getAllgoodsType(String xpath) {
		List<WebElement> listGoods=driver.findElements(By.xpath(xpath)) ;
		for(WebElement e:listGoods) {
			System.out.println(e.getText());
		}
	}
	
	//获取网页标题
	public String getTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "获取标题失败";
		}
	}
	
	//显式等待explicitly,等待网页标题以 小写 cheese开头
	public void explicitlyWaitTitle() {
		try {
			// 设定等待的事件，多少秒会超时。这里是10秒
			WebDriverWait eWait=new WebDriverWait(driver, 10);
			eWait.until(
					new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return d.getTitle().toLowerCase().startsWith("cheese");
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//显示等待，等待元素表达式可以在页面定位到该元素(不常用，一般不与隐式等待同时使用)
	public void explicitlyWaitEle(String xpathExp) {
		try {
			WebDriverWait eWait=new WebDriverWait(driver,10);
			eWait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpathExp));
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//显示等待，等待条件是指定的元素定位方法能够定位到的元素，出现了
	//显式等待，还可以使用selenium已经预定义好的一些等待条件。 
	//该方法比较常用，足够使用
	public void explicitlyWaitEleLoc(String xpathExp) {
		try {
			WebDriverWait ewait =new WebDriverWait(driver, 10);
			ewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExp)));
//			AutoLogger.log.info("显示等待指定的元素"+xpathExp+"出现了。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//强制等待 
	public void halt(String waitTime) {
		int time=Integer.parseInt(waitTime);
		try {
			//线程休眠，让程序停止一段时间，这个时间是固定的，没有任何条件来解除等待。
			//乘以1000转换为等待秒数。
			Thread.sleep(time*1000);
			AutoLogger.log.info("强制等待"+waitTime+"秒钟");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("强制等待"+waitTime+"秒钟,失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	//关闭整个浏览器以及driver的进程
	public void closeBrowser() {
		
		try {
			driver.quit();
			AutoLogger.log.info("关闭浏览器");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("关闭浏览器失败!");
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	
	//封装一个基于输入的用户名、密码完成商城登陆的方法
	public  void shopLogin(String username,String password) {
		click("//a[text()='登录']");
		//输入
		input("//input[@id='username']", username);
		input("//input[@id='password']", password);
		input("//input[@id='verify_code']", "1");//需要开发那边配合一下，使用万能验证码(随便输都可以通过)
		//点击登陆  按钮
		click("//a[@name='sbtbutton']");
	}
	//将鼠标移动到某个元素上悬停
	public void hover(String xPath) {
		try {
			Actions act=new Actions(driver);
			//记得在act方法编辑完之后，加上.perform()方法调用，让动作执行
			act.moveToElement(driver.findElement(By.xpath(xPath))).perform();
			AutoLogger.log.info("鼠标移动到"+xPath+"元素上悬停");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("鼠标移动到"+xPath+"元素上悬停 失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("hover");
		}
	}
	
	/*
	 * 切换浏览器窗口的方法
	 * @param exTitle 预期的浏览器窗口标题
	 */
	public void switchWindowByTitile(String exTitle) {
		Set<String> handles=driver.getWindowHandles();
		System.out.println(handles);
		String targetHandle="";
		//遍历所有的句柄，判断这个句柄对应的浏览器窗口标题是否是预期值
		for(String s:handles) {
			//切换到各个窗口句柄，获取其标题，判断是否等于预期值
			if(driver.switchTo().window(s).getTitle().equals(exTitle)) {
				//如果是预期值，则说明找到了需要切换窗口的句柄
				targetHandle=s;
				break;
			}
		}
		//切换到找到的目标句柄中
		//		driver.switchTo().window(targetHandle);
	}
	
	
	//切换窗口的新版本方法//该方法用不了
	public void switchByName(String exTitile) {
		driver.switchTo().window(exTitile);
	}	
		
	//定位Iframe一般使用id或者是name属性来定位
	public void switchIframe(String FrameName) {
		try {
			driver.switchTo().frame(FrameName);
//			System.out.println("切换Iframe成功！");
			AutoLogger.log.info("切换 "+FrameName+" Iframe成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("切换Iframe失败");
			AutoLogger.log.error("切换 "+FrameName+" Iframe失败！");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("switchIframe");
		}
	}
	//如果Iframe没有id、name属性，需要使用元素通过xpath来定位
	public void switchIframeAsEle(String xPath) {
		try {
			WebElement frameElemen=driver.findElement(By.xpath(xPath));
			driver.switchTo().frame(frameElemen);
//			System.out.println("切换Iframe成功！！");
			AutoLogger.log.info("通过元素"+xPath+"来定位并切换Iframe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("切换Iframe失败！！");
			AutoLogger.log.error("通过元素"+xPath+"来定位并切换Iframe失败！");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("switchIframeAsEle");
		}
	}
	
	//通过js滚动到最低端
	public void scrollToEnd() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	//通过js脚本拼接，实现滚动到指定的y坐标位置
	//@yAxis
	public void scrollVertical(String yAxis) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+yAxis+")");
	}
	
	//带参数的js脚本调用，适用于通过xpath定位到元素然后调用js方法操作
	//@param cmd js脚本，使用arguments[0].click()这种方式进行参数的调用
	//@param xPath 传递的参数，通过xpath定位元素。
	
	public void runJsWithArg(String cmd,String xPath) {
		//首先通过xpath定位到一个元素
		WebElement ele=driver.findElement(By.xpath(xPath));
		//然后再js执行器执行时，把元素作为参数传进去，然后在js脚本里面通过argument[0]来进行调用。
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//
		js.executeScript(cmd, ele);
		
	}
	/*
	 * 
	 */
	public String getJsReturnWithArg(String cmd,String xPath) {
		//首先通过xpath定位到一个元素
				WebElement ele=driver.findElement(By.xpath(xPath));
				//然后再js执行器执行时，把元素作为参数传进去，然后在js脚本里面通过argument[0]来进行调用。
				JavascriptExecutor js=(JavascriptExecutor)driver;
				//在执行js脚本时，加上return,将结果进行返回
				String result=	js.executeScript("return "+cmd, ele).toString();
			  return result;
	}
	/*
	 * 基于select元素中的option选项文本内容进行选择
	 * @param xpath 
	 * @prame visibleText ：被选中的数据
	 */
	public void selectByText(String xpath,String visibleText) {
		try {
			//通过select类的构造方法，完成将元素转换成select对象的过程
			Select sel=new Select(driver.findElement(By.xpath(xpath)));
			sel.selectByVisibleText(visibleText);
			
			AutoLogger.log.info("操作 "+xpath+"元素，选择 "+visibleText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("操作 "+xpath+"元素，选择 "+visibleText+"失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("selectByText");
		}
	}
	/*
	 * 通过select元素中的option选项的value属性进行选择
	 * @param xpath 元素
	 * @prame optionValue  选项的属性值
	 */
	public void selectByValue(String xpath,String optionValue) {
		try {
			//通过select类的构造方法，完成将元素转换成select对象的过程
			Select sel=new Select(driver.findElement(By.xpath(xpath)));
			sel.selectByValue(optionValue);
			AutoLogger.log.info("你选的是select下拉框元素  "+xpath+" 选中的value值是： "+optionValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.error("对select下拉框操作出错！");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("selectByValue");
		}
	}
	
	//文件上传的方法
	public void uploadFile(String xpath,String filePath) {
		input(xpath,filePath);
	}
	/*
	 * 断言：基于获取到的元素的内容，判断用例执行是否成功
	 */
	public void assertEleText(String xPath,String exText) {
		String eleText=driver.findElement(By.xpath(xPath)).getText();
		AutoLogger.log.info("断言文本的内容是："+eleText);
		if(eleText.equals(exText)) {
			AutoLogger.log.info("断言文本的内容是："+exText+"测试成功");
		}else {
			AutoLogger.log.info("断言文本的内容是："+exText+"测试失败");
			//出错的时候调用截图的方法
			saveScrShot("assertEleText");
		}
	}
	/*
	 * 断言页面中是否包含指定的内容
	 */
	public void assertPageContains(String exText) {
		System.out.println("exText="+exText);
			String pageSource=driver.getPageSource();
//			System.out.println("pageSource="+pageSource);
			if(pageSource.contains(exText)) {
				AutoLogger.log.info("测试成功");
			}else {
				try {System.out.println("exText="+exText);
				System.out.println("pageSource="+pageSource);
				AutoLogger.log.info("测试失败");
				saveScrShot("assertPageContains");
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					AutoLogger.log.error(e,e.fillInStackTrace());
				}
			}
		
	}
	/*
	 * 断言：某个元素的某个属性的值符合预期
	 */
	public void asserEleAttr(String xpaty,String exAttrKey,String exAttrValue) {
		WebElement ele=driver.findElement(By.xpath(xpaty));
		if(ele.getAttribute(exAttrKey).equals(exAttrValue)) {
			AutoLogger.log.info("测试成功！！");
		}else {
			AutoLogger.log.info("测试失败");
		}
	}
	
	//断言：基于获取到的元素的文本内容，判断用例执行是否成功
	public void assertEleContainsText(String xPath,String exText) {
		String eleText=driver.findElement(By.xpath(xPath)).getText();
//		System.out.println("断言元素内容是："+eleText);
		AutoLogger.log.info("断言元素的文本内容是："+exText);
		if(eleText.contains(exText)) {
//			System.out.println("测试成功");
			AutoLogger.log.info("测试成功");
		}
		else {
//			System.out.println("测试失败");
			AutoLogger.log.info("测试失败");
			//出错的时候调用截图的方法
			saveScrShot("assertEleContainsText");
		}
	}
	
	/*
	 * 报错时的截图实现，保存的截图文件名格式为方法+当前时间.png
	 * @param method 报错的方法名
	 */
	public void saveScrShot(String method) {
		//获取当前的执行时间
		Date date=new Date();
		//将时间转换为指定的格式,+号，-号这些分割分可以自己替换成自己喜欢的，2020/2/10 22:07  20200210+22-07-33
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		//将当前时间转换给上一步指定格式的字符串
		String createdate=sdf.format(date);
		//拼接文件名，形式为，工作目录路径+方法名+执行时间.png
		String scrName="log/ScrShot/"+method+createdate+".png";
		System.out.println("------saveScrShot--拼接后的scrName="+scrName);
		//以当前文件名创建一个空的png文件
		File scrShot=new File(scrName);
		//将截图保存到内存中的临时文件
		File tmp=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(tmp, scrShot);
			AutoLogger.log.info("错误截图保存在："+scrName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			AutoLogger.log.info("截图失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			
		}
	}
	
	
	//切入iframe之后，调用该方法，切回最外层的html
	public void switchToRoot() {
		try {
			driver.switchTo().defaultContent();
			AutoLogger.log.info("切回最外层的html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.error("切回最外层的html失败");
			AutoLogger.log.error(e,e.fillInStackTrace());
			//出错的时候调用截图的方法
			saveScrShot("switchToRoot");
		}
	}
}
