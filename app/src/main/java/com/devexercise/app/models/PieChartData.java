package com.devexercise.app.models;

import java.util.List;

/**
 * Created by prudhvi on 3/9/2015.
 */
public class PieChartData {
    private List<Integer> totalHits;

    public PieChartData() {

    }

    public PieChartData(List<Integer> totalHits) {
        this.totalHits = totalHits;
    }

    public void setTotalHits(List<Integer> totalHits) {
        this.totalHits = totalHits;
    }

    public List<Integer> getTotalHits() {
        return totalHits;
    }
}
