import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus

import internal.GlobalVariable as GlobalVariable

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

String TestCaseNameExtent = TestCaseName + ' Using Remote File - Through Context Menu'

def extentTest = extent.startTest(TestCaseNameExtent)

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
	CustomKeywords.'preReq.jobSubmissionForPreReq.JSAllFileds'(ToChange, ChangeValue, extentTest)
	
	WebUI.delay(2)
    WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

    if (TestCaseName.contains('ShortCut')) {
        WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
        def shortCutFileLocation = ('/stage/' + GlobalVariable.G_userName) + '/ShortCutFiles'
        extentTest.log(LogStatus.PASS, 'Navigating to - ' + shortCutFileLocation)
        WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), shortCutFileLocation)
        WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
    } else {
        WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))

        def fileLocation = ('/stage/' + GlobalVariable.G_userName) + '/InputDeck'

        WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), fileLocation)

        WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

        extentTest.log(LogStatus.PASS, 'Navigated to /stage/InputDeck in RFB ')
    }
    
    WebUI.delay(3)

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

    def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'), 
        10)

    if (isElemenetPresent) {
        WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

        extentTest.log(LogStatus.PASS, 'Clicked on Submit Buttton ')
    }
    
    WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

    def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

    extentTest.log(LogStatus.PASS, 'Notification Generated')

    String[] splitAddress = jobText.split('\\(')

    def JobID = splitAddress[1]

    String[] jobIdArr = JobID.split('\\)')

    toget = (jobIdArr[0])

    GlobalVariable.G_JobID = toget

    println('********************************************')

    println(GlobalVariable.G_JobID)

    extentTest.log(LogStatus.PASS, 'JobID - ' + GlobalVariable.G_JobID)

    println('********************************************')

    TestObject jobIdEle = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjJobRow'(GlobalVariable.G_JobID)

    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    WebUI.delay(2)

    String jobState = CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobState'(jobIdEle, extentTest)

    extentTest.log(LogStatus.PASS, 'Current Job State - ' + jobState)

    if (GlobalVariable.G_Browser == 'Edge') {
        WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
    }
    
    extentTest.log(LogStatus.PASS, 'Job Submission Done')
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

