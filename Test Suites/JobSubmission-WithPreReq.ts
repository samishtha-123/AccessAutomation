<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>JobSubmission-WithPreReq</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <testSuiteGuid>b07ea388-51d6-4bca-891a-0e37ab8ba067</testSuiteGuid>
   <testCaseLink>
      <guid>7bc7bc28-41a1-4577-9ebc-c81a9a651a45</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>false</isRun>
      <testCaseId>Test Cases/PreReq/old-Pre-Req-Script-For-JobSubmission</testCaseId>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>0e8aad25-1d99-4379-8210-3b7d88bd03b1</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>6158ddd4-6f7d-4983-873f-6c167915cd28</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>e3cb4ecf-deea-473d-ab7f-88fe768d1709</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>5c7d5c30-576c-4557-9ef7-d580c546a40c</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>eb5e92ab-73b3-49a0-b744-ff72ea4203fe</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>583c7ef2-a0a6-4d17-83bc-f51830bd475f</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/JobSubmission/JobSubmission-RemoteFile-RFB-ContextMenu</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>ed1470ab-1991-49ff-8a46-cecf94f54d4b</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-6</value>
         </iterationEntity>
         <testDataId>Data Files/TestDataForJobSubmission</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>ed1470ab-1991-49ff-8a46-cecf94f54d4b</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>AppName Element</value>
         <variableId>50871651-4438-4afa-a3d1-e015def75e60</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>ed1470ab-1991-49ff-8a46-cecf94f54d4b</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>RemoteFile</value>
         <variableId>fa016057-1646-44eb-aca6-ca256affe5b9</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>ed1470ab-1991-49ff-8a46-cecf94f54d4b</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Version</value>
         <variableId>66f583dd-0003-4edf-b994-00dbc3b28059</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>ed1470ab-1991-49ff-8a46-cecf94f54d4b</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Desription</value>
         <variableId>449134c1-4ff3-4836-9e8f-ac89476b1ec7</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>ed1470ab-1991-49ff-8a46-cecf94f54d4b</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>FileArg</value>
         <variableId>c20ffa81-ef5c-405a-b281-7d065b6fbd73</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>601c2b5f-1d03-4cf3-a497-e01956d76a11</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/JobSubmission/JobSubmission-LocalFile</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>c6aabb16-5970-4a57-a016-a3eab8f1be7a</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>15-28</value>
         </iterationEntity>
         <testDataId>Data Files/TestDataForJobSubmission</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>c6aabb16-5970-4a57-a016-a3eab8f1be7a</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>AppName Element</value>
         <variableId>c0e00e77-2e30-4ab6-bc45-41d8bbda7cf5</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>c6aabb16-5970-4a57-a016-a3eab8f1be7a</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>LocalFile</value>
         <variableId>75329fac-7ea5-40fc-b431-aa90ea397a2d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>c6aabb16-5970-4a57-a016-a3eab8f1be7a</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Desription</value>
         <variableId>35c7aefd-dd64-4838-885f-042cd15ce777</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>c6aabb16-5970-4a57-a016-a3eab8f1be7a</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Version</value>
         <variableId>ebeb91af-0057-4d95-9227-7de3a1528b75</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>c6aabb16-5970-4a57-a016-a3eab8f1be7a</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>FileArg</value>
         <variableId>46cec43c-9ef7-4d65-9267-9aa8ff4307f7</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>3767f343-a838-48e2-9a5d-2520ef673774</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/JobSubmission/JobSubmission</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-14</value>
         </iterationEntity>
         <testDataId>Data Files/TestDataForJobSubmissio-New</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>AppName</value>
         <variableId>26fbd4fc-965f-401e-a5c4-6fb431cd201b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>InputFile</value>
         <variableId>e9d3b8c0-f5fe-46e9-a72e-1a22d9f59c79</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>TestCaseName</value>
         <variableId>518a544d-f5ac-448a-acfc-06b909661db8</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>FileArg</value>
         <variableId>e66844e3-6552-46e7-8030-77932e3be780</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Mode</value>
         <variableId>ea501a92-5498-450d-afcd-13bfabb19fdd</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>ToChange</value>
         <variableId>816d5f07-afb8-4c81-9dde-4af3c892b31c</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>6ea2c98b-1e3b-40a3-a374-db6c0bb09761</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>ChangeValue</value>
         <variableId>ea3c8f43-9d3d-4606-b294-b1768dbf277b</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
