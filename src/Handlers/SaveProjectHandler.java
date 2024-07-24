package Handlers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import GooeyBits.GooeyDisplayFrame;
import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.CharacterParser;
import Parsers.NodeParser;
import decisionTree.Node;

public class SaveProjectHandler extends GooeyListener{
    private GooeyEntryFrame geFrame;
    private Node start, end;    //the bookend nodes
    
    public void setGooeyFrame(GooeyEntryFrame geFr) {
    	geFrame = geFr;
    }
    
    public void actionPerformed(ActionEvent actn) {
        if(actn.getActionCommand().equalsIgnoreCase("save")) {
        	/*
             * Prompt the user for a directory in which to save the new Character file.
             */
            JFileChooser fileDialog = new JFileChooser("Please specify a directory.");
        		
    		fileDialog.setSelectedFile(null);    //no file initially chosen
    		
    		/*
    		 * The following statement sets the JFileChooser so the user can
    		 * only choose a directory and not a file.
    		 */
    		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		
    		/*
    		 * If the user chooses the OK option, the following section will take
    		 * the selected directory and pass it as a File object to the Manager object's
    		 * loadDirectory() method.
    		 */
    		if(fileDialog.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
    		    return;
    		}
    		
    		fileDialog.setCurrentDirectory(fileDialog.getSelectedFile());    //Set the directory to the user selection
    		//Create the project directory
    		String path = fileDialog.getCurrentDirectory().getPath() + "//" + geFrame.getText("title") + "//";
    		
    		//make the file directory
            File file = new File(path);
    		file.mkdir();
    		
    		//make the characters directory
    		(new File(path + "characters")).mkdir();
    		
    		//make the images directory
    		(new File(path + "images")).mkdir();
    		
    		//make the node directory
    		(new File(path + "nodes")).mkdir();
    		
    		/*
    		 * Call the NodeParser's saveNodeFile() method to create or update the file in the directory.
    		 */
    	    start = new Node("n_1", geFrame.getText("description"));
//    	    start.setNextNodeID("n_1");
    	    start.setTitle(geFrame.getText("title"));
    	    
//    	    try {
//    	        start.setImage(geFrame.getText("image"));
//    	    }
//    	    catch(FileNotFoundException e) {
//    	        ExceptionHandler.handleException(e);
//    	    }
    	    
    	    end = new Node("n_0", null);
    	    end.setNextNodeID("n_0");
  
        	//create the bookend nodes
        	try {
    		    NodeParser.saveNodeFile(start, path + "//nodes");
    		    NodeParser.saveNodeFile(end, path + "//nodes");
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	catch(IOException e2) {
    			ExceptionHandler.handleException(e2);
    		}
        	
        	JOptionPane.showMessageDialog(null, "Project created successfully.");
        }
        geFrame.close();
    }
}
