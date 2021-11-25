package mju.com.station;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReadStationExcel re = new ReadStationExcel(getBaseContext());
        re.readStationExcel();
        ReadAmenitiesExcel ra = new ReadAmenitiesExcel(getBaseContext());
        ra.readAmenitiesExcel();
    }

}