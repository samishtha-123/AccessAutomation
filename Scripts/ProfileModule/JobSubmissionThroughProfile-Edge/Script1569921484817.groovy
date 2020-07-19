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
import org.openqa.selenium.By as By
import org.openqa.selenium.Point as Point
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.interactions.Action as Action
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement as WebElement
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent

WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

Robot robot = new Robot()


WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))


WebUI.click(findTestObject('NewJobPage/button_New Job'))

WebUI.delay(2)

WebUI.doubleClick(findTestObject('NewJobPage/Link_Optistruct'))

WebUI.click(findTestObject('NewJobPage/AppList_NewPro'))

WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

WebUI.click(findTestObject('JobSubmissionForm/Link_Server'))

WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/Link_stage'), 3)

WebUI.delay(2)

WebUI.click(findTestObject('JobSubmissionForm/Link_stage'))


robot.keyPress(KeyEvent.VK_ENTER)
robot.keyRelease(KeyEvent.VK_ENTER)

WebUI.delay(2)
WebUI.click(findTestObject('JobSubmissionForm/Link_InputDeck'))

robot.keyPress(KeyEvent.VK_ENTER)
robot.keyRelease(KeyEvent.VK_ENTER)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))

WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 'bar.fem')

WebUI.delay(3)

WebUI.click(findTestObject('JobSubmissionForm/File_barfem_FF'))

WebUI.click(findTestObject('JobSubmissionForm/button_Select'))

WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Lable_SelectedFile_barFem'), 5)

WebUI.waitForElementClickable(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

WebUI.waitForElementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))


String[] splitAddress = jobText.split("\\(")
def JobID = splitAddress[1]
String[] jobIdArr = JobID.split("\\)")
toget= jobIdArr[0]
GlobalVariable.G_JobID = toget
println('********************************************')
println(GlobalVariable.G_JobID )
println('********************************************')


WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)



