package qr.qrynov;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class createQRActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView CreatedQR;
    private Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
        CreatedQR = (ImageView) findViewById(R.id.QRCreated);
        generate = (Button) findViewById(R.id.generateButton);
        generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.generateButton){
            EditText inputQR = (EditText) findViewById(R.id.inputQR);
            Bitmap QRBitmap = utils.QRActions.createQrCodeFromContent(String.valueOf(inputQR.getText()));
            CreatedQR.setImageBitmap(QRBitmap);
        }
    }
}
