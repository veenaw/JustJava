package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        //Log.v("MainActivity", "hasWhipedCream =" + hasWhippedCream);

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        //Log.v("MainActivity", "hasChocolate =" + hasChocolate);

        EditText nameEditText = (EditText) findViewById(R.id.name_text_view);
        String name = nameEditText.getText().toString();
        //Log.v("MainActivity", "name=" + name);

        String priceMessage;
        int price = calculatePrice(quantity, hasWhippedCream, hasChocolate);
        priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        //displayMessage(priceMessage);

        composeEmail(name, priceMessage);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot more than 100 coffees!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
        displayPrice(quantity * price);

    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffees!!!", Toast.LENGTH_SHORT).show();
            return;
        }
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
     * @param quantity        is the number of cups of coffee ordered
     * @param addWhippedCream whether or not user want whippedCream topping
     * @param addChocolate    whether or not user wants chocolate topping
     */
    private int calculatePrice(int quantity, boolean addWhippedCream, boolean addChocolate) {
        price = 5;
        if (addWhippedCream)
            price += 1;

        if (addChocolate)
            price += 2;

        int total = quantity * price;
        return total;
    }

    /**
     * Calculates the price of the order.
     *
     * @param price           is the price of coffee
     * @param addWhippedCream whether or not user want whippedCream topping
     * @param addChocolate    whether or not user wants chocolate topping
     * @return summary string
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name : " + name;
        priceMessage += "\nAdd whipped cream ?" + addWhippedCream;
        priceMessage += "\n Add Chocolate?" + addChocolate;
        priceMessage += "\nQuantity =" + quantity;
        priceMessage += "\nTotal = " + price;
        priceMessage += "\nThank You !!";
        return priceMessage;
    }

    /*
    *
    * @param name Name of customer
    * @param orderSummary body of mail
     */
    public void composeEmail(String name, String orderSummary) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}