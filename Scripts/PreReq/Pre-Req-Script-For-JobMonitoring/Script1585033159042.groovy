import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : 'pbsadmin', ('password') : 'pbsadmin'],FailureHandling.STOP_ON_FAILURE)
String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

String TestCaseNameExtent = TestCaseName

def extentTest = extent.startTest(TestCaseNameExtent)

def filePathShellRun = RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh'

def newFPSHR = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathShellRun)

def filePathShellFail = RunConfiguration.getProjectDir() + '/Upload/JobFiles/bar.fem'

def newFPSHFail = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathShellFail)

def filePathOpti = RunConfiguration.getProjectDir() + '/Upload/JobFiles/box.fem'

def newFPOpti = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathOpti)

def f1 = RunConfiguration.getProjectDir() + '/Upload/JobFiles/CUBE_0000.rad'

def p1 = CustomKeywords.'generateFilePath.filePath.getFilePath'(f1)

try {
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),
			10)

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	extentTest.log(LogStatus.PASS, 'Navigated Job Tab')

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)

	WebUI.click(newAppObj)

	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

	//	WebUI.doubleClick(newAppObj)


	def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()
	def location=navLocation
	println('##################################################################')
	println (location)
	println('##################################################################')

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))


	WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

	WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

	WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

	WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

	WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), 'ForJM')
	WebUI.click(findTestObject('FilesPage/btn_Save'))
	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_Refresh'))

	WebUI.delay(2)

	location=location+'/ForJM'

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))


	def jobid

	jobid = CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(newFPSHR, AppName)

	jobid=CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(newFPSHFail, AppName)
	jobid=CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(newFPOpti, 'Optistruct')
	jobid=CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(p1, 'RADIOSS-SMP')
	println('==============================================================')

	println(' last jobid ---- ' + jobid)

	println('==============================================================')

	String[] splitAddress = jobid.split('\\.')

	println('==============================================================')

	println(' hostname ---- ' + (splitAddress[1]))

	println('==============================================================')

	GlobalVariable.G_PBSHostName = (splitAddress[1])

	println('********************************************')

	extentTest.log(LogStatus.PASS, 'hostname  - ' + GlobalVariable.G_PBSHostName)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	CustomKeywords.'preReq.jobMonitorigColFilter.addColumn'(extentTest)

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
