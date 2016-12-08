package www.luoyin.com.jni;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import www.luoyin.com.R;
import www.luoyin.com.jni.complicated.MaybeUtils;


public class Jni01Activity extends AppCompatActivity
{

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni01);

        mTv = (TextView) findViewById(R.id.id_textview);
        JniTest jniTest = new JniTest();
        String res = jniTest.welcome("张鸿洋");
        mTv.setText(res);
        Thread thread = new Thread();
        MaybeUtils generate = MaybeUtils.generate();
        Log.e("TAG", generate.getNum()+"");

        AlertDialog.Builder b = new AlertDialog.Builder(this);


    }


}
