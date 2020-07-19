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

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def location='/stage/LotOfFiles/'

	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')


	if (TestCaseName.contains('Page Navigation')){

		WebUI.verifyElementPresent(findTestObject('FilesPage/FilesPageNavigation'), FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('FilesPage/FilesPageNavigation'), FailureHandling.STOP_ON_FAILURE)
		extentTest.log(LogStatus.PASS, 'Click on Files page navigation')

	} else {

		//WebUI.verifyElementPresent(findTestObject('FilesPage/Arrow Navigation'), FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('FilesPage/Arrow Navigation'), FailureHandling.STOP_ON_FAILURE)
		extentTest.log(LogStatus.PASS, 'Click on Arrow navigation')
	}


	//WebUI.verifyElementPresent(findTestObject('FilesPage/PageHolder'), FailureHandling.STOP_ON_FAILURE)
	//TestObject pageholdervalue = new TestObject('objectName')
	//pageholdervalue.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
	String data=WebUI.getAttribute(findTestObject('FilesPage/PageHolder'), "value")
	println"value of page holder - "+ data

	extentTest.log(LogStatus.PASS, 'Test case for pagination passed')
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

