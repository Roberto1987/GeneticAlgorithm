package draw;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import utility.SystemConstants;
/**
 * This class implements the drawing of the chart about the average scores in a simulation: it's necessary to set the
 * path in the string of the file creation when it saves the chart in a .png image; is also possible to create a JPanel with
 * the chart, but it's very slow and it's not suggested. The operations for a jpanel visualization are commented.
 *
 * @author rob
 *
 */


public class Grapher {
/**
 * This methods execute the plot of the scores of a single simulation, with a file printing
 * @param scores
 * @param s
 */
    public  void Graphing(final double[][] scores,final int s) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               // JFrame frame = new JFrame("Charts");

                //frame.setSize(1200, 800);
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setVisible(true);

                XYDataset ds = createDataset(scores);
                int k=s+1;
                JFreeChart chart = ChartFactory.createXYLineChart("Average Score trend, simulation "+k,
                        "Generation", "Score", ds, PlotOrientation.VERTICAL, true, true,
                        false);
                //XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                XYPlot plot = chart.getXYPlot();
				//plot.setRenderer(renderer);
				plot.setBackgroundPaint(Color.DARK_GRAY);
				plot.setRangeGridlinesVisible(true);
				plot.setRangeGridlinePaint(Color.BLACK);

				plot.setDomainGridlinesVisible(true);
				plot.setDomainGridlinePaint(Color.BLACK);
               // ChartPanel cp = new ChartPanel(chart);
               // frame.getContentPane().add(cp);




                File f = new File(SystemConstants.IMAGE_PATH+"image"+s+".png");
                try {
					ChartUtilities.saveChartAsJPEG(f, chart, 1200, 800);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        });

    }
/**
 * This class is responsable for the dataset creation for the chart
 * @param scores
 * @return
 */
    private static XYDataset createDataset(double[][] scores) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries[] series = new XYSeries[SystemConstants.POPULATION];
        //creating a series for each individuals
        for(int j=0;j<SystemConstants.POPULATION;j++){
        	int l=j+1;
        	series[j]= new XYSeries(" "+l+" ");
        }
        for(int j=0;j<SystemConstants.GENERATIONS+500;j++){
        	for(int i=0;i<SystemConstants.POPULATION;i++){
        		//moving the rows j, the individuals, with locked generation, the column i
        		series[i].add(j,scores[i][j]);
        	}
        }
      /*
        Random random = new Random();
        for(int i=1;i<10;i++){
        	series1.add(random.nextInt(i), random.nextInt(i));
        	series2.add(random.nextInt(i), random.nextInt(i));
        	series3.add(random.nextInt(i), random.nextInt(i));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        */
        for(int j=0;j<SystemConstants.POPULATION;j++){
        	dataset.addSeries(series[j]);
        }

        return dataset;
    }

}