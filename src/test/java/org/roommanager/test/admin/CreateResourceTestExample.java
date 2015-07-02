package org.roommanager.test.admin;
import org.apache.log4j.Logger;
import org.roommanager.framework.pages.admin.HomeAdminPageExample;
import org.roommanager.framework.pages.admin.LoginPageExample;
import org.roommanager.framework.pages.admin.ResourceCreatePageExample;
import org.roommanager.framework.pages.admin.ResourcePageExample;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateResourceTestExample extends TestBase{
	/*Test Case:
	 * Verify that is possible create a resource*/
  Logger logger=Logger.getLogger("test02CreateResource");

  @Test  (groups = {"ACCEPTANCE"})
	public void CreateResource() throws Exception {
		String nameResource = "Resource1";
		String message = "The resource cannot be created";
		driver.get("http://172.20.208.174:4044/admin/#/login");
		HomeAdminPageExample home = new HomeAdminPageExample(driver);
		LoginPageExample login = new LoginPageExample(driver);
		login.btn_signIn();
		home.lnk_Resources();
		ResourcePageExample resourcePage = new ResourcePageExample(driver);
		resourcePage.btn_Add();
		ResourceCreatePageExample resourceAddPage = new ResourceCreatePageExample(driver);
		resourceAddPage.txt_name().txt_displayName().btn_Save();
		String resources = resourcePage.getResourceName(nameResource);
		Assert.assertEquals(resources, nameResource, message);
  }
  
  /*Ya no se debe incluir BeforeSuite ni AfterSuite*/

}