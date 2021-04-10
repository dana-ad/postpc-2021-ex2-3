I pledge the highest level of ethical principles in support of academic excellence.  I ensure that all of my work reflects my own abilities and not those of someone else.

QUESTIONS:
Saying we want to add a cool feature - button "x" to run multiplication.

1. What code do we need to change/add/remove to support this feature?
Answer - we need to add this button in the xml file (and of course change visually other buttons if space is needed), and then add the relevant feature for this button (multiplication) in the simpleCalculator, simpleCalculatorImpl and change the order of actions since multiplication has priority to plus and minus, so it does require some changes in the existing methods I wrote (calculation can't be done at the time of presses like I did now but to wait until the user presses the equals button and only then make the calculation.

2. Which tests can we run on the calculator? On the activity? On the app?
Answer - we can check if this calculator indeed respects the right order of operations (multiplication comes before minus or plus) and all the other tests we ran on the other operators.

# AndroidCalculator - Calculator exercise for Android developers

## In this project:
- Calculator screen with XML ready for portrait and landscape
- Calculator interface used by the Activity
- Unit tests for the calculator and the activity

## Your job:
- Implement `SimpleCalculatorImpl.java`
- add more unit tests to `SimpleCalculatorImpl.java`
- Implement `MainActivity.java`
- add more unit tests to `MainActivityTest.java`
- add more flow tests to `AppFlowTest.java`

Basically look for "TODO" in the code.


Good luck!
