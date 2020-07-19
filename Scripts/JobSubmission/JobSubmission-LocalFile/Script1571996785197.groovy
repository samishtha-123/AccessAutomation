import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

String TestCaseNameExtent = TestCaseName + ' Using Local File'

def extentTest = extent.startTest(TestCaseNameExtent)


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

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def location='/stage/'+GlobalVariable.G_userName+'/JSUploads/'

	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')

	def FolderEmptytext = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/FilesPage/Label_FolderEmpty'), 5)

	println(FolderEmptytext)

	if (FolderEmptytext) {

		println('FolderEmptytext')    }
	else {

		WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))

		def ele = WebUI.verifyElementClickable(findTestObject('FilesPage/FilesDelete_img'))

		println(ele)

		WebUI.click(findTestObject('FilesPage/FilesDelete_img'))

		WebUI.delay(2)

		WebUI.click(findTestObject('GenericObjects/btn_Yes'))

	}

	def filePath = (RunConfiguration.getProjectDir() + '/Upload/') + InputFile

	def newFP = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePath)

	println(newFP)

	WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP)
	extentTest.log(LogStatus.PASS, 'Uploading File to RFB - '+InputFile)

	def FileUploadClose = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'), 10)

	println("upload Notfication - "+FileUploadClose)

	TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',
			InputFile, true)



	def UploadedFile = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 10)
	println("uploaded file - "+UploadedFile)


	if (UploadedFile) {

		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'))    }
	else {


	}

	WebUI.delay(9)

	WebUI.click(newFileObj)

	WebUI.rightClick(newFileObj)

	extentTest.log(LogStatus.PASS, 'Right Clicked on Uploaded file ')
	WebUI.delay(2)

	String idForCntxtMn = 'Add as ' + FileArg

	TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
			'id', 'equals', idForCntxtMn, true)

	WebUI.click(newRFBContextMnOption)

	extentTest.log(LogStatus.PASS, 'Clicked on context menu - '+  idForCntxtMn)
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

