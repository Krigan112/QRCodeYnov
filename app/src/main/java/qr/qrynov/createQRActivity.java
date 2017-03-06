package qr.qrynov;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;

public class createQRActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
        Button mybutton = (Button) findViewById(R.id.generateButton);
        mybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.generateButton){
            EditText inputQR = (EditText) findViewById(R.id.inputQR);
            Bitmap monImage = utils.QRActions.createQrCodeFromContent(String.valueOf(inputQR.getText()));
            ImageView monImageView = (ImageView) findViewById(R.id.imageViewCreateQrCode);
            monImageView.setImageBitmap(monImage);
        }

    }
}
