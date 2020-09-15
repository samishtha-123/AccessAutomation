package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot
import java.awt.event.KeyEvent as KeyEvent

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class JobSubmissions {

	@Keyword
	def JSAllFileds(String toChange,String ChangeValue,extentTest) {

		def stageOut=ChangeValue
		Robot rob = new Robot()
		def navLocation=(new generateFilePath.filePath()).execLocation()
		def fileLocation =navLocation+ '/JobsModule'
		switch (toChange) {

			case 'NCPU':
				WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))
				rob.keyPress(KeyEvent.VK_BACK_SPACE)
				rob.keyRelease(KeyEvent.VK_BACK_SPACE)
				rob.keyPress(KeyEvent.VK_2)
				rob.keyRelease(KeyEvent.VK_2)
				break

			case 'QUEUE':
				WebUI.click(findTestObject('WIP/RadioBtn_All Fields'))
				WebUI.scrollToElement(findTestObject('WIP/label_Queue'), 0)
				WebUI.click(findTestObject('WIP/div_workq'))
				TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals',	ChangeValue, true)
				WebUI.mouseOver(newQueueObj)
				WebUI.click(newQueueObj)
				break


			case 'MEM':
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/List_Memory'))
				rob.keyPress(KeyEvent.VK_BACK_SPACE)
				rob.keyRelease(KeyEvent.VK_BACK_SPACE)
				rob.keyPress(KeyEvent.VK_BACK_SPACE)
				rob.keyRelease(KeyEvent.VK_BACK_SPACE)
				rob.keyPress(KeyEvent.VK_1)
				rob.keyRelease(KeyEvent.VK_1)
				rob.keyPress(KeyEvent.VK_2)
				rob.keyRelease(KeyEvent.VK_2)
				rob.keyPress(KeyEvent.VK_0)
				rob.keyRelease(KeyEvent.VK_0)
				break

			case 'OUTFOLDER':
				WebUI.click(findTestObject('JobSubmissionForm/RadioBtn_All Fields'))
				WebUI.scrollToElement(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'),2)
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'))
				WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/TextBx_OutPut_Folder'), stageOut)
				break

			case 'VERSION':
				println ("version --- "+ ChangeValue)

				if (ChangeValue.equals('ShellScript')) {
					println('no version for this app')
				} else {
					WebUI.scrollToElement(findTestObject('JobSubmissionForm/versionDropDown'), 3)
					WebUI.click(findTestObject('JobSubmissionForm/versionDropDown'))
					TestObject newVerObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text','equals',ChangeValue, true)
					WebUI.click(newVerObj)
				}
				break
			case 'SetOutPutDir':
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
				WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), fileLocation)
				WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
				extentTest.log(LogStatus.PASS, 'Navigated to '+ fileLocation +'in RFB ')

				WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)
				WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
				WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
				WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
				WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), stageOut)
				WebUI.click(findTestObject('FilesPage/btn_Save'))

				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_Refresh'))

				TestObject newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_Folder'), 'title', 'equals',stageOut, true)
				WebUI.click(newFolderObj)
				WebUI.rightClick(newFolderObj)
				extentTest.log(LogStatus.PASS, 'Right Clicked on Input file ' + stageOut)
				WebUI.delay(2)
				String idForCntxtMn = 'Add as Output'
				TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
						'id', 'contains', idForCntxtMn, true)
				WebUI.delay(2)
				WebUI.click(newRFBContextMnOption)
				extentTest.log(LogStatus.PASS, 'Clicked on context menu - ' + idForCntxtMn)
				break;
		}
	}


	@Keyword
	def selectFile(String mode , String InputFile,extentTest){

		TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',
				InputFile, true)
		extentTest.log(LogStatus.PASS, 'Input Fileselectionmode- '+ mode)

		def navLocation=(new generateFilePath.filePath()).execLocation()
		def shortCutFileLocation

		def location=navLocation+'/JSUploads/'
		def fileLocation =navLocation+ '/JobsModule/InputDeck'
		if(GlobalVariable.G_Platform.equals('Windows')) {
			shortCutFileLocation = 'C:/stage/ShortCutFiles'
		}
		else {
			shortCutFileLocation = '/stage/'+GlobalVariable.G_userName+'/ShortCutFiles'
		}
		def folderLocation=GlobalVariable.G_userName+'-ToSetOutPutDir'


		switch (mode){
			case 'Local':
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
				WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
				WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
				extentTest.log(LogStatus.PASS, 'Navigated to '+ location +'in RFB ')
				def FolderEmptytext = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/FilesPage/Label_FolderEmpty'), 5)
				println(FolderEmptytext)
				if (FolderEmptytext) {
					println('FolderEmptytext')
				}
				else {
					WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
					def ele = WebUI.verifyElementClickable(findTestObject('FilesPage/FilesDelete_img'))
					println(ele)
					WebUI.click(findTestObject('FilesPage/FilesDelete_img'))
					WebUI.delay(2)
					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				}
				def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/') + InputFile
				def newFP = (new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP)
				extentTest.log(LogStatus.PASS, 'Uploading File to RFB - '+InputFile)
				def FileUploadClose = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'), 10)
				println("upload Notfication - "+FileUploadClose)
				def UploadedFile = (new customWait.WaitForElement()).WaitForelementPresent(newFileObj, 10)
				println("uploaded file - "+UploadedFile)
				if (UploadedFile) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'))
				}
				return newFileObj
				break;

			case 'Remote':
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
				WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), fileLocation)
				WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
				extentTest.log(LogStatus.PASS, 'Navigated to '+ fileLocation +'in RFB ')
				WebUI.delay(3)
				WebUI.waitForElementPresent(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 5)
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))
				WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), InputFile)
				WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))
				WebUI.delay(3)
				return newFileObj
				break;

			case 'ShortCut':
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
				println(shortCutFileLocation)
				extentTest.log(LogStatus.PASS, 'Navigating to - ' + shortCutFileLocation)
				WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), shortCutFileLocation)
				WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
				return newFileObj
				break;


			case 'LocalRad':
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
				WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
				WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
				extentTest.log(LogStatus.PASS, 'Navigated to '+ location +'in RFB ')
				def FolderEmptytext = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/FilesPage/Label_FolderEmpty'), 5)
				println(FolderEmptytext)
				if (FolderEmptytext) {
					println('FolderEmptytext')
				}
				else {
					WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
					def ele = WebUI.verifyElementClickable(findTestObject('FilesPage/FilesDelete_img'))
					println(ele)
					WebUI.click(findTestObject('FilesPage/FilesDelete_img'))
					WebUI.delay(2)
					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				}
				def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/') + InputFile
				def newFP = (new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP)
				def f2 = RunConfiguration.getProjectDir() + '/Upload/JobFiles/CUBE_0001.rad'
				def p2 = (new generateFilePath.filePath()).getFilePath(f2)
				WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'), p2)

				extentTest.log(LogStatus.PASS, 'Uploading File to RFB - '+InputFile)
				def FileUploadClose = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'), 10)
				println("upload Notfication - "+FileUploadClose)
				def UploadedFile = (new customWait.WaitForElement()).WaitForelementPresent(newFileObj, 10)
				println("uploaded file - "+UploadedFile)
				if (UploadedFile) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'))
				}
				return newFileObj
				break;



			case 'LocalForm':
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
				WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
				WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
				extentTest.log(LogStatus.PASS, 'Navigated to '+ location +'in RFB ')
				def FolderEmptytext = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/FilesPage/Label_FolderEmpty'), 5)
				println(FolderEmptytext)
				if (FolderEmptytext) {
					println('FolderEmptytext')
				}
				else {
					WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
					def ele = WebUI.verifyElementClickable(findTestObject('FilesPage/FilesDelete_img'))
					println(ele)
					WebUI.click(findTestObject('FilesPage/FilesDelete_img'))
					WebUI.delay(2)
					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				}
				def filePath = (RunConfiguration.getProjectDir() + '/Upload/') + InputFile
				def newFP = (new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('JobSubmissionForm/LocalFileUploadElement'), newFP)
				extentTest.log(LogStatus.PASS, 'Uploading File to Primary File element - '+InputFile)
				def FileUploadClose = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'), 10)
				println("upload Notfication - "+FileUploadClose)
				def UploadedFile = (new customWait.WaitForElement()).WaitForelementPresent(newFileObj, 10)
				println("uploaded file - "+UploadedFile)
				return newFileObj
				break;
		}

	}



	@Keyword
	def printJobState(WebDriver katalonWebDriver,extentTest){
		def LogStatus = com.relevantcodes.extentreports.LogStatus

		WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
		WebUI.setText(findTestObject('JobMonitoringPage/JM_SearchBox'),GlobalVariable.G_JobID)
		WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))

		if(GlobalVariable.G_Browser.equals('FireFox')) {
			WebUI.delay(5)
			extentTest.log(LogStatus.PASS, 'Waiting for jobs table to load on FireFox')
		}

		String myXpath="//div[@col-id='jobState']"
		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println listElement.size()

		for(int i =1;i<listElement.size();i++) {
			RemoteWebElement ele = listElement.get(i)
			String myText=ele.getText()
			println (ele.getText())
			extentTest.log(LogStatus.PASS, 'Current Job State for Job ID  - '+ GlobalVariable.G_JobID+ ' is - '+myText)
		}
	}
}
