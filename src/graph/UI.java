package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
	private JButton readDataButton;
	private JButton buildGraphButton;
	private JButton findShortestPathButton;
	private JTextArea textArea;
	private GraphCanvas graphCanvas;

	public UI() {
		initializeUI();
		setupListeners();
	}

	private void initializeUI() {
		setTitle("Graph Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Create navbar panel
		JPanel navbarPanel = new JPanel();
		navbarPanel.setBackground(Color.LIGHT_GRAY);
		navbarPanel.setLayout(new GridLayout(4, 1));

		// Create buttons for navbar
		readDataButton = new JButton("Read Data");
		buildGraphButton = new JButton("Build Graph");
		findShortestPathButton = new JButton("Find Shortest Path");

		// Add buttons to navbar panel
		navbarPanel.add(readDataButton);
		navbarPanel.add(buildGraphButton);
		navbarPanel.add(findShortestPathButton);

		// Create canvas panel
		graphCanvas = new GraphCanvas();

		// Create text area
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(10); // Adjust the number of visible rows

		// Create scroll pane for the text area
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Create a panel to contain canvas and text area using GridBagLayout
		JPanel canvasAndTextAreaPanel = new JPanel(new GridBagLayout());
		canvasAndTextAreaPanel.setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		// Add canvas to the canvas and text area panel
		canvasAndTextAreaPanel.add(graphCanvas, gbc);

		gbc.gridy = 1;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Add scroll pane to the canvas and text area panel
		canvasAndTextAreaPanel.add(scrollPane, gbc);

		// Add navbar panel to the frame
		add(navbarPanel, BorderLayout.WEST);

		// Add canvas and text area panel to the frame
		add(canvasAndTextAreaPanel, BorderLayout.CENTER);
	}

	private void setupListeners() {
		readDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Implement read data functionality
				textArea.setText("Reading data...");
			}
		});

		buildGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Implement build graph functionality
				textArea.setText("Building graph...");
				graphCanvas.repaint(); // Redraw the graph on canvas
			}
		});

		findShortestPathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Implement find shortest path functionality
				textArea.setText("Finding shortest path...");
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UI ui = new UI();
				ui.setSize(800, 600);
				ui.setVisible(true);
			}
		});
	}

	class GraphCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dummy data for the graph
            int nodeRadius = 30;
            int nodeSpacing = 100;
            int startX = nodeSpacing;
            int startY = getHeight() / 2;
            
            // Draw nodes
            for (int i = 0; i < 5; i++) {
                int x = startX + i * nodeSpacing;
                int y = startY;
                g.setColor(Color.BLUE);
                g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
                g.setColor(Color.WHITE);
                g.drawString("Node " + i, x - 15, y + 5);
            }

            // Draw edges
            g.setColor(Color.BLACK);
            for (int i = 0; i < 4; i++) {
                int x1 = startX + i * nodeSpacing + nodeRadius;
                int y1 = startY;
                int x2 = startX + (i + 1) * nodeSpacing - nodeRadius;
                int y2 = startY;
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}

	