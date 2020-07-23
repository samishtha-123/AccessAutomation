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

def Browser = GlobalVariable.G_Browser

WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('NewJobPage/button_New Job'))

WebUI.doubleClick(findTestObject(AppName))

WebUI.delay(2)

CustomKeywords.'funtionsForEdge.EdgeFunctions.uploadFileEdge'(findTestObject('JobSubmissionForm/Link_My Computer'), InputFile)

WebUI.delay(9)


/*
WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

println('Job Text = ' + jobText)

String[] splitAddress = jobText.split(' ')

def len = splitAddress[2].length()

def toget = splitAddress[2].substring(1, len - 1)

GlobalVariable.G_JobID = toget

println('********************************************')

println(GlobalVariable.G_JobID)

println('********************************************')

WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFileCreateNotification'('')
*/


println('********************************************')

println(GlobalVariable.G_JobID)

println('********************************************')

