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
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.util.KeywordUtil

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

/*WebDriver driver = DriverFactory.getWebDriver()
 Capabilities caps = ((driver) as RemoteWebDriver).getCapabilities()
 String browserName = caps.getBrowserName()
 String browserVersion = caps.getVersion()
 def Browser = GlobalVariable.G_Browser*/

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)



try {


	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	WebUI.delay(2)
	
	extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
	
	TestObject viewIconTile=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title', 'equals',"Tile View", true)
	TestObject viewIconList=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title', 'equals',"List View", true)

	viewIconTilePresent=WebUI.waitForElementPresent(viewIconTile, 3, FailureHandling.CONTINUE_ON_FAILURE)

	viewIconListPresent=WebUI.waitForElementPresent(viewIconList, 3, FailureHandling.CONTINUE_ON_FAILURE)

	println("viewIconTilePresent - "+viewIconTilePresent)
	println("viewIconListPresent - "+viewIconListPresent)

	if(viewIconListPresent)
	{
		WebUI.click(viewIconList)
		extentTest.log(LogStatus.PASS, 'Changing File View to ListView')
		WebUI.delay(2)
	}
	
	
	
	
	def FolderEmptytext=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('FilesPage/Label_FolderEmpty'),5)
	
	println (FolderEmptytext)

	if (FolderEmptytext)
	{

		extentTest.log(LogStatus.INFO, 'Folder is Empty')
		extentTest.log(LogStatus.PASS, 'Folder is Empty -Nothing to delete')

	}

	else

	{			
		WebUI.click(findTestObject('FilesPage/CheckBox_Select_All'))
		extentTest.log(LogStatus.PASS,"Selected All items")
		def ele = WebUI.verifyElementClickable(findTestObject('FilesPage/FilesDelete_img'))

		println(ele)/*
		extentTest.log(LogStatus.PASS, 'Clicked on Delte Icon')
		WebUI.click(findTestObject('FilesPage/FilesDelete_img'))
		WebUI.delay(3)
		WebUI.click(findTestObject('GenericObjects/btn_Yes'))
		extentTest.log(LogStatus.PASS, "Deleted all the items")
*/	}
}
catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepFailedException  e)
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