import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

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

String ReportFile = GlobalVariable.G_ReportName + '.html'
def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)

try {
    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    extentTest.log(LogStatus.PASS, 'Navigated Job Tab')

    WebUI.delay(2)

    TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', 
        AppName, true)

    WebUI.click(newAppObj)

    extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

    WebUI.delay(2)

    extentTest.log(LogStatus.PASS, 'Switched to Generic Profile')

    WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
	WebUI.delay(2)


	WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

	
	
	//((JavascriptExecutor)DriverFactory.getWebDriver()).
	WebElement element = WebUI.executeScript(" return document.getElementById(\'submission_primary_file_field_hidden\');", null)
	println(element.getClass())
			def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),9)

	if (isElemenetPresent)
	{
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
	}
	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

	extentTest.log(LogStatus.INFO, 'NOtification Generated')

	String[] splitAddress = jobText.split('\\(')

	def JobID = splitAddress[1]

	String[] jobIdArr = JobID.split('\\)')

	toget = (jobIdArr[0])

	GlobalVariable.G_JobID = toget

	println('********************************************')

	println(GlobalVariable.G_JobID)

	extentTest.log(LogStatus.INFO, 'JobID - ' + GlobalVariable.G_JobID)

	println('********************************************')

	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	extentTest.log(LogStatus.PASS, 'Job Submission Done')
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