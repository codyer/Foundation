package foundation.chinaredstar.com.demos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import foundation.chinaredstar.com.demos.dialog.DialogDemoActivity;
import foundation.chinaredstar.com.demos.piker.PikerDemoActivity;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        findViewById(R.id.timePicker).setOnClickListener(this);
        findViewById(R.id.iosDialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.timePicker:
                intent = new Intent(DemoActivity.this, PikerDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.iosDialog:
                intent = new Intent(DemoActivity.this, DialogDemoActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }
}
