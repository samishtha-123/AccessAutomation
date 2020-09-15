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
import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_AdminUser, ('password') : GlobalVariable.G_AdminPasswd],
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

def extentTest = extent.startTest(TestCaseName)
def result
WebUI.delay(2)
try
{
	WebUI.delay(2)
	//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	WebUI.delay(3)
	WebUI.mouseOver(findTestObject('Cluster_Registration/Manageservice'))
	WebUI.click(findTestObject('Cluster_Registration/Manageservice'))
	extentTest.log(LogStatus.PASS, 'Click on Manage Service')
	WebUI.delay(2)
	
	if (userChoice.contains('valid'))
	{   
	    WebUI.rightClick(findTestObject('Cluster_Registration/Available'))
		WebUI.click(findTestObject('Cluster_Registration/Delete'))
		extentTest.log(LogStatus.PASS, 'Delete Existing Cluster')
		
		WebUI.click(findTestObject('FilesPage/Confirm_button'))
		extentTest.log(LogStatus.PASS, 'Click on Ok')
		
		WebUI.click(findTestObject('Cluster_Registration/Configure_Services'))
		extentTest.log(LogStatus.PASS, 'Click on Configure Service')
		
		WebUI.click(findTestObject('Cluster_Registration/Server_Name'))
		WebUI.setText(findTestObject('Cluster_Registration/Server_Name'),server)
		extentTest.log(LogStatus.PASS, 'Add server name')
		
		WebUI.click(findTestObject('Cluster_Registration/Url'))
		WebUI.setText(findTestObject('Cluster_Registration/Url'),url)
		extentTest.log(LogStatus.PASS, 'Add url')
		
		WebUI.click(findTestObject('Cluster_Registration/Username'))
		WebUI.setText(findTestObject('Cluster_Registration/Username'),username)
		extentTest.log(LogStatus.PASS, 'Add username')
		
		WebUI.click(findTestObject('Cluster_Registration/Password'))
		WebUI.setText(findTestObject('Cluster_Registration/Password'),password)
		extentTest.log(LogStatus.PASS, 'Add password')
		
		WebUI.click(findTestObject('Cluster_Registration/Rootdir'))
		WebUI.setText(findTestObject('Cluster_Registration/Rootdir'),rootdir)
		extentTest.log(LogStatus.PASS, 'Add root directory')
		
		WebUI.click(findTestObject('Cluster_Registration/Add_Server'))
		extentTest.log(LogStatus.PASS, 'Click on add server')
		
		def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Cluster_Registration/Add_Server'), 
        30)

    if (submitBtn) {
        
		WebUI.mouseOver(findTestObject('Cluster_Registration/Manageservice'))
		WebUI.click(findTestObject('Cluster_Registration/Manageservice'))
		WebUI.delay(3)
		extentTest.log(LogStatus.PASS, 'Click on Manage Service')

        
    }
	result = CustomKeywords.'operations_JobsModule.GetJobRowDetails.newGrid'(katalonWebDriver, colid, server,
		extentTest)
		//WebUI.verifyElementPresent(findTestObject('Cluster_Registration/Verify_Url'), 2)
		extentTest.log(LogStatus.PASS, 'Verify the cluster')
	}
	else if (userChoice.contains('invalid'))
	{
		WebUI.rightClick(findTestObject('Cluster_Registration/Available'))
		WebUI.click(findTestObject('Cluster_Registration/Delete'))
		extentTest.log(LogStatus.PASS, 'Delete Existing Cluster')
		
		WebUI.click(findTestObject('FilesPage/Confirm_button'))
		extentTest.log(LogStatus.PASS, 'Click on Ok')
		
		WebUI.click(findTestObject('Cluster_Registration/Configure_Services'))
		extentTest.log(LogStatus.PASS, 'Click on Configure Service')
		
		WebUI.click(findTestObject('Cluster_Registration/Server_Name'))
		WebUI.setText(findTestObject('Cluster_Registration/Server_Name'),server)
		extentTest.log(LogStatus.PASS, 'Add server name')
		
		WebUI.click(findTestObject('Cluster_Registration/Url'))
		WebUI.setText(findTestObject('Cluster_Registration/Url'),url)
		extentTest.log(LogStatus.PASS, 'Add url')
		
		WebUI.click(findTestObject('Cluster_Registration/Username'))
		WebUI.setText(findTestObject('Cluster_Registration/Username'),username)
		extentTest.log(LogStatus.PASS, 'Add username')
		
		WebUI.click(findTestObject('Cluster_Registration/Password'))
		WebUI.setText(findTestObject('Cluster_Registration/Password'),password)
		extentTest.log(LogStatus.PASS, 'Add password')
		
		WebUI.click(findTestObject('Cluster_Registration/Rootdir'))
		WebUI.setText(findTestObject('Cluster_Registration/Rootdir'),rootdir)
		extentTest.log(LogStatus.PASS, 'Add root directory')
		
		WebUI.click(findTestObject('Cluster_Registration/Add_Server'))
		extentTest.log(LogStatus.PASS, 'Click on add server')
		
		def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Cluster_Registration/Add_Server'),
		30)

	if (submitBtn) {
		
		WebUI.mouseOver(findTestObject('Cluster_Registration/Manageservice'))
		WebUI.click(findTestObject('Cluster_Registration/Manageservice'))
		WebUI.delay(3)
		extentTest.log(LogStatus.PASS, 'Click on Manage Service')

		
	}
	   
	    WebUI.verifyElementPresent(findTestObject('Cluster_Registration/Configure_Services'),2)
	    extentTest.log(LogStatus.PASS, 'Click on Configure Service')
		
	}
	else if (userChoice.contains('delete cluster'))
	{
		WebUI.rightClick(findTestObject('Cluster_Registration/Available'))
		WebUI.click(findTestObject('Cluster_Registration/Delete'))
		extentTest.log(LogStatus.PASS, 'Delete Existing Cluster')
		
		WebUI.click(findTestObject('FilesPage/Confirm_button'))
		extentTest.log(LogStatus.PASS, 'Click on Ok')
		
		WebUI.verifyElementPresent(findTestObject('Cluster_Registration/Configure_Services'), 2)
		extentTest.log(LogStatus.PASS, 'Click on Configure Service')
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

