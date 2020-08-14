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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Capabilities as Capabilities
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

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

String screenShot='ExtentReports/'+TestCaseName+option+GlobalVariable.G_Browser+'.png'

TestObject newFileObj

WebUI.delay(2)

try {
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(LogStatus.PASS, 'Click on files tab')
	WebUI.delay(3)
	WebUI.click(findTestObject('FilesPage/Bookmark'))
	extentTest.log(LogStatus.PASS, 'Click on bookmark')


	switch (option){
		case 'create':

			WebUI.click(findTestObject('FilesPage/Createbookmark'))
			extentTest.log(LogStatus.PASS, 'Click on create new bookmark')
			WebUI.delay(2)

			WebUI.setText(findTestObject('Filespage/Enter Name'),bookMarkName)
			extentTest.log(LogStatus.PASS, 'Enter the Name of bookmark')


			WebUI.setText(findTestObject('Object Repository/FilesPage/TxtBx_BookMarkLocation'),location)
			extentTest.log(LogStatus.PASS, 'Enter the Path of bookmark')


			WebUI.click(findTestObject('FilesPage/Confirm_button'))
			extentTest.log(LogStatus.PASS, 'Click on ok button')

			WebUI.click(findTestObject('FilesPage/Bookmark'))
			extentTest.log(LogStatus.PASS, 'Click on bookmark')

			WebUI.click(findTestObject('FilesPage/Managebookmark'))
			extentTest.log(LogStatus.PASS, 'Click on manage bookmark')

			TestObject bookmark = WebUI.modifyObjectProperty(findTestObject('FilesPage/Check_Bookmark'), 'text','equals',bookMarkName , true)
			WebUI.click(bookmark)
			extentTest.log(LogStatus.PASS, 'To check the created bookmark')
			break;
			
		case'create read only':
		    WebUI.click(findTestObject('FilesPage/Createbookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on create new bookmark')
		    WebUI.delay(2)

		    WebUI.setText(findTestObject('Filespage/Enter Name'),bookMarkName)
		    extentTest.log(LogStatus.PASS, 'Enter the Name of bookmark')


		    WebUI.setText(findTestObject('Object Repository/FilesPage/TxtBx_BookMarkLocation'),location)
		    extentTest.log(LogStatus.PASS, 'Enter the Path of bookmark')


		    WebUI.click(findTestObject('FilesPage/Confirm_button'))
		    extentTest.log(LogStatus.PASS, 'Click on ok button')

		    WebUI.click(findTestObject('FilesPage/Bookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on bookmark')

		    WebUI.click(findTestObject('FilesPage/Managebookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on manage bookmark')

		    TestObject bookmark = WebUI.modifyObjectProperty(findTestObject('FilesPage/Check_Bookmark'), 'text','equals',bookMarkName , true)
		    WebUI.click(bookmark)
		    extentTest.log(LogStatus.PASS, 'To check the created bookmark')
			
			WebUI.verifyElementPresent(findTestObject('FilesPage/Error_popup'), 2)
			extentTest.log(LogStatus.PASS, 'Verify error message')
			
		    break
		
		    
			
		case'create empty name':
		
		    WebUI.click(findTestObject('FilesPage/Createbookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on create new bookmark')
		    WebUI.delay(2)

		    WebUI.setText(findTestObject('Filespage/Enter Name'),bookMarkName)
		    extentTest.log(LogStatus.PASS, 'Enter the Name of bookmark')


		    WebUI.setText(findTestObject('Object Repository/FilesPage/TxtBx_BookMarkLocation'),location)
		    extentTest.log(LogStatus.PASS, 'Enter the Path of bookmark')


		    WebUI.click(findTestObject('FilesPage/Cancel_button'))
		    extentTest.log(LogStatus.PASS, 'Click on ok button')
			
			break
			
		case'manage bookmark invalid path':
		
		    WebUI.click(findTestObject('FilesPage/Createbookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on create new bookmark')
		    WebUI.delay(2)

		    WebUI.setText(findTestObject('Filespage/Enter Name'),bookMarkName)
		    extentTest.log(LogStatus.PASS, 'Enter the Name of bookmark')


		    WebUI.setText(findTestObject('Object Repository/FilesPage/TxtBx_BookMarkLocation'),location)
		    extentTest.log(LogStatus.PASS, 'Enter the Path of bookmark')


		    WebUI.click(findTestObject('FilesPage/Confirm_button'))
		    extentTest.log(LogStatus.PASS, 'Click on ok button')

		    WebUI.click(findTestObject('FilesPage/Bookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on bookmark')
			
			WebUI.rightClick(findTestObject('FilesPage/DeleteFolder_Bookmark'))
			WebUI.click(findTestObject('FilesPage/DeleteFolder'))
			
			WebUI.click(findTestObject('FilesPage/button_Yes'))
			extentTest.log(LogStatus.PASS, 'Click on yes button')

		    WebUI.click(findTestObject('FilesPage/Managebookmark'))
		    extentTest.log(LogStatus.PASS, 'Click on manage bookmark')
			
			TestObject bookmark = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_BookMark'), 'text','equals',location , true)
			WebUI.click(bookmark)
			extentTest.log(LogStatus.PASS, 'Clicked on created bookmark')
			
			WebUI.verifyElementPresent(findTestObject('FilesPage/InvalidPath_popup'), 2)
			extentTest.log(LogStatus.PASS, 'Verify invalid path given')
			
			break
			
			


		case 'remove':

			WebUI.click(findTestObject('FilesPage/Managebookmark'))
			extentTest.log(LogStatus.PASS, 'Click on manage bookmark')
			WebUI.delay(2)
			
			TestObject bookmark = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_BookMark'), 'text','equals',location , true)
			WebUI.click(bookmark)
			extentTest.log(LogStatus.PASS, 'Clicked on created bookmark')
			WebUI.delay(2)
			if (TestCaseName.contains("No")) {
				println("No")
				WebUI.click(findTestObject('Object Repository/Cancel_ModalPanel'))
				extentTest.log(LogStatus.PASS, 'Click on cancel button')


			}
			else {
				println("yes")

				WebUI.click(findTestObject('Object Repository/GenericObjects/btn_Yes'))
				extentTest.log(LogStatus.PASS, 'Click on remove button')
			}
		/*else
		 {
		 WebUI.click(findTestObject('FilesPage/Managebookmark'))
		 extentTest.log(LogStatus.PASS, 'Click on manage bookmark')
		 WebUI.click(findTestObject('FilesPage/SelectAll-BookMark'))
		 extentTest.log(LogStatus.PASS, 'Select all bookmarks')
		 WebUI.click(findTestObject('FilesPage/Confirm_button'))
		 extentTest.log(LogStatus.PASS, 'Click on confirm button')
		 WebUI.click(findTestObject('Object Repository/Cancel_ModalPanel'))
		 extentTest.log(LogStatus.PASS, 'Click on cancel button')
		 WebUI.click(findTestObject('FilesPage/Bookmark'))
		 extentTest.log(LogStatus.PASS, 'Click on bookmark')
		 WebUI.click(findTestObject('FilesPage/Managebookmark'))
		 extentTest.log(LogStatus.PASS, 'Click on manage bookmark')
		 WebUI.click(findTestObject('FilesPage/No data'))
		 extentTest.log(LogStatus.PASS, 'Check for no data')
		 }*/
			
			if (GlobalVariable.G_Browser == 'IE') {
				WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
			}
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

