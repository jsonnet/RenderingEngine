package com.jsonnet.renderEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * RenderEngine created by Joshua S. on 25.07.2015.
 */
public class Window implements Runnable {
    //Window size
    public int WIDTH, HEIGHT;
    //Window title
    public String TITLE;
    //Updates per sec
    public int TARGET_UPS;
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
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    public void tick() {

    }

    public void render() {

    }

    @Override
    public void run() {
        int fps = 0, tick = 0;
        double fpsTimer = System.currentTimeMillis();

        // sec per Update
        double SpU = 1D / TARGET_UPS;
        // nanosec per Tick
        double NspT = SpU * 1000000000D;
        double then = System.nanoTime();
        double now;
        //Count of unprocessed Frames
        double unprocessed = 0;


        while (isRunning) {
            now = System.nanoTime();
            unprocessed += (now - then) / NspT;
            then = now;

            while (unprocessed >= 1) {
                tick();
                tick++;
                unprocessed--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            render();
            fps++;

            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                System.out.printf("FPS: %d, UPS: %d%n", fps, tick);
                fps = 0;
                tick = 0;
                fpsTimer += 1000;
            }
        }

        this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
    }

}
