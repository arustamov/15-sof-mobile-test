
# 15 SOF mobile automated tests with Serenity BDD (wrapping Appium) and Cucumber


## Get the code:

    git clone git@github.com:arustamov/15-sof-mobile-test.git
    cd 15-sof-mobile-test
    
## Connect/Run an Android device instance (physical or emulator) and verify with:

    adb devices
    
## Start appium:
    
    script/start_appium.sh

## Run the test(s) with Maven against Android device (physical or emulator):

For example against android emulator single instance running as emulator-5554:
    
    mvn clean verify -Dappium.udid=emulator-5554
    
## View the report:

Open `index.html` test report in the `target/site/serenity` directory

    open target/site/serenity/index.html
