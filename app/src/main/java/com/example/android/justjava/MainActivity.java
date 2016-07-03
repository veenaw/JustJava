package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantity = 1, price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("MainActivity", "hasWhipedCream =" + hasWhippedCream);
        int price = calculatePrice(quantity);
        String priceMessage = createOrderSummary(price, hasWhippedCream);
        displayMessage(priceMessage);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        displayPrice(quantity * price);
    }

    public void decrement(View view) {

        quantity = quantity - 1;
        displayQuantity(quantity);
        displayPrice(quantity * price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }

    /**
     * Calculates the price of the order.
     *
     * @param price           is the price of coffee
     * @param addWhippedCream whether or not user want whippedCream topping
     * @return summary string
     */
    private String createOrderSummary(int price, boolean addWhippedCream) {
        String priceMessage = "Name : Veena Avinash Wagle";
        priceMessage += "\nAdd whipped cream ?" + addWhippedCream;
        priceMessage += "\nQuantity =" + quantity;
        priceMessage += "\nTotal = " + price;
        priceMessage += "\nThank You !!";
        return priceMessage;
    }
}