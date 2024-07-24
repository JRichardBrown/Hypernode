package Program;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GooeyBits.GooeyFrame;
import GooeyBits.GooeyListener;
import Handlers.CreateCharHandler;
import Handlers.CreateNodeHandler;
import Handlers.EditNodeHandler;
import Handlers.LoadProjectHandler;
import Handlers.NewProjectHandler;
import Handlers.RunProjectHandler;


public class EventController {

    /*
     * preconditions: none
     *
     */
    public static void main(String[] args) {

        String[] mmNameArray = new String[] {"Load Project", "New Project"};
        
        LoadProjectHandler lpHandler = new LoadProjectHandler();
        
        NewProjectHandler npHandler = new NewProjectHandler();
        
        GooeyListener[] mmListener = new GooeyListener[] {lpHandler, npHandler};
        
        GooeyFrame mmFrame = new GooeyFrame("Hypernode 1.0", mmNameArray, mmListener);
        
        mmFrame.resize(400, 100);
        
        mmFrame.reposition(0, 0);
        
        ImageIcon imgIcon = new ImageIcon("default.png");
        
        mmFrame.setIcon(imgIcon.getImage());
        
        mmFrame.closeProgramOnExit();
    }      
}
