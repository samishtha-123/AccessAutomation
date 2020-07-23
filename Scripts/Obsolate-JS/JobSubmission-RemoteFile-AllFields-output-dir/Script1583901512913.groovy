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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent


'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)

WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

WebUI.click(findTestObject('NewJobPage/button_New Job'))

WebUI.delay(2)

WebUI.doubleClick(findTestObject(AppName))

if (Version == 'ShellScript') {
    println('no version for this app')
} else {
    WebUI.click(findTestObject('JobSubmissionForm/versionDropDown'))

    TestObject newVerObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals', 
        Version, true)

    WebUI.click(newVerObj)
}

WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))

WebUI.setText(findTestObject('JobSubmissionForm/List_NCPUS'), NCPUS)

WebUI.click(findTestObject('WIP/RadioBtn_All Fields'))

WebUI.scrollToElement(findTestObject('WIP/label_Queue'), 0)

WebUI.click(findTestObject('WIP/div_workq'))

TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals', 
    QueueName, true)

WebUI.mouseOver(newQueueObj)

WebUI.click(newQueueObj)

//div[@id='submission_ncpus_field']//input[@class='rw-widget-input rw-input']
WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

WebUI.click(findTestObject('JobSubmissionForm/Link_Server'))

WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/Link_stage'), 3)

WebUI.delay(2)

WebUI.doubleClick(findTestObject('JobSubmissionForm/Link_stage'))

if (TestCaseName.contains('ShortCut')) {
    WebUI.doubleClick(findTestObject('JobSubmissionForm/link_ShortCutFiles'))
} else {
    WebUI.doubleClick(findTestObject('JobSubmissionForm/Link_InputDeck'))
}

WebUI.delay(3)

WebUI.waitForElementPresent(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 5)

WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))

WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), InputFile)

WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))

WebUI.delay(3)

TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals', 
    InputFile, true)

WebUI.click(newFileObj)

WebUI.click(findTestObject('JobSubmissionForm/button_Select'))

WebUI.click(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'))

WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'), '/stage/output')

def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'))

if (isElemenetPresent) {
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


WebUI.click(findTestObject('FilesPage/a_Files'))
WebUI.delay(3)
WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),"/stage/output")
Robot robot = new Robot()
robot.keyPress(KeyEvent.VK_ENTER)
robot.keyRelease(KeyEvent.VK_ENTER)
WebUI.delay(3)

String[] job = toget.split('\\.')

def s1=job[0]

TestObject eFile = CustomKeywords.'generateFilePath.filePath.getErrorFileName'(s1)

TestObject oFile = CustomKeywords.'generateFilePath.filePath.getOutFileName'(s1)

iselementPresent = WebUI.verifyElementPresent(oFile, 2)

println('out file exists - ' + iselementPresent)

extentTest.log(LogStatus.INFO, 'out exists')

iselementPresent = WebUI.verifyElementPresent(eFile, 2)

println('error file exists - ' + iselementPresent)

extentTest.log(LogStatus.PASS, 'Job Submission Done')

extent.endTest(extentTest)

extent.flush()



if (GlobalVariable.G_Browser == 'Edge') {
    WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
}

println('********************************************')

