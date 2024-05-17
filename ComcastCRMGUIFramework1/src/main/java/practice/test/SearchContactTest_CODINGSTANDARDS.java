package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for Contact module
 * @author gagan
 */
public class SearchContactTest_CODINGSTANDARDS extends BaseClass {
/**
 * Scenario:login()==>navigateContact==>createcontact()==>verify
 */
	@Test
	public void searchContactTest()
	{
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("url", "username", "password");
	}
	
}
