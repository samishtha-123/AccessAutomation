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

String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)
try
{


	WebUI.delay(3)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	def toget = GlobalVariable.G_JobID

	println(toget)

	WebUI.delay(4)
	TestObject jobRowObj = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjJobRow'(toget)

	TestObject jobRowObjIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjJobIndentifierLatest'(toget)
	WebUI.delay(3)
	WebUI.click(jobRowObj)
	WebUI.rightClick(jobRowObj)

	extentTest.log(LogStatus.INFO, 'Right Clicked on Job  - ' + GlobalVariable.G_JobID)

	WebUI.delay(3)

	WebUI.click(findTestObject('JobMonitoringPage/ContextMN_JobRow_Resubmit'))

	WebUI.verifyElementPresent(findTestObject('JobSubmissionForm/label_Application'), 9)

	WebUI.verifyElementPresent(findTestObject('JobSubmissionForm/label_Application'), 1)

	WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
	
	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
	
		extentTest.log(LogStatus.INFO, 'NOtification Generated')
	
		String[] splitAddress = jobText.split('\\(')
	
		def JobID = splitAddress[1]
	
		String[] jobIdArr = JobID.split('\\)')
	
		toget = (jobIdArr[0])
	

	println('********************************************')

	println(toget)

	extentTest.log(LogStatus.INFO, 'JobID - ' + toget)

	println('********************************************')


	extentTest.log(LogStatus.PASS, 'Resubmitted Job  - ' + toget)


	if (GlobalVariable.G_Browser == 'Edge') {
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


