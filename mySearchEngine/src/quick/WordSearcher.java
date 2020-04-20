/********************************************************************************
 * 
 *   Title:  The following program will search through three specific text files
 *   		 within a directory for a word the user has entered. The program will 
 *           return how many times the word is used within the three text files. 
 *           A GUI will be implemented using the java.swing library.
 *   Date:   02/04/2020
 *   Author: c18424946 - Nicolas Condrea
 *   
 *******************************************************************************/

package quick;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class WordSearcher implements ActionListener {

	private static JLabel userLabel;
	private static JTextField userText;
	private static JButton button;
	private static JLabel result;
	private static JLabel success1, success2, success3;
	
	
	public static void main(String[] args) {
        
			// Calling the GUISetup() method
			GUISetup();

	} //main
	
	
	// Method which will run after the user hits enter
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int txt1 = 0;
		int txt2 = 0;
		int txt3 = 0;
		
		String userWord = userText.getText();
		
		// Validating the user's word to check if it's a word
		if(userWord.matches("[a-zA-Z]+")) {
			
			// Change the font colour
			success1.setForeground(Color.black);
			result.setText("Word Proccessed!");
			
			// Storing the directory of the three text files
			String directoryPath = "C:\\Users\\nicol\\eclipse-workspace\\mySearchEngine\\src\\quick\\";
			int counter = 0;
			
			
			File directory = new File(directoryPath);
			Scanner scan = new Scanner(System.in);
			
	        File[] listOfFiles = directory.listFiles();
			
			if (listOfFiles.length > 0) {
	        	for (int i = 0; i < listOfFiles.length; i ++) {
	            	
	        		// Checking each file to see if it's valid
	        		if (listOfFiles[i].exists()) {
	                	scan = null;
	                	
	                    try {
							scan = new Scanner(new BufferedReader(new FileReader(directoryPath + listOfFiles[i].getName())));
							
						} catch (Exception FileNotFoundException) {
							// If the directory is invalid this message will appear onto the GUI
							success1.setText("The specified pathname does not exist!");
							
						}
	                    
		                while (scan.hasNext()) {
		                	String words = scan.next();
		                	
			                // Searching for the word entered by the user
			                if (words.equals(userWord)) {
			                	// If word is found within the files the count will be incremented
			                	counter ++;
			                	
			                }
		                	
		                }
		                
		                
		                // This section of code will return the strongest match
		                if (i == 0) { 
			                txt1 = counter;
		                } else if (i == 1) {
				            txt2 = counter;
		                } else {
				            txt3 = counter;
		                }
		                
		                if (txt1>txt2 && txt1>txt3) {
		                	success1.setText("The word is present " + txt1 + " time(s) in text file #1.");
		                	if(txt2 > txt3) {
		                		success2.setText("The word is present " + txt2 + " time(s) in text file #2.");
		                		success3.setText("The word is present " + txt3 + " time(s) in text file #3.");
		                	} else {
		                		success2.setText("The word is present " + txt3 + " time(s) in text file #3.");
		                		success3.setText("The word is present " + txt2 + " time(s) in text file #2.");
		                	}
		                } else if (txt2>txt3 && txt2>txt1) {
		                	success1.setText("The word is present " + txt2 + " time(s) in text file #2.");
		                	if(txt1 > txt3) {
		                		success2.setText("The word is present " + txt1 + " time(s) in text file #1.");
		                		success3.setText("The word is present " + txt3 + " time(s) in text file #3.");
		                	} else {
		                		success2.setText("The word is present " + txt3 + " time(s) in text file #3.");
		                		success3.setText("The word is present " + txt1 + " time(s) in text file #1.");
		                	}
		                } else if (txt3>txt1 && txt3>txt2) {
		                	success1.setText("The word is present " + txt3 + " time(s) in text file #3.");
		                	if(txt1 > txt2) {
		                		success2.setText("The word is present " + txt1 + " time(s) in text file #1.");
		                		success3.setText("The word is present " + txt2 + " time(s) in text file #2.");
		                	} else {
		                		success2.setText("The word is present " + txt2 + " time(s) in text file #2.");
		                		success3.setText("The word is present " + txt1 + " time(s) in text file #1.");
		                	}
		                }
		                
			            // Closing Scanner
		                scan.close();
	        		}
		                
            	}
        	
        	} 
        	
        } else {
        	// Setting font colour to red to indicate an error to the user
        	success1.setForeground(Color.red);
        	success1.setText("That's not a word! Please re-enter.");
        	success2.setText("");
        	success3.setText("");
        	result.setText("Invalid!");
        	
    	}
	
	} //actionPerformed()
	
	
	// Creating a method which will setup the GUI
	public static void GUISetup() {
		
		// Creating a panel
		JPanel panel = new JPanel(); 
		
		// Creating a frame
		JFrame frame = new JFrame("My Search Engine"); 
		
		// Setting the frame size in pixels
		frame.setSize(600, 250); 
		
		// This will let us close the frame once we click X 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.add(panel);
		
		panel.setLayout(null);
		
		
        userLabel = new JLabel("Enter a word you would like to search: ");
		userLabel.setBounds(10, 20, 250, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(250, 20, 300, 25);
		panel.add(userText);
		
		button = new JButton("Enter");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new WordSearcher());
		panel.add(button);
		
		result = new JLabel("");
		result.setBounds(255, 45, 300, 25);
		panel.add(result);
		
		success1 = new JLabel("");
		success1.setBounds(10, 110, 300, 35);
		panel.add(success1);
		
		success2 = new JLabel("");
		success2.setBounds(10, 130, 300, 35);
		panel.add(success2);
		
		success3 = new JLabel("");
		success3.setBounds(10, 150, 300, 35);
		panel.add(success3);
		
		
		frame.setVisible(true);
        		
	} // GUISetup() 
	
} // WordSearcher()