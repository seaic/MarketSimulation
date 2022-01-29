package com.company;

import com.company.data.DataBucket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.company.Main.startBudget;

/**
 * @author Noah Mannhart
 * @version 1.0
 * This Class is used for creating the LineChart with an animation
 */

public class CreateLineChartAnimation extends Application {
  private static final List<String> dateData = DataBucket.DATE_BUCKET;
  private static final List<Double> stockData = DataBucket.DATA_BUCKET;
  double simulationBudget = startBudget / stockData.get(0); // calculate simulation budget with the actual stock price
  private static Integer i = 0;
  private ScheduledExecutorService scheduledExecutorService;

  public static void main(String[] args, String s) throws IOException {
    launch(args);
  }

  //Method to make Chart animation
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("PredictTheMarket");

    //defining the axes
    final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Time");
    xAxis.setAnimated(false); // axis animations are removed
    yAxis.setLabel("Value (CHF)");
    yAxis.setAnimated(false); // axis animations are removed

    //creating the line chart with two axis created above
    final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
    lineChart.setTitle("Line Chart animation for " + FileManager.getFileName());
    lineChart.setAnimated(false); // disable animations

    //defining a series to display data
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Stock Price");

    //add series to chart
    lineChart.getData().add(series);

    //setup scene
    Scene scene = new Scene(lineChart, 800, 600);
    primaryStage.setScene(scene);

    //show the stage
    primaryStage.show();
    lineChart.setCreateSymbols(false); //hide dots
    //this is used to display time in HH:mm:ss format
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    //setup a scheduled executor to periodically put data into the chart
    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    //put dummy data onto graph per second
    scheduledExecutorService.scheduleAtFixedRate(() -> {

      //Update the chart
      Platform.runLater(() -> {
        //get current time
        //Date now = new Date();
        //put data with current date
        series.getData().add(new XYChart.Data<>(dateData.get(i), (stockData.get(i) * simulationBudget)));
        //if (series.getData().size() > WINDOW_SIZE)
        //series.getData().remove(0);
      });
      i++;
    }, 0, 1, TimeUnit.SECONDS);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    scheduledExecutorService.shutdownNow();
  }
}
