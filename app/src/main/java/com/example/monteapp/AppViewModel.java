package com.example.monteapp;

import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {
    private double p = 0;
    private int progress;

    public int getProgress() {
        return progress;
    }

    public double getP() {
        return p;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setP(double p) {
        this.p = p;
    }
}
