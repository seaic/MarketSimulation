package com.company;

import com.company.data.DataBucket;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.company.Main.startBudget;

/**
 * @author Noah Mannhart
 * @version 1.0
 * This Class is used for creating the LineChart without an animation
 */

public class CreateLineChart extends Application {
  private static final List<Double> stockData = DataBucket.DATA_BUCKET;
  private static final List<String> dateData = DataBucket.DATE_BUCKET;
  double simulationBudget = startBudget / stockData.get(0); // calculate simulation budget with the actual stock price

  public static void main(String[] args, String startBudget) throws IOException {
    launch(args);
  }

  //Method to start creating the Line Chart
  @Override
  public void start(Stage stage) {
    stage.setTitle("Market Simulation");
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Month");

    final LineChart<String, Number> lineChart =
        new LineChart<String, Number>(xAxis, yAxis);

    lineChart.setTitle("Line Chart for " + FileManager.getFileName());

    XYChart.Series series = new XYChart.Series();
    series.setName("My portfolio");
    lineChart.setCreateSymbols(false); //hide dots
    //Data from List is used for Line Chart
    int i = 0;
    for (Double price : stockData) {
      series.getData().add(new XYChart.Data(dateData.get(i), (price * simulationBudget)));
      i++;
    }

    //Creates grid for showing Chart
    Scene scene = new Scene(lineChart, 800, 600);
    lineChart.getData().add(series);

    stage.setScene(scene);
    stage.show();
  }
}
