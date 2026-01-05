/*
The main class here is the App class, and basically it holds the main() that will execute to launch our app

 */

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        // SwingUtilities.invokeLater() is used to allow our GUI to be created and updated in a thread-safe manner.
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                // instantiate a RockPaperScissorGUI obj
                RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();

                // Display the GUI
                rockPaperScissorGUI.setVisible(true);

            }
        });
    }

}
