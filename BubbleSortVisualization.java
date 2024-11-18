import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BubbleSortVisualization extends JPanel {

    private int[] numbers;
    private final int size = 200;
    private final int gap = 4;

    public BubbleSortVisualization() {
        // Initialize the array with values from 1 to size (200)
        numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }

        // Shuffle the array randomly
        shuffleArray(numbers);
    }

    // Shuffle the array using the Fisher-Yates shuffle algorithm
    private void shuffleArray(int[] arr) {
        Random random = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Swap elements at index and i
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    // Function to visualize the swapping of lines
    private void swap(int i, int j) {
        try {
            // Delay to visualize the swap
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Swap the elements
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

        // Repaint the panel to show the changes
        repaint();
    }

    // Override paintComponent to draw the lines
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color to black
        this.setBackground(Color.BLACK);

        // Set line color to white and draw the lines
        g.setColor(Color.WHITE);
        for (int i = 0; i < size; i++) {
            g.drawLine(gap * i, size, gap * i, size - numbers[i]);
        }
    }

    // Bubble sort algorithm with visualization
    public void bubbleSort() {
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(j, j + 1); // Swap elements and visualize
                }
            }
        }
    }

    // Main method to run the visualization
    public static void main(String[] args) {
        // Create the JFrame window
        JFrame frame = new JFrame("Bubble Sort Visualization");
        BubbleSortVisualization visualizer = new BubbleSortVisualization();

        // Set frame properties
        frame.add(visualizer);
        frame.setSize(800, 600); // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on 'X' click
        frame.setVisible(true); // Make the window visible

        // Delay a little before starting the sorting
        try {
            Thread.sleep(1000); // Wait for 1 second before starting the sort
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the bubble sort visualization
        visualizer.bubbleSort();
    }
}
