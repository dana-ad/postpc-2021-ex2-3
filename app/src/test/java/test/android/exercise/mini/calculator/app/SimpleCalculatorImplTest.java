package test.android.exercise.mini.calculator.app;
import android.exercise.mini.calculator.app.SimpleCalculatorImpl;
import org.junit.Test;
import java.io.Serializable;
import static org.junit.Assert.*;
public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.deleteLast();
    assertEquals("4", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // give some input
    firstCalculator.insertDigit(8);
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(3);
    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);
    // load the saved state to second calc and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("8-3", secondCalculator.output());
  }

  @Test
  public void check_delete_inTheMiddle_of_operation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(1);
    // delete last
    calculatorUnderTest.deleteLast();
    // give some input
    calculatorUnderTest.insertDigit(2);
    assertEquals("5+8-12", calculatorUnderTest.output());
  }

  @Test
  public void use_deleteLast_manyTimes_inARow(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(4);
    // delete until last digit
    for(int i =1; i<=4 ; i++) {
      calculatorUnderTest.deleteLast();
    }
    assertEquals("6", calculatorUnderTest.output());
    // delete last digit
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
    // delete way after the output is empty
    for(int i =1; i<=4 ; i++) {
      calculatorUnderTest.deleteLast();
    }
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void check_clear_inTheMiddle_of_operation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(9);
    // clear
    calculatorUnderTest.clear();
    // give some input
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(6);
    // clear
    calculatorUnderTest.clear();
    // give some input
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    // calc 8-4
    assertEquals("4", calculatorUnderTest.output());


  }
  //"8-3=+2=-1="
  @Test
  public void check_responseTo_equal_inTheMiddle_of_operation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("6", calculatorUnderTest.output());
  }

  // given input "999-444-222=-333", expected output is "333-333"
  @Test
  public void check_responseTo_operation_rightAfter_equal(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    for (int i = 0; i < 3; i++){
      calculatorUnderTest.insertDigit(9);
    }
    calculatorUnderTest.insertMinus();
    for (int i = 0; i < 3; i++){
      calculatorUnderTest.insertDigit(4);
    }
    calculatorUnderTest.insertMinus();
    for (int i = 0; i < 3; i++){
      calculatorUnderTest.insertDigit(2);
    }
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    for (int i = 0; i < 3; i++){
      calculatorUnderTest.insertDigit(3);
    }
    assertEquals("333-333", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());

  }
  @Test
  public void calling_delete_when_noInput_isGiven_shouldResult0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }


  @Test
  public void check_different_input_toDifferent_calculators_and_loadStateTo_oneAnother(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(5);
    secondCalculator.insertDigit(6);
    secondCalculator.insertPlus();
    secondCalculator.insertDigit(1);
    Serializable savedState = firstCalculator.saveState();
    secondCalculator.loadState(savedState);
    assertEquals("0-5", secondCalculator.output());
  }

  // 546-13+54-976+3-19=
  @Test
  public void calculate_ABig_and_difficult_sequence(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(9);
    assertEquals("546-13+54-976+3-19", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("-405", calculatorUnderTest.output());
  }

  @Test
  public void use_operations_rightAfter_AnAction_of_clear_Delete_Or_Operation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertEquals();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertMinus();
    assertEquals("9-", calculatorUnderTest.output());
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    assertEquals("9-3", calculatorUnderTest.output());
  }

  @Test
  public void do_many_operations_inARow_and_check_ifOutput_isCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    assertEquals("82+", calculatorUnderTest.output());
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    assertEquals("82-3", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("79", calculatorUnderTest.output());
  }

  @Test
  public void get_negative_number_after_calculation_and_keep_calculation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    assertEquals("-47", calculatorUnderTest.output());
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(6);
    assertEquals("-47+7-56", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("-96", calculatorUnderTest.output());
  }

  @Test
  public void switch_state_of_two_calculators(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(3);
    firstCalculator.insertDigit(9);
    secondCalculator.insertDigit(7);
    secondCalculator.insertMinus();
    secondCalculator.insertDigit(1);
    Serializable firstSaved = firstCalculator.saveState();
    Serializable secondSaved = secondCalculator.saveState();
    secondCalculator.loadState(firstSaved);
    firstCalculator.loadState(secondSaved);
    assertEquals("0+39", secondCalculator.output());
    assertEquals("7-1", firstCalculator.output());
  }

}