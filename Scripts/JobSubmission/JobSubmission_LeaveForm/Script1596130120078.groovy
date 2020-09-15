import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)


try {

	def jobsTab=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 10)
	if(jobsTab)
	{
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	extentTest.log(LogStatus.PASS, 'Navigated Job Tab')
	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)

	WebUI.click(newAppObj)
	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - '+AppName)

	//	WebUI.doubleClick(newAppObj)

	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
	WebUI.delay(2)


	jobsTab=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 10)
	if(jobsTab)
	{
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
		def LeaveJobs=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/JobSubmissionForm/Title_Leave_Job_submission_form'), 10)
		if(LeaveJobs)
		{
			WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Text_LeavePage'), 4)
			extentTest.log(LogStatus.PASS, 'Verified the - Leave Job submission form? - dialog box')
			
					}

	}
	switch(userChoice)
	{
		case 'Yes':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))
			extentTest.log(LogStatus.PASS, 'Clicked on Yes on dialog box')
			WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/JM_SelectAppLable'),3)
			extentTest.log(LogStatus.PASS, 'Verified user is navigated  to Jobs Page')
			break
		case 'No':
			WebUI.click(findTestObject('Object Repository/GenericObjects/btn_No'))
			extentTest.log(LogStatus.PASS, 'Clicked on No on dialog box')
			WebUI.verifyElementPresent(findTestObject('Object Repository/NewJobPage/GenericProfile'), 3)
			extentTest.log(LogStatus.PASS, 'Verified user stays on Job Submission Form')
			break
		case 'Close':
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_LeavePage'))
			extentTest.log(LogStatus.PASS, 'Clicked on Close Icon on dialog box')
			WebUI.verifyElementPresent(findTestObject('Object Repository/NewJobPage/GenericProfile'), 3)
			extentTest.log(LogStatus.PASS, 'Verified user stays on Job Submission Form')
			break

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

