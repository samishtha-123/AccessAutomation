package buildTestObj


import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject


public class CreateJobSubmissionObjs {


	@Keyword
	def myLeftNavAppIdentifier(String AppName) {
		String p1 = "//span[contains(@data-node,'\"id\":\""
		String p2 = "\"')]"
		String xpath_LeftNavAppIdentifier = p1+ AppName + p2
		println xpath_LeftNavAppIdentifier
		// Building job indentifier obj
		TestObject LeftNavAppIdentifier = new TestObject('objectName')
		LeftNavAppIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_LeftNavAppIdentifier)
		return LeftNavAppIdentifier
	}
	@Keyword
	def myJobLinkRow(String JobID)
	{
		
		String p1 = "//a[contains(text(),'"
		String p2 = "')]"
		String xpath_JobIDLink = p1+ JobID + p2
		println xpath_JobIDLink
		// Building job indentifier obj
		TestObject JobIDLinkIdentifier = new TestObject('objectName')
		JobIDLinkIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_JobIDLink)
		return JobIDLinkIdentifier
		
				}
	
	}


