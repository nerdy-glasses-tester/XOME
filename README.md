Page Object Model Selenium Appium Framework using Java, TestNG

***As of December 20 2025 the selenium web tests and appium mobile tests are passing.
The web tests are automated on Chrome only, because Firefox can't override captcha.
I only automated on a real android samsung galaxy tablet device. I don't have an actual build to automate on android simulator.
I didn't automate for ios, because I don't have the xome ios app file and I don't want to buy a apple developer license for one time use only since I'm unemployed now. If you are reading this and are hiring, please let me know and give me an interview. I can be reached at angeetong@gmail.com.
This time I used ChatGPT to fine tune my coding. AI is amazing at helping me debug. Since I'm limited by home conditions and don't have a real office enviroment my automation is limited.



NOTE:
The sample XOME.com website used is a live product so it is always changing, so the test automation tests may get out of sync when the pages change.
I will update the tests when I get a chance.

Angee

Download Java JDK 17

Copy /Xome/lib/chromedriver and geckdodriver into your /User/username/drivers folder

Set properties file to your apkfile and set apk path in TestBase file for your real devices

adb devices
cd to /Users/username/eclipse-workspace/XOME/src/test/resources
adb install base-clean.apk

mvn clean

mvn dependency:resolve

Change POM.xml to TestNG2 for web automation or TestNG5 for mobile automation depending on what you want to test. 
For Firefox browser, remember to created your browser profile and then update the test base.

mvn compile test -Dsurefire.SuiteXmlFiles=src/test/resources/TestNG2.xml (web)

mvn compile test -Dsurefire.SuiteXmlFiles=src/test/resources/TestNG5.xml (mobile)


