package qr.qrynov;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;

class utils {
    static class QRActions {

        static BitMatrix mybitmatrix;
        static QRCodeWriter myQrCodeCreate = new QRCodeWriter();

        static Bitmap createQrCodeFromContent(String content) {

            int height = 300;
            int width = 300;

            Bitmap ImageBitmap = Bitmap.createBitmap(width,
                    height, Bitmap.Config.ARGB_8888);

            if (content != null && !Objects.equals(content, "")) {

                try {
                    mybitmatrix = myQrCodeCreate.encode(content, BarcodeFormat.QR_CODE, width, height);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        ImageBitmap.setPixel(i, j, mybitmatrix.get(i, j) ? Color.BLACK
                                : Color.WHITE);
                    }
                }
            }
            return ImageBitmap;
        }
    }

    static  class QrStorage{
        static void WriteContents(Context context, String data){
            FileOutputStream fOut = null;
            OutputStreamWriter osw = null;
            File f = new File(Environment.getExternalStorageDirectory(), "Android/data/QRCodeYnov/Qr_code");
            try{
                fOut = context.openFileOutput(f.getName(),Context.MODE_APPEND);
                osw = new OutputStreamWriter(fOut);
                osw.write(data);
                osw.flush();
                //popup surgissant pour le résultat
                Toast.makeText(context, "Settings saved",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show();
            }
            finally {
                try {
                    osw.close();
                    fOut.close();
                } catch (IOException e) {
                    Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show();
                }
            }


        }
        static String ReadSettings(Context context){
            FileInputStream fIn = null;
            InputStreamReader isr = null;

            char[] inputBuffer = new char[255];
            String data = null;
            try{
                fIn = context.openFileInput("Android/data/QRCodeYnov/Qr_code");
                isr = new InputStreamReader(fIn);
                isr.read(inputBuffer);
                data = new String(inputBuffer);
                //affiche le contenu de mon fichier dans un popup surgissant
                Toast.makeText(context, " "+data,Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(context, "Settings not read",Toast.LENGTH_SHORT).show();
            }
            /*finally {
               try {
                      isr.close();
                      fIn.close();
                      } catch (IOException e) {
                        Toast.makeText(context, "Settings not read",Toast.LENGTH_SHORT).show();
                      }
            } */
            return data;
        }
    }

}




