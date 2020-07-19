import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot
import java.awt.event.KeyEvent

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


String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus;

String TCName = TestCaseName+' - '+proName
def extentTest = extent.startTest(TCName)
Robot rob = new Robot()

WebUI.delay(2)
try
{
	WebUI.delay(3)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

	WebUI.delay(2)


	TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
			'equals', 'Tile View', true)

	TestObject viewIconList = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
			'equals', 'List View', true)

	viewIconTilePresent = WebUI.waitForElementPresent(viewIconTile, 3, FailureHandling.CONTINUE_ON_FAILURE)

	viewIconListPresent = WebUI.waitForElementPresent(viewIconList, 3, FailureHandling.CONTINUE_ON_FAILURE)

	println('viewIconTilePresent - ' + viewIconTilePresent)

	println('viewIconListPresent - ' + viewIconListPresent)

	if (viewIconListPresent) {
		WebUI.click(viewIconList)
		extentTest.log(LogStatus.PASS, 'Changed View to ListView')
		WebUI.delay(2)
	}

	WebUI.delay(2)

	if (TestCaseName.contains('tile view'))
	{

		WebUI.click(viewIconTile)
		extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' + TestCaseName)
		WebUI.delay(2)
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',
				fileName, true)
	}
	else {
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
				fileName, true)
	}



	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def folderPath='/stage/'+GlobalVariable.G_userName+'/ForProfiles/InputDeck'
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),folderPath)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to - '+folderPath)


	WebUI.setText(findTestObject('JobSubmissionForm/textBx_file_filter'), fileName)
	WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))

	WebUI.delay(2)

	WebUI.click(newFileObj)
	WebUI.rightClick(newFileObj)
	WebUI.delay(2)

	extentTest.log(LogStatus.PASS, 'Right Clicked on file  - '+fileName)
	String proElement=AppName+'-'+proName

	println("sub menu item -"+proElement)

	TestObject newProObj=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/ContextMn_Item_Optistruct-NewPro'), 'text', 'contains', proElement, true)


	WebUI.click(findTestObject('FilesPage/ContextMn_Process With'))

	WebUI.delay(3)
	extentTest.log(LogStatus.PASS, 'Clicked on Process With ')

	/*	if(TestCaseName.contains('Sort Order'))
	 {
	 String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	 WebUI.takeScreenshot(screenShotPath)
	 }
	 */
	def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newProObj,10)

	println("sub menu present - "+ isElemenetPresent)
	if (isElemenetPresent)
	{
		WebUI.delay(5)
		WebUI.click(newProObj)
		extentTest.log(LogStatus.PASS, 'Clicked on - '+proElement)
	}
	else
	{
		extentTest.log(LogStatus.PASS, 'Profile Conetext-menu item not found - '+proElement)
	}


	if(TestCaseName.contains('Incompelete'))
	{
		WebUI.delay(2)
		WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))
		rob.keyPress(KeyEvent.VK_BACK_SPACE)
		rob.keyRelease(KeyEvent.VK_BACK_SPACE)
		rob.keyPress(KeyEvent.VK_2)
		rob.keyRelease(KeyEvent.VK_2)
		def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),10)
		if (submitBtn) {
			WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
			extentTest.log(LogStatus.PASS, 'Clicked on Submit Button ')
		}
	}
	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

	extentTest.log(LogStatus.PASS, 'Verified Job Submission Notification')

	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

	String[] splitAddress = jobText.split(' ')

	def len = splitAddress[2].length()

	def toget = splitAddress[2].substring(1, len - 1)

	println('********************************************')

	println(toget)

	println('********************************************')


	extentTest.log(LogStatus.PASS, 'Job ID  - '+toget)

	if (GlobalVariable.G_Browser == 'Edge')
	{
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}

}catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)

}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}

