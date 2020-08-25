package chen.test.adminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import chen.test.webkeyword.WebKeyWord;

public class AdminLoginPage {
	WebKeyWord web=null;
	
	public String url="http://testingedu.com.cn:8000/index.php/Admin/Admin/login";
	
	//通过@FindBy注释给参数赋值，xpath定位表达式
	@FindBy(xpath = "//input[@name='username']")
	public WebElement username;
	@FindBy(xpath = "//input[@name='password']")
	public WebElement password;
	@FindBy(xpath = "//input[@name='vertify']")
	public WebElement verifyCode;
	@FindBy(xpath = "//input[@name='submit']")
	public WebElement loginBut;
	
	//构造函数，把driver实例传递进来
	
	public AdminLoginPage(WebKeyWord web) {
		// TODO Auto-generated constructor stub
		this.web=web;
	}
	//访问指定的url地址
	//通过PageFactory进行页面初始化，将@FindBy注解的成员变量生效
	public void load() {
		web.visitWeb(url);
		PageFactory.initElements(web.driver, this);
	}
	//实现登陆
	public void login() {
		username.sendKeys("admin");
		password.sendKeys("123456");
		verifyCode.sendKeys("1");
		loginBut.click();
		//等待3秒钟
		web.halt("3");
		//关闭浏览器以及浏览器驱动
		web.closeBrowser();
		
	}
}
