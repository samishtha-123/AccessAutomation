import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus

import internal.GlobalVariable as GlobalVariable

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()

// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)

String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

String screenShot='ExtentReports/'+TestCaseName+userChoice+GlobalVariable.G_Browser+'.png'

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(4)
try
{
	
	String myXpath="//div[@col-id='jobId']"
	WebUI.delay(4)
	List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
	println listElement.size()
	
					RemoteWebElement ele = listElement.get(1)
			String myText=ele.getText()
			println (" job id - "+myText)
	WebUI.delay(3)
	WebUI.click(findTestObject('Generic_Actions/Generic_Actions'))
	extentTest.log(LogStatus.PASS, 'Click on Generic actions')
	
	switch(userChoice)
	{
		case'Qstat':
		
		   WebUI.click(findTestObject('Generic_Actions/Qstat_of_Job'))
		   extentTest.log(LogStatus.PASS, 'Click on Qstat_of_Job')
		   
		   WebUI.click(findTestObject('Generic_Actions/Confirm_button'))
		   extentTest.log(LogStatus.PASS, 'Click on run')
		   
		   WebUI.verifyElementPresent(findTestObject('Generic_Actions/Generic_Actions'), 5)
		   extentTest.log(LogStatus.PASS, 'Verify generic actions button is present')
		   
		   
		   
		   break
		   
		case'Trace job empty job id':
		
		   WebUI.click(findTestObject('Generic_Actions/Trace_of_Job'))
		   extentTest.log(LogStatus.PASS, 'Click on Trace_of_job')
		   
		   WebUI.click(findTestObject('Generic_Actions/Jobid'))
		   extentTest.log(LogStatus.PASS, 'Click on job id')
		   
		   WebUI.click(findTestObject('Generic_Actions/Confirm_button'))
		   extentTest.log(LogStatus.PASS, 'Click on run')
		   WebUI.delay(3)
		   
		   WebUI.verifyElementPresent(findTestObject('Generic_Actions/Confirm_button'), 5)
		   
		   WebUI.takeScreenshot(screenShot)
		   extentTest.log(LogStatus.PASS, 'Verify job id is required to trace the job')
		   
		   if (GlobalVariable.G_Browser == 'IE') {
			   WebUI.click(findTestObject('Generic_Actions/Close_button'))
		   }
		   
		   break
		   
		 case'Trace job':
		   
		   WebUI.click(findTestObject('Generic_Actions/Trace_of_Job'))
		   extentTest.log(LogStatus.PASS, 'Click on Trace_of_job')
		 
		   WebUI.click(findTestObject('Generic_Actions/Jobid'))
		   WebUI.setText(findTestObject('Generic_Actions/Jobid'), myText)
		   extentTest.log(LogStatus.PASS, 'Add the job id')
		 
		   WebUI.click(findTestObject('Generic_Actions/Confirm_button'))
		   extentTest.log(LogStatus.PASS, 'Click on run')
		   WebUI.delay(3)
		   
		   WebUI.click(findTestObject('Generic_Actions/Confirm_button'))
		   extentTest.log(LogStatus.PASS, 'Click on Close')
		   
		   
		   
		   break
		   
		case'Verify Generic Action enabled':
		
		  WebUI.verifyElementPresent(findTestObject('Generic_Actions/Generic_Actions'), 5)
		  extentTest.log(LogStatus.PASS, 'Verify generic actions is enabled')
		  
		  break
		  
	
		
	
		
	}
	
	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	
	
}
catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)
}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)
}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}