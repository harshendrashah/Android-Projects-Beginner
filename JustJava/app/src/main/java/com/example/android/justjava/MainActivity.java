/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameOfCustomer = (EditText) findViewById(R.id.name_field);
        String customerName = nameOfCustomer.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calclulatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price, customerName, hasWhippedCream, hasChocolate);
        //to compose an email of order summary
        composeEmail(priceMessage,customerName);
        displayMessage(priceMessage)
        ;
    }

    public void incrementQuantity(View view) {
        if(quantity==100){
            //Error meassagge as Toast beacause you can not have more than 100 cups of coffee
            Toast.makeText(this,"Maximum Order limit reached",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrementQuantity(View view) {
        if (quantity == 1) {
            //Error message as Toast beaceuse order at least contain 1 cup of coffee
            Toast.makeText(this,"Order should at least conatain one cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method calculates total order price
     * @param hasWhippedCream checks the order has WHipped Cream or not
     * @param hasChocolate checks the order has Chocolate or not
     * @return total price of order
     */
    private int calclulatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (hasChocolate) {
            basePrice +=  2;
        }
        if (hasWhippedCream){
            basePrice += 1 ;
        }
        return basePrice * quantity;
    }

    /**
     * This Method creats order summary
     * @param price is total price of order
     * @param customerName is name of the customer
     * @param hasWhippedCream checks the order has WHipped Cream or not
     * @param hasChocolate checks the order has Chocolate or not
     * @return final order summary
     */
    private String createOrderSummary(int price, String customerName, boolean hasWhippedCream, boolean hasChocolate) {

        String summary = getString(R.string.order_summary_name,customerName)+ "\n";
        summary += getString(R.string.quantity) + " : " + quantity + "\n";
        if(hasWhippedCream){
            summary+=getString(R.string.order_summary_whipped_cream) + "\n";
        }
        if(hasChocolate){
            summary+=getString(R.string.order_summary_chocolate) + "\n";
        }
        summary += getString(R.string.total_price) + price;
        summary += "\n" +getString(R.string.thank_you);
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }

    /**
     * this method is for composing an email of the order summary
     * @param orderSummary is the summary of the order
     * @param customerName is name of the customer
     */
    public void composeEmail( String orderSummary, String customerName) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + customerName);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}