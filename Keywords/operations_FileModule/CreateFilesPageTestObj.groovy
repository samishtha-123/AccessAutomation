package operations_FileModule


import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class CreateFilesPageTestObj {
	@Keyword
	def FolderRowIdentifireForUnCompressedFile() {
		String x1 = '//div[contains(@data-path,\'/stage/'
		String x2 =GlobalVariable.G_userName
		String x3
		String x4
		if(Operation.contains('icon')) {
			x3='/FilesModule/FileOpsIcons'
		}
		else {
			x3='/FilesModule/FileOps'
			println ("else block form VerifyCompressedFile operation - "+  Operation)
		}

		if (TestCaseName.contains('tile view')) {
			x4 = '/ToCompress_TV_archive_\')]'
		}
		else {
			x4 = '/ToCompress_LV_archive_\')]'
		}

		String xpath_compressedfileXpath = x1+x2+x3+x4
		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		return compressedFileObjIdentifierLatest
	}



	@Keyword
	def VerifyCompressedFile(String TestCaseName , String Operation) {
		String fileNew
		if(TestCaseName.contains('tile view')) {
			fileNew='ToCompress_TV_archive_'
		}
		else {
			fileNew='ToCompress_LV_archive_'
		}

		WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		println(fileNew)
		WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), fileNew)
		WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))



		if(TestCaseName.contains('tile view')) {


			String xpath_compressedfileXpathTV = "//label[@id='file_text'][contains(text(),'ToCompress_TV_archive_')]"
			println xpath_compressedfileXpathTV
			// Building job indentifier obj
			TestObject compressedFileObjIdentifierTV = new TestObject('objectName')
			compressedFileObjIdentifierTV.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpathTV)
			String CompressTitle=WebUI.getAttribute(compressedFileObjIdentifierTV,'title')
			println("==========================================================")
			println(CompressTitle)
			println("==========================================================")
			return CompressTitle

		}
		else {
			String xpath_compressedfileXpath = "//div[@aria-rowindex='2']//div[@class='show-text-ellipsis retain-whitespace']"
			println xpath_compressedfileXpath
			// Building job indentifier obj
			TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
			compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
			String CompressTitle=WebUI.getAttribute(compressedFileObjIdentifierLatest,'title')
			println("==========================================================")
			println(CompressTitle)
			println("==========================================================")
			return CompressTitle
		}
	}


	@Keyword
	def VerifyUnCompressedFile(String CompressedFileName , String Operation) {

		String CompressedFileXpath
		String FolderXpath
		if(CompressedFileName.contains('TV')) {

			String f1="//label[@id='file_text'][@title='"
			String f2 ="']"
			CompressedFileXpath=f1+CompressedFileName+f2
			println("==============================================================")
			println (' CompressedFileXpath  TV---- ' +CompressedFileXpath)
			println("==============================================================")

			String[] splitAddress = CompressedFileName.split('\\.')
			String x1="//label[@id='tile_dir_input'][@title='"
			FolderXpath=x1+splitAddress[0]+f2
			println("==============================================================")
			println (' Folder Name TV ---- ' +FolderXpath)
			println("==============================================================")
		}
		else {
			String p1="//div[@title = '"
			String p2 ="']"
			CompressedFileXpath=p1+CompressedFileName+p2
			println("==============================================================")
			println (' CompressedFileXpath  LV---- ' +CompressedFileXpath)
			println("==============================================================")

			String[] splitAddress = CompressedFileName.split('\\.')
			FolderXpath=p1+splitAddress[0]+p2
			println("==============================================================")
			println (' Folder Name LV ---- ' +FolderXpath)
			println("==============================================================")
		}

		TestObject newFileObj=new TestObject('objectName')
		newFileObj.addProperty('xpath', ConditionType.EQUALS, CompressedFileXpath)
		WebUI.click(newFileObj)
		WebUI.rightClick(newFileObj)
		TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
		WebUI.click(newFileOp)
		WebUI.delay(2)
		WebUI.click(findTestObject('GenericObjects/btn_Yes'))
		WebUI.delay(2)

		TestObject newFolderObj=new TestObject('objectName')
		newFolderObj.addProperty('xpath', ConditionType.EQUALS, FolderXpath)
		def isElemenet=WebUI.verifyElementPresent(newFolderObj, 5, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.click(newFolderObj)
		WebUI.doubleClick(newFolderObj)

		return true
	}

	@Keyword
	def myTestObjFilesCol(String colName) {
		String x1 = '//label[@id="'
		String x2 = '"]//span[@class="tickmark"]'

		String xpath_FilesCols = x1+colName+x2
		println xpath_FilesCols
		// Building job indentifier obj
		TestObject createFilesColIdentifier = new TestObject('objectName')
		createFilesColIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_FilesCols)
		return createFilesColIdentifier
	}
}
