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

def Browser = GlobalVariable.G_Browser

WebUI.callTestCase(findTestCase('Obsolete/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password
        , ('') : ''], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)

WebUI.click(findTestObject('FilesPage/Files_TileView_Btn'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

WebUI.click(findTestObject('FilesPage/ListItem_File'))

WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)

WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), 'Myfile10')

WebUI.click(findTestObject('FilesPage/btn_Save'))

