package com.dd.test.puzzleview_android.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.test.puzzleview_android.R;
import com.dd.test.puzzleview_android.activity.dialog.TemplateDialog;
import com.dd.test.puzzleview_android.activity.entity.ImageBean;
import com.dd.test.puzzleview_android.activity.entity.ImageItem;
import com.dd.test.puzzleview_android.activity.entity.Puzzle;
import com.dd.test.puzzleview_android.activity.util.FileUtil;
import com.dd.test.puzzleview_android.activity.view.PuzzleView;
import com.dd.test.puzzleview_android.activity.view.TopView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by dd on 16/1/13.
 * 拼图主界面
 */
public class PuzzleActivity extends Activity implements View.OnClickListener {

    private Context context;
    private TopView topView;
    private LinearLayout puzzleLL;
    private PuzzleView puzzleView;
    private TextView templateTv;
    private List<ImageBean> imageBeans;
    private Puzzle puzzleEntity;
    private TemplateDialog templateDialog;
    private String pathFileNmae;
    private int lastSelect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        init();
    }

    private void init() {

        context = PuzzleActivity.this;
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        topView = (TopView) findViewById(R.id.top_view);
        puzzleLL = (LinearLayout) findViewById(R.id.puzzle_ll);
        puzzleView = (PuzzleView) findViewById(R.id.puzzle_view);
        templateTv = (TextView) findViewById(R.id.template_tv);
    }

    private void initData() {

        imageBeans = (List<ImageBean>) getIntent().getSerializableExtra("pics");
        getFileName(imageBeans.size());
        templateDialog = new TemplateDialog(context, imageBeans.size());
        topView.setTitle("拼图");
        topView.setRightWord("保存");
        puzzleView.setPics(imageBeans);
        if (pathFileNmae != null) {
            initCoordinateData(pathFileNmae, 0);
        }
    }

    private void initEvent() {
        templateTv.setOnClickListener(this);

        topView.setOnLeftClickListener(new TopView.OnLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
            }
        });
        topView.setOnRightClickListener(new TopView.OnRightClickListener() {
            @Override
            public void rightClick() {
                savePuzzle();
                finish();
            }
        });

        templateDialog.setOnItemClickListener(new TemplateDialog.OnItemClickListener() {
            @Override
            public void OnItemListener(int position) {
                if (position != lastSelect) {
                    initCoordinateData(pathFileNmae, position);
                    puzzleView.invalidate();
                    lastSelect = position;
                }
                templateDialog.dismiss();
            }
        });
    }

    private void getFileName(int picNum) {

        switch (picNum) {

            case 2:
                pathFileNmae = "num_two_style";
                break;
            case 3:
                pathFileNmae = "num_three_style";
                break;
            case 4:
                pathFileNmae = "num_four_style";
                break;
            case 5:
                pathFileNmae = "num_five_style";
                break;
            default:
                break;
        }
    }

    private void initCoordinateData(String fileName, int templateNum) {

        String data = new FileUtil(context).readAsset(fileName);
        try {
            Gson gson = new Gson();
            puzzleEntity = gson.fromJson(data, Puzzle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (puzzleEntity != null && puzzleEntity.getStyle() != null && puzzleEntity.getStyle().get(templateNum).getPic() != null) {
            puzzleView.setPathCoordinate(puzzleEntity.getStyle().get(templateNum).getPic());
        }

    }

    private void savePuzzle() {

        buildDrawingCache(puzzleLL);
        puzzleLL.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        Bitmap bitmap = puzzleLL.getDrawingCache().copy(Bitmap.Config.RGB_565, true);
        try {
            File file = FileUtil.saveBitmapJPG(context,"dd" + System.currentTimeMillis(), bitmap);
            Intent intent = new Intent("puzzle");
            intent.putExtra("picPath", file.getPath());
            sendBroadcast(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildDrawingCache(View view) {
        try {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.template_tv:
                templateDialog.show();
                break;

            default:
                break;
        }
    }

}
