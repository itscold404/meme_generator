import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 * You will need to implement certain portions of this class, marked with comments starting with "TODO" to connect 
 * it with your existing code. 
 * 
 * This class provides an example layout for the GUI. You are encouraged to be creative in your design. 
 * More information about Swing is online at: 
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 * 
 * 
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    
    
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);


        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel();
        
        // Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);
        
        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        
        // TODO The button needs a listener
        OpenButtonListener open = new OpenButtonListener();
        backgroundImageButton.addActionListener(open);
        
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);
        

        // TODO Complete the Control Panel implementation (with Background Image and Meme panels)
//-----------------------background image----------------------------------------------------------------
        JPanel titlePanel = new JPanel(); //start of title label and box
        
        JLabel titleLabel = new JLabel("Title: ");
        titlePanel.add(titleLabel);
        titleLabel.setPreferredSize(new Dimension(100, 20));
        
        JTextField titleBox = new JTextField();
        titlePanel.add(titleBox);
        titleBox.setPreferredSize(new Dimension(350, 20));
        
        backgroundImagePanel.add(titlePanel); //end of title label and box
   
        JPanel descriptionPanel = new JPanel(); //start of description label and box
        
        JLabel descLabel = new JLabel("Description: ");
        descriptionPanel.add(descLabel);
        descLabel.setPreferredSize(new Dimension(100, 20));
        
        JTextField descBox = new JTextField();
        descriptionPanel.add(descBox);
        descBox.setPreferredSize(new Dimension(350, 20));
        
        backgroundImagePanel.add(descriptionPanel, BorderLayout.PAGE_END); //end of description label and box
//------------------meme panel----------------------------------------------------------------------
        JPanel memePanel = new JPanel(new BorderLayout());
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));
        
        JPanel captionPanel = new JPanel();

        JLabel captionLabel = new JLabel("Caption: ");
        captionPanel.add(captionLabel);
        captionLabel.setPreferredSize(new Dimension(100, 20));
        
        JTextField captionBox = new JTextField();
        captionPanel.add(captionBox);
        captionBox.setPreferredSize(new Dimension(350, 20));
        
        memePanel.add(captionPanel, BorderLayout.NORTH);
        
        JPanel verticalAlignPanel = new JPanel();
        
        JLabel verticalLabel = new JLabel("Vertical Align: ");
        verticalAlignPanel.add(verticalLabel);
        verticalLabel.setPreferredSize(new Dimension(100, 20));
        
        String[] verticalAligns = {"top", "middle", "bottom"};
        JComboBox verticalAlignOptions = new JComboBox(verticalAligns);
        verticalAlignOptions.setPreferredSize(new Dimension(350,20));
        verticalAlignPanel.add(verticalAlignOptions);
        
        memePanel.add(verticalAlignPanel);        
//-------------------------buttons-----------------------------------------------------
        
        JPanel buttons = new JPanel();
        
        JButton generate = new JButton("Generate");
        JButton save = new JButton("Save");
        
        generate.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evt) {
                String captionFromBox = captionBox.getText();
                String verticalAlignFromBox = verticalAlignOptions.getSelectedItem().toString(); 
                String bImageName = backgroundImageFileNameLabel.getText();
                String title = titleBox.getText();
                String description = descBox.getText();
                BufferedImage createdMeme;
                
                currentMeme = new GraphicalMeme(new BackgroundImage(bImageName,title, description), 
                		captionFromBox, user);
                
                currentMeme.setCaptionVerticalAlign(verticalAlignFromBox);
                
                try {
                	memeViewPanel.removeAll(); //needed to clear pane
					createdMeme = currentMeme.compileMeme();
					JLabel tempMeme = new JLabel(new ImageIcon(createdMeme));
					memeViewPanel.add(tempMeme, BorderLayout.CENTER);
					memeViewPanel.revalidate();
					//memeViewPanel.repaint(); //this could have been used but had to tab out for image to generate. revalidate 
											   //didnt have this issue
					
				} catch (IOException e) {
					memeViewPanel.removeAll(); //needed to clear pane
					
					if(!(backgroundImageFileNameLabel.getText().substring(backgroundImageFileNameLabel.getText().length()-5).equals(".jpg"))) {
						memeViewPanel.add(new JLabel("make sure image file is .jpg, .jpeg, or .png"), BorderLayout.NORTH);
					}
					
					//telling the user what is empty so they know what to add
					String print = "Make sure ";
					
					if(backgroundImageFileNameLabel.getText().equals("<choose>"))
						print += "image_file ";
					
					if(titleBox.getText().equals(""))
						print += "title ";
					
					if(descBox.getText().equals(""))
						print += "description ";
					
					if(captionBox.getText().equals(""))
						print += "caption ";
					
					print += "is/are not empty";
					
					JLabel errorPrint = new JLabel(print); //printing out what the user is missing
					errorPrint.setPreferredSize(new Dimension(400, 20));
					memeViewPanel.add(errorPrint, BorderLayout.CENTER);
					
					memeViewPanel.revalidate(); //needed to remake the pane
					e.printStackTrace();
				}
                }
        });
        
        save.addActionListener(new SaveButtonListener());
        
        buttons.add(generate);
        buttons.add(save);
        
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        controlPanel.add(memePanel, BorderLayout.CENTER);
        controlPanel.add(buttons,BorderLayout.SOUTH);
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }
    
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                
                 try {
					ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("meme could not be saved");
					e.printStackTrace();
				}

            }

        }
    }
}