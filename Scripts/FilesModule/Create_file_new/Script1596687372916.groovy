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
	
	WebUI.waitForElementVisible(findTestObject('GenericObjects/TitleLink_Files'), 5)
	
		WebUI.waitForElementClickable(findTestObject('GenericObjects/TitleLink_Files'), 5)
	
		WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
		WebUI.delay(6)
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
	
	
	
	else if (userChoice.contains('Positive flow'))
	{
		if (TestCaseName.contains('tile view')) {
			WebUI.click(viewIconTile)
			extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)
	
			WebUI.delay(2)
			
		
			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)
		
			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
			extentTest.log(LogStatus.PASS, "Clicked on New File Button" )
		
		
			WebUI.click(findTestObject('FilesPage/ListItem_File'))
		
			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
		
			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)
		
			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
		
		
			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)
			
			extentTest.log(LogStatus.PASS, "Enterted File Name to create "+fileName )
			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
		
		
				
				String file = fileName.trim()
			
				WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_FileCreation'), 3)
				
					println(WebUI.getText(findTestObject('Notificactions/Notification_FileCreation')))
				
					WebUI.delay(2)
				
					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				
					WebUI.delay(2)
				
					//WebUI.mouseOver(findTestObject('Notificactions/xpath_notification'))
					TestObject createFileNotification = CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFileCreateNotification'(file)
				
					'Verify notification'
					def notification = WebUI.verifyElementPresent(createFileNotification, 5)
				
					println(notification)
				
					if(notification)
					{
						
						extentTest.log(LogStatus.PASS, fileName + "- Created file and verified notification")
				
					}
					else
					{
						extentTest.log(LogStatus.PASS, fileName+" - Not Created" )
						extentTest.log(LogStatus.FAIL)
					}
		} else {
			
		
		WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)
		
			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
			extentTest.log(LogStatus.PASS, "Clicked on New File Button" )
		
		
			WebUI.click(findTestObject('FilesPage/ListItem_File'))
		
			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
		
			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)
		
			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
		
		
			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)
			
			extentTest.log(LogStatus.PASS, "Enterted File Name to create "+fileName )
			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
		
		
				
				String file = fileName.trim()
			
				WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_FileCreation'), 3)
				
					println(WebUI.getText(findTestObject('Notificactions/Notification_FileCreation')))
				
					WebUI.delay(2)
				
					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				
					WebUI.delay(2)
				
					//WebUI.mouseOver(findTestObject('Notificactions/xpath_notification'))
					TestObject createFileNotification = CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFileCreateNotification'(file)
				
					'Verify notification'
					def notification = WebUI.verifyElementPresent(createFileNotification, 5)
				
					println(notification)
				
					if(notification)
					{
						
						extentTest.log(LogStatus.PASS, fileName + "- Created file and verified notification")
				
					}
					else
					{
						extentTest.log(LogStatus.PASS, fileName+" - Not Created" )
						extentTest.log(LogStatus.FAIL)
					}
				
			
		
		
		}
	}
    else if (userChoice.contains('Negative flow'))
	{
		
		
		if (TestCaseName.contains('tile view')) {
			WebUI.click(viewIconTile)
			extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)
	
			WebUI.delay(2)
			
			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)
			
				'Click New File Button '
				WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
				extentTest.log(LogStatus.PASS, "Clicked on New File Button" )
			
			
				WebUI.click(findTestObject('FilesPage/ListItem_File'))
			
				WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
			
				WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)
			
				WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
			
			
				WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)
				
				extentTest.log(LogStatus.PASS, "Enterted File Name to create "+fileName )
				'Click save\r\n'
				WebUI.click(findTestObject('FilesPage/btn_Save'))
				extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
				
			
				WebUI.verifyElementPresent(findTestObject('FilesPage/SpecialChar_popup'), 3)
			
		} else {
			
		WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)
		
			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
			extentTest.log(LogStatus.PASS, "Clicked on New File Button" )
		
		
			WebUI.click(findTestObject('FilesPage/ListItem_File'))
		
			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
		
			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)
		
			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))
		
		
			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)
			
			extentTest.log(LogStatus.PASS, "Enterted File Name to create "+fileName )
			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
			
			WebUI.verifyElementPresent(findTestObject('FilesPage/SpecialChar_popup'), 3)
		}
		
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
