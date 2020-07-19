package operations_FileModule

import java.awt.Robot
import java.awt.event.KeyEvent as KeyEvent

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus
import internal.GlobalVariable

public class getRowDetails {

	@Keyword
	def getSearchResutls(WebDriver katalonWebDriver,extentTest,searchStr){
		String myXpath=null;
		String myText=null;
		WebUI.delay(5)
		def LogStatus = com.relevantcodes.extentreports.LogStatus


		myXpath="//div[@col-id='name']"


		//myXpath="//div[@aria-colindex='2']"
		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println("-------------------------------------")
		println listElement.size()
		println("-------------------------------------")

		for(int i =0;i<listElement.size();i++) {
			RemoteWebElement ele = listElement.get(i)
			myText=ele.getText()
			println (ele.getText())
			if(myText.toLowerCase().contains(searchStr.toLowerCase())) {
				println("PASS")
				extentTest.log(LogStatus.PASS, 'Current row in files table - '+i + '- file name - '+myText)
			}
			else {
				println("FAIL")
			}
		}
	}
}
