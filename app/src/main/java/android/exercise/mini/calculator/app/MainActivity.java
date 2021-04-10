package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }
    // find all views
    TextView button0 =  findViewById(R.id.button0);
    TextView button1 =  findViewById(R.id.button1);
    TextView button2 =  findViewById(R.id.button2);
    TextView button3 =  findViewById(R.id.button3);
    TextView button4 =  findViewById(R.id.button4);
    TextView button5 =  findViewById(R.id.button5);
    TextView button6 =  findViewById(R.id.button6);
    TextView button7 =  findViewById(R.id.button7);
    TextView button8 =  findViewById(R.id.button8);
    TextView button9 =  findViewById(R.id.button9);
    TextView clearButton =  findViewById(R.id.buttonClear);
    TextView equalsButton =  findViewById(R.id.buttonEquals);
    TextView minusButton =  findViewById(R.id.buttonMinus);
    TextView plusButton =  findViewById(R.id.buttonPlus);
    View backspaceButton = findViewById(R.id.buttonBackSpace);
    TextView mainTextView = findViewById(R.id.textViewCalculatorOutput);

    // setup visibility of mainTextView and initialize to current output
    mainTextView.setText(calculator.output());
    mainTextView.setVisibility(View.VISIBLE);

    button0.setOnClickListener(v -> {
      calculator.insertDigit(0);
      mainTextView.setText(calculator.output());
    });

    button1.setOnClickListener(v -> {
      calculator.insertDigit(1);
      mainTextView.setText(calculator.output());
    });

    button2.setOnClickListener(v -> {
      calculator.insertDigit(2);
      mainTextView.setText(calculator.output());
    });

    button3.setOnClickListener(v -> {
      calculator.insertDigit(3);
      mainTextView.setText(calculator.output());
    });

    button4.setOnClickListener(v -> {
      calculator.insertDigit(4);
      mainTextView.setText(calculator.output());
    });

    button5.setOnClickListener(v -> {
      calculator.insertDigit(5);
      mainTextView.setText(calculator.output());
    });

    button6.setOnClickListener(v -> {
      calculator.insertDigit(6);
      mainTextView.setText(calculator.output());
    });

    button7.setOnClickListener(v -> {
      calculator.insertDigit(7);
      mainTextView.setText(calculator.output());
    });

    button8.setOnClickListener(v -> {
      calculator.insertDigit(8);
      mainTextView.setText(calculator.output());
    });

    button9.setOnClickListener(v -> {
      calculator.insertDigit(9);
      mainTextView.setText(calculator.output());
    });

    clearButton.setOnClickListener(v -> {
      calculator.clear();
      mainTextView.setText(calculator.output());
    });

    equalsButton.setOnClickListener(v -> {
      calculator.insertEquals();
      mainTextView.setText(calculator.output());
    });

    minusButton.setOnClickListener(v -> {
      calculator.insertMinus();
      mainTextView.setText(calculator.output());
    });

    plusButton.setOnClickListener(v -> {
      calculator.insertPlus();
      mainTextView.setText(calculator.output());
    });

    backspaceButton.setOnClickListener(v -> {
      calculator.deleteLast();
      mainTextView.setText(calculator.output());
    });

  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("calculator state", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Serializable savedState = savedInstanceState.getSerializable("calculator state");
    TextView mainTextView = findViewById(R.id.textViewCalculatorOutput);
    calculator.loadState(savedState);
    mainTextView.setText(calculator.output());
  }
}