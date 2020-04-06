package com.example.android.yourcartdelivery.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderModel implements Serializable {
    public static String order_id;
    public static String isPrimary;
    public static String order_type;
    String[] cell_id;
    String[] cell_lat;
    String[] cell_lng;
    ArrayList<CellModel> cells;

    public OrderModel(String order_id, String isPrimary, String[] cell_id, String[] cell_lat, String[] cell_lng, String order_type) {
        OrderModel.order_id = order_id;
        OrderModel.isPrimary = isPrimary;
        this.cell_lat = cell_lat;
        this.cell_lng = cell_lng;
        this.cell_id = cell_id;
        OrderModel.order_type = order_type;
        setCells(cell_id,cell_lat, cell_lng);
    }

    public static String getOrder_type() {
        return order_type;
    }

    public static void setOrder_type(String order_type) {
        OrderModel.order_type = order_type;
    }

    public static String getOrder_id() {
        return order_id;
    }

    public static void setOrder_id(String order_id) {
        OrderModel.order_id = order_id;
    }

    public static String getIsPrimary() {
        return isPrimary;
    }

    public static void setIsPrimary(String isPrimary) {
        OrderModel.isPrimary = isPrimary;
    }

    private void setCells(String[] cell_id,String[] cell_lat, String[] cell_lng) {
        cells = new ArrayList<>();
        for (int i = 0; i < cell_lat.length; i++) {
            cells.add(new CellModel(cell_id[i],cell_lat[i], cell_lng[i]));
        }
    }

    public ArrayList<CellModel> getCells() {
        return cells;
    }

    public void setCells(ArrayList<CellModel> cells) {
        this.cells = cells;
    }
}
