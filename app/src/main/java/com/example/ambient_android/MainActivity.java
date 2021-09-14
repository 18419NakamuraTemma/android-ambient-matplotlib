package com.example.ambient_android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);

        iv = (ImageView)findViewById(R.id.image_view);
        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        final Python py = Python.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				
                PyObject pyo = py.getModule("main"); //main.py‚ÌŒÄ‚Ño‚µ
                PyObject obj = pyo.callAttr("main"); //main.py“à‚É‚ ‚émain‚ÌŒÄ‚Ño‚µ

                String str = obj.toString(); //•Ô‚Á‚Ä‚«‚½’l‚ğStringŒ^‚É•ÏŠ·

                byte data[] = android.util.Base64.decode(str,Base64.DEFAULT); //StringŒ^‚ğbyteŒ^‚É•ÏŠ·

                Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length); //•ÏŠ·‚µ‚½byte‚ÌƒŠƒXƒg‚©‚çBitmap‚ğì¬
                if(bmp != null)
                    iv.setImageBitmap(bmp); //Bitmap‚ğImageview‚É”½‰f

            }
        });

    }
}