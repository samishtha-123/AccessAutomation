import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.testng.Assert as Assert
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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/*WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.G_BaseUrl)

WebUI.delay(3)

WebUI.maximizeWindow()*/
def Browser = GlobalVariable.G_Browser

if (Browser == 'Edge Chromium') {
	WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_Details_link'))

	WebUI.delay(3)

	WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_proceed_link'))

	WebUI.delay(3)
	WebUI.deleteAllCookies()
}
else if (Browser == 'IE') {
	
	WebUI.click(findTestObject('Object Repository/GenericObjects/IE_Details_Link'))

	WebUI.delay(3)
	WebUI.waitForElementVisible(findTestObject('Object Repository/GenericObjects/EdgeProceedeLink'), 2)
	WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeProceedeLink'))

	WebUI.delay(3)
}

/*
 * addes chrome capability - --ignore-certificate-errors
 *
 * if (Browser == 'Chrome') {
	WebUI.click(findTestObject('Object Repository/GenericObjects/button_advance_chrome'))

	WebUI.delay(1)

	WebUI.click(findTestObject('GenericObjects/ChromeProceedLink'))

	WebUI.delay(1)
}*/
WebUI.setText(findTestObject('LoginPage/username_txtbx'), username)

WebUI.setText(findTestObject('LoginPage/password_txtbx'), password)

WebUI.click(findTestObject('LoginPage/login_btn'))
WebUI.delay(2)
/*
CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 10)

def accessLinkPresent = WebUI.verifyElementPresent(findTestObject('GenericObjects/TitleLink_Jobs'), 5)

if (accessLinkPresent) {
    Assert.assertTrue(accessLinkPresent)

    KeywordUtil.markPassed('SUCCESS: Successfully logged in')
} else {
    KeywordUtil.markFailed('FAILURE: Login Failed')
}
*/
