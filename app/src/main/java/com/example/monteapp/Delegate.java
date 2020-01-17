package com.example.monteapp;

public interface Delegate {
    void onPreExecute();
    void onProgressChanged(int progress);
    void onPostExecute(double result);
}
