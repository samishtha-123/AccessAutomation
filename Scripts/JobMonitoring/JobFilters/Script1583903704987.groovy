
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus

import internal.GlobalVariable as GlobalVariable

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

def isElementPresentRight

def isElementPresentDown

WebUI.delay(2)

try {
    WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

    WebUI.delay(2)
	def isFilterPresent= CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'),5)
	if(isFilterPresent)
	{
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'))
		extentTest.log(LogStatus.PASS, 'Clicked on filter delete icon' )
		WebUI.refresh()
	}

    WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

    TestObject newJobFilterCategoryDown = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjFilterCategoryIdentifierDown'(
        FilterCategory)

    TestObject newJobFilterCategoryRight = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjFilterCategoryIdentifierRight'(
        FilterCategory)
	WebUI.delay(2)

    TestObject newJobFilterCategory = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Title_FilterCategory'), 
        'text', 'equals', FilterTitle, true)
	WebUI.delay(2)

    TestObject newJobFilterValue = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Title_FilterCategory'), 
        'text', 'equals', FilterValue, true)

    isElementPresentDown = WebUI.waitForElementPresent(newJobFilterCategoryDown, 3, FailureHandling.CONTINUE_ON_FAILURE)

    isElementPresentRight = WebUI.waitForElementPresent(newJobFilterCategoryRight, 3, FailureHandling.CONTINUE_ON_FAILURE)

    println('**************************')

    println(isElementPresentDown)

    println(isElementPresentRight)

    println('**************************')

    WebUI.delay(4)
	WebUI.scrollToElement(newJobFilterCategory, 3)

    if (isElementPresentDown) {
        println('down')

        WebUI.click(newJobFilterValue)
    }
    
    if (isElementPresentRight) {
        println('right')

        WebUI.click(newJobFilterCategory)

        extentTest.log(LogStatus.PASS, 'Selected filter category ' + FilterTitle)

        WebUI.delay(2)

        WebUI.click(newJobFilterValue)

        extentTest.log(LogStatus.PASS, 'Selected filter value ' + FilterValue)
    }
    
	println("AllJobsUser -- "+AllJobsUser)
    if (TestCaseName.contains('AllJobs')) {
		
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RadioBtn_AllJobs'))
		WebUI.delay(1)
		result =CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobByUser'(katalonWebDriver, dataAttribute, FilterValue, 
            extentTest, AllJobsUser)
		
       
    } 
	if (TestCaseName.contains('MyJobs')) {
		
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RadioBtn_MyJobs'))
		WebUI.delay(1)
		result =CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobByUser'(katalonWebDriver, dataAttribute, FilterValue,
			extentTest, AllJobsUser)
		
	   
	}
	else {
        result = CustomKeywords.'operations_JobsModule.GetJobRowDetails.newGrid'(katalonWebDriver, dataAttribute, FilterValue, 
            extentTest)
    }
    
    println(('==================' + result) + '==========================')

    if (result) {
        extentTest.log(LogStatus.FAIL, 'Job Not Filtered for ' + FilterValue)
    } else {
        extentTest.log(LogStatus.PASS, ('Job  Filtered for ' + FilterValue) + ' Jobs')
    }
    WebUI.delay(2)
    if (GlobalVariable.G_Browser == 'IE') {
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

