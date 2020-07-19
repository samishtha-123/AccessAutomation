package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class executeJobAction_Icon {

	@Keyword
	def perfromJobAction(String Action , String TestCaseName , extentTest) {
		def isNotoficationPresent
		boolean result=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		WebUI.delay(3)

		switch (Action) {

			case 'delete_icon':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Icon_JObAction'), 'id', 'equals', Action, true)
				WebUI.click(newFileOp)
				WebUI.delay(3)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_JobDelete'),5)
				println("notification status - "+result)
				return result
				break


			case 'terminate_icon':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				if (TestCaseName.contains("No")) {
					println("No")
					WebUI.click(findTestObject('GenericObjects/btn_No'))
					result=true
					extentTest.log(LogStatus.PASS, 'Not terminating job  ')
				}
				else {

					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
					WebUI.delay(2)
					extentTest.log(LogStatus.PASS, 'terminating job  ')
					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
					WebUI.delay(2)
					isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobTerminate'), 5)
					println("notification status - "+isNotoficationPresent)
					extentTest.log(LogStatus.PASS, 'Verified notification')
					result=isNotoficationPresent
				}
				return result
				break

		}

	}
}