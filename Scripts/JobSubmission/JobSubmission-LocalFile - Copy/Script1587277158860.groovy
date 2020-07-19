import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver


import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable
'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = (EventFiringWebDriver) DriverFactory.getWebDriver()
// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver


String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

TestObject newFileObj

try {
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),
			10)

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	extentTest.log(LogStatus.PASS, 'Navigated Jobs Tab')

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)

	WebUI.click(newAppObj)

	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

	//	WebUI.doubleClick(newAppObj)
	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))

	WebUI.delay(2)

	CustomKeywords.'operations_JobsModule.JobSubmissions.JSAllFileds'(ToChange, ChangeValue, extentTest)


	WebUI.delay(2)

	WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

	WebUI.delay(3)



	if(ExecMode.equals('LocalForm'))
	{
		newFileObj =CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
	}
	else
	{

		if(ExecMode.equals('Local'))
		{
			newFileObj =CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			
		}
		else{
			if (TestCaseName.contains('ShortCut')) {
				ExecMode='ShortCut'

				newFileObj =CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			}
			else{

				newFileObj =CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			}
		}
		WebUI.click(newFileObj)

		WebUI.rightClick(newFileObj)

		extentTest.log(LogStatus.PASS, 'Right Clicked on Input file ' + InputFile)

		WebUI.delay(2)

		String idForCntxtMn = 'Add as ' + FileArg

		TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
				'id', 'equals', idForCntxtMn, true)

		WebUI.click(newRFBContextMnOption)

		extentTest.log(LogStatus.PASS, 'Clicked on context menu - ' + idForCntxtMn)
	}

	def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),
			10)

	if (submitBtn) {
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

		extentTest.log(LogStatus.PASS, 'Clicked on Submit Button ')
	}

	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

	extentTest.log(LogStatus.PASS, 'Notification Generated')

	println('Job Text = ' + jobText)

	String[] splitAddress = jobText.split(' ')

	def len = (splitAddress[2]).length()

	def toget = (splitAddress[2]).substring(1, len - 1)

	GlobalVariable.G_JobID = toget

	println('********************************************')

	println(GlobalVariable.G_JobID)

	extentTest.log(LogStatus.PASS, 'Job ID - ' + GlobalVariable.G_JobID)

	extentTest.log(LogStatus.PASS, 'Job Submission Done for - ' + TestCaseName)

	println('********************************************')

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	
	CustomKeywords.'operations_JobsModule.JobSubmissions.printJobState'(katalonWebDriver, extentTest)
	
	
	/*
	TestObject jobIdEle = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjJobRow'(GlobalVariable.G_JobID)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.delay(2)

	String jobState = CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobState'(jobIdEle, extentTest)

	extentTest.log(LogStatus.PASS, 'Current Job State - ' + jobState)
*/	
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



