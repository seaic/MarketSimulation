package com.company;

import com.company.data.DataBucket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
  private static String endDate = null;
  private static double endValue = 0;
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
      endDate = dateData.get(i);
      endValue = (price * simulationBudget);
      i++;
    }

    BorderPane borderPane = new BorderPane();

    //Create a portfolio simulation holding label
    Label portfolio = new Label();
    portfolio.setFont(Font.font(20));
    BorderPane.setAlignment(portfolio, Pos.BOTTOM_CENTER);
    BorderPane.setMargin(portfolio, new Insets(10,10,10,10));
    borderPane.setBottom(portfolio);

    borderPane.setCenter(lineChart);

    //create a exit button
    Button exit = new Button("Exit");
    exit.setOnAction(value -> {
      Platform.exit();
    });
    borderPane.setRight(exit);

    // Change decimale precision to two decimal
    Double truncatedDouble = BigDecimal.valueOf(endValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
    portfolio.setText("End value at " + endDate + " : " + truncatedDouble + " USD");

    //Creates grid for showing Chart
    Scene scene = new Scene(borderPane, 800, 600);
    lineChart.getData().add(series);

    stage.setScene(scene);
    stage.show();
  }
}
