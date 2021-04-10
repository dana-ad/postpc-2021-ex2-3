package android.exercise.mini.calculator.app;
import java.io.Serializable;
import java.util.ArrayList;

public class SimpleCalculatorImpl implements SimpleCalculator {

  private String current_str = "";
  private int first_num;
  boolean first_num_exists = false;
  private boolean last_input_is_num = false;
  private char last_sign_input;
  private String last_num_input = "";
  private ArrayList<Character> operations = new ArrayList<>();

  @Override
  public String output() {
    if (current_str.equals("")){
      return "0";
    }
    return current_str;
  }

  @Override
  public void insertDigit(int digit) {
    if (digit >= 0 && digit <=9) {
      if (current_str.equals("0"))
        current_str = Integer.toString(digit);
      else
        current_str += Integer.toString(digit);
      last_num_input += Integer.toString(digit);
      last_input_is_num = true;
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  private String delete_last_helper(String to_edit){
    if (to_edit.length() > 1)
      return to_edit.substring(0, to_edit.length()-1);
    return "";
  }

  private int operate(int first, int second) {
    if (last_sign_input == '+')
      return first + second;
    return first - second;
  }

  private void operator_called(){
    if (!first_num_exists)
      first_num = Integer.parseInt(last_num_input);
    else if (!last_num_input.equals("")){
      int second_num = Integer.parseInt(last_num_input);
      first_num = operate(first_num, second_num);
    }
    first_num_exists = true;
    last_num_input = "";
    last_input_is_num = false;
  }


  @Override
  public void insertPlus() {
    if (current_str.equals("")){
      insertDigit(0);
    }
    if (last_input_is_num) {
      current_str += '+';
      operator_called();
      last_sign_input = '+';
      operations.add('+');
    }
  }

    @Override
    public void insertMinus () {
    if (current_str.equals("")){
        insertDigit(0);
    }
    if (last_input_is_num) {
      current_str += '-';
      operator_called();
      last_sign_input = '-';
      operations.add('-');
    }
  }

    @Override
    public void insertEquals () {
      if (current_str.equals("")){
        insertDigit(0);
      }
      if (!last_input_is_num){
        current_str = delete_last_helper(current_str);
        if (operations.size() > 0)
          operations.remove(operations.size() - 1);
        if (operations.size() > 0)
          last_sign_input = operations.get(operations.size() - 1);
      }
      operator_called();
      current_str = Integer.toString(first_num);
      operations = new ArrayList<>();
      last_input_is_num = true;
    }

    @Override
    public void deleteLast () {
      if (current_str.length() > 0) {
        if (last_input_is_num){
          last_num_input = delete_last_helper(last_num_input);
          if (last_num_input.length() == 0)
            last_input_is_num = false;
        }
        else {
          operations.remove(operations.size() - 1);
          if (operations.size() > 0)
            last_sign_input = operations.get(operations.size() - 1);
          last_input_is_num = true;
        }
        current_str = delete_last_helper(current_str);
      }
    }

    @Override
    public void clear () {
      current_str = "";
      first_num_exists = false;
      last_input_is_num = false;
      last_num_input = "";
      operations = new ArrayList<>();
    }

    @Override
    public Serializable saveState () {
      return new CalculatorState(current_str, last_num_input, last_sign_input,
              first_num, first_num_exists, last_input_is_num, operations);
    }

    @Override
    public void loadState (Serializable prevState){
      if (!(prevState instanceof CalculatorState)) {
        return; // ignore
      }
      CalculatorState casted = (CalculatorState) prevState;
      this.current_str = casted.current_str;
      this.last_num_input = casted.last_num_input;
      this.last_sign_input = casted.last_sign_input;
      this.first_num = casted.first_num;
      this.first_num_exists = casted.first_num_exists;
      this.last_input_is_num = casted.last_input_is_num;
      this.operations = casted.operations;
    }

    private static class CalculatorState implements Serializable {
      private String current_str;
      private String last_num_input;
      private char last_sign_input;
      private int first_num;
      private boolean first_num_exists;
      private boolean last_input_is_num;
      private ArrayList<Character> operations;

      CalculatorState(String current_str, String last_num_input, char last_sign_input,
                      int first_num, boolean first_num_exists, boolean last_input_is_num,
                      ArrayList<Character> operations){
        this.current_str = current_str;
        this.last_num_input = last_num_input;
        this.last_sign_input = last_sign_input;
        this.first_num = first_num;
        this.first_num_exists = first_num_exists;
        this.last_input_is_num = last_input_is_num;
        this.operations = operations;
      }

    }
  }

