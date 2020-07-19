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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
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

def GlobalVariable.G_Browser = GlobalVariable.G_Browser*/

String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)
try
{


	WebUI.delay(2)

	WebUI.waitForElementPresent(findTestObject('GenericObjects/TitleLink_Files'), 5)

	WebUI.waitForElementVisible(findTestObject('GenericObjects/TitleLink_Files'), 5)

	WebUI.delay(5, FailureHandling.STOP_ON_FAILURE)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

	WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))

	WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)

	WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), 'ToDeleteMyFolder')

	WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))

	WebUI.waitForElementPresent(findTestObject('FilesPage/FolderRowItem_ListView'), 3)

	WebUI.click(findTestObject('FilesPage/FolderRowItem_ListView'))
	extentTest.log(LogStatus.INFO, 'Selected Folder - ToDeleteMyFolder')

	WebUI.click(findTestObject('FilesPage/FilesDelete_img'))
	extentTest.log(LogStatus.INFO, 'Clicked Delete Icon ')

	//WebUI.verifyElementText(findTestObject('FilesPage/Folder_delete_msg'), 'Are you sure you want to delete this item?')
	WebUI.click(findTestObject('FilesPage/Files_folder_delbutton_Yes'))
	extentTest.log(LogStatus.PASS, 'Deleted Folder - ToDeleteMyFolder' )

	if (GlobalVariable.G_Browser == 'Edge') {
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
