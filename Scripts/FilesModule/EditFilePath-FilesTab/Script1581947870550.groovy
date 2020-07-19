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

WebUI.delay(2)

try {


	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(LogStatus.PASS, 'Naviagted to Files Tab')
	

	WebUI.delay(2)


	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	
			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),"/stage/BookMark/")
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(LogStatus.PASS, 'Navigated to /stage/BookMark/ in files tab ')
	
	
	
	WebUI.delay(4)


TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'text', 'equals',
		'bookMarked.txt', true)

def isFilePresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj,10)
if (isFilePresent)
{
	extentTest.log(LogStatus.PASS, 'Verified navigated Folder')
}


if (GlobalVariable.G_Browser == 'Edge') {
	WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
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

