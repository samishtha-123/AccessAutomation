package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class folderOperations {


	@Keyword
	def executeFileOperations(String Operation,String TestCaseName ,extentTest) {
		boolean result=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		println ("Control in Keyword")
		WebUI.delay(2)
		switch (Operation) {
			case 'Copy':
				TestObject newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFolderOp)
				extentTest.log(LogStatus.PASS, 'Clicked on Context Menu Option for - '+Operation)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def fileToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					fileToCheck='ToCopy_TV.txt'
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(LogStatus.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'),5,FailureHandling.CONTINUE_ON_FAILURE)
					if(isElemenetPresent) {
						WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
					}
					else {
						WebUI.rightClick(findTestObject('FilesPage/Canvas_FilesPage_TileView'))
					}
					WebUI.delay(2)
					WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_TileGrid_Paste'))
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(LogStatus.PASS, 'Navigated to ToPaste Folder')
					fileToCheck='ToCopy_LV.txt'
					WebUI.delay(2)
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'),5,FailureHandling.CONTINUE_ON_FAILURE)
					if(isElemenetPresent) {
						WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
					}
					else {
						WebUI.rightClick(findTestObject('FilesPage/Canvas_FilesPage_ListView'))
					}
					WebUI.delay(2)
					WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Paste'))
				}


				extentTest.log(LogStatus.PASS, 'Clicked on Paste Option')

				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'text', 'equals',
						fileToCheck, true)

				def isFilePresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent){
					extentTest.log(LogStatus.PASS, 'Verified Pasted Folder - '+ fileToCheck)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(LogStatus.PASS, "Opened Notification Panel" )
				WebUI.delay(2)
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CopyFile'),5)
				println("notification status - "+result)
				extentTest.log(LogStatus.PASS, "Verified notification for operation - "+Operation)
				return result
				break

			case 'Cut':
				TestObject newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFolderOp)
				extentTest.log(LogStatus.PASS, 'Clicked on Context Menu Option for - '+Operation)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")

					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(LogStatus.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
					TestObject viewIconList=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title', 'equals',"List View", true)

					def viewIconListPresent=WebUI.waitForElementPresent(viewIconList, 3, FailureHandling.CONTINUE_ON_FAILURE)

					if(viewIconListPresent) {
						WebUI.click(viewIconList)
						WebUI.delay(2)
					}
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(LogStatus.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
				}

				def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'), 10,FailureHandling.CONTINUE_ON_FAILURE)

				if(isElemenetPresent) {
					WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
				}

				else {
					WebUI.rightClick(findTestObject('FilesPage/Canvas_FilesPage_ListView'))
				}
				WebUI.delay(2)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Paste'))
				extentTest.log(LogStatus.PASS, 'Clicked on Paste Option')
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(LogStatus.PASS, "Opened Notification Panel" )

				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CutFile'),5)
				extentTest.log(LogStatus.PASS, "verified notification for operation - "+Operation)
				println("notification status - "+result)
				return result
				break

			case 'Rename':

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				extentTest.log(LogStatus.PASS, 'Clicked on Context Menu Option for - '+Operation)

				WebUI.delay(2)
				WebUI.waitForElementVisible(findTestObject('FilesPage/NewFile_input'), 3)


				if (TestCaseName.contains('Job Submission')) {

					def Renameto='RenameFolderJS'
					TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals','MyFolderRename' , true)
					WebUI.setText(renameTextBxObj, Renameto)
					extentTest.log(LogStatus.PASS, 'Renamed folder to  ')
					WebUI.click(findTestObject('FilesPage/btn_Save'))
					WebUI.delay(2)
					extentTest.log(LogStatus.PASS, 'Clicked on Save Button')
					WebUI.click(findTestObject('FilesPage/Icon_Close'))

				}
				else{
					if(TestCaseName.contains('tile view')) {
						def Renameto='MyFolderRename'
						TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'MyFolderRename_TV', true)
						WebUI.setText(renameTextBxObj, Renameto)
						extentTest.log(LogStatus.PASS, 'Renamed folder to  '+Renameto)

						WebUI.click(findTestObject('FilesPage/btn_Save'))
						WebUI.delay(3)
						extentTest.log(LogStatus.PASS, 'Clicked on Save Button')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						extentTest.log(LogStatus.PASS, "Opened Notification Panel" )


						WebUI.delay(2)
						WebUI.click(findTestObject('FilesPage/Icon_Close'))
						TestObject  newFileObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_TileView'), 'title', 'equals',
								Renameto, true)
						def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('Object Repository/Notificactions/Notification_CopyFile'), 10,FailureHandling.CONTINUE_ON_FAILURE)
						if(isElemenetPresent){
							println ("Filed renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
						}
						else {
							println ("Filed not renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
						}
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						result=WebUI.waitForElementPresent(newFileObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
						extentTest.log(LogStatus.PASS, "verified notification for operation - "+Operation)
					}
					else {
						def Renameto='RenameFolder'

						TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals','MyFolderRename_LV' , true)
						WebUI.setText(renameTextBxObj, Renameto)
						extentTest.log(LogStatus.PASS, 'Renamed folder to  ')
						WebUI.click(findTestObject('FilesPage/btn_Save'))
						WebUI.delay(2)
						extentTest.log(LogStatus.PASS, 'Clicked on Save Button')
						WebUI.click(findTestObject('FilesPage/Icon_Close'))
						TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_ListView'), 'title', 'equals',
								Renameto, true)
						def isElemenetPresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
						if(isElemenetPresent){
							println ("Folder renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
							result=true
						}
						else {
							println ("Folder not renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
							result=false
						}

						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						extentTest.log(LogStatus.PASS, "Opened Notification Panel" )


						result=WebUI.waitForElementPresent(newFileObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
						extentTest.log(LogStatus.PASS, "verified notification for operation - "+Operation)
					}
				}
				return result
				break


			case 'Compress':

				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Compress'))
				WebUI.delay(5)

				extentTest.log(LogStatus.PASS, 'Clicked on Context Menu Option for - '+Operation)

				println"Clicked Compress"
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(LogStatus.PASS, "Opened Notification Panel" )

				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CompressFile'),5)
				extentTest.log(LogStatus.PASS, "verified notification for operation - "+Operation)
				println("notification status - "+result)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))

				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)

					WebUI.click(findTestObject("Object Repository/FilesPage/SortBy-Option"))
					WebUI.delay(3)
					WebUI.mouseOver(findTestObject("Object Repository/FilesPage/SortList-Option"))
					WebUI.click(findTestObject("Object Repository/FilesPage/SortList-Option"))
					TestObject sortIconDown=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/SortBy-Order'), 'class', 'equals',"down-arrow tile-sortable-icon focus_enable_class", true)
					def sortIconUp=WebUI.waitForElementPresent(findTestObject('Object Repository/FilesPage/SortBy-Order'), 3, FailureHandling.CONTINUE_ON_FAILURE)
					def isIconPresent=WebUI.waitForElementPresent(sortIconDown, 3, FailureHandling.CONTINUE_ON_FAILURE)
					println("sortIconUp - "+sortIconUp)
					println("sortIconDown - "+isIconPresent)
					if(sortIconUp)
					{
						WebUI.click(findTestObject('Object Repository/FilesPage/SortBy-Order'))
						WebUI.delay(2)
					}
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFolder_TileView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
					println  "RowItem_CompressedFolder_TileView - "+isElemenetPresent
					result=isElemenetPresent
				}
				else {
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFolder_ListView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
					println  "RowItem_CompressedFolder_ListView - "+isElemenetPresent
					result=isElemenetPresent
				}
				WebUI.delay(1)
				println "=================================================================="
				println" UNCOMPRESS SCENARIO"
				println "=================================================================="
				String compressedFileName=(new operations_FileModule.CreateFolderPageTestObj()).VerifyCompressedFile(TestCaseName,Operation)
				println(compressedFileName)
				extentTest.log(LogStatus.PASS, "Compressed FileName "+compressedFileName)
				WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), compressedFileName)
				WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
				extentTest.log(LogStatus.PASS, 'Searched for Compressed File ')
				TestObject newFileObj
				TestObject newFolderObj
				String UnCompressedFolderName
				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',
							compressedFileName, true )
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'MyFolderCompress_TV_archive_')]")
					UnCompressedFolderName='MyFolderCompress_TV'
				}
				else {
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title','equals', compressedFileName, true)
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[contains(text(),'MyFolderCompress_LV_archive_')]")
					UnCompressedFolderName='MyFolderCompress_LV'
					WebUI.delay(3)
				}

				WebUI.click(newFileObj)

				WebUI.rightClick(newFileObj)
				extentTest.log(LogStatus.PASS, 'rightClicked on Compressed File ')
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
				extentTest.log(LogStatus.PASS, 'performed Un-Compress Operation on'+compressedFileName)
				WebUI.delay(3)
				WebUI.rightClick(newFileObj)
				WebUI.delay(2)
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
				WebUI.click(newFileOp)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(LogStatus.PASS, 'Deleted Compressed File ')
				WebUI.click(newFolderObj)
				WebUI.doubleClick(newFolderObj)
				extentTest.log(LogStatus.PASS, 'Navigated to Uncompressed Folder')
				WebUI.delay(2)
				result=(new operations_FileModule.CreateFolderPageTestObj()).VerifyUnCompressedFile(UnCompressedFolderName,Operation)
				extentTest.log(LogStatus.PASS, 'Verified Uncompressed folder contains original file ')
				return result
				break


			case 'Delete':
				if (TestCaseName.contains('Job Submission')) {
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_JobSubmission_FileOperations'), 'id', 'equals', 'Delete', true)
					WebUI.click(newFileOp)

				}
				else
				{
					TestObject newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
					WebUI.click(newFolderOp)
				}
				WebUI.delay(3)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_DeleteFile'),5)
				println("notification status - "+result)
				return result
				break

			case 'Download':
				println Operation
				TestObject newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileGrid_Download'), 'id', 'equals', Operation, true)
				WebUI.click(newFolderOp)

				WebUI.delay(5)

				File downloadFolder = new File(GlobalVariable.G_DownloadFolder)

				List namesOfFiles = Arrays.asList(downloadFolder.list())

				if (namesOfFiles.contains('MyFolder')) {
					println('success')
					//extentTest.log(LogStatus.PASS, 'file to downloaded ')

				} else {
					println('fail')
				}

				return true
				break

			case 'Upload':
				def filePath = (RunConfiguration.getProjectDir() + '/Upload/ToUpload.txt')
				def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP )
				return true
				break



		}
	}
}