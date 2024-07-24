package Handlers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import GooeyBits.GooeyDisplayFrame;
import GooeyBits.GooeyFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.NodeParser;
import decisionTree.Tree;

public class RunProjectHandler extends GooeyListener{
	String projectDir;
	
	@Override
    public void actionPerformed(ActionEvent actn) {
		
        Hashtable<String, MMCharacter>chtrHT = new Hashtable<String, MMCharacter>();
    	
//        JFileChooser fileDialog = new JFileChooser("Please select a directory.");    //create JFileChooser object to allow file selection
//        
//        fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);    //set file chooser to only look for directories
//
//        fileDialog.setSelectedFile(null);    //no file initially chosen
//
//        //Prompt the user to choose a file to load.
//        //If the user chooses "cancel," display a message and exit the program.
//        if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//            JOptionPane.showMessageDialog(null, "No directory selected. This program will exit.");
//            return;
//        }
//        
//        String basePath = fileDialog.getSelectedFile().toString() + "\\";
        
        NodeParser nParser = new NodeParser();
        
        String fileName = "n_1.txt";    //set the first filename
        
        Tree dTree = new Tree();
        
        try {
            dTree.setCurrentNode(nParser.parseFile(new File(projectDir + "nodes//" + fileName), projectDir, chtrHT));
        } 
        catch(Exception e) {
        	ExceptionHandler.handleException(e);
        	return;
        }
        
        
    	//Set up the game GUI
    	String[] strArray = new String[]{"Next", "1", "2", "3", "4"};
    	
        SelHandler buttHandler = new SelHandler(dTree, fileName, projectDir, chtrHT);
        
    	GooeyListener[] gListener = new GooeyListener[]{buttHandler, buttHandler, buttHandler, 
    			buttHandler, buttHandler};
    	
        GooeyDisplayFrame frame = new GooeyDisplayFrame(dTree.getCurrentNode().getTitle(), strArray, gListener);
        
        frame.print(dTree.getCurrentNode().getDescription());    //Display the opening screen
        
        ImageIcon image = dTree.getCurrentNode().getImage();
        
        try {
        	if(image != null)
                frame.setImage(image);
            //DEBUG
//            System.out.println(projectDir + "test.png");
        }
        
        catch(NullPointerException e) {
        	ExceptionHandler.handleException(e);
        	return;
        }
         
        buttHandler.setGFrame(frame);	
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}
}
