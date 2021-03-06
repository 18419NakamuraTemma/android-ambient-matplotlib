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
				
                PyObject pyo = py.getModule("main"); //main.pyの呼び出し
                PyObject obj = pyo.callAttr("main"); //main.py内にあるmainの呼び出し

                String str = obj.toString(); //返ってきた値をString型に変換

                byte data[] = android.util.Base64.decode(str,Base64.DEFAULT); //String型をbyte型に変換

                Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length); //変換したbyteのリストからBitmapを作成
                if(bmp != null)
                    iv.setImageBitmap(bmp); //BitmapをImageviewに反映

            }
        });

    }
}