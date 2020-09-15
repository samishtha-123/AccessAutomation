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
	def deleteOption = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/SubMenu_Delete'),5)
	def duplicateOption = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/SubMenu_Duplicate'),5)
	def setAsDefaultOption = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/SubMenu_SetAsDefault'),5)
	extentTest.log(LogStatus.PASS, 'Right Cicked on Profile -  '+proName )
	extentTest.log(LogStatus.PASS, 'Profile Action -  '+ myOption)
	

	switch(myOption)
	{
		case 'Delete':
			if (deleteOption) {
				extentTest.log(LogStatus.PASS, 'Test to verify delete option exists - Pass ')

				WebUI.mouseOver(findTestObject('JobSubmissionForm/SubMenu_Delete'))
				WebUI.click(findTestObject('JobSubmissionForm/SubMenu_Delete'))
				extentTest.log(LogStatus.PASS, 'Clicked on Delete ')
				WebUI.delay(3)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			}

			extentTest.log(LogStatus.PASS, 'Deleted Profile - '+proName )

			break;


		case 'Duplicate':

			extentTest.log(LogStatus.PASS, 'Duplicate Profile Option Case - '+duplicateOption )
			if (duplicateOption) {
				extentTest.log(LogStatus.PASS, 'Test to verify duplicate option exists - Pass ')
				WebUI.click(findTestObject('Object Repository/ProfileOptions/SubMenu_SetAsDefault'))
				def duplicatePro=proName+'-copy'
				println(duplicatePro)
				TestObject LeftNavAppIdentifierProDuplicate = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(duplicatePro)
				WebUI.waitForElementPresent(LeftNavAppIdentifierProDuplicate, 5)

				def isProfilePersentPro1 = WebUI.verifyElementPresent(LeftNavAppIdentifierProDuplicate, 5)
				if(isProfilePersentPro1)
				{
					extentTest.log(LogStatus.PASS, 'duplicate created ')
				}
			}
			break;

		case 'SetAsDefault':
		extentTest.log(LogStatus.PASS, 'Duplicate Profile Option Case - '+setAsDefaultOption )
		if (setAsDefaultOption) {
			extentTest.log(LogStatus.PASS, 'Test to verify set as default option exists - Pass ')
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/SubMenu_SetAsDefault'))
			def not=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_ProfileAsDefault'), 5)
			extentTest.log(LogStatus.PASS, 'notification - '+ not)
			
		}

			break


	}

	extentTest.log(LogStatus.PASS, 'Deleted Profile - '+proName )
	
	if (GlobalVariable.G_Browser == 'IE')
	{
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}


}catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	
	String p =TCName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,ex)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))



}
catch (StepErrorException  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)

	String p =TCName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,ex)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))



}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}
