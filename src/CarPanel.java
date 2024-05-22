import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class to represent the Car
class Car {
    // Method to draw the car on the given Graphics object at the specified coordinates
    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.RED); // Set the color to red
        g.fillRect(x, y - 40, 50, 40); // Draw a rectangle to represent the car, offset to position it correctly
    }
}

// Class to represent the JPanel that will display and animate the car
public class CarPanel extends JPanel {
    private Car car; // The car object
    private int carX; // The x-coordinate of the car
    private int carY; // The y-coordinate of the car
    private boolean moving; // Indicates whether the car is moving
    private int direction; // 1 for moving right, -1 for moving left
    private Timer timer; // Timer to control the animation

    // Constructor for CarPanel
    public CarPanel() {
        car = new Car(); // Create a new Car object
        carX = 0; // Initialize the car's x-coordinate to 0 (start at the left edge)
        carY = getHeight() / 2; // Initialize the car's y-coordinate to the center of the panel
        moving = true; // Set the car to be moving initially
        direction = 1; // Set the initial direction to right

        setBackground(Color.LIGHT_GRAY); // Set the background color of the panel to light gray

        // Create a timer that fires an ActionEvent every 30 milliseconds
        timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (moving) { // Only move the car if it is set to moving
                    carX += 5 * direction; // Update the car's x-coordinate based on the direction and speed
                    if (carX > getWidth() - 50 || carX < 0) { // Check if the car hits the edge
                        direction *= -1; // Reverse the direction
                    }
                    repaint(); // Repaint the panel to update the car's position
                }
            }
        });
        timer.start(); // Start the timer

        // Add a mouse listener to handle mouse clicks on the panel
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > carX + 25) { // If the click is to the right of the car
                    moving = false; // Stop the car
                } else if (e.getX() < carX + 25) { // If the click is to the left of the car
                    moving = true; // Start the car moving
                }
            }
        });
    }

    // Method to paint the component (the car panel)
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensure the panel is properly rendered
        carY = getHeight() / 2; // Keep the car vertically centered
        car.draw(g, carX, carY); // Draw the car at its current position
    }

    // Main method to set up the JFrame and add the CarPanel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Animation"); // Create a new JFrame with the title "Car Animation"
        CarPanel carPanel = new CarPanel(); // Create a new CarPanel
        frame.add(carPanel); // Add the CarPanel to the frame
        frame.setSize(400, 400); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the frame is closed
        frame.setVisible(true); // Make the frame visible
    }
}
