package chen.test.driverself;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
//该类是 专门 用于生成 火狐浏览器的driver实例的
//Firefox浏览器驱动类
public class FirefDriver {
	/*
	 * 该类是创建火狐浏览器的驱动实例。
	 * 给chen.test.webkeyword.java类中的openBrowser（）调用的。
	 */
	WebDriver driver=null;
	
	public FirefDriver(String propath,String driverPath) {
		// TODO Auto-generated constructor stub
		System.setProperty("webdriver.gecko.driver", driverPath);
		//用于旧版本的火狐浏览器，需设置浏览器的安装根目录，新版本的不能自定义安装根目录，所以新版本的火狐浏览器就用不上该配置了。
		if(propath!=null &&propath.length()>0) {
			System.setProperty("webdriver.firefox.bin", propath);
		}
		
		//用户文件配置(以登陆的方式访问)
//		File pro=new File("C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\gq8xwyjm.default");
//		FirefoxProfile profile=new FirefoxProfile(pro);
		FirefoxOptions opt=new FirefoxOptions();
//		opt.setProfile(profile);
		
		
		 try {
			driver=new FirefoxDriver(opt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("log--error：创建driver实例失败------");
		}
	}
	public WebDriver getDriver() {
		return this.driver;
	}
}
