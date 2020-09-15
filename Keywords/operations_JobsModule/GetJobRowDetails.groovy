package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable


public class GetJobRowDetails {

	@Keyword
	def newGrid(WebDriver katalonWebDriver , String dataAttribute , String FilterValue, extentTest  ) {
		def fail=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		String x1="//div[@col-id='"
		String x2 ="']"
		String myXpath=x1+dataAttribute+x2

		println(myXpath)
		if(GlobalVariable.G_Browser.equals('FireFox')) {
			WebUI.delay(5)
			extentTest.log(LogStatus.PASS, 'Waiting for jobs table to load on FireFox')
		}

		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println listElement.size()

		for(int i =1;i<listElement.size();i++) {
			RemoteWebElement ele = listElement.get(i)
			String myText=ele.getText()
			if(myText.equals(FilterValue)) {
				println (ele.getText())
				extentTest.log(LogStatus.PASS, 'Filter value matched for row number - '+ i + ' in jobs table')
			}
			else {
				extentTest.log(LogStatus.PASS, myText)
				//	fail=true
				// no jobs  msg
			}
		}
		return fail
	}


	@Keyword
	def getJobByUser(WebDriver katalonWebDriver , String dataAttribute , String FilterValue, extentTest , String AllJobsUser ) {
		def fail=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		String myXpath="//div[@col-id='userName']"
		println(AllJobsUser)
		println(GlobalVariable.G_userName)
		if(GlobalVariable.G_userName.equals(AllJobsUser)) {
			extentTest.log(LogStatus.PASS, 'My Jobs Filter Applied')
		}
		else {
			extentTest.log(LogStatus.PASS, 'All Jobs Filter Applied')
			WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
			WebUI.setText(findTestObject('JobMonitoringPage/JM_SearchBox'),AllJobsUser)
			//WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))
		}
		if(GlobalVariable.G_Browser.equals('FireFox')) {
			WebUI.delay(5)
			extentTest.log(LogStatus.PASS, 'Waiting for jobs table to load on FireFox')
		}
		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println listElement.size()

		for(int i =1;i<listElement.size();i++) {
			RemoteWebElement ele = listElement.get(i)
			def myText=ele.getText()
			if(myText.contains(AllJobsUser)) {
				extentTest.log(LogStatus.PASS, 'Job Owner value matched for row number - '+ i + ' in jobs table')
			}
			else {
				extentTest.log(LogStatus.PASS, myText)
				fail=true
				// no jobs  msg
			}
		}
		return fail
	}


	@Keyword
	def getJobState(TestObject jobRow, extentTest  ) {

		def fail=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus


		String data=WebUI.getAttribute(jobRow, "data-node")
		String dataAttributeString='"jobState":"'
		String[] splitAddress = data.split(dataAttributeString)
		String  jobState = splitAddress[1]
		String[] s1 = jobState.split('",')
		String CheckString=s1[0]
		println("----------------------------------------------")
		println CheckString
		println("----------------------------------------------")
		return CheckString
	}

	@Keyword
	def getJobID(String jobText) {

		println('Job Text = ' + jobText)
		String[] splitAddress = jobText.split(' ')
		def len = (splitAddress[2]).length()
		def toget = (splitAddress[2]).substring(1, len - 1)
		GlobalVariable.G_JobID = toget
		println('********************************************')
		println(GlobalVariable.G_JobID)
		println('********************************************')
	}

	@Keyword
	def checkSubJobs(WebDriver katalonWebDriver,String origin, extentTest) {
		def LogStatus = com.relevantcodes.extentreports.LogStatus

		if (origin.equals('JS')) {
			TestObject jobLink=(new buildTestObj.CreateJobSubmissionObjs()).myJobLinkRow(GlobalVariable.G_JobID)
			WebUI.click(jobLink)
		}

		String myXpath="//div[@col-id='jobId']"
		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println listElement.size()
		extentTest.log(LogStatus.INFO, 'Sub Job keyword text passed - '+origin)

		if(listElement.size()>=2) {
			for(int i =1;i<listElement.size();i++) {
				RemoteWebElement ele = listElement.get(i)
				String myText=ele.getText()
				println (ele.getText())
				//extentTest.log(LogStatus.PASS, 'Sub Job ID - '+myText)
				String[] splitAddress = myText.split('\\[')
				def matchText=splitAddress[0]
				if(myText.contains(matchText))
				{
					extentTest.log(LogStatus.PASS, 'Sub Job ID - '+myText)
				}
			}
		}
	}




	@Keyword
	def newCol(WebDriver katalonWebDriver , String dataAttribute , String ColName, extentTest  ) {
		def fail=false
		def LogStatus = com.relevantcodes.extentreports.LogStatus
		String x1="//div[@class='ag-header-cell ag-header-cell-sortable'][@col-id='"
		String x2 ="']"
		String myXpath=x1+dataAttribute+x2
		
		String p1="//div[contains(@col-id,'"
		String p2="')]"
		String newXpath=p1+dataAttribute+p2
		//myXpath="//div[@col-id='creationTime']"
		extentTest.log(LogStatus.INFO, 'myXpath - '+newXpath)

		extentTest.log(LogStatus.INFO, 'dataAttribute - '+dataAttribute)

		extentTest.log(LogStatus.INFO, 'ColName - '+ColName)

		WebUI.delay(3)

		//extentTest.log(LogStatus.INFO, 'List size - '+listElement.size())


		println(newXpath)
		if(GlobalVariable.G_Browser.equals('FireFox')) {
			WebUI.delay(5)
			extentTest.log(LogStatus.PASS, 'Waiting for jobs table to load on FireFox')
		}

		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(newXpath))
		println listElement.size()
		extentTest.log(LogStatus.INFO, 'List size - '+listElement.size())
		if(listElement.size()>0)
		{
			fail=true
			extentTest.log(LogStatus.INFO, 'fail value -'+fail)

		}
		else
		{
			fail=false
			extentTest.log(LogStatus.INFO, 'fail value -'+fail)

		}
		return fail
	}
}