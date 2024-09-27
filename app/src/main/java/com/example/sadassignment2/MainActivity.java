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
        setContentView(R.layout.activity_main); // Ensure this matches your XML filename

        // Initialize views
        checkBoxShirt = findViewById(R.id.checkBoxShirt);
        checkBoxPants = findViewById(R.id.checkBoxPants);
        checkBoxShoes = findViewById(R.id.checkBoxShoes);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        ratingBarExperience = findViewById(R.id.ratingBarExperience);
        seekBarQuantity = findViewById(R.id.seekBarQuantity);
        quantityValue = findViewById(R.id.quantityValue);
        switchNotification = findViewById(R.id.switchNotification);
        orderButton = findViewById(R.id.order_btn);

        // Set up SeekBar listener to display selected quantity
        seekBarQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityValue.setText("Quantity: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }
        });

        // Set up the order button listener
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });
    }

    // Method to handle order submission
    private void submitOrder() {
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");

        // Check selected products
        if (checkBoxShirt.isChecked()) {
            orderSummary.append("- Shirt - 2000 TK\n");
        }
        if (checkBoxPants.isChecked()) {
            orderSummary.append("- Pants - 1500 TK\n");
        }
        if (checkBoxShoes.isChecked()) {
            orderSummary.append("- Shoes - 1999 TK\n");
        }

        // Get selected payment method
        int selectedPaymentId = radioGroupPayment.getCheckedRadioButtonId();
        RadioButton selectedPaymentButton = findViewById(selectedPaymentId);
        if (selectedPaymentButton != null) {
            orderSummary.append("Payment Method: ").append(selectedPaymentButton.getText()).append("\n");
        }

        // Get experience rating
        float rating = ratingBarExperience.getRating();
        orderSummary.append("Experience Rating: ").append(rating).append("\n");

        // Get quantity
        int quantity = seekBarQuantity.getProgress();
        orderSummary.append("Quantity: ").append(quantity).append("\n");

        // Check promotional notifications
        orderSummary.append("Receive Promotions: ").append(switchNotification.isChecked() ? "Yes" : "No").append("\n");

        // Display order summary in a Toast
        Toast.makeText(MainActivity.this, orderSummary.toString(), Toast.LENGTH_LONG).show();
    }
}
