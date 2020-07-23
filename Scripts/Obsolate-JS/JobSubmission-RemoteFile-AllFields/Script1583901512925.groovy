import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent

import org.openqa.selenium.Keys as Keys

import com.fasterxml.jackson.core.Version
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

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

try {
    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    WebUI.delay(2)

    WebUI.delay(2)

    TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', 
        AppName, true)

    WebUI.click(newAppObj)


    WebUI.delay(2)

    if (Version.equals('ShellScript')) {
        println('no version for this app')
    } else {
        WebUI.scrollToElement(findTestObject('JobSubmissionForm/versionDropDown'), 3)

        WebUI.click(findTestObject('JobSubmissionForm/versionDropDown'))

        TestObject newVerObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 
            'equals', Version, true)

        WebUI.click(newVerObj)

        extentTest.log(LogStatus.PASS, 'Set value for Version - ' + Version)
    }
    
    WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))

    Robot rob = new Robot()

    rob.keyPress(KeyEvent.VK_BACK_SPACE)

    rob.keyRelease(KeyEvent.VK_BACK_SPACE)

    rob.keyPress(KeyEvent.VK_2)

    rob.keyRelease(KeyEvent.VK_2)

    //	WebUI.sendKeys(findTestObject('JobSubmissionForm/List_NCPUS'), '2')
    /*WebUI.setText(findTestObject('JobSubmissionForm/List_NCPUS'), '2')
WebUI.Wai
*/
    extentTest.log(LogStatus.PASS, 'Set value for NCPUS - ' + NCPUS)

    WebUI.click(findTestObject('WIP/RadioBtn_All Fields'))

    extentTest.log(LogStatus.PASS, 'Clicked on All fileds')

    WebUI.scrollToElement(findTestObject('WIP/label_Queue'), 0)

    WebUI.click(findTestObject('WIP/div_workq'))

    TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals', 
        QueueName, true)

    WebUI.mouseOver(newQueueObj)

    WebUI.click(newQueueObj)

    extentTest.log(LogStatus.PASS, 'Selected Queue - ' + QueueName)

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

    def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'))

    if (isElemenetPresent) {
        WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
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

    if (GlobalVariable.G_Browser == 'Edge') {
        WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
    }
    
    extentTest.log(LogStatus.PASS, 'Job Submission Done')

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

