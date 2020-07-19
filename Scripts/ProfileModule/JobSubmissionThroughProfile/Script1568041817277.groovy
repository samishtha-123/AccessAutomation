import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

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

/*WebDriver driver = DriverFactory.getWebDriver()
 Capabilities caps = ((driver) as RemoteWebDriver).getCapabilities()
 String browserName = caps.getBrowserName()
 String browserVersion = caps.getVersion()
 def GlobalVariable.G_Browser = GlobalVariable.G_Browser
 */
String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus

String TCName = TestCaseName+' - '+proName
def extentTest = extent.startTest(TCName)

WebUI.delay(2)

try {
	WebUI.delay(2)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)

	WebUI.click(newAppObj)

	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - '+AppName)

	WebUI.delay(2)
	extentTest.log(LogStatus.PASS, 'Switched to Generic Profile')

	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
	WebUI.delay(2)

	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)
	WebUI.waitForElementPresent(LeftNavAppIdentifier, 5)
	WebUI.click(LeftNavAppIdentifier)




	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def fileLocation='/stage/'+GlobalVariable.G_userName+'/ForProfiles/InputDeck'
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),fileLocation)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to '+fileLocation+' in RFB ')

	WebUI.delay(3)

	WebUI.waitForElementPresent(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 5)

	WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))

	WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), fileName)

	WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))

	WebUI.delay(3)

	TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',
			fileName, true)

	WebUI.click(newFileObj)
	WebUI.rightClick(newFileObj)

	WebUI.delay(2)


	String idForCntxtMn="Add as "+FileArg
	TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),'id' , 'equals' , idForCntxtMn , true)
	WebUI.click(newRFBContextMnOption)

	extentTest.log(LogStatus.PASS, 'Selected Context Menu option - '+idForCntxtMn)



	def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job_Profile'),10)

	if (isElemenetPresent)
	{
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
	}


	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

	String[] splitAddress = jobText.split('\\(')

	def JobID = splitAddress[1]

	String[] jobIdArr = JobID.split('\\)')

	toget = (jobIdArr[0])

	GlobalVariable.G_JobID = toget

	println('********************************************')

	println(GlobalVariable.G_JobID)

	extentTest.log(LogStatus.PASS, 'Notification Generated ')

	extentTest.log(LogStatus.PASS, 'Job Submitted JobID - ' + GlobalVariable.G_JobID)

	println('********************************************')

	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)

}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}
