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
import java.awt.Robot
import java.awt.event.KeyEvent as KeyEvent



def Browser = GlobalVariable.G_Browser

WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

not_run: WebUI.click(findTestObject('GenericObjects/btn_Yes'))

WebUI.click(findTestObject('NewJobPage/button_New Job'))

WebUI.delay(2)

WebUI.doubleClick(findTestObject(AppName))

WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

WebUI.click(findTestObject('JobSubmissionForm/Link_Server'))


WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/Link_stage'), 3)

WebUI.delay(2)

WebUI.click(findTestObject('JobSubmissionForm/Link_stage'))

Robot robot = new Robot()
robot.keyPress(KeyEvent.VK_ENTER)
robot.keyRelease(KeyEvent.VK_ENTER)

WebUI.delay(2)
WebUI.click(findTestObject('JobSubmissionForm/Link_InputDeck'))

robot.keyPress(KeyEvent.VK_ENTER)
robot.keyRelease(KeyEvent.VK_ENTER)

WebUI.delay(2)


WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), InputFile)

WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))

WebUI.delay(3)

WebUI.click(findTestObject(FileElement))

WebUI.click(findTestObject('JobSubmissionForm/button_Select'))

//WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Lable_SelectedFile_barFem'), 5)

WebUI.waitForElementClickable(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

String[] splitAddress = jobText.split('\\(')

def JobID = splitAddress[1]

String[] jobIdArr = JobID.split('\\)')

toget = (jobIdArr[0])

GlobalVariable.G_JobID = toget

println('********************************************')

println(GlobalVariable.G_JobID)

println('********************************************')

if (Browser == 0)
{
	  WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
 }
