package com.dd.test.puzzleview_android.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dd.test.puzzleview_android.R;
import com.dd.test.puzzleview_android.activity.view.TopView;

public class MainActivity extends AppCompatActivity {

    private TopView topView;
    private Button picSelectBtn;
    private ImageView picShowImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topView = (TopView) findViewById(R.id.top_view);
        picSelectBtn = (Button) findViewById(R.id.pic_select);
        picShowImageView = (ImageView) findViewById(R.id.pic_show);

        topView.setTitle("puzzle-android");
        topView.hide(TopView.LEFT);

        picSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToPuzzle = new Intent(MainActivity.this, PuzzlePickerActivity.class);
                startActivity(intentToPuzzle);
            }
        });
    }


}
