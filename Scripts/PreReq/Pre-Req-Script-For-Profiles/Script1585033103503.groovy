import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys


import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)

String ReportFile = GlobalVariable.G_ReportName + '.html'

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

String TestCaseNameExtent = TestCaseName

def extentTest = extent.startTest(TestCaseNameExtent)

def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()



try {


	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	WebUI.delay(2)


	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def folderPath1='/stage/'+GlobalVariable.G_userName
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),folderPath1)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to - '+folderPath1)
	
	WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)

	WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

	WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

	WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

	WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), 'ForProfiles')
	extentTest.log(LogStatus.PASS, "Entered Folder Name to Create - ForProfiles")

	WebUI.click(findTestObject('FilesPage/btn_Save'))
	extentTest.log(LogStatus.PASS, "Clicked on Save Button" )


	WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

	WebUI.click(findTestObject('FilesPage/ListItem_Folder'))

	WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)

	WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), 'ProfileFiles')
	extentTest.log(LogStatus.PASS, "Entered Folder Name to Create - ProfileFiles")
	WebUI.click(findTestObject('FilesPage/btn_Save'))
	extentTest.log(LogStatus.PASS, "Clicked on Save Button" )

	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def folderPath=navLocation+'/ProfileFiles'
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),folderPath)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to - '+folderPath)

	def filePathRad = (RunConfiguration.getProjectDir()) + '/Upload/JobFiles/CUBE_0001.rad'
	def newFPRad = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathRad)
	println(newFPRad)
	WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFPRad)

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	def folderPath2=navLocation+'/ForProfiles'
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'),folderPath2)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(LogStatus.PASS, 'Navigated to - '+folderPath2)

	def filePathFT = (RunConfiguration.getProjectDir() + '/Upload/') + 'InputDeck.zip'
	def newFPFT=CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathFT)
	println(newFPFT)

	WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFPFT )
	extentTest.log(LogStatus.PASS, 'Uploading zip file - ToUpload.zip')

	WebUI.delay(5)
	
	TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
		'InputDeck.zip', true)
	WebUI.rightClick(newFileObj)
	WebUI.delay(3)
	WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
	
	
	WebUI.delay(2)
	//add to check inputdeck folder is listed
	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(LogStatus.FAIL, ex)

	KeywordUtil.markFailed('ERROR: ' + e)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(LogStatus.FAIL, e)

	KeywordUtil.markFailed('ERROR: ' + e)
}
finally {
	extent.endTest(extentTest)

	extent.flush()


}

