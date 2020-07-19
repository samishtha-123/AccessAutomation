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
import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)


String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

def extentTest = extent.startTest(TestCaseName)
def result 
WebUI.delay(2)
try
{
	WebUI.delay(2)
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	if (TestCaseName.contains('AllJobs')) {
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RadioBtn_AllJobs'))
		WebUI.delay(1)
		extentTest.log(LogStatus.PASS, 'All Jobs Filter Applied')
		WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
		//WebUI.setText(findTestObject('JobMonitoringPage/JM_SearchBox'),AllJobsUser)
		WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), AllJobsUser)
		//	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))

		if(GlobalVariable.G_Browser.equals('FireFox')) {
			WebUI.delay(5)
			extentTest.log(LogStatus.PASS, 'Waiting for jobs table to load on FireFox')
		}
	}
	
	TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',
			jobState, true)

	WebUI.click(newJobFilter)

	WebUI.delay(2)
	extentTest.log(LogStatus.INFO, 'Clicked on job with state  - ' + jobState)

	println jobState
	TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',	jobState, true)
	if(TestCaseName.contains('DoubleClick'))
	{
		WebUI.doubleClick(newJobRow)
	}
	else
	{
		WebUI.rightClick(newJobRow)
	}
	
			
	WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))
	
switch (folder) {

		case 'Running':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RunningFolder'))
			extentTest.log(LogStatus.PASS, 'Navigated to Running Folder')
			result=WebUI.verifyElementClickable(findTestObject('JobMonitoringPage/ErrMsg_NotJobOwner'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		case 'Output':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/OutputFolder'))
			extentTest.log(LogStatus.PASS, 'Navigated to Output Folder')
			result=WebUI.verifyElementClickable(findTestObject('JobMonitoringPage/ErrMsg_NotJobOwner'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
			case 'Input':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/InputFolder'))
			extentTest.log(LogStatus.PASS, 'Navigated to Inputjob_detail_download_btn Folder')
			result=WebUI.verifyElementClickable(findTestObject('JobMonitoringPage/ErrMsg_NotJobOwner'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
	}

	
	
	TestObject newJobAction=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Icon_JObAction'), 'id', 'equals', jobAction, true)
	result=WebUI.verifyElementClickable(newJobAction, FailureHandling.CONTINUE_ON_FAILURE)

		result=CustomKeywords.'operations_JobsModule.executeJobAction_topmenu.perfromJobAction'(jobAction,TestCaseName,extentTest)
	
			
		if(result)
		{
			extentTest.log(LogStatus.PASS, ' Verified job action - ' + jobAction + ' for job state '+ jobState)
		}	
		else
		{
			extentTest.log(LogStatus.FAIL,'some exception')
		}
		
		
		if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}

}

catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)

}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}

