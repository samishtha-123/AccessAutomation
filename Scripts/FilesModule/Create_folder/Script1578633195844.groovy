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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)
/*
WebDriver driver = DriverFactory.getWebDriver()

Capabilities caps = ((driver) as RemoteWebDriver).getCapabilities()

String browserName = caps.getBrowserName()

String browserVersion = caps.getVersion()

def GlobalVariable.G_Browser = GlobalVariable.G_Browser
*/
String ReportFile = GlobalVariable.G_ReportName + '.html'
def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)


def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)

try {
	WebUI.delay(2)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(LogStatus.PASS, "Navigated to Files Tab " )

	WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

	WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

	WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

	WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

	WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), FolderName)
	extentTest.log(LogStatus.PASS, "Entered Folder Name to Create "+FolderName )

	WebUI.click(findTestObject('FilesPage/btn_Save'))
	extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
	

	WebUI.delay(2)

	WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))

	WebUI.delay(2)

	TestObject createFolderNotification=CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFolderCreateNotification'(FolderName)

	extentTest.log(LogStatus.PASS, 'Created Folder  - ' + FolderName)



	'Verify notification'
	def notification = WebUI.verifyElementPresent(createFolderNotification, 5)

	println(notification)

	if(notification)
	{
	
		extentTest.log(LogStatus.PASS, FolderName + "- Created Folder and verified notification")

	}
	else
	{
		extentTest.log(LogStatus.PASS, FolderName+" - Not Created" )
		extentTest.log(LogStatus.FAIL)
	}

	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}catch (Exception  ex)
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
