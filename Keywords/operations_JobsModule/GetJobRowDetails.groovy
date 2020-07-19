package operations_JobsModule

import java.io.ObjectInputStream.FilterValues

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus


public class GetJobRowDetails {

	@Keyword
	def newGrid(WebDriver katalonWebDriver , String dataAttribute , String FilterValue, extentTest  ) {
		def fail=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		String x1="//div[@col-id='"
		String x2 ="']"
		String myXpath=x1+dataAttribute+x2

		println(myXpath)

		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println listElement.size()

		for(int i =1;i<listElement.size();i++) {
			RemoteWebElement ele = listElement.get(i)
			String myText=ele.getText()
			println(myText)
			if(myText.equals(FilterValue)) {
				println (ele.getText())
				extentTest.log(LogStatus.INFO, 'Filter value matched for row number - '+ i + ' in jobs table')
			}
			else {
				extentTest.log(LogStatus.INFO, myText)
				fail=true
				// no jobs  msg
			}
		}
		return fail
	}

	/*
	 @Keyword
	 def grid(WebDriver katalonWebDriver , String dataAttribute , String FliterValue, extentTest  ) {
	 def fail=false
	 def LogStatus = com.relevantcodes.extentreports.LogStatus
	 List<WebElement> listElement = katalonWebDriver.findElements(By.xpath("//div[contains(@id, '_row')]"))
	 //List listElement = driver.findElements(By.xpath("//div[contains(@id, '_row')]"))
	 //List listElement = ((RemoteWebDriver) (((EventFiringWebDriver) driver).findElements(By.xpath("//div[contains(@id, '_row')]"))))
	 println listElement.size()
	 extentTest.log(LogStatus.INFO, 'Number of filterd jobs '+ listElement.size() )
	 for(int i =0;i<listElement.size();i++) {
	 RemoteWebElement ele = listElement.get(i)
	 String eleID=ele.getAttribute("id")
	 println eleID
	 TestObject testObject = new TestObject();
	 testObject.addProperty("id", ConditionType.EQUALS, eleID)
	 WebUI.delay(2)
	 WebUI.click(testObject)
	 WebUI.delay(2)
	 String data=WebUI.getAttribute(testObject, "data-node")
	 String dataAttributeString='"'+dataAttribute+'":"'
	 String[] splitAddress = data.split(dataAttributeString)
	 String  jobState = splitAddress[1]
	 String[] s1 = jobState.split('",')
	 String CheckString=s1[0]
	 if(CheckString.equalsIgnoreCase(FliterValue)) {
	 extentTest.log(LogStatus.INFO, 'Filter value matched for '+ i + ' row in jobs table')
	 }
	 else {
	 fail=true
	 }
	 }
	 return fail
	 }
	 */

	@Keyword
	def getJobState(TestObject jobRow, extentTest  ) {

		def fail=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus


		String data=WebUI.getAttribute(jobRow, "data-node")
		String dataAttributeString='"jobState":"'
		String[] splitAddress = data.split(dataAttributeString)
		String  jobState = splitAddress[1]
		String[] s1 = jobState.split('",')
		String CheckString=s1[0]
		println("----------------------------------------------")
		println CheckString
		println("----------------------------------------------")
		return CheckString
	}
}