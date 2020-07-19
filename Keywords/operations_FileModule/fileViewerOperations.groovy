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

public class fileViewerOperations {


	@Keyword
	def executeFileOperations(String Operation,String TestCaseName ,extentTest) {
		boolean result=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		println ("Control in Keyword --- "+ Operation )
		WebUI.delay(2)
		switch (Operation) {

			case 'Details':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				WebUI.verifyElementPresent(findTestObject('FilesPage/Viewdetails_File'), 3, FailureHandling.STOP_ON_FAILURE)
				extentTest.log(LogStatus.PASS, 'Clicked on file to view details')
				WebUI.click(findTestObject('Object Repository/FilesPage/Close_DetailsPanel'))
				result=true
				return result
				break


			case 'Edit':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				WebUI.click(findTestObject('FilesPage/FileViewer_Edit'))
				WebUI.click(findTestObject('Object Repository/FilesPage/close_edit'))
				extentTest.log(LogStatus.PASS, 'Click on file to edit')

				result=true
				return result
				break

			case 'Download':

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(LogStatus.PASS, 'Click on file to download')

				File downloadFolder = new File('C:\\Users\\rohinis\\Downloads')

				List namesOfFiles = Arrays.asList(downloadFolder.list())

				if (namesOfFiles.contains('ForFileViewer.txt')) {
					println('success')
					//extentTest.log(LogStatus.PASS, 'file to downloaded ')
					result=true
				} else {
					println('fail')
					result=false
				}

				return true
				break

			case 'Delete':

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(LogStatus.PASS, 'Click on file to delete')
				result=true
				return result
				break

			case 'Tail':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(LogStatus.PASS, 'Click on file to perform tail operation')
				result=true
				return result
				break
		}
	}
}