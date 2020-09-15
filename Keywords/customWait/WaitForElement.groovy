package customWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class WaitForElement {


	@Keyword
	def WaitForelementPresent(TestObject to, timeOut) {
		def isElemenetPresent = false
		def i=0
		while (isElemenetPresent == false && i<timeOut) {
			WebUI.delay(1)
			try {
				WebUI.verifyElementPresent(to, 1)
				isElemenetPresent =WebUI.verifyElementClickable(to)
				//, FailureHandling.CONTINUE_ON_FAILURE)
			}
			catch (Exception  ex) {
				println("Exception")
			}
			catch (StepFailedException e) {
				println("StepFailedException")
			}
			println(isElemenetPresent)
			i++
		}
		return isElemenetPresent
	}


	@Keyword
	def getFilterCaterogyState(TestObject newJobFilterCategoryDown, TestObject newJobFilterCategoryRight){
		def isElementNotPresentDown
		def isElementNotPresentRight
		String toreturn
		try{
			isElementPresentDown = WebUI.waitForElementPresent(newJobFilterCategoryDown,3, FailureHandling.CONTINUE_ON_FAILURE)
			isElementPresentRight = WebUI.waitForElementPresent(newJobFilterCategoryRight, 5, FailureHandling.CONTINUE_ON_FAILURE)
			println "after looking "
			println isElementNotPresentDown
			println isElementNotPresentRight
			if(isElementNotPresentDown) {
				toreturn="Down"
				println("returning - Down arrow")
			}
			if(isElementNotPresentRight) {
				toreturn="right"
				println("returning - right arrow")
			}
		}

		catch (Exception  ex) {
			println("Exception")
		}
		catch (StepFailedException e) {
			println("StepFailedException")
		}
		finally {
			return toreturn
		}
	}
}
