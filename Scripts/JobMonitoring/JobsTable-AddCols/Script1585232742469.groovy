import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
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

	extentTest.log(LogStatus.PASS, 'Navigated to Jobs Page')

	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/JM_column_selector_icon'))

	extentTest.log(LogStatus.PASS, 'Clicked on Gear Icon ')

	WebUI.waitForElementPresent(findTestObject('Object Repository/JobMonitoringPage/JM_Job_ColumnFilter'), 5)

	WebUI.setText(findTestObject('Object Repository/JobMonitoringPage/JM_Job_ColumnFilter'), ColName)

	extentTest.log(LogStatus.PASS, 'Searched column to be added/removed - ' + ColName)

	WebUI.delay(3)

	TestObject filterCB = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterCheckBox'),
			'id', 'equals', ColCheckBx, true)

	TestObject filterLabel = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterLable'),
			'id', 'equals', ColLable, true)

	def isElementChecked = WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)


	println(isElementChecked)

	extentTest.log(LogStatus.INFO, 'isElementChecked - ' + isElementChecked)
	result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)
	

	switch (userChoice) {
		case 'add':

			if (isElementChecked)
			 {
				println('Boxed checked')
				WebUI.click(filterLabel)
				/*extentTest.log(LogStatus.INFO, 'check 1')

				WebUI.click(filterLabel)
				extentTest.log(LogStatus.INFO, 'check 2')
*/

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
				extentTest.log(LogStatus.PASS, 'col already selected')
				result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)
				
			}
			else {
				println('in else block ')
				WebUI.click(filterLabel)
				extentTest.log(LogStatus.PASS, 'Checked the checkbox to select the column')
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
				extentTest.log(LogStatus.PASS, 'Clicked on Apply button')
				result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)
				
				}

			extentTest.log(LogStatus.INFO, 'result value - ' + result)
			if(result)
			{
				extentTest.log(LogStatus.PASS, 'Jobs col added - ' + ColName)
			}
			else{
				extentTest.log(LogStatus.FAIL, 'Jobs col not added - ' + ColName)

			}
			break
		case 'remove':
			isElementChecked = WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)

			if (isElementChecked) {
				WebUI.click(filterLabel)

				extentTest.log(LogStatus.PASS, 'Checked the checkbox to select the column')

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))

				extentTest.log(LogStatus.PASS, 'Clicked on Apply button')
			}
			else
			{
				println ("col is not added")
			}
			result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,
					extentTest)
			extentTest.log(LogStatus.INFO, 'result value - ' + result)
			if(result)
			{
				extentTest.log(LogStatus.FAIL, 'Jobs col not removed - ' + ColName)
			}
			else{
				extentTest.log(LogStatus.PASS, 'Jobs col removed - ' + ColName)

			}
			break

	}

	WebUI.delay(1)

	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	String p =TestCaseName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,ex)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))


	KeywordUtil.markFailed('ERROR: ' + e)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	String p =TestCaseName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,ex)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))


	KeywordUtil.markFailed('ERROR: ' + e)
}

catch (StepFailedException e) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	String p =TestCaseName+GlobalVariable.G_Browser+'.png'
	extentTest.log(LogStatus.FAIL,ex)
	extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))

	KeywordUtil.markFailed('ERROR: ' + e)
}


finally {
	extent.endTest(extentTest)

	extent.flush()
}

