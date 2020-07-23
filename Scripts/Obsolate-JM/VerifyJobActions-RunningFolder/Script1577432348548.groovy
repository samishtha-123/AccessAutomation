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

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

/*WebDriver driver = DriverFactory.getWebDriver()

Capabilities caps = ((driver) as RemoteWebDriver).getCapabilities()

String browserName = caps.getBrowserName()

String browserVersion = caps.getVersion()

def GlobalVariable.G_Browser = GlobalVariable.G_Browser*/

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus

def iselementPresent

try {
    WebUI.delay(2)

    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

    TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'id', 'equals', 
        'Running', true)

    WebUI.click(newJobFilter)

    WebUI.delay(2)

    TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals', 
        'Running', true)

    WebUI.rightClick(newJobRow)

    WebUI.delay(2)

    TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'), 'id', 
        'equals', 'View Details', true)

    WebUI.click(newJobAction)

    WebUI.delay(2)

    WebUI.delay(2)

    def jobID = WebUI.getText(findTestObject('JobDetailsPage/jobTitle'))

    String[] splitAddress = jobID.split('\\.')

    GlobalVariable.G_JobIdFromDetails = (splitAddress[0])

    println(GlobalVariable.G_JobIdFromDetails)


	
    WebUI.click(findTestObject('JobDetailsPage/a_Running Folder'))
	
    switch (Action) {
        case 'VerifyFiles':
            def extentTest = extent.startTest(TestCaseName)
            iselementPresent = WebUI.verifyElementPresent(findTestObject('JobDetailsPage/FileLink_pbsjob_env'), 2)
            println('pbsjob.env file exists - ' + iselementPresent)
            extentTest.log(LogStatus.INFO, 'pbsjob.env exists')
			TestObject eFile=CustomKeywords.'generateFilePath.filePath.getErrorFileName'(GlobalVariable.G_JobIdFromDetails)
			TestObject oFile=CustomKeywords.'generateFilePath.filePath.getOutFileName'(GlobalVariable.G_JobIdFromDetails)
			iselementPresent=WebUI.verifyElementPresent(oFile,2)
			println('out file exists - ' + iselementPresent)
			extentTest.log(LogStatus.INFO, 'out exists')
			iselementPresent=WebUI.verifyElementPresent(eFile,2)
			println('error file exists - ' + iselementPresent)
			extentTest.log(LogStatus.INFO, 'error exists')
			extentTest.log(LogStatus.PASS, 'Files Verified')
			extent.endTest(extentTest)
			break
			
        case 'Upload':
            def extentTest = extent.startTest(TestCaseName)
            def filePath = (RunConfiguration.getProjectDir() + '/Upload/') + InputFile
            def newFP = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePath)
            println(newFP)
            WebUI.uploadFile(findTestObject('JobDetailsPage/btn_upload_runningFolder'), newFP)
            WebUI.delay(9)
            TestObject newUploadedFile = WebUI.modifyObjectProperty(findTestObject('JobDetailsPage/FileLink_pbsjob_env'),'text', 'equals', InputFile, true)
            iselementPresent = WebUI.verifyElementPresent(newUploadedFile, 2)
            println('uploaded file exists - ' + iselementPresent)
            extentTest.log(LogStatus.PASS, 'uploaded file ')
            break
			
        case 'Download':
            def extentTest = extent.startTest(TestCaseName)
            TestObject newUploadedFile = WebUI.modifyObjectProperty(findTestObject('JobDetailsPage/FileLink_pbsjob_env'),'text', 'equals', InputFile, true)
            WebUI.rightClick(newUploadedFile)
            WebUI.click(findTestObject('JobDetailsPage/span_Download'))
            extentTest.log(LogStatus.PASS, 'downloaded file ')
            break
			
        case 'Terminate':
            def extentTest = extent.startTest(TestCaseName)
            WebUI.click(findTestObject('JobDetailsPage/button_Terminate'))
            WebUI.delay(2)
            WebUI.click(findTestObject('GenericObjects/btn_Yes'))
            extentTest.log(LogStatus.PASS, 'terminated ')
            break
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


    extent.flush()


}

