package com.rpassmore.projects.ui.vaddin;

import com.rpassmore.projects.data.entity.ElectricReading;
import com.rpassmore.projects.data.repo.ElectricReadingRepository;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.base.renderers.MarkerRenderer;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.Yaxes;
import org.dussan.vaadin.dcharts.metadata.styles.MarkerStyles;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.AxesDefaults;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.renderers.axis.LinearAxisRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

import java.time.LocalDateTime;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	@Autowired
	private ElectricReadingRepository repo;

	private final Label label = new Label("Reading history (hrs)");
	private final Grid grid = new Grid();

	private final Slider slider = new Slider(1, 72);

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(label, slider);
		HorizontalLayout charts = new HorizontalLayout(makeChart());
		slider.setValue(24.0);
		slider.setImmediate(true);
		slider.addValueChangeListener(e -> listReadings(slider.getMin(), slider.getValue()));
    slider.setWidth(800, Unit.PIXELS);

		VerticalLayout mainLayout = new VerticalLayout(actions, charts, grid);
		setContent(mainLayout);

		// Configure layouts and components
		//actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setSizeFull();

		//grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("current", "voltage", "power", "readingDate");
    grid.sort("readingDate", SortDirection.DESCENDING);
    grid.setSizeFull();

		// Initialize listing
    listReadings(slider.getMin(), slider.getMax());
  }

	// tag::listCustomers[]
	private void listReadings(double startHours, double endHours) {
		grid.setContainerDataSource(new BeanItemContainer(ElectricReading.class, repo.findByDateBetween(LocalDateTime.now().minusHours((long) endHours), LocalDateTime.now().minusHours((long)startHours))));
	}
	// end::listCustomers[]

	private DCharts makeChart() {
		DataSeries dataSeries = new DataSeries();
		dataSeries.newSeries();
		for (float i = 0; i < 2 * Math.PI; i += 0.4) {
			dataSeries.add(i, Math.cos(i));
		}
		dataSeries.newSeries();
		for (float i = 0; i < 2 * Math.PI; i += 0.4) {
			dataSeries.add(i, Math.sin(i - 0.8));
		}
		dataSeries.newSeries();
		for (float i = 0; i < 2 * Math.PI; i += 0.4) {
			dataSeries.add(i, 2.5 + Math.pow(i / 4, 2));
		}
		dataSeries.newSeries();
		for (float i = 0; i < 2 * Math.PI; i += 0.4) {
			dataSeries.add(i, -2.5 - Math.pow(i / 4, 2));
		}

		Series series = new Series()
				.addSeries(
						new XYseries()
								.setLineWidth(2)
								.setMarkerOptions(
										new MarkerRenderer()
												.setStyle(MarkerStyles.DIAMOND)))
				.addSeries(
						new XYseries(Yaxes.Y2).
								setShowLine(false)
								.setMarkerOptions(
										new MarkerRenderer()
												.setSize(7)
												.setStyle(MarkerStyles.X)))
				.addSeries(
						new XYseries(Yaxes.Y3)
								.setMarkerOptions(
										new MarkerRenderer()
												.setStyle(MarkerStyles.CIRCLE)))
				.addSeries(
						new XYseries(Yaxes.Y4)
								.setLineWidth(5)
								.setMarkerOptions(
										new MarkerRenderer()
												.setStyle(MarkerStyles.FILLED_SQUARE)
												.setSize(10)));

		AxesDefaults axesDefaults = new AxesDefaults()
				.setUseSeriesColor(true)
				.setRendererOptions(
						new LinearAxisRenderer()
								.setAlignTicks(true));

		Axes axes = new Axes()
				.addAxis(
						new XYaxis(XYaxes.Y))
				.addAxis(
						new XYaxis(XYaxes.Y2))
				.addAxis(
						new XYaxis(XYaxes.Y3))
				.addAxis(
						new XYaxis(XYaxes.Y4));

		Options options = new Options()
				.setSeries(series)
				.setAxesDefaults(axesDefaults)
				.setAxes(axes);

		DCharts chart = new DCharts()
				.setDataSeries(dataSeries)
				.setOptions(options)
				.show();

		return chart;
	}
}
