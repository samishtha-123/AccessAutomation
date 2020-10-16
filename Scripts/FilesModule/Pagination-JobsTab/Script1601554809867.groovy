import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

ReportFile = (GlobalVariable.G_ReportName + '.html')

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

def viewIconTilePresent

def viewIconListPresent

def pageno

TestObject newFileObj

WebUI.delay(2)

try {
	if (TestCaseName.contains('jump to'))
	{
		if (userChoice.equals('valid')) {
			
			WebUI.click(findTestObject('JobMonitoringPage/Jumpto'))
			def number= '2'
		
			WebUI.clearText(findTestObject('JobMonitoringPage/Jumpto'))
			WebUI.setText(findTestObject('JobMonitoringPage/Jumpto'),number)
			//WebUI.sendKeys(findTestObject('JobMonitoringPage/Jumpto'), '2')
			WebUI.click(findTestObject('JobMonitoringPage/Go_button'))
			String data=WebUI.getAttribute(findTestObject('FilesPage/PageHolder'), "value")
			println"value of page holder - "+ data

		}
		else if (userChoice.equals('invalid char')) {

			println('kkkk')
			WebUI.click(findTestObject('JobMonitoringPage/Jumpto'))
			def number= '2#'
		
			WebUI.clearText(findTestObject('JobMonitoringPage/Jumpto'))
			WebUI.setText(findTestObject('JobMonitoringPage/Jumpto'),number)
			//WebUI.sendKeys(findTestObject('JobMonitoringPage/Jumpto'), '2')
			WebUI.click(findTestObject('JobMonitoringPage/Go_button'))
			String data=WebUI.getAttribute(findTestObject('FilesPage/PageHolder'), "value")
			println"value of page holder - "+ data

		}
		
	}
	else if (TestCaseName.contains('job selection')) {
		
		//TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',jobState, true)
	
		//WebUI.rightClick(newJobRow)
		WebUI.click(findTestObject('JobMonitoringPage/div_Completed'))
		WebUI.click(findTestObject('JobMonitoringPage/Jumpto'))
		def number= '2'
	
		WebUI.clearText(findTestObject('JobMonitoringPage/Jumpto'))
		WebUI.setText(findTestObject('JobMonitoringPage/Jumpto'),number)
		//WebUI.sendKeys(findTestObject('JobMonitoringPage/Jumpto'), '2')
		WebUI.click(findTestObject('JobMonitoringPage/Go_button'))
		WebUI.verifyElementPresent(findTestObject('FilesPage/Clearselection_noti'), 3)
		WebUI.click(findTestObject('FilesPage/button_Cancel'))
		
	  }
	else if (TestCaseName.contains('job details'))
	{  
		WebUI.click(findTestObject('JobMonitoringPage/Jumpto'))
		def number= '2'
	
		WebUI.clearText(findTestObject('JobMonitoringPage/Jumpto'))
		WebUI.setText(findTestObject('JobMonitoringPage/Jumpto'),number)
		//WebUI.sendKeys(findTestObject('JobMonitoringPage/Jumpto'), '2')
		WebUI.click(findTestObject('JobMonitoringPage/Go_button'))
		
		WebUI.doubleClick(findTestObject('JobMonitoringPage/div_Completed'))
		WebUI.click(findTestObject('FilesPage/Jobs'))
		String data=WebUI.getAttribute(findTestObject('FilesPage/PageHolder'), "value")
		println"value of page holder - "+ data
		
	}
	
	else if (TestCaseName.contains('greator arrows'))
	{
		WebUI.click(findTestObject('JobMonitoringPage/Jumpto'))
		def number= '1'
	
		WebUI.clearText(findTestObject('JobMonitoringPage/Jumpto'))
		WebUI.setText(findTestObject('JobMonitoringPage/Jumpto'),number)
		
		WebUI.click(findTestObject('JobMonitoringPage/Go_button'))
		WebUI.verifyElementNotClickable(findTestObject('JobMonitoringPage/Arrowone'),7)
		
		
	}
	else if (TestCaseName.contains('no jobs'))
	{
		WebUI.verifyElementPresent(findTestObject('JobMonitoringPage/Nojobs'), 4)
	}
	
	
	
	

}


catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(LogStatus.FAIL, ex)

	KeywordUtil.markFailed('ERROR: ' + e)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(LogStatus.FAIL, e)

	KeywordUtil.markFailed('ERROR: ' + e)
}
finally {
	extent.endTest(extentTest)

	extent.flush()


}

