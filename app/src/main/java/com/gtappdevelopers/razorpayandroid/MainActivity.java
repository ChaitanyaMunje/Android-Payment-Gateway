package com.gtappdevelopers.razorpayandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    //variables for our edit tect and button.
    private EditText amountEdt;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing all our variables.
        amountEdt = findViewById(R.id.idEdtAmount);
        payBtn = findViewById(R.id.idBtnPay);
        //adding on click listner to our button.
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line we are getting amount that is entered by user.
                String samount = amountEdt.getText().toString();
                //rounding off the amount.
                int amount = Math.round(Float.parseFloat(samount) * 100);
                //initialize razorpay account.
                Checkout checkout = new Checkout();
                //set your id as below
                checkout.setKeyID("Enter your key id here ");
                //set image
                checkout.setImage(R.drawable.gfgimage);
                //initialize json object
                JSONObject object = new JSONObject();
                try {
                    //to put name
                    object.put("name", "Geeks for Geeks");
                    //put description
                    object.put("description", "Test payment");
                    //to set theme color
                    object.put("theme.color", "");
                    //put the currency
                    object.put("currency", "INR");
                    //put amount
                    object.put("amount", amount);
                    //put mobile number
                    object.put("prefill.contact", "9284064503");
                    //put email
                    object.put("prefill.email", "chaitanyamunje@gmail.com");
                    //open razorpay to checkout activity
                    checkout.open(MainActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        //this method is called on payment success.
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

        //on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}