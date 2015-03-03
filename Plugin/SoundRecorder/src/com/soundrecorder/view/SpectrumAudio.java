package com.soundrecorder.view;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SpectrumAudio extends AnchorPane {

	private XYChart.Data<String, Number>[] series1Data;
	private AudioSpectrumListener audioSpectrumListener;
	private BarChart<String, Number> toAdd;

	public SpectrumAudio() {
		super();
		init();
	}
	
	private void init() {
		this.setMaxHeight(Region.USE_COMPUTED_SIZE);
		this.setMaxWidth(Region.USE_COMPUTED_SIZE);
		toAdd = createChart();
		this.getChildren().add(toAdd);
		AnchorPane.setBottomAnchor(toAdd, 0.0);
		AnchorPane.setTopAnchor(toAdd, 0.0);
		AnchorPane.setRightAnchor(toAdd, 0.0);
		AnchorPane.setLeftAnchor(toAdd, 0.0);
		this.setStyle("-fx-background-color: transparent;");
		toAdd.setStyle("-fx-background-color: transparent;");
		audioSpectrumListener = new AudioSpectrumListener() {
			@Override
			public void spectrumDataUpdate(double timestamp, double duration,
					float[] magnitudes, float[] phases) {
				for (int i = 0; i < series1Data.length; i++) {
					series1Data[i].setYValue(magnitudes[i] + 60);
				}
			}
		};
	}

	protected BarChart<String, Number> createChart() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis(0, 50, 10);
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis,
				yAxis);
		bc.setStyle("-fx-background-color: transparent;");
		bc.setLegendVisible(false);
		bc.setAnimated(false);
		bc.setBarGap(0);
		bc.setCategoryGap(1);
		bc.setVerticalGridLinesVisible(false);
		bc.setHorizontalGridLinesVisible(false);
		bc.setHorizontalZeroLineVisible(false);
		bc.setVerticalZeroLineVisible(false);
		xAxis.setTickLabelsVisible(false);
		yAxis.setTickLabelsVisible(false);
		xAxis.setTickMarkVisible(false);
		yAxis.setTickMarkVisible(false);
		yAxis.setMinorTickVisible(false);
		xAxis.setOpacity(0);
		yAxis.setOpacity(0);
		// setup chart
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis,
				null, ""));
		// add starting data
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		// noinspection unchecked
		series1Data = new XYChart.Data[128];
		String[] categories = new String[128];
		for (int i = 0; i < series1Data.length; i++) {
			categories[i] = Integer.toString(i + 1);
			series1Data[i] = new XYChart.Data<String, Number>(categories[i], 50);
			series1.getData().add(series1Data[i]);
		}
		bc.getData().add(series1);
		return bc;
	}

	public AudioSpectrumListener getAudioSpectrumListener() {
		return audioSpectrumListener;
	}
	
	
}
