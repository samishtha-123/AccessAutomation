import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus as LogStatus

import internal.GlobalVariable as GlobalVariable
'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

String ReportFile = GlobalVariable.G_ReportName + '.html'
def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)


def LogStatus = com.relevantcodes.extentreports.LogStatus

def extentTest = extent.startTest(TestCaseName)

WebUI.delay(2)

try {

	WebUI.waitForElementVisible(findTestObject('GenericObjects/TitleLink_Files'), 5)

	WebUI.waitForElementClickable(findTestObject('GenericObjects/TitleLink_Files'), 5)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	WebUI.delay(6)
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



	if (userChoice.contains('Positive flow'))
	{
		if (TestCaseName.contains('tile view')) {
			WebUI.click(viewIconTile)
			extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)

			WebUI.delay(2)

			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

			WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

			WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), FolderName)
			extentTest.log(LogStatus.PASS, "Entered Folder Name to Create "+FolderName )

			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )

			String folder = FolderName.trim()

			WebUI.delay(2)

			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))


			WebUI.delay(2)

			TestObject createFolderNotification=CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFolderCreateNotification'(folder)

			extentTest.log(LogStatus.PASS, 'Created Folder  - ' + FolderName)



			'Verify notification'
			def notification = WebUI.verifyElementPresent(createFolderNotification, 5)

			println(notification)

			if(notification)
			{

				extentTest.log(LogStatus.PASS, FolderName + "- Created Folder and verified notification")

			}
			else
			{
				extentTest.log(LogStatus.PASS, FolderName+" - Not Created" )
				extentTest.log(LogStatus.FAIL)
			}


		} 
		else {

			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

			WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

			WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), FolderName)
			extentTest.log(LogStatus.PASS, "Entered Folder Name to Create "+FolderName )

			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )

			WebUI.delay(2)

			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))

			WebUI.delay(2)

			TestObject createFolderNotification=CustomKeywords.'buildTestObj.CreateTestObjFiles.myTestObjFolderCreateNotification'(FolderName)

			extentTest.log(LogStatus.PASS, 'Created Folder  - ' + FolderName)



			'Verify notification'
			def notification = WebUI.verifyElementPresent(createFolderNotification, 5)

			println(notification)

			if(notification)
			{

				extentTest.log(LogStatus.PASS, FolderName + "- Created Folder and verified notification")

			}
			else
			{
				extentTest.log(LogStatus.PASS, FolderName+" - Not Created" )
				extentTest.log(LogStatus.FAIL)
			}




		}
	}
	else if (userChoice.contains('Negative flow'))
	{


		if (TestCaseName.contains('tile view')) {
			WebUI.click(viewIconTile)
			extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)

			WebUI.delay(2)

			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

			WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

			WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), FolderName)
			extentTest.log(LogStatus.PASS, "Entered Folder Name to Create "+FolderName )
			
			WebUI.delay(3)

			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
			
			WebUI.delay(3)
			

		    WebUI.verifyElementPresent(findTestObject('FilesPage/SpecialChar_popup'), 3)
			
			extentTest.log(LogStatus.PASS, "Verified error pop-up" )
			
			WebUI.delay(4)
			WebUI.click(findTestObject('FilesPage/Close'))
			extentTest.log(LogStatus.PASS, "Clicked on cancel Button" )

		} else {

			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

			WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

			WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), FolderName)
			extentTest.log(LogStatus.PASS, "Entered Folder Name to Create "+FolderName )

			WebUI.click(findTestObject('FilesPage/btn_Save'))
			extentTest.log(LogStatus.PASS, "Clicked on Save Button" )
			WebUI.verifyElementPresent(findTestObject('FilesPage/SpecialChar_popup'), 3)
			
			extentTest.log(LogStatus.PASS, "Verified error pop-up" )
			
			WebUI.delay(4)
			WebUI.click(findTestObject('FilesPage/Close'))
			extentTest.log(LogStatus.PASS, "Click on close" )

		}

	}



	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)
}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)
}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}
