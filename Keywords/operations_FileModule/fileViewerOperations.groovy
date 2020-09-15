package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

public class fileViewerOperations {


	@Keyword
	def executeFileOperations(WebDriver katalonWebDriver,String Operation,String TestCaseName ,extentTest) {
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
				TestObject newFileNameDetails=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/FileName_Text_Details'), 'title', 'equals', 'ForFileViewer.txt', true)
				TestObject newFileOwnerDetails=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/FileOwner_Text_Details'), 'text', 'equals', GlobalVariable.G_userName, true)
				def isFileNamePresent=WebUI.verifyElementPresent(newFileNameDetails, 5)
				def isFileOwnerPresent=WebUI.verifyElementPresent(newFileOwnerDetails, 5)
				if(isFileNamePresent && isFileOwnerPresent) {
					extentTest.log(LogStatus.PASS, 'File name and file owner verified')
					result=true
				}
				else {
					extentTest.log(LogStatus.FAIL, 'File name and file owner not matched')
					result=false
				}

				WebUI.click(findTestObject('Object Repository/FilesPage/Close_DetailsPanel'))

				return result
				break


			case 'EditSave':
				def myXpath="//div[@id='brace-editor']//textarea"
				def myLineXpath='//div[@class="ace_line_group"]'
				WebUI.click(findTestObject('FilesPage/FileViewer_Edit'))
				extentTest.log(LogStatus.PASS, 'Click on file edit icon')
				WebUI.delay(3)
				WebElement textEle=katalonWebDriver.findElement(By.xpath(myXpath))

				List<WebElement> linesBeforeEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num=linesBeforeEdit.size()
			//	extentTest.log(LogStatus.INFO, 'linesBeforeEdit - '+ num)

				RemoteWebElement ele = textEle
				ele.sendKeys("new line added")
				ele.sendKeys('\n')
				extentTest.log(LogStatus.PASS, 'Added new line into file')
				WebUI.click(findTestObject('Object Repository/FilesPage/btn_Save_fileEditor'))
				extentTest.log(LogStatus.PASS, 'Saved the file ')

				WebUI.click(findTestObject('FilesPage/FileViewer_Edit'))
				extentTest.log(LogStatus.PASS, 'Click on file edit icon again to check added lines ')
				WebUI.delay(3)
				List<WebElement> linesAfterEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num3=linesAfterEdit.size()

			//	extentTest.log(LogStatus.INFO, 'linesAfterEdit - '+ num3)

				if(num3>num) {
					extentTest.log(LogStatus.PASS, 'New  lines added in file')
					result=true
				}
				else {
					extentTest.log(LogStatus.FAIL, 'New line not added in file')

					result=false
				}
				return result
				break


			case 'EditCancel':
				def myXpath="//div[@id='brace-editor']//textarea"
				def myLineXpath='//div[@class="ace_line_group"]'
				WebUI.click(findTestObject('FilesPage/FileViewer_Edit'))
				extentTest.log(LogStatus.PASS, 'Click on file edit icon')
				WebUI.delay(3)
				WebElement textEle=katalonWebDriver.findElement(By.xpath(myXpath))

				List<WebElement> linesBeforeEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num=linesBeforeEdit.size()
				//extentTest.log(LogStatus.INFO, 'linesBeforeEdit - '+ num)

				RemoteWebElement ele = textEle
				ele.sendKeys("new line added")
				ele.sendKeys('\n')
				extentTest.log(LogStatus.PASS, 'Added new line into file')
				WebUI.click(findTestObject('Object Repository/FilesPage/btn_Cancel_fileEditor'))
				extentTest.log(LogStatus.PASS, 'Saved the file ')

				WebUI.click(findTestObject('FilesPage/FileViewer_Edit'))
				extentTest.log(LogStatus.PASS, 'Click on file edit icon again to check added lines ')
				WebUI.delay(3)
				List<WebElement> linesAfterEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num3=linesAfterEdit.size()

				//extentTest.log(LogStatus.INFO, 'linesAfterEdit - '+ num3)

				if(num3>num) {
					extentTest.log(LogStatus.FAIL, 'New lines added in file')
					result=false
				}
				else {
					extentTest.log(LogStatus.PASS, 'New line not added in file')

					result=true
				}
				return result
				break

			case 'Download':

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(LogStatus.PASS, 'Click on file to download')
				WebUI.delay(3)
				def downloadLoc=GlobalVariable.G_DownloadFolder
				File downloadFolder = new File("C://KatalonDownloads")

				List namesOfFiles = Arrays.asList(downloadFolder.list())
				println(namesOfFiles.size())
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