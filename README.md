Page Object Model Selenium Appium Framework using Java, TestNG

***As of Nov 3rd 2019, the selenium web tests are passing after updating to new locators.
Will have to look at Appium later if it is still passing.
***Previously updated on Jun 6th 2019. Selenium and Appium tests all passing.

NOTE:
The sample website used is a live product so it is always changing, so the test automation tests may get out of sync when the pages change. I will update the tests when I get a chance.

Angee

mvn clean
mvn dependency:resolve
mvn compile test -Dsurefire.SuiteXmlFiles=/src/test/resources/TestNG3.xml

