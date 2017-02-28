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

    private IntentIntegrator qrScan;
    private BitMatrix mybitmatrix;
    QRCodeWriter myQrCodeCreate = new QRCodeWriter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton mybutton = (FloatingActionButton) findViewById(R.id.fab);
        mybutton.setOnClickListener(this);
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

        return super.onOptionsItemSelected(item);
    }

        public void onActivityResult(int requestCode, int resultCode, Intent intent) {

            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null) {
                String scanContent = scanningResult.getContents();
                String scanFormat = scanningResult.getFormatName();
                TextView scan_format = (TextView) findViewById(R.id.TypeQRCode);
                TextView scan_content = (TextView) findViewById(R.id.ContentQRCode);
                int width = 150;
                int height = 150;
                try {
                    mybitmatrix=  myQrCodeCreate.encode(scanContent,BarcodeFormat.QR_CODE,width,height);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                Bitmap ImageBitmap = Bitmap.createBitmap(width,
                        height, Bitmap.Config.ARGB_8888);

                for (int i = 0; i < width; i++) {// width
                    for (int j = 0; j < height; j++) {// height
                        ImageBitmap.setPixel(i, j, mybitmatrix.get(i, j) ? Color.BLACK
                                : Color.WHITE);
                    }
                }
                ImageView QrCodeImageView = (ImageView) findViewById(R.id.imageViewQrcode);
                QrCodeImageView.setImageBitmap(ImageBitmap);





                scan_format.setText("FORMAT: " + scanFormat);
                scan_content.setText("CONTENT: " + scanContent);

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
            myintent.initiateScan();
            myintent.setOrientationLocked(false);
        }

    }
}