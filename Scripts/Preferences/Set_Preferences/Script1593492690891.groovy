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

String TCName=TestCaseName

def extentTest = extent.startTest(TCName)

def viewIconTilePresent

def viewIconListPresent

TestObject newFileObj

WebUI.delay(2)

try {

	switch(userChoice)
	{

		case 'verify theme colour':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.verifyElementPresent(findTestObject('Preferences/ThemeColour'),5)
			extentTest.log(LogStatus.PASS, 'Verify theme colur')

			break

		case 'verify font size':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.verifyElementPresent(findTestObject('Preferences/Fontsize'),5)
			extentTest.log(LogStatus.PASS, 'Verify font size')

			break

		case 'Diagnosis':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')


			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.click(findTestObject('Preferences/Reset'))
			extentTest.log(LogStatus.PASS, 'Reset all')

			WebUI.click(findTestObject('Preferences/Confirm_button'))
			extentTest.log(LogStatus.PASS, 'Click on yes button')


			WebUI.verifyElementPresent(findTestObject('Preferences/Reset_popup'), 2)

		case 'Remote Session':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')
			WebUI.delay(3)

			WebUI.verifyElementPresent(findTestObject('Preferences/Remotesession'), 5)
			extentTest.log(LogStatus.PASS, 'Verify Remote session resolution')

			break

		case 'Profile':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.click(findTestObject('Preferences/Profilename'))
			WebUI.setText(findTestObject('Preferences/Profilename'),'samishtha')
			extentTest.log(LogStatus.PASS, 'Add profile name')

			WebUI.click(findTestObject('Preferences/Profile_email'))
			WebUI.setText(findTestObject('Preferences/Profile_email'),'samishtha.taneja@altair.com')
			extentTest.log(LogStatus.PASS, 'Add email id')

			WebUI.click(findTestObject('Preferences/Back'))
			extentTest.log(LogStatus.PASS, 'Click on back')

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.click(findTestObject('Preferences/Profile'))
			extentTest.log(LogStatus.PASS, 'Click on profile')



		case 'Notifications On':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.verifyElementPresent(findTestObject('Preferences/Tickmark'),5)

			WebUI.click(findTestObject('Preferences/Back'))
			extentTest.log(LogStatus.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'


			def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)

			if (isElemenetPresent)
			{
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
				extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
			}


			WebUI.delay(2)
			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)

			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
			extentTest.log(LogStatus.PASS, "Clicked on New File Button" )


			WebUI.click(findTestObject('FilesPage/ListItem_File'))

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))


			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), 'new.txt')

			extentTest.log(LogStatus.PASS, "Enterted File Name to create " )
			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )

			WebUI.verifyElementPresent(findTestObject('Preferences/Notification_popup'), 2)

			break


		case 'Notifications Off':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.click(findTestObject('Preferences/Tickmark'))

			WebUI.click(findTestObject('Preferences/Back'))
			extentTest.log(LogStatus.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'


			def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)

			if (isElemenetPresent)
			{
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
				extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
			}


			WebUI.delay(2)
			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)

			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
			extentTest.log(LogStatus.PASS, "Clicked on New File Button" )


			WebUI.click(findTestObject('FilesPage/ListItem_File'))

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))


			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)

			extentTest.log(LogStatus.PASS, "Enterted File Name to create " )
			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )


			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			extentTest.log(LogStatus.PASS, "Opened Notification Panel" )
			WebUI.delay(2)
			result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_FileCreation'),5)
			println("notification status - "+result)
			extentTest.log(LogStatus.PASS, "Verified notification for operation - ")
			break

		case 'Job Submission form open':

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

			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals',
					AppName, true)

			WebUI.click(newAppObj)

			extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

		//	WebUI.doubleClick(newAppObj)
			WebUI.delay(2)

			def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),
					5)

			if (errorPanel) {
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
			}

			WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))

			WebUI.delay(2)

			WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

			WebUI.delay(3)

			if (ExecMode.equals('LocalForm')) {
				newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			} else {
				if (ExecMode.equals('Local')) {
					newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
				} else {
					if (TestCaseName.contains('ShortCut')) {
						ExecMode = 'ShortCut'

						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
					} else {
						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
					}
				}

				WebUI.click(newFileObj)

				WebUI.rightClick(newFileObj)

				extentTest.log(LogStatus.PASS, 'Right Clicked on Input file ' + InputFile)

				WebUI.delay(2)

				String idForCntxtMn = 'Add as ' + FileArg

				TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
						'id', 'equals', idForCntxtMn, true)

				WebUI.click(newRFBContextMnOption)

				extentTest.log(LogStatus.PASS, 'Clicked on context menu - ' + idForCntxtMn)
			}

			def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),
					10)

			if (submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

				extentTest.log(LogStatus.PASS, 'Clicked on Submit Button ')
			}

			WebUI.verifyElementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)
			extentTest.log(LogStatus.PASS, 'Verify submit button present')

			break

		case 'Job Submission form close':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')
			WebUI.delay(2)

			WebUI.click(findTestObject('Preferences/Job_submission_tickmark'))
			extentTest.log(LogStatus.PASS, 'Enable tick mark')

			WebUI.click(findTestObject('Preferences/Back'))
			extentTest.log(LogStatus.PASS, 'Click on back')

			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals',
					AppName, true)

			WebUI.click(newAppObj)

			extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

		//	WebUI.doubleClick(newAppObj)
			WebUI.delay(2)

			def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),
					5)

			if (errorPanel) {
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
			}

			WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))

			WebUI.delay(2)

			WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

			WebUI.delay(3)

			if (ExecMode.equals('LocalForm')) {
				newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			} else {
				if (ExecMode.equals('Local')) {
					newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
				} else {
					if (TestCaseName.contains('ShortCut')) {
						ExecMode = 'ShortCut'

						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
					} else {
						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
					}
				}

				WebUI.click(newFileObj)

				WebUI.rightClick(newFileObj)

				extentTest.log(LogStatus.PASS, 'Right Clicked on Input file ' + InputFile)

				WebUI.delay(2)

				String idForCntxtMn = 'Add as ' + FileArg

				TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
						'id', 'equals', idForCntxtMn, true)

				WebUI.click(newRFBContextMnOption)

				extentTest.log(LogStatus.PASS, 'Clicked on context menu - ' + idForCntxtMn)
			}

			def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),
					10)

			if (submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

				extentTest.log(LogStatus.PASS, 'Clicked on Submit Button ')
			}

			WebUI.verifyElementPresent(findTestObject('NewJobPage/AppList_ShellScript'), 5)
			extentTest.log(LogStatus.PASS, 'Verify app def is present')

			break

		case 'Hidden Files Enable':

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('Preferences/Preference'))
			extentTest.log(LogStatus.PASS, 'Click on preference')

			TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
					preference, true)
			WebUI.click(prefer)
			extentTest.log(LogStatus.PASS, 'Click on preference')

			WebUI.click(findTestObject('Preferences/Tickmark'))
			extentTest.log(LogStatus.PASS, 'Enable hidden items')

			WebUI.click(findTestObject('Preferences/Back'))
			extentTest.log(LogStatus.PASS, 'Click on back')


			'Navigate to Files Tab\r\n'


			def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)

			if (isElemenetPresent)
			{
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
				extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
			}

			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			def location='/stage/'+GlobalVariable.G_userName

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')

			WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)


			println('.hidden')

			WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), '.hiddenfile')
			extentTest.log(LogStatus.PASS, 'Looking for file to perfrom operation - ')

			WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))

			extentTest.log(LogStatus.PASS, 'Clicked on File')



			break


		case 'Hidden Files Disable':
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


			'Navigate to Files Tab\r\n'


			def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)

			if (isElemenetPresent)
			{
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
				extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
			}

			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			def location='/stage/'+GlobalVariable.G_userName

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')


		/*
		 WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		 WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		 println('.hidden')
		 WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), '.hidden')
		 extentTest.log(LogStatus.PASS, 'Looking for file to perfrom operation - ')
		 WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		 extentTest.log(LogStatus.PASS, 'Clicked on File')
		 prefer
		 */
			WebUI.verifyElementPresent(findTestObject('Preferences/HiddenFile'), 5)

			break

		/*	 
		 case 'Pagination':
		 WebUI.click(findTestObject('Preferences/Profiletab'))
		 extentTest.log(LogStatus.PASS, 'Click on profile tab')
		 WebUI.click(findTestObject('Preferences/Preference'))
		 extentTest.log(LogStatus.PASS, 'Click on preference')
		 TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
		 preference, true)
		 WebUI.click(prefer)
		 extentTest.log(LogStatus.PASS, 'Click on preference')
		 break
		 case 'Tail Frequency':
		 WebUI.click(findTestObject('Preferences/Profiletab'))
		 extentTest.log(LogStatus.PASS, 'Click on profile tab')
		 WebUI.click(findTestObject('Preferences/Preference'))
		 extentTest.log(LogStatus.PASS, 'Click on preference')
		 TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',
		 preference, true)
		 WebUI.click(prefer)
		 extentTest.log(LogStatus.PASS, 'Click on preference')
		 break
		 */

		case 'Hidden Files Disable':

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


			'Navigate to Files Tab\r\n'


			def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)

			if (isElemenetPresent)
			{
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
				extentTest.log(LogStatus.PASS, "Navigated to Files Tab" )
			}

			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			def location='/stage/'+GlobalVariable.G_userName

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')


		/*
		 WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		 WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		 println('.hidden')
		 WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), '.hidden')
		 extentTest.log(LogStatus.PASS, 'Looking for file to perfrom operation - ')
		 WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		 extentTest.log(LogStatus.PASS, 'Clicked on File')
		 prefer
		 */
			WebUI.verifyElementNotPresent(findTestObject('Preferences/HiddenFile'), 5)

			break

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
			extentTest.log(LogStatus.INFO, 'Clicked on job with state  - Completed')

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
			WebUI.verifyElementPresent(findTestObject('JobMonitoringPage/OutputFolder_File'), 5)

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


