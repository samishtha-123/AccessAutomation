package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class executeJobAction_JobDetails_Topmenu {

	@Keyword
	def perfromJobAction(String Action , String TestCaseName , extentTest) {
		def isNotoficationPresent
		boolean result=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		WebUI.delay(3)

		switch (Action) {
			case 'job_detail_download_btn':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Jobdetails_topmenu'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(2)
				TestObject jobTitle=(new buildTestObj.CreateTestObj()).myJobTitleIndentifier()
				def jobID=WebUI.getText(jobTitle)
				String[] splitAddress = jobID.split('\\.')
				GlobalVariable.G_JobIdFromDetails=splitAddress[0]
				println GlobalVariable.G_JobIdFromDetails
				extentTest.log(LogStatus.PASS, 'job id from details page '+ GlobalVariable.G_JobIdFromDetails)
				result=true
				break



			case 'job_detail_resubmit_btn':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Jobdetails_topmenu'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(2)
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

				return result
				break

			case 'job_detail_terminate_btn':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Jobdetails_topmenu'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

				WebUI.delay(2)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(LogStatus.PASS, 'terminating job  ')
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
				isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobTerminate'), 5)
				println("notification status - "+isNotoficationPresent)
				extentTest.log(LogStatus.PASS, 'Verified notification')
				result=isNotoficationPresent

				result=true
				break

			case 'job_detail_upload_btn':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Jobdetails_topmenu'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				result=true
				break

			case 'job_detail_delete_btn':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Jobdetails_topmenu'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(2)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(LogStatus.PASS, 'deleting job  ')
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(3)
				isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
				println("notification status - "+isNotoficationPresent)
				extentTest.log(LogStatus.PASS, 'Verified notification')
				result=isNotoficationPresent
				result=true
				break
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
