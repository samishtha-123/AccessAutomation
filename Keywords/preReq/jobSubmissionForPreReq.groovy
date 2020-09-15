package preReq

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


public class jobSubmissionForPreReq {

	@Keyword
	def JSPreReq(String newFP , String AppName) {

		String App=AppName.toLowerCase()

		String x1="//span[@id='node-name-"
		String x2="']"
		String xpathAppIdentifier=x1+App+x2
		TestObject AppObj = new TestObject('objectName')
		AppObj.addProperty('xpath', ConditionType.EQUALS, xpathAppIdentifier)
		WebUI.click(AppObj)

		WebUI.delay(2)

		def errorPanel = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'), 5)
		if (errorPanel) {
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
		}


		WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
		WebUI.delay(2)
		WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))

		Robot rob = new Robot()
		rob.keyPress(KeyEvent.VK_BACK_SPACE)
		rob.keyRelease(KeyEvent.VK_BACK_SPACE)

		if(App.equals('radioss-smp')) {
			rob.keyPress(KeyEvent.VK_2)
			rob.keyRelease(KeyEvent.VK_2)
			rob.keyPress(KeyEvent.VK_9)
			rob.keyRelease(KeyEvent.VK_9)
			//def f1 = RunConfiguration.getProjectDir() + '/Upload/LAME_EQUERRE_0000.rad'
			def f2 = RunConfiguration.getProjectDir() + '/Upload/JobFiles/CUBE_0001.rad'
			def p2 = (new generateFilePath.filePath()).getFilePath(f2)

			WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'), p2)
			WebUI.click(findTestObject('JobSubmissionForm/RadioBtn_All Fields'))
			WebUI.scrollToElement(findTestObject('JobSubmissionForm/label_Queue'), 0)
			WebUI.click(findTestObject('WIP/div_workq'))
			TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals',
					'compute', true)

			WebUI.mouseOver(newQueueObj)
			WebUI.click(newQueueObj)

		}
		else {
			rob.keyPress(KeyEvent.VK_2)
			rob.keyRelease(KeyEvent.VK_2)
		}
		WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)
		WebUI.delay(3)
		WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'),newFP )
		String xpathJobFileIdentifier="//i[contains(@class,'remove-icon unity_close')]"
		TestObject jobFileObj = new TestObject('objectName')
		jobFileObj.addProperty('xpath', ConditionType.EQUALS, xpathJobFileIdentifier)

		def jobFileObjPresent =	(new customWait.WaitForElement()).WaitForelementPresent(jobFileObj, 10)

		for(int i =0;i<4;i++) {
				def submitBtn=(new customWait.WaitForElement()).WaitForelementPresent (findTestObject('JobSubmissionForm/button_Submit_Job'), 10)
				if(submitBtn) {
					WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
				}
		}
		WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
		def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

		String[] splitAddress = jobText.split('\\(')
		def JobID = splitAddress[1]
		String[] jobIdArr = JobID.split('\\)')
		def toget = (jobIdArr[0])
		return toget
	}
}
