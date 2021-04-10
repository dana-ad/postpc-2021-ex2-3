package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class AppFlowTest {

  private ActivityController<MainActivity> activityController;
  private MainActivity activityUnderTest;
  private View button0;
  private View button1;
  private View button2;
  private View button3;
  private View button4;
  private View button5;
  private View button6;
  private View button7;
  private View button8;
  private View button9;
  private View buttonBackspace;
  private View buttonClear;
  private View buttonPlus;
  private View buttonMinus;
  private View buttonEquals;
  private TextView textViewOutput;

  /** initialize main activity with a real calculator */
  @Before
  public void setup(){
    activityController = Robolectric.buildActivity(MainActivity.class);
    activityUnderTest = activityController.get();
    activityController.create().start().resume();
    button0 = activityUnderTest.findViewById(R.id.button0);
    button1 = activityUnderTest.findViewById(R.id.button1);
    button2 = activityUnderTest.findViewById(R.id.button2);
    button3 = activityUnderTest.findViewById(R.id.button3);
    button4 = activityUnderTest.findViewById(R.id.button4);
    button5 = activityUnderTest.findViewById(R.id.button5);
    button6 = activityUnderTest.findViewById(R.id.button6);
    button7 = activityUnderTest.findViewById(R.id.button7);
    button8 = activityUnderTest.findViewById(R.id.button8);
    button9 = activityUnderTest.findViewById(R.id.button9);
    buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
    buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
    buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);
    buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
    buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
    textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
  }

  @Test
  public void flowTest1(){
    // run clicks on "13+5"
    for (View button: Arrays.asList(
      button1, button3, buttonPlus, button5
    )) {
      button.performClick();
    }

    assertEquals("13+5", textViewOutput.getText().toString());
  }


  @Test
  public void flowTest2(){
    // run clicks on "7+5<backspace>4="
    for (View button: Arrays.asList(
      button7, buttonPlus, button5, buttonBackspace, button4, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("11", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest3(){
    // run clicks on "-6+22="
    for (View button: Arrays.asList(
            buttonMinus, button6, buttonPlus, button2, button2, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("16", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest4(){
    // run clicks on "0-5+-3"
    for (View button: Arrays.asList(
            button0, buttonMinus, button5, buttonPlus, buttonMinus, button3
    )) {
      button.performClick();
    }

    assertEquals("0-5+3", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest5(){
    // run clicks on "8<clear>+9="
    for (View button: Arrays.asList(
            button8, buttonClear, buttonPlus, button9, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("9", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest6(){
    // run clicks on "1-<clear><clear><clear>"
    for (View button: Arrays.asList(
            button1, buttonMinus, buttonClear, buttonClear, buttonClear
    )) {
      button.performClick();
    }

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest7(){
    // run clicks on "5-<backspace><backspace>"
    for (View button: Arrays.asList(
            button5, buttonMinus, buttonBackspace, buttonBackspace
    )) {
      button.performClick();
    }

    assertEquals("0", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest8(){
    // run clicks on "2+-4-=3"
    for (View button: Arrays.asList(
            button2, buttonPlus, buttonMinus, button4, buttonMinus, buttonEquals, button3
    )) {
      button.performClick();
    }

    assertEquals("63", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest9(){
    // run clicks on "68="
    for (View button: Arrays.asList(
            button6, button8,buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("68", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest10(){
    // run clicks on "01="
    for (View button: Arrays.asList(
            button0, button1 ,buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("1", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest11(){
    // run clicks on "<clear>7"
    for (View button: Arrays.asList(
            buttonClear, button7
    )) {
      button.performClick();
    }

    assertEquals("7", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest12(){
    // run clicks on "5+3=+6+2-=1"
    for (View button: Arrays.asList(
            button5, buttonPlus, button3, buttonEquals, buttonPlus, button6, buttonPlus, button2,
            buttonMinus, buttonEquals, button1
    )) {
      button.performClick();
    }

    assertEquals("161", textViewOutput.getText().toString());
  }

}
