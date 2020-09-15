package jobActions

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class executeJobAction {

	@Keyword
	def perfromJobAction(String Action , String TestCaseName , extentTest) {
		def isNotoficationPresent
		boolean result=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		WebUI.delay(3)

		switch (Action) {
			case 'View Details':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

				WebUI.delay(2)
				def jobID=WebUI.getText(findTestObject('JobDetailsPage/jobTitle'))
				String[] splitAddress = jobID.split('\\.')
				GlobalVariable.G_JobIdFromDetails=splitAddress[0]
				println GlobalVariable.G_JobIdFromDetails
				extentTest.log(LogStatus.PASS, 'job id from details page '+ GlobalVariable.G_JobIdFromDetails)
				result=true
				break

			case 'Delete':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(3)
				if (TestCaseName.contains("No")) {
					WebUI.click(findTestObject('GenericObjects/btn_No'))
					result=true
					extentTest.log(LogStatus.PASS, 'Not deleting job  ')
				}
				else {
					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
					WebUI.delay(2)
					extentTest.log(LogStatus.PASS, 'deleting job  ')
					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
					WebUI.delay(3)
					isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
					println("notification status - "+isNotoficationPresent)
					extentTest.log(LogStatus.PASS, 'Verified notification')
					result=isNotoficationPresent
				}
				break

			case 'Terminate':
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
				break

			case 'Resubmit':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
				extentTest.log(LogStatus.PASS, 'resubmitted job  ')
				isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
				def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
				String[] splitAddress = jobText.split('\\(')
				def JobID = splitAddress[1]
				String[] jobIdArr = JobID.split('\\)')
				def toget = (jobIdArr[0])
				println ("job id from keyword - "+ toget)
				extentTest.log(LogStatus.PASS, 'Verified notification - new job id '+ toget)
				result=isNotoficationPresent

				break

			case 'Download':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				extentTest.log(LogStatus.PASS, 'Downloading job')
				result=true
				break

			case 'Move To Queue':
				println ("Form job actions - "+Action)
				WebUI.delay(2)
				WebUI.click(findTestObject('JobMonitoringPage/ContextMenu_MoveToQueue'))
				WebUI.delay(2)
				WebUI.click(findTestObject('JobMonitoringPage/CntxtMn-SubMn_accessQueue'))
				TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',	'Queued', true)
				WebUI.rightClick(newJobRow)
				WebUI.delay(1)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', 'View Details', true)
				WebUI.click(newJobAction)
				WebUI.delay(2)

				WebUI.click(findTestObject('JobDetailsPage/JobDetailsLink_Details'))
				extentTest.log(LogStatus.PASS,"Navigated to Details Tab")
				WebUI.click(findTestObject('JobDetailsPage/TextBx_DetailsFilter'))

				WebUI.setText(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), 'queue name')
				WebUI.click(findTestObject('JobDetailsPage/Detail_QueueName'))
				TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobDetailsPage/Detail_QueueName'), 'text', 'equals',	'accessQueue', true)
				println("---------- queuename "+WebUI.waitForElementPresent(newQueueObj, 4, FailureHandling.CONTINUE_ON_FAILURE))
			/*
			 def jobID=WebUI.getText(findTestObject('JobDetailsPage/jobTitle'))
			 String[] splitAddress = jobID.split('\\.')
			 GlobalVariable.G_JobIdFromDetails=splitAddress[0]
			 println GlobalVariable.G_JobIdFromDetails
			 */extentTest.log(LogStatus.PASS, 'job id from details page '+ GlobalVariable.G_JobIdFromDetails)
				result=true
				break


			default:
				break
		}
		return result
	}
}
