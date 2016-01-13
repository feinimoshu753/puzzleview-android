package com.dd.test.puzzleview_android.activity.entity;

import java.util.List;

/**
 * Created by dd on 16/1/13.
 * 单张图片实体
 */
public class ImageItem {

    private List<Coordinates> coordinates;

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }
}
