<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>FolderModule</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <testSuiteGuid>623fcdb4-7032-469d-8931-a6fafbaa4839</testSuiteGuid>
   <testCaseLink>
      <guid>7d489355-efb5-4a58-b6c7-25c152ea7dfd</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>false</isRun>
      <testCaseId>Test Cases/PreReq/Pre-Req-Script-FolderOps</testCaseId>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>97df21b5-186f-4009-8bba-9433f9ed2dd4</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>a67f2e1f-7e28-4689-b63e-465081eec7b0</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>daf251f1-faf2-45b5-bb8b-ea5d4f81a96b</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/FilesModule/Create_folder</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>5ae74293-6d8b-40d0-b0b2-7b5483170f9d</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/TestDataForCreateFolder</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>5ae74293-6d8b-40d0-b0b2-7b5483170f9d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>FolderName</value>
         <variableId>ee5dd06b-fe53-4cd7-b0ae-9d5aa0e1949d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>5ae74293-6d8b-40d0-b0b2-7b5483170f9d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>TestCaseName</value>
         <variableId>7d839b7d-abd5-48a9-9050-61b7d5242a7c</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>68735d7c-86f3-4498-b607-5bc786ff5da7</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>2ac884d7-c404-4be8-aa5f-b7f034d37368</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/FilesModule/FolderOperations</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>f2327e05-e85b-4e9e-97a7-64a1e130d308</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-10</value>
         </iterationEntity>
         <testDataId>Data Files/TestDataForFolderOperations</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>f2327e05-e85b-4e9e-97a7-64a1e130d308</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>FolderName</value>
         <variableId>aeb53a0e-86e0-4c0a-b07f-a600d966296b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>7387cc82-73d5-4707-9683-6385e40d7781</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>f2327e05-e85b-4e9e-97a7-64a1e130d308</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>TestCaseName</value>
         <variableId>b2b766af-3fe6-4951-8c7c-f50e8d8e8c45</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>f2327e05-e85b-4e9e-97a7-64a1e130d308</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Operation</value>
         <variableId>efc4ecd9-7862-4f03-a81e-d1c0cd816749</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>6efcc209-680e-46c6-8bae-a4c4d2877107</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/FilesModule/FolderOperations_Topmenu_Icons</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>2c9be12f-3551-4ebc-9c98-841acb61f1ed</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-8</value>
         </iterationEntity>
         <testDataId>Data Files/TestDataForFolderOperations</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>2c9be12f-3551-4ebc-9c98-841acb61f1ed</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>FolderName</value>
         <variableId>37bb42fb-bb25-4415-b65d-f62436826b25</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>d1a6d2f5-7fea-401c-879c-96e515d0904e</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>2c9be12f-3551-4ebc-9c98-841acb61f1ed</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>TestCaseName</value>
         <variableId>e5b7f64a-03e6-4210-bba9-f79611e3c08b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>2c9be12f-3551-4ebc-9c98-841acb61f1ed</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>IconOperation</value>
         <variableId>3da23bfd-8e4f-4249-9c91-343235062b08</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
