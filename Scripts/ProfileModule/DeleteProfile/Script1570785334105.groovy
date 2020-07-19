import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable


'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

String TCName = TestCaseName+' - '+proName
def extentTest = extent.startTest(TCName)

WebUI.delay(2)
try
{


	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)

	WebUI.click(newAppObj)
	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission form for - '+AppName )
	
	WebUI.delay(2)

	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)
	WebUI.waitForElementPresent(LeftNavAppIdentifier, 5)
	WebUI.click(LeftNavAppIdentifier)

	WebUI.delay(2)
	
	WebUI.rightClick(LeftNavAppIdentifier)

	extentTest.log(LogStatus.PASS, 'Right Cicked on Profile -  '+proName )


	WebUI.click(findTestObject('JobSubmissionForm/SubMenu_Delete'))

	extentTest.log(LogStatus.PASS, 'Clicked on Delet ')
	WebUI.delay(3)

	WebUI.click(findTestObject('GenericObjects/btn_Yes'))

	extentTest.log(LogStatus.PASS, 'Deleted Profile - '+proName )
	
	if (GlobalVariable.G_Browser == 'Edge')
	{
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}


}catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}
