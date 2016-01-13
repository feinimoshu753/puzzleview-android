package com.dd.test.puzzleview_android.activity.entity;

/**
 * Created by dd on 16/1/13.
 * 模板实体
 */
public class Template {

    private int path;
    private boolean isSelected;

    public Template(int path, boolean isSelected) {
        this.path = path;
        this.isSelected = isSelected;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
