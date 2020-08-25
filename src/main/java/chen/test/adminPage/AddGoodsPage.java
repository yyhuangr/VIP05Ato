package chen.test.adminPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import chen.test.webkeyword.WebKeyWord;

public class AddGoodsPage {
	public WebKeyWord web=null;
	
	@FindBy(xpath = "//a[text()='商城']")
	public WebElement shop;
	
	@FindBy(xpath = "//iframe[@id='workspace']")
	public WebElement workSpaceIframe;
	
	@FindBy(xpath = "//div[@title='添加商品']")
	public WebElement add;
	
	@FindBy(xpath = "//input[@name='goods_name']")
	public WebElement GoodsName;
	
	@FindBy(xpath = "//*[@id='cat_id']")
	public WebElement goodsCat1;
	
	@FindBy(xpath = "//*[@id='cat_id_2']")
	public WebElement goodsCat2;
	
	@FindBy(xpath = "//*[@id='cat_id_3']")
	public WebElement goodsCat3;
	
	@FindBy(xpath = "//input[@name='shop_price']")
	public WebElement shopPrice;
	
	@FindBy(xpath = "//input[@name='market_price']")
	public WebElement marketPrice;
	
	@FindBy(xpath = "//*[@id='is_free_shipping_label_1']")
	public WebElement freeShipping;
	
	@FindBy(xpath = "//a[@id='submit']")
	public WebElement submitBtn;
	
	public AddGoodsPage(WebKeyWord web) {
		// TODO Auto-generated constructor stub
		this.web=web;
	}
	public void load() {
		
//		web.visitWeb("http://testingedu.com.cn:8000C/index.php/Admin/Index/index");
		//http://112.74.191.10:8000/index.php/Admin/Index/index
		//加载页面元素
		PageFactory.initElements(web.driver, this);
	}
	//添加商品
	public void addGoods() {
//		web.halt("5");
		//点击商城
		shop.click();
		web.driver.switchTo().frame(workSpaceIframe);
		//添加商品
		add.click();
		GoodsName.sendKeys("测试商品");
		Select cat1=new Select(goodsCat1);
		cat1.selectByValue("52");
		Select cat2=new Select(goodsCat2);
		cat2.deselectByValue("53");
		Select cat3=new Select(goodsCat3);
		cat3.selectByValue("359");
		//本店售价
		shopPrice.sendKeys("5000");
		//市场价
		marketPrice.sendKeys("5550");
		//是否包邮
		freeShipping.click();
		submitBtn.click();
	}
	
	
}
