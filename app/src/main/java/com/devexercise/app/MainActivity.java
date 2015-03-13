package com.devexercise.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.devexercise.app.models.PieChartData;
import com.devexercise.app.services.AccountsOperation;
import com.devexercise.app.services.PieChartOperation;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DonutSeries;
import com.shinobicontrols.charts.DonutSeriesStyle;
import com.shinobicontrols.charts.PieDonutSeries;
import com.shinobicontrols.charts.Series;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;

import java.util.List;


public class MainActivity extends Activity {

    public static List<Integer> chartDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only set the chart up the first time the Activity is created
        if(savedInstanceState == null) {
            ChartFragment chartFragment = (ChartFragment) getFragmentManager().findFragmentById(R.id.chart);
            ShinobiChart shinobiChart = chartFragment.getShinobiChart();
            shinobiChart.setLicenseKey("cx+oxi1uI7n3eYkMjAxNTA0MDhwcnVkaHZpOTQ4QGdtYWlsLmNvbQ==9GeuKR60CWgHbir/mvQsX0s07SYRDpIWm6mLvNWiNyWRPRpQKOCQueO5RqvuQysmNoX2FXVAyatRWusOGNG561f1VX4QH4M/EJDjqddibplG1M+lO42jazFejI+9gBkzg+f5KMnbC/k3X6DTXiTYL2gUj3LU=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");

            DataAdapter<String, Double> dataAdapter = new SimpleDataAdapter<String, Double>();
            for(int i=0; i<3; i++) {
                dataAdapter.add(new DataPoint<String, Double>("", chartDataList.get(i).doubleValue()));
            }

            // Create a DonutSeries and give it the data adapter
            DonutSeries series = new DonutSeries();
            series.setInnerRadius(0.7f);
            series.setDataAdapter(dataAdapter);
            series.setSelectionMode(Series.SelectionMode.POINT_SINGLE);
            series.setSelectedPosition(0.0f);
            shinobiChart.addSeries(series);

            // Apply styling to the Pie Series
            DonutSeriesStyle style = series.getStyle();
            style.setFlavorColors(new int[]{
                    Color.argb(255, 238, 98, 98), // red
                    Color.argb(255, 105, 200, 153), // green
                    Color.argb(255, 241, 237, 54) // yellow
            });
            style.setRadialEffect(PieDonutSeries.RadialEffect.BEVELLED_LIGHT);
            style.setCrustShown(false);
        }

    }

    public void onAccountsButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, AccountListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
