package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")

	public void getProductInfoTest(String brandName,String productName) {
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");

		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);

		//capture product info
		String x="(//span[text()='"+productName+"'])[1]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText(); //we must use findElement only,don't use @FindBy-Since,we must Dynamic Xpath here.
		System.out.println(price);
		driver.quit();
	}

	@DataProvider
	public Object [][] getData() throws Throwable
	{
		ExcelUtility elib=new ExcelUtility();
		int rowCount = elib.getRowcount("product");

		Object[][] objArr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) //here i=0,because object array always start from '0' only & due to which we are mentioning (i+1)inside the loop.
			                        //(or) we can delete the header line present in the Excel sheet.
			{
			objArr[i][0]=elib.getDataFromExcel("product", i+1, 0);
			objArr[i][1]=elib.getDataFromExcel("product", i+1, 1);
			}
	

		return objArr;
	}
}

