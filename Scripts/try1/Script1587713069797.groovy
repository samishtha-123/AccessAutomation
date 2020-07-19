import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://localhost:32772/pbsworks')

WebUI.setText(findTestObject('Page_Altair Access/input_concat(id(  username  ))_username'), 'rohini')

WebUI.setEncryptedText(findTestObject('Page_Altair Access/input_concat(id(  password  ))_password'), 'p8rFjx5pgvU=')

WebUI.click(findTestObject('Page_Altair Access/input_concat(id(  loginbtn  ))_loginbtn'))

WebUI.click(findTestObject('Page_Altair Access/input_concat(id(  loginbtn  ))_loginbtn'))

WebUI.doubleClick(findTestObject('Page_Altair Access/input_concat(id(  username  ))_username'))

WebUI.setText(findTestObject('Page_Altair Access/input_concat(id(  username  ))_username'), 'david')

WebUI.setEncryptedText(findTestObject('Page_Altair Access/input_concat(id(  password  ))_password'), '1pIjjCeH/JU=')

WebUI.click(findTestObject('Page_Altair Access/input_concat(id(  loginbtn  ))_loginbtn'))

WebUI.click(findTestObject('JobMonitoringPage/JM_column_selector_icon'))

WebUI.click(findTestObject('JobMonitoringPage/JM_FilterLable'))

WebUI.click(findTestObject('Page_Altair Access/span_Creation Time'))

WebUI.click(findTestObject('Page_Altair Access/span_Queue Name'))

WebUI.click(findTestObject('Page_Altair Access/span_Name'))

WebUI.click(findTestObject('Page_Altair Access/span_Name'))

WebUI.click(findTestObject('Page_Altair Access/span_User Name'))

WebUI.click(findTestObject('Page_Altair Access/span_User Name'))

WebUI.click(findTestObject('JobMonitoringPage/JM_FilterLable'))

WebUI.setText(findTestObject('JobMonitoringPage/JM_Job_ColumnFilter'), 'new')

WebUI.click(findTestObject('null'))

