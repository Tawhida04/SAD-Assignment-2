package com.example.sadassignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxShirt, checkBoxPants, checkBoxShoes;
    private RadioGroup radioGroupPayment;
    private RatingBar ratingBarExperience;
    private SeekBar seekBarQuantity;
    private TextView quantityValue;
    private Switch switchNotification;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxShirt = findViewById(R.id.checkBoxShirt);
        checkBoxPants = findViewById(R.id.checkBoxPants);
        checkBoxShoes = findViewById(R.id.checkBoxShoes);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        ratingBarExperience = findViewById(R.id.ratingBarExperience);
        seekBarQuantity = findViewById(R.id.seekBarQuantity);
        quantityValue = findViewById(R.id.quantityValue);
        switchNotification = findViewById(R.id.switchNotification);
        orderButton = findViewById(R.id.order_btn);

        seekBarQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityValue.setText("Quantity: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });
    }

    private void submitOrder() {
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");

        if (checkBoxShirt.isChecked()) {
            orderSummary.append("- Shirt - 2000 TK\n");
        }
        if (checkBoxPants.isChecked()) {
            orderSummary.append("- Pants - 1500 TK\n");
        }
        if (checkBoxShoes.isChecked()) {
            orderSummary.append("- Shoes - 1999 TK\n");
        }

        int selectedPaymentId = radioGroupPayment.getCheckedRadioButtonId();
        RadioButton selectedPaymentButton = findViewById(selectedPaymentId);
        if (selectedPaymentButton != null) {
            orderSummary.append("Payment Method: ").append(selectedPaymentButton.getText()).append("\n");
        }

        float rating = ratingBarExperience.getRating();
        orderSummary.append("Experience Rating: ").append(rating).append("\n");

        int quantity = seekBarQuantity.getProgress();
        orderSummary.append("Quantity: ").append(quantity).append("\n");

        orderSummary.append("Receive Promotions: ").append(switchNotification.isChecked() ? "Yes" : "No").append("\n");

        Toast.makeText(MainActivity.this, orderSummary.toString(), Toast.LENGTH_LONG).show();
    }
}
