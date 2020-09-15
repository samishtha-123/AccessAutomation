import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot
import java.awt.event.KeyEvent as KeyEvent

import org.openqa.selenium.Keys

import com.kms.katalon.core.configuration.RunConfiguration
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
Robot rob = new Robot()



def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()
println('##################################################################')
println (navLocation)
println('##################################################################')



WebUI.delay(2)
try
{
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)
	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)

	WebUI.click(newAppObj)

	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - '+AppName)
	def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),2)

	if (errorPanel) {
		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
	}

	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
	WebUI.delay(2)

	WebUI.scrollToElement(findTestObject('NewJobPage/List_NCPU_White_Theam'), 3)

	WebUI.setText(findTestObject('NewJobPage/List_NCPU_White_Theam'), '2')

	WebUI.setText(findTestObject('NewJobPage/List_Mem_WhiteTheam'), '120')

	extentTest.log(LogStatus.PASS, 'Changed the value for NCPU and MEMORY fileds ')


	switch(ProfileType)
	{
		case 'Default':
			WebUI.click(findTestObject('NewJobPage/CheckBx_SetProfileDefault'))
			extentTest.log(LogStatus.PASS, 'Setting as default profile ')
			break;
		case'Local':
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))

			def location=navLocation+'/ProfileFiles/'
			println('##################################################################')
			println (location)
			println('##################################################################')

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(LogStatus.PASS, 'Navigated to /stage/'+GlobalVariable.G_userName+'/ProfileFiles/ in RFB ')
			def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/') + InputFile
			def newFP = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'),newFP)
			extentTest.log(LogStatus.PASS, 'Uploading File to RFB - '+InputFile)
			WebUI.delay(3)
			break;
		case 'Remote':
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			def fileLocation=navLocation+'/ForProfiles/InputDeck/'
			println('##################################################################')
			println (fileLocation)
			println('##################################################################')
			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), fileLocation)
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(LogStatus.PASS, 'Navigated to /stage/InputDeck in RFB ')
			WebUI.waitForElementPresent(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 5)
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))
			WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), RemoteFile)
			WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))
			WebUI.delay(3)
			TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',
					RemoteFile, true)
			WebUI.click(newFileObj)
			WebUI.rightClick(newFileObj)
			extentTest.log(LogStatus.PASS, 'Context Menu Invoked for Input File - ' + RemoteFile)
			WebUI.delay(2)
			String idForCntxtMn = 'Add as ' + FileArg
			TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
					'id', 'equals', idForCntxtMn, true)
			WebUI.click(newRFBContextMnOption)
			extentTest.log(LogStatus.PASS, 'Selected Context Menu option - ' + idForCntxtMn)
			break;

		case 'Incomplete':
			WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			break;

		case 'NoFile':
			println('No File selected')
			break;


		case 'Duplicate':
			WebUI.rightClick(LeftNavAppIdentifier)
			def duplicateOption = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/SubMenu_Duplicate'),5)
			extentTest.log(LogStatus.PASS, 'Duplicate Profile Option Case - '+duplicateOption )
			if (duplicateOption) {
				extentTest.log(LogStatus.PASS, 'Test to verify duplicate option exists - Pass ')
				WebUI.click(findTestObject('JobSubmissionForm/SubMenu_Duplicate'))
				def duplicatePro=proName+'-copy'
				println(duplicatePro)
				TestObject LeftNavAppIdentifierProDuplicate = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(duplicatePro)
				WebUI.waitForElementPresent(LeftNavAppIdentifierProDuplicate, 5)

				def isProfilePersentPro1 = WebUI.verifyElementPresent(LeftNavAppIdentifierProDuplicate, 5)
				if(isProfilePersentPro1)
				{
					extentTest.log(LogStatus.PASS, 'Duplicate profile created - '+ duplicatePro)
				}
			}
			println('No File selected')
			break;
	}


	WebUI.click(findTestObject('NewJobPage/Btn_Save As'))
	WebUI.delay(3)

	WebUI.waitForElementPresent(findTestObject('NewJobPage/label_Save Profile'), 5)
	WebUI.delay(3)

	if (GlobalVariable.G_Browser == 'IE') {
		CustomKeywords.'funtionsForEdge.EdgeFunctions.setTestToField'(proName, findTestObject('NewJobPage/TxtBx_saveProfile'))
	} else {
		WebUI.sendKeys(findTestObject('NewJobPage/TxtBx_saveProfile'), proName)
	}




	if(ProfileType.equals('Cancel'))
	{
		WebUI.click(findTestObject('Object Repository/NewJobPage/button_Cancel'))
		extentTest.log(LogStatus.PASS, 'Clicked on Save As ')
		extentTest.log(LogStatus.PASS, 'Entered profile name -  '+proName)
		extentTest.log(LogStatus.PASS, 'Profile Creation Option Selected - '+ProfileType)
		def isProfilePersentProCan = WebUI.verifyElementPresent(LeftNavAppIdentifier, 3,FailureHandling.CONTINUE_ON_FAILURE)
		if(isProfilePersentProCan)
		{
			extentTest.log(LogStatus.PASS, 'Profile not created - '+ proName)
		}
		else
		{

			extentTest.log(LogStatus.PASS, 'Profile not created - '+ proName)

		}

	}
	else
	{
		if(ProfileType.equals('Duplicate'))
		{
			WebUI.click(findTestObject('Object Repository/NewJobPage/button_Cancel'))

		}
		else{

			extentTest.log(LogStatus.PASS, 'Clicked on Save As ')
			extentTest.log(LogStatus.PASS, 'Entered profile name -  '+proName)
			WebUI.click(findTestObject('NewJobPage/button_Save'))
			WebUI.delay(3)
			WebUI.waitForElementPresent(LeftNavAppIdentifier, 5)

			def isProfilePersent = WebUI.verifyElementPresent(LeftNavAppIdentifier, 5)

			if (isProfilePersent) {
				extentTest.log(LogStatus.PASS, 'Verified newly created profile  -  '+proName)

				KeywordUtil.markPassed('Profile Created')
			} else {
				KeywordUtil.markFailed('Profile Creation Failed')
			}


		}
	}



	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}


}catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	String p =TestCaseName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,ex)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))

	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	String p =TestCaseName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,e)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))

	KeywordUtil.markFailed('ERROR: '+ e)

}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}
