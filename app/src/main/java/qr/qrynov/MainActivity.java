package qr.qrynov;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
// on importe les classes IntentIntegrator et IntentResult de la librairie zxing
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private IntentIntegrator qrScan;
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
            new IntentIntegrator(this).initiateScan();
        }

    }
}
