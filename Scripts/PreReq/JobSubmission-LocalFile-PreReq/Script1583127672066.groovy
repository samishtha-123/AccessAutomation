import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys


import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
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

String TestCaseNameExtent = TestCaseName + ' Using Local File'

def extentTest = extent.startTest(TestCaseNameExtent)


try {

	
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def folderPath='/stage/'+GlobalVariable.G_userName
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),folderPath)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to - '+folderPath)

	def filePathFT = (RunConfiguration.getProjectDir() + '/Upload/') + 'InputDeck.zip'
	def newFPFT=CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathFT)
	println(newFPFT)

	WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFPFT )
	extentTest.log(LogStatus.PASS, 'Uploading zip file - ToUpload.zip')
	
	WebUI.delay(5)
	WebUI.click(findTestObject('Object Repository/FilesPage/button_Yes'))
	extentTest.log(LogStatus.PASS, 'Clicked YES on Unzip on Upload confirmation pop-up')
	WebUI.delay(2)
	//add to check inputdeck folder is listed 

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

	if (Version.equals('ShellScript')) {
		println('no version for this app')
	} else {
		WebUI.scrollToElement(findTestObject('JobSubmissionForm/versionDropDown'), 3)

		WebUI.click(findTestObject('JobSubmissionForm/versionDropDown'))

		TestObject newVerObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text',
				'equals', Version, true)

		WebUI.click(newVerObj)
		extentTest.log(LogStatus.PASS, 'App Version Selected - '+Version)
	}

	WebUI.delay(2)

	WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

	WebUI.delay(3)

	def filePath = (RunConfiguration.getProjectDir() + '/Upload/') + InputFile

	def newFP = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePath)

	println(newFP)

	WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'), newFP)

	String x1 = "//span[contains(text(),'/stage/"+GlobalVariable.G_userName+"/"
	String x2="')]"
	String xpathJobFileIdentifier=x1+InputFile+x2
	TestObject jobFileObj = new TestObject('objectName')
	jobFileObj.addProperty('xpath', ConditionType.EQUALS, xpathJobFileIdentifier)

	def jobFileObjPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(jobFileObj, 10)

	if(jobFileObjPresent){
		extentTest.log(LogStatus.PASS, 'Uploading File to JS Form  - '+InputFile)
	}

	
	
	WebUI.click(findTestObject('WIP/RadioBtn_All Fields'))
	
	WebUI.click(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'))
	def stageOut='/stage/'+GlobalVariable.G_userName
	WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'), stageOut)

	
	
	def submitBtn=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'), 10)
	if(submitBtn)
	{
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
		extentTest.log(LogStatus.PASS, 'Clicked on Submit Button ')
	}
	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

	extentTest.log(LogStatus.PASS, 'Notification Generated')

	println('Job Text = ' + jobText)

	String[] splitAddress = jobText.split(' ')

	def len = splitAddress[2].length()

	def toget = splitAddress[2].substring(1, len - 1)

	GlobalVariable.G_JobID = toget

	println('********************************************')

	println(GlobalVariable.G_JobID)

	extentTest.log(LogStatus.PASS, 'Job ID - ' + GlobalVariable.G_JobID)

	extentTest.log(LogStatus.PASS, 'Job Submission Done for - '+ TestCaseNameExtent)

	println('********************************************')

	
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

