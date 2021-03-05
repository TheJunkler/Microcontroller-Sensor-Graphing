// code is currently written for a single stream of sensor data, change line 46 for mutliple sensors

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;

public class SensorGraph {
	
	static SerialPort chosenPort;
	static int x = 0;

	public static void main(String[] args) {
		
		// create and configure window w/ drop-down box and "connect" button
		JFrame window = new JFrame();
		window.setTitle("Sensor Graph GUI");
		window.setSize(1500, 900);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Connect");
		JPanel topPanel = new JPanel();
		topPanel.add(portList);
		topPanel.add(connectButton);
		window.add(topPanel, BorderLayout.NORTH);
		
		// populate the drop-down box using serial communication
		SerialPort[] ports = SerialPort.getCommPorts();
			for(int i = 0; i < ports.length; i++)
				portList.addItem(ports[i].getSystemPortName());
		
		// create the graph, in this case I went with a line graph
		XYSeries series = new XYSeries("Light Sensor Readings");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		// dataset.addSeries(series); // this is where you can add multiple streams of serial data (for multiple sensors) **
		JFreeChart chart = ChartFactory.createXYLineChart("Light Readings", "Time (10 milliseconds)", "ADC Reading", dataset);
		window.add(new ChartPanel(chart), BorderLayout.CENTER);
		
		// equipt the connect button
		connectButton.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				if(connectButton.getText().equals("Connect"))	{
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort())	{
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
					}
					
					// thread that listens for incoming sensor text data from the Arduino and populates the graph
					Thread thread = new Thread() {
						@Override public void run()	{
							Scanner sc = new Scanner(chosenPort.getInputStream());
							while(sc.hasNextLine())	{
								try {
									String line = sc.nextLine();
									int number = Integer.parseInt(line);
									series.add(x++, number);
									window.repaint();
								} catch(Exception e) {}
							}
							sc.close();
						}
					};
					thread.start();
				} else	{
					// disconnects from the USB serial port
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
					series.clear();
					x = 0;
				}
			}
		});
		
		window.setVisible(true);
		
	}
}
