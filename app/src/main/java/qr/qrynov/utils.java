package qr.qrynov;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

public class utils {
    public static class QRActions{

        static BitMatrix mybitmatrix;
        static QRCodeWriter myQrCodeCreate = new QRCodeWriter();

        public static Bitmap createQrCodeFromContent(String content) {

            int height = 300;
            int width = 300;

            Bitmap ImageBitmap = Bitmap.createBitmap(width,
                    height, Bitmap.Config.ARGB_8888);

            if (content != null && content != "") {

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
}
