package qr.qrynov;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;

public class createQRActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.generateButton){
            EditText inputQR = (EditText) findViewById(R.id.inputQR);
            utils.QRActions.createQrCodeFromContent(String.valueOf(inputQR.getText()));
        }
    }
}
