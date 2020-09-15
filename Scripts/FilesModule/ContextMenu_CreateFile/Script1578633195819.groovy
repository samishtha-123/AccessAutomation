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

fileName="CntxMenu"+fileName
WebUI.delay(2)

def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()
def location=navLocation
println('##################################################################')
println (location)
println('##################################################################')

try
{
	'Navigate to Files Tab\r\n'
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))



	extentTest.log(LogStatus.PASS, "Navigated to Files Tab " )
	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to - '+location)

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


	WebUI.delay(3)
	WebUI.click(findTestObject('FilesPage/ContextMenu_CreateNewFile'))
	extentTest.log(LogStatus.PASS,"Clicked context menu - New file ")

	WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

	WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)

	WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))


	WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)

	WebUI.click(findTestObject('FilesPage/btn_Save'))
	extentTest.log(LogStatus.PASS, "Clicked on Save Button" )


	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_FileCreation'), 3)

	println(WebUI.getText(findTestObject('Notificactions/Notification_FileCreation')))

	WebUI.delay(2)

	WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))

	WebUI.delay(2)

	//WebUI.mouseOver(findTestObject('Notificactions/xpath_notification'))
	TestObject createFileNotification = CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFileCreateNotification'(fileName)

	'Verify notification'
	def notification = WebUI.verifyElementPresent(createFileNotification, 5)

	println(notification)

	if(notification)
	{
		extentTest.log(LogStatus.PASS, fileName+" -  File To Create" )
		extentTest.log(LogStatus.PASS, fileName + "- Created file and verified notification")

	}
	else
	{
		extentTest.log(LogStatus.PASS, fileName+" - Not Created" )
		extentTest.log(LogStatus.FAIL)
	}

	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	def des = TestCaseName.toString()


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



