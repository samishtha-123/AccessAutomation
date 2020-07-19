import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement as RemoteWebElement
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
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

WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()

// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)
def result
TestObject newFileObj

try {
    def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 
        10)

    if (jobsTab) {
        WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
    }
    
    extentTest.log(LogStatus.PASS, 'Navigated Jobs Tab')

    WebUI.delay(2)

    TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', 
        AppName, true)

    WebUI.click(newAppObj)

    extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

    //	WebUI.doubleClick(newAppObj)
    WebUI.delay(2)

    def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'), 
        5)

    if (errorPanel) {
        WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
    }
    
    WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))

    WebUI.delay(2)

    CustomKeywords.'operations_JobsModule.JobSubmissions.JSAllFileds'(ToChange, ChangeValue, extentTest)

    WebUI.delay(2)

    WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

    WebUI.delay(3)

    if (ExecMode.equals('LocalForm')) {
        newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
    } else {
        if (ExecMode.equals('Local')) {
            newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
        } else {
            if (TestCaseName.contains('ShortCut')) {
                ExecMode = 'ShortCut'

                newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
            } else {
                newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
            }
        }
        
        WebUI.click(newFileObj)

        WebUI.rightClick(newFileObj)

        extentTest.log(LogStatus.PASS, 'Right Clicked on Input file ' + InputFile)

        WebUI.delay(2)

        String idForCntxtMn = 'Add as ' + FileArg

        TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'), 
            'id', 'equals', idForCntxtMn, true)

        WebUI.click(newRFBContextMnOption)

        extentTest.log(LogStatus.PASS, 'Clicked on context menu - ' + idForCntxtMn)
    }
    
    def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'), 
        10)

    if (submitBtn) {
        WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

        extentTest.log(LogStatus.PASS, 'Clicked on Submit Button ')
    }
    
    WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

    def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

    extentTest.log(LogStatus.PASS, 'Notification Generated')

    println('Job Text = ' + jobText)

    String[] splitAddress = jobText.split(' ')

    def len = (splitAddress[2]).length()

    def toget = (splitAddress[2]).substring(1, len - 1)

    GlobalVariable.G_JobID = toget

    println('********************************************')

    println(GlobalVariable.G_JobID)

    extentTest.log(LogStatus.PASS, 'Job ID - ' + GlobalVariable.G_JobID)

    println('********************************************')

    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    WebUI.delay(3)

    String myXpath = '//a[@id=\'mon_recent_jobname_label\']'

    println(myXpath)

    WebElement LS = katalonWebDriver.findElement(By.xpath(myXpath))

    WebUI.delay(3)

    RemoteWebElement ele = LS

    String myText = ele.getText()

    println('********************************************')

    println('LSJobID---- ' + myText)

    println('********************************************')

    extentTest.log(LogStatus.PASS, 'Job ID from LastSubmitted Link- ' + myText)

    if (TestCaseName.contains('Details')) {
        TestObject jobLink = CustomKeywords.'buildTestObj.CreateJobSubmissionObjs.myJobLinkRow'(GlobalVariable.G_JobID)
        WebUI.click(jobLink)
		TestObject jobTitle =CustomKeywords.'buildTestObj.CreateTestObj.myJobFromLastSubmitted'()
		result=WebUI.verifyElementPresent(jobTitle,5)
	
		if (result) {
			extentTest.log(LogStatus.PASS, 'Job ID - Form LastSubmitted matched the Job Id on details page')
		} else {
			extentTest.log(LogStatus.FAIL, 'Job ID - Form LastSubmitted not matched the Job Id on details page')
		}
		
    }
    
	if(TestCaseName.contains('Match'))
	{
    if (toget.equals(myText)) {
        extentTest.log(LogStatus.PASS, 'Job ID - from notofication and Form LastSubmitted link on JM page Match ')
    } else {
        extentTest.log(LogStatus.FAIL, 'Job ID - from notofication and Form LastSubmitted link on JM page Do Not Match ')
    }
	}
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



