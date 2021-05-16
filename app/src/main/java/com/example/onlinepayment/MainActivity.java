package com.example.onlinepayment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.razorpay.Checkout;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    
    Button button;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // how to get order id
                // using https and postman
                // documentation url https://razorpay.com/docs/payment-gateway/android-integration/standard/
                // part 3 ?????
                Checkout checkout = new Checkout();
                Checkout.preload(getApplicationContext());
                checkout.setKeyID("rzp_test_hefX7w1gE6cLmq");
                checkout.setImage(R.mipmap.ic_launcher);
                final Activity activity = MainActivity.this;
                try {
                    JSONObject options = new JSONObject();
                    options.put("name", "Botlife");
                    options.put("description", "All the best");
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                    options.put("theme.color", "#000000");
                    options.put("currency", "INR");
                    options.put("amount", 1000);//pass amount in currency subunits
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);
                    options.put("retry", retryObj);
                    checkout.open(activity, options);
                } catch(Exception e) {
                    Log.e(TAG, "Error in starting Razorpay Checkout", e);
                }

            }
        });
    }
}