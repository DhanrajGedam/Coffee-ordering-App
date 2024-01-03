package com.example.justjava;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;
    boolean whip,cho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder (View view){
        CheckBox check=(CheckBox) findViewById(R.id.Whipped_cream);
       boolean hasWhippedCream=check.isChecked();

        CheckBox ch=(CheckBox) findViewById(R.id.chocolate);
        boolean chocolate= ch.isChecked();

        EditText txt=(EditText)  findViewById(R.id.name);
         Editable name1=txt.getText();

        int price= calculatePrice();

       String name= createOrderSummary(hasWhippedCream,chocolate,name1);

       whip=hasWhippedCream;
        cho=chocolate;

       displayMessage(name+"\n"+"Quantity:"+quantity+"\n"+"Total: "+price+"\nThank You!!!");

         Intent intent = new Intent(Intent.ACTION_SENDTO);
         intent.setData(Uri.parse("mailto:")); // only email apps should handle this
         intent.putExtra(Intent.EXTRA_TEXT,name+"\n"+"Quantity:"+quantity+"\n"+"Total: "+price+"\nThank You!!!" );
         intent.putExtra(Intent.EXTRA_SUBJECT, "NEED COFFEE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
          }






    }

    public void increment (View view){
        if(quantity==100) {
            Toast.makeText(this, "we cant serve more than 100", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity=quantity+1;
            displayQuantity(quantity);


         }



    public void decerement (View view){

            if(quantity<=-1)  {
                Toast.makeText(this, "we cant serve negative", Toast.LENGTH_SHORT).show();
                return;
            }

            quantity=quantity-1;
            displayQuantity(quantity);

         }

    private void displayQuantity(int aditya) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+aditya);
    }


    private int calculatePrice() {
           int price=5;

            if(whip==true)
            {
                price=price+1;
            }

        if(cho==true)
        {
            price=price+2;
        }



           int mrp= quantity*price;
        return mrp;
    }






    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView .setText(message);
    }

    private String createOrderSummary(boolean cream,boolean chocolate,Editable aditya){

        String name= "\nEnter your name:"+aditya;
        name+="\nAdd Whipped Cream ?"+cream;
        name+="\nAdd Chocolate?"+chocolate;
        return name;



    }




}
