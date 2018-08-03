package com.example.wifitest;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    Button on, off, connect;
    TextView textView;
    WifiManager mainWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        on = findViewById(R.id.button2);
        off = findViewById(R.id.button);
        connect = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offButtonClick();
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectButtonClick();
            }
        });

    }

    //this is used to connect with the given wifi network
    private void connectButtonClick() {

        String networkSSID = "HPHOTSPOT";
        String networkPass = "123456789";

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        mainWifi.addNetwork(conf);
        List<WifiConfiguration> list = mainWifi.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                mainWifi.disconnect();
                mainWifi.enableNetwork(i.networkId, true);
                mainWifi.reconnect();

                break;
            }
        }
    }

    private void onButtonClick() {
        if (!mainWifi.isWifiEnabled()) {
            mainWifi.setWifiEnabled(true);
            System.out.println("WiFi enabled");
        }
        //for(String a: receiverWifi)
    }

    private void offButtonClick() {
        if (mainWifi.isWifiEnabled()) {
            mainWifi.setWifiEnabled(false);
            System.out.println("WiFi Disabled");
        }
    }


}
