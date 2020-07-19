import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)
try
{
	'Navigate to Files Tab\r\n'
	
	
	def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)
	
		if (isElemenetPresent)
		{
			WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
			extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
		}

	
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
		
		extentTest.log(LogStatus.PASS, fileName + "- Created file and verified notification")

	}
	else
	{
		extentTest.log(LogStatus.PASS, fileName+" - Not Created" )
		extentTest.log(LogStatus.FAIL)
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