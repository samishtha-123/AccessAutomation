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

'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)


WebUI.delay(2)

	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))


	WebUI.delay(2)


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
		
	
	
	