package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onOrder(View view){
        display(quantity);
        displayPrice(calculatePrice(quantity));
        TextView orderSummary = findViewById(R.id.order_summary);
        orderSummary.setText(R.string.summary);
        CheckBox checkBox = findViewById(R.id.whipping_cream);
        CheckBox checkBox1 = findViewById(R.id.chocolate);
        int price = calculatePrice(quantity);
        EditText editText = findViewById(R.id.name_field);
        if(checkBox.isChecked()){
            price += 10;
            displayPrice(price);
        }
        if(checkBox1.isChecked()){
            price += 20;
            displayPrice(price);
        }
        String priceMessage = "Name: "+editText.getText()+"\nQuantity: "+quantity+"\nTotal: $" +price+ "\nis Whipped Cream checked?: "+ checkBox.isChecked()+"\nis Chocolate checked? "+checkBox1.isChecked()+ "\nThank You";
        Toast.makeText(this,"thank you for using our app",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "This is the order");
        intent.putExtra(intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }
    }
    private int calculatePrice(int quantity){
        return quantity*2;
    }
    private void display(int num){
        TextView quantity = findViewById(R.id.quantity_text_view);
        String quantityOfCoffees = Integer.toString(num);
        quantity.setText(quantityOfCoffees);
    }
    private void displayPrice(int number) {
        TextView priceTextView =  findViewById(R.id.price_text_view);
        String value = "Total: $" + (number);
        priceTextView.setText(value);
    }
    public void increment(View view){
        display(++quantity);
        displayPrice(calculatePrice(quantity));
    }
    public void decrement(View view){
        --quantity;
        if(quantity<0) {
            Toast.makeText(getApplicationContext(), "quantity cannot be less than 0", Toast.LENGTH_SHORT).show();
            quantity = 0;
        }
        display(quantity);
        displayPrice(calculatePrice(quantity));
    }
}
