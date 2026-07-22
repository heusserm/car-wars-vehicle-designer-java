package com.xndev.carwarsvehicledesignerjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private static final String POLICY_URL = "https://www.sjgames.com/general/online_policy.html";
    private static final String STORE_URL = "https://warehouse23.com/collections/car-wars-classic";

    private static final String ATTRIBUTION_HTML =
            "Car Wars Vehicle Designer is a free game aid for the Car Wars Compendium and prior "
                    + "versions of the game, used with permission (<a href=\"" + POLICY_URL
                    + "\">SJG Online Policy</a>). This app does not provide a standalone game. You can "
                    + "purchase the classic versions of the game that this aid supports at <a href=\""
                    + STORE_URL + "\">Warehouse 23</a>."
                    + "<br><br>Application designed and implemented by Matthew Heusser · "
                    + "<a href=\"mailto:Matt@xndev.com\">Matt@xndev.com</a>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.buttonViewVehicles).setOnClickListener(v ->
                startActivity(new Intent(this, VehicleListActivity.class)));

        findViewById(R.id.buttonDesignVehicle).setOnClickListener(v ->
                startActivity(new Intent(this, DesignVehicleActivity.class)));

        TextView attribution = findViewById(R.id.textAttribution);
        attribution.setText(Html.fromHtml(ATTRIBUTION_HTML, Html.FROM_HTML_MODE_LEGACY));
        attribution.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
