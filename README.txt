# Execute tests
In order to execute tests, different options need to be provided through command line.

## Test execution options
1. mvn test in command line
2. -Dtest=<className> - fully qualified name of test class to run

Example: mvn -Dtest=LoginUISuite test

### Chosen solution

Implemented web automation framework from scratch using Page Object Model as design pattern
Given the opportunity to run tests in parallel through jUnit 5
Use API as preconditions for delete/create resources

Future framework improvements:
- put username and password in a separate file
- rename variables/methods/locators
- use constants to store values
- adding java doc
- format code

#### Need to be installed
    JDK 14 => https://jdk.java.net/14/ => required
    Intellij Community Edition => https://www.jetbrains.com/idea/  => optional






