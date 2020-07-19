package preReq

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class jobMonitorigColFilter {


	@Keyword
	def addColumn(extentTest) {


		String [] ColName = ['Application', 'Queue', 'User', 'Time']
		String [] ColFilterLabel=['job_col_filter_application', 'job_col_filter_queueName', 'job_col_filter_userName', 'job_col_filter_creationTime']
		String [] ColFilterCB=[
			'cb_job_col_filter_application',
			'cb_job_col_filter_queueName',
			'cb_job_col_filter_userName',
			'cb_job_col_filter_creationTime'
		]

		WebUI.delay(2)
		println('Clicking on setting')


		int i =0
		for (String name:ColName) {

			String ColLable =ColFilterLabel[i]
			String ColCheckBx=ColFilterCB[i]

			println('Filter - '+ name);
			println('Col Lable - '+ColLable)
			println ('ColCheckBx - '+ColCheckBx )

		//	WebUI.mouseOver(findTestObject('Object Repository/JobMonitoringPage/JM_column_selector_icon'))

			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/JM_column_selector_icon'))

			WebUI.waitForElementPresent(findTestObject('Object Repository/JobMonitoringPage/JM_Job_ColumnFilter'),5)

			WebUI.setText(findTestObject('Object Repository/JobMonitoringPage/JM_Job_ColumnFilter'), name)

			WebUI.delay(3)

			TestObject filterCB=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterCheckBox'), 'id', 'equals', ColCheckBx, true)

			TestObject filterLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterLable'), 'id', 'equals', ColLable, true)


			def isElementChecked=WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)
			println (isElementChecked)
			if(isElementChecked) {
				println("Boxed checked")
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Cancel'))
			}
			else {
				println("in else block ")
				WebUI.click(filterLabel)
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
			}
			WebUI.delay(1)
			i=i+1
		}
	}
}
