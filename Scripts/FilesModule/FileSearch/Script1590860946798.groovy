import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

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

ReportFile = (GlobalVariable.G_ReportName + '.html')

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

String TCName = TestCaseName

def extentTest = extent.startTest(TCName)

def viewIconTilePresent

def viewIconListPresent

TestObject newFileObj

WebUI.delay(2)

try {
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(LogStatus.PASS, 'Navigated to Files Tab')
	WebUI.delay(2)
	TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
			'equals', 'Tile View', true)
	TestObject viewIconList = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
			'equals', 'List View', true)

	viewIconTilePresent = WebUI.waitForElementPresent(viewIconTile, 3, FailureHandling.CONTINUE_ON_FAILURE)
	viewIconListPresent = WebUI.waitForElementPresent(viewIconList, 3, FailureHandling.CONTINUE_ON_FAILURE)

	println('viewIconTilePresent - ' + viewIconTilePresent)
	println('viewIconListPresent - ' + viewIconListPresent)

	if (viewIconListPresent) {
		WebUI.click(viewIconList)
		extentTest.log(LogStatus.PASS, 'Changed View to ListView')
		WebUI.delay(2)
	}
	WebUI.delay(2)
	println(TestCaseName)

	if (TestCaseName.contains('tile view'))
	{
		WebUI.click(viewIconTile)
		extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' + TestCaseName)
		WebUI.delay(2)
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', searchStr,true)
	} else {
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', searchStr,true)
	}

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
	def location = navLocation + '/FilesModule/FileOps/'
	println('##################################################################')
	println(location)
	println('##################################################################')
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')
	WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
	WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
	println(searchStr)
	WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), searchStr)
	extentTest.log(LogStatus.PASS, 'Search String - ' + searchStr)
	WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
	CustomKeywords.'operations_FileModule.getRowDetails.getSearchResutls'(katalonWebDriver, extentTest,searchStr)

}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TCName) + GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL, ex)
	KeywordUtil.markFailed('ERROR: ' + e)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TCName) + GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL, e)
	KeywordUtil.markFailed('ERROR: ' + e)
}
finally {
	extent.endTest(extentTest)
	extent.flush()
}
