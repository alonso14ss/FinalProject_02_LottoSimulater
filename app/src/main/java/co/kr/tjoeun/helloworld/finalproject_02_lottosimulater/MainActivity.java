package co.kr.tjoeun.helloworld.finalproject_02_lottosimulater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import co.kr.tjoeun.helloworld.finalproject_02_lottosimulater.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }
}
