Page Object Model Selenium Appium Framework using Java, TestNG

***As of Feb 7th 2021 the selenium web tests and appium mobile tests are passing.

NOTE:
The sample XOME.com website used is a live product so it is always changing, so the test automation tests may get out of sync when the pages change.
I will update the tests when I get a chance.

Angee

mvn clean


mvn dependency:resolve

Change POM.xml to TestNG2 or TestNG5 depending on what you want to test. 
For Firefox browser, remember to created your browser profile and then update the test base.

mvn compile test -Dsurefire.SuiteXmlFiles=/src/test/resources/TestNG2.xml (web)


mvn compile test -Dsurefire.SuiteXmlFiles=/src/test/resources/TestNG5.xml (appium)

