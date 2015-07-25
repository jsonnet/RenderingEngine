package com.jsonnet.renderEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Created by Joshua on 25.07.2015.
 */
public class Window implements Runnable {
    //Window size
    public int WIDTH, HEIGHT;
    //Window title
    public String TITLE;
    //Is the specific window running
    public boolean isRunning;
    //Actual window
    private JFrame frame;

    public Window(String TITLE, int WIDTH, int HEIGHT) {
        //Set variables
        this.TITLE = TITLE;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        //Create window
        this.frame = new JFrame(TITLE);
        //Correctly terminate program
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set window size
        this.frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //Update window
        this.frame.pack();
        //Positions the window
        this.frame.setLocationRelativeTo(null);
        //Makes the window not resizable
        this.frame.setResizable(false); //TODO
        //Makes the window visible
        this.frame.setVisible(true);

    }

    public void start() {
        //Allow loop to work
        isRunning = true;
        //Starts loop / thread
        new Thread(this).start();
    }

    public void stop() {
        //Stops the loop
        isRunning = false;
    }

    @Override
    public void run() {

        while (isRunning) {

        }

        this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
    }

}
