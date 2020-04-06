package com.example.android.yourcartdelivery.Models;

import java.util.ArrayList;

public class ModelOrdersLiveTask {
    private String order_id;

    public String getOfferAccepted() {
        return offerAccepted;
    }

    public void setOfferAccepted(String offerAccepted) {
        this.offerAccepted = offerAccepted;
    }

    private String offerAccepted;

    public ModelOrdersLiveTask() {
    }

    private ArrayList<ModelCellsLiveTask> cells;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public ArrayList<ModelCellsLiveTask> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ModelCellsLiveTask> cells) {
        this.cells = cells;
    }

    public ModelOrdersLiveTask(String order_id, ArrayList<ModelCellsLiveTask> cells) {
        this.order_id = order_id;
        this.cells = cells;
    }
}
