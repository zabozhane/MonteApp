package com.example.monteapp;

import android.os.AsyncTask;

public class MonteCarlo extends AsyncTask<Integer, Double, Double> {
    private int iteration;
    private Delegate delegate;


    MonteCarlo(int iteration, Delegate delegate) {
        this.iteration = iteration;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        delegate.onPreExecute();
    }

    @Override
    protected Double doInBackground(Integer... integers) {
        double x, y;
        int k = 0;
        for (int i = 1; i <= iteration; i++) {
            x = Math.random();
            y = Math.random();
            if (x * x + y * y <= 1) k++;
            double progress = (double) i / iteration * 100;
            if (progress % 5 == 0)
                publishProgress(progress);
        }
        return (4. * k) / iteration;

    }

    @Override
    protected void onPostExecute(Double aDouble) {
        delegate.onPostExecute(aDouble);
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        delegate.onProgressChanged(values[0].intValue());
    }
}
