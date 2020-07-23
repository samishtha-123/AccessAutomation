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

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()

Capabilities caps = ((driver) as RemoteWebDriver).getCapabilities()

String browserName = caps.getBrowserName()

String browserVersion = caps.getVersion()

def Browser = GlobalVariable.G_Browser

String ReportFile = GlobalVariable.G_ReportName + '.html'

println(browserName)

println(browserVersion)

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, browserName, browserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)

try {
    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    not_run: WebUI.click(findTestObject('GenericObjects/btn_Yes'))

    WebUI.click(findTestObject('NewJobPage/button_New Job'))

    WebUI.delay(2)

    WebUI.doubleClick(findTestObject(AppName))

    WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

    if (Version == 'ShellScript') {
        println('no version for this app')
    } else {
        WebUI.click(findTestObject('JobSubmissionForm/versionDropDown'))

        TestObject newVerObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals', 
            Version, true)

        WebUI.click(newVerObj)
    }
    
    WebUI.click(findTestObject('JobSubmissionForm/Link_Server'))

    WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/Link_stage'), 3)

    WebUI.doubleClick(findTestObject('JobSubmissionForm/Link_stage'))

    if (TestCaseName.contains('ShortCut')) {
        WebUI.click(findTestObject('JobSubmissionForm/link_ShortCutFiles'))
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

    //WebUI.click(findTestObject('JobSubmissionForm/File_barfem_FF'))
    WebUI.click(findTestObject('JobSubmissionForm/button_Select'))

    //WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Lable_SelectedFile_barFem'), 5)
    WebUI.waitForElementClickable(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

    WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

    WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

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

    if (Browser == 'Edge') {
        WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
    }
    
    extentTest.log(LogStatus.PASS, 'Job Submission Done')
}
catch (StepErrorException e) {
    String screenShotPath = ('ExtentReports/' + TestCaseName) + '.png'

    WebUI.takeScreenshot(screenShotPath)

    extentTest.log(LogStatus.FAIL, e)
} 

extent.endTest(extentTest)

extent.flush()

