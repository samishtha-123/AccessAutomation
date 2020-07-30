import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus


import internal.GlobalVariable as GlobalVariable

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

ReportFile = (GlobalVariable.G_ReportName + '.html')

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

String TCName=TestCaseName+' through ContextMenu options'

def extentTest = extent.startTest(TCName)

def viewIconTilePresent

def viewIconListPresent

TestObject newFileObj

WebUI.delay(2)

try {
	
	switch(userChoice)
	{
	
	  
	     
	case 'Persistence Off':
	    
	    WebUI.click(findTestObject('Preferences/Profiletab'))
	    extentTest.log(LogStatus.PASS, 'Click on profile tab')

	    WebUI.click(findTestObject('Preferences/Preference'))
	    extentTest.log(LogStatus.PASS, 'Click on preference')

	    TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
	    preference, true)
	    WebUI.click(prefer)
	    extentTest.log(LogStatus.PASS, 'Click on preference')
		
		
		
		WebUI.click(findTestObject('Preferences/Back'))
		extentTest.log(LogStatus.PASS, 'Click on back')
		
		WebUI.delay(2)
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	
		TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',
				'Completed', true)
	
		WebUI.click(newJobFilter)
	
		WebUI.delay(2)
		extentTest.log(LogStatus.INFO, 'Clicked on job with state  - ')
	
		
		TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',	'Completed', true)
			WebUI.rightClick(newJobRow)
			
			
		
		WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))
		
		WebUI.waitForElementVisible(findTestObject('JobMonitoringPage/OutputFolder_File'), 5)
		
		WebUI.click(findTestObject('Preferences/Sessions'))
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
		WebUI.delay(2)
		WebUI.verifyElementPresent(findTestObject('Preferences/Appdef'), 5)
		extentTest.log(LogStatus.PASS, 'Verify app def is present')
	    break
		
	case 'Persistence On':
	    
	WebUI.click(findTestObject('Preferences/Profiletab'))
	extentTest.log(LogStatus.PASS, 'Click on profile tab')

	WebUI.click(findTestObject('Preferences/Preference'))
	extentTest.log(LogStatus.PASS, 'Click on preference')

	TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
	preference, true)
	WebUI.click(prefer)
	extentTest.log(LogStatus.PASS, 'Click on preference')
	
	WebUI.click(findTestObject('Preferences/Tickmark'))
	extentTest.log(LogStatus.PASS, 'Click on Tickmark')
	
	WebUI.click(findTestObject('2019.3/Application_name'))
	extentTest.log(LogStatus.PASS, 'Click on Apllication name')
	
	
	
	break
	
	   
		
	case 'RVS':
	    
	    WebUI.click(findTestObject('Preferences/Profiletab'))
	    extentTest.log(LogStatus.PASS, 'Click on profile tab')

	    WebUI.click(findTestObject('Preferences/Preference'))
	    extentTest.log(LogStatus.PASS, 'Click on preference')

	    TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
	    preference, true)
	    WebUI.click(prefer)
	    extentTest.log(LogStatus.PASS, 'Click on preference')
		
		WebUI.verifyElementPresent(findTestObject('Preferences/Autorefreshtime'), 5)
		extentTest.log(LogStatus.PASS, 'Verify autorefresh time')
	    
	    break
		    
	}
	 
	
	
	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TCName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(LogStatus.FAIL, ex)

	KeywordUtil.markFailed('ERROR: ' + e)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TCName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(LogStatus.FAIL, e)

	KeywordUtil.markFailed('ERROR: ' + e)
}
finally {
	extent.endTest(extentTest)

	extent.flush()


}


