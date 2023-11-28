# Technical Assessment

## Automation Framework
### Requirements

- Java 11 JDK
- Maven 3

### Run tests

Using the followings commands you can the test suite in a specific browser

| Browser                  | Command                                                 |
|--------------------------|---------------------------------------------------------|
| `Chrome (by default)`    | ``BROWSER=chrome mvn clean test`` or ``mvn clean test`` |
| `Firefox`                | ``BROWSER=firefox mvn clean test``                      |
| `Edge`                   | ``BROWSER=edge mvn clean test``                         |
| `Safari (only on macOS)` | ``BROWSER=safari mvn clean test``                       |

After the execution of the tests is possible to view the results in an HTML Report using this command your default browser will be opened with the results

`./allure-2.14.0/bin/allure serve`

or generate static files

`./allure-2.14.0/bin/allure generate`

### JMeter

Test Plan.jmx is in Docs folder

### TestCases

TestCases - Challenge is in Docs folder



*With :heart: from Chile, Barbara Obando*
