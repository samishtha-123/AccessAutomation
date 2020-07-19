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
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.testng.Assert as Assert
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password], 
    FailureHandling.STOP_ON_FAILURE)

ReportFile = (GlobalVariable.G_ReportName + '.html')

def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)

def LogStatus = com.relevantcodes.extentreports.LogStatus

//String TCName = TestCaseName + ' through ContextMenu options'

def extentTest = extent.startTest(TestCaseName)

def viewIconTilePresent

def viewIconListPresent

TestObject newFileObj

WebUI.delay(2)

try {
    WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

    extentTest.log(LogStatus.PASS, 'Navigated to Files Tab')

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

    println(TestCaseName)

    if (TestCaseName.contains('Upload')) {
        println(Operation) //	WebUI.click(newFileObj)
       
    } else {
        if (TestCaseName.contains('tile view')) {
            WebUI.click(viewIconTile)

            extentTest.log(LogStatus.PASS, 'Changed View to Tile View - test case - ' + TestCaseName)

            WebUI.delay(2)

            newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', 
                fileName, true)
        } else {
            newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', 
                fileName, true)
        }
        
        WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))

        def location = (('/stage/' + GlobalVariable.G_userName) + '/FilesModule') + '/FileOps/'

        WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

        WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

        extentTest.log(LogStatus.PASS, 'Navigated to /stage/JSUploads in RFB ')

        WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))

        WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)

        if (TestCaseName.contains('add column selector')) {
			
			CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('FilesPage/Column_selector'),10)
            WebUI.click(findTestObject('FilesPage/Column_selector'))
			extentTest.log(LogStatus.PASS, 'Click on column selector to add new column')
            WebUI.setText(findTestObject('FilesPage/Column_filter'), column)
			

            TestObject columnName = CustomKeywords.'operations_FileModule.CreateFilesPageTestObj.myTestObjFilesCol'(colName)
			
            WebUI.click(columnName)

            //WebUI.click(findTestObject('FilesPage/Select_column'))

            WebUI.click(findTestObject('FilesPage/Confirm_button'))
			
			
			CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('FilesPage/Columnname'),10)
			def columnPresent = WebUI.verifyElementPresent(findTestObject('FilesPage/Columnname'), 5)
			
			if (columnPresent) {
						
				KeywordUtil.markPassed('SUCCESS: Column is present')
			} else {
				KeywordUtil.markFailed('FAILURE: Column not present')
			}
        } 
		
		else if (TestCaseName.contains('ascending order'))
		{
			
			WebUI.verifyElementPresent(findTestObject('FilesPage/Dateselector'), 7)
			String data1 = WebUI.getText(findTestObject('FilesPage/Dateselector'))
			println(data1)
			WebUI.click(findTestObject('FilesPage/Dateselector'))
			extentTest.log(LogStatus.PASS, 'Click on date selector ')
			WebUI.click(findTestObject('FilesPage/ColumnFilesTab'))
			WebUI.click(findTestObject('FilesPage/Dateselector'))
			
			WebUI.verifyElementPresent(findTestObject('FilesPage/Dateselector'), 7)
			String data = WebUI.getText(findTestObject('FilesPage/Dateselector'))
		    println(data)
			
			WebUI.verifyNotEqual(data1, data)	
		
		}
		
		else
		{ 
			
			CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('FilesPage/Column_selector'),10)
			
			WebUI.click(findTestObject('FilesPage/Column_selector'))
			extentTest.log(LogStatus.PASS, 'Click on column selector')
						WebUI.setText(findTestObject('FilesPage/Column_filter'), column)
						
			
						TestObject columnName = CustomKeywords.'jobActions.CreateFilesPageTestObj.myTestObjFilesCol'(colName)
						
						WebUI.click(columnName)
						extentTest.log(LogStatus.PASS, 'Select the column to delete')
						//WebUI.click(findTestObject('FilesPage/Select_column'))
			
						WebUI.click(findTestObject('FilesPage/Confirm_button'))
						extentTest.log(LogStatus.PASS, 'Click on confirm button')
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





