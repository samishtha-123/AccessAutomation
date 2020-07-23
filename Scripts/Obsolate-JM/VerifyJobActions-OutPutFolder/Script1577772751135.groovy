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

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus


def iselementPresent

try {
	WebUI.delay(2)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'id', 'equals','Completed', true)

	WebUI.click(newJobFilter)

	WebUI.delay(2)

	TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals','Completed', true)

	WebUI.rightClick(newJobRow)

	WebUI.delay(2)

	TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'), 'id','equals', 'View Details', true)

	WebUI.click(newJobAction)

	WebUI.delay(2)

	WebUI.delay(2)

	def jobID = WebUI.getText(findTestObject('JobDetailsPage/jobTitle'))

	String[] splitAddress = jobID.split('\\.')

	GlobalVariable.G_JobIdFromDetails = (splitAddress[0])

	println(GlobalVariable.G_JobIdFromDetails)

	WebUI.click(findTestObject('JobDetailsPage/JobDetailsLink_Output'))


	def jobName=WebUI.getAttribute(findTestObject('JobDetailsPage/Link_JobName_xpath'), 'title')


	switch (Action) {
		case 'Download':
			def extentTest = extent.startTest(TestCaseName)
			WebUI.click(findTestObject('JobDetailsPage/button_Download'))
			WebUI.delay(4)
			CustomKeywords.'generateFilePath.filePath.downloadverification'(jobName)
			extentTest.log(LogStatus.INFO, 'Navigated to OutPut Tab')
			extentTest.log(LogStatus.PASS, 'clicked on download')
			extent.endTest(extentTest)
			break

		case 'Resubmit':
			def extentTest = extent.startTest(TestCaseName)
			WebUI.click(findTestObject('JobDetailsPage/button_Resubmit'))
			WebUI.delay(2)
			WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
			isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			String[] splitAddressR = jobText.split('\\(')
			def JobID = splitAddressR[1]
			String[] jobIdArr = JobID.split('\\)')
			def toget = (jobIdArr[0])
			println ("job id from keyword - "+ toget)
			result=isNotoficationPresent
			extentTest.log(LogStatus.PASS, 'resubmit')
			extent.endTest(extentTest)
			break

		case 'Delete':
			def extentTest = extent.startTest(TestCaseName)
			WebUI.click(findTestObject('JobDetailsPage/button_Delete'))
			if (TestCaseName.contains("No")) {
				WebUI.click(findTestObject('GenericObjects/btn_No'))
			}
			else {
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
				isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
				println("notification status - "+isNotoficationPresent)
				result=isNotoficationPresent
			}
			extentTest.log(LogStatus.PASS, 'delete')
			extent.endTest(extentTest)
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

