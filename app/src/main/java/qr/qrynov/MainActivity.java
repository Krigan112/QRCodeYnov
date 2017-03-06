package qr.qrynov;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;


// on importe les classes IntentIntegrator et IntentResult de la librairie zxing
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView myImageView;
    private IntentIntegrator qrScan;
    private BitMatrix mybitmatrix;
    QRCodeWriter myQrCodeCreate = new QRCodeWriter();
    private Bitmap ImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView MyTextView = (TextView) findViewById(R.id.ContentQRCode);
        MyTextView.setText("");
        setSupportActionBar(toolbar);
        FloatingActionButton mybutton = (FloatingActionButton) findViewById(R.id.fab);
        mybutton.setOnClickListener(this);
        myImageView = (ImageView) findViewById(R.id.imageViewQrcode);
        myImageView.setVisibility(View.INVISIBLE);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add_Qr_code)
        {
            Intent createQrIntent = new Intent(this,createQRActivity.class);
            startActivity(createQrIntent);
        }
        if (id== R.id.action_liststorage)
        {
            Intent ShowStorage = new Intent(this,List.class);
            startActivity(ShowStorage);
        }

        return super.onOptionsItemSelected(item);
    }

        public void onActivityResult(int requestCode, int resultCode, Intent intent) {

            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null) {
                //Get scan result
                String scanContent = scanningResult.getContents();
                String scanFormat = scanningResult.getFormatName();
                TextView scan_format = (TextView) findViewById(R.id.TypeQRCode);
                TextView scan_content = (TextView) findViewById(R.id.ContentQRCode);
                // scan_format.setText("FORMAT: " + scanFormat);
                //Place scan result
                scan_content.setText("Contenu : " + scanContent);
                utils.QrStorage.WriteContents(this,scanContent);
                utils.QrStorage.ReadSettings(this);
                TextView welcomeMessage = (TextView) findViewById(R.id.WelcomeTxt);
                welcomeMessage.setText("");

                // Recreate the QrCode from content
                ImageBitmap = utils.QRActions.createQrCodeFromContent(scanContent);
                ImageView QrCodeImageView = (ImageView) findViewById(R.id.imageViewQrcode);
                QrCodeImageView.setImageBitmap(ImageBitmap);
                QrCodeImageView.setVisibility(View.VISIBLE);



            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Aucune donnée reçu!", Toast.LENGTH_SHORT);
                toast.show();
            }

        }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
           IntentIntegrator myintent =  new IntentIntegrator(this);
            // initiate the QrCode Scan
            myintent.initiateScan();
            myintent.setOrientationLocked(false);
        }

    }
}
