import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MemoryGame
{

   //Initialize color strings for Memory Game   
   String[] solutions = new String[5];
   
   //Intialize int variable to iterate through guesses
   int num = 0;
   
   //Initialize variables for ColorComparison class
   String userEntry = "";
   String color = "";
   
   //Method used to construct randomized color list
   public void RandomizeColors()
   {
      //create color array list
      String[] colors = new String[10];
      
      //Populate / define items in array   
      colors[0] = "white";
      colors[1] = "black";
      colors[2] = "red";            
      colors[3] = "orange";   
      colors[4] = "yellow";   
      colors[5] = "green";
      colors[6] = "blue";
      colors[7] = "indigo";      
      colors[8] = "violet";
      colors[9] = "brown";
      
      //Instance random object   
      Random r = new Random();
      
      //Randomly assign colors to solutions array   
      for (int i = 0; i < solutions.length; i++)
      {
         solutions[i] = colors[r.nextInt(10)];
      }      
   }

   //Intro
   /****************************************************/
   //Declare elements to be used in GUI
   public JFrame mainWindow;
   public JFrame firstWindow;
   public JLabel lblInstructions;
   public JLabel lblColors;
   public JLabel lblEntry;
   public JLabel lblResult;
   public JTextField txtField1;
   public JButton btnOK;
   
   //GUI Frame Constructors
   /******************************************************/
      
   //Constructor for First GUI Frame (First step in program)
   public MemoryGame ()
   {   
      RandomizeColors();
      createContents1();
      
      firstWindow.setVisible(true);
   }
   
   //First Frame
   public void createContents1()
   {
   
      //Setup First Frame
      firstWindow = new JFrame();
      
      
      firstWindow.setSize(500,200);
      firstWindow.setTitle("Message");
      firstWindow.setDefaultCloseOperation(firstWindow.EXIT_ON_CLOSE);
      firstWindow.setLayout(new FlowLayout());
      
      //First Window - create contents
      lblInstructions = new JLabel("How good is your memory?\nTry to memorize this color sequence: ");
      lblColors = new JLabel(solutions[0] + " " + solutions[1] + " " + solutions[2] + " " + solutions[3] + " " + solutions[4] );
      btnOK = new JButton("OK");
      
      //First Window - add contents
      firstWindow.add(lblInstructions);
      firstWindow.add(lblColors);
      firstWindow.add(btnOK);
      
      //First Window - attatch action listener
      btnOK.addActionListener(new EventListener1());
   
   }//end of create contents1 (constructor for intro frame)
   
   //Main Frame
   public void createContents2()
   {
      //Setup Frame
      mainWindow = new JFrame();
      
      mainWindow.setSize(500,300);
      mainWindow.setTitle("Memory Game");
      mainWindow.setDefaultCloseOperation(mainWindow.EXIT_ON_CLOSE);
      mainWindow.setLayout(new FlowLayout());
      
      //Main Window - Create Contents
      lblEntry = new JLabel("Enter color number "+ (num+1) + ":");
      txtField1 = new JTextField(15);
      
      //Main Window - Add Contents
      mainWindow.add(lblEntry);
      mainWindow.add(txtField1);
      
      //Main Window -  Connect Action / Event Listener to text field
      txtField1.addActionListener(new EventListener2()); 
   
   }//end of create contents 2 (constructor for main GUI frame)
   
   /*******************************************************/
   //Action Listener classes 
   public class EventListener1 implements ActionListener
   {
   
      public void actionPerformed (ActionEvent e)
      {
         firstWindow.setVisible(false);
         
         //Constructor for Main Window
         createContents2();
         
         mainWindow.setVisible(true);
      
      }
      
   }//end of firstWindow Event Listener
   
   //1st color check
   public class EventListener2 implements ActionListener
   {
   

      public void actionPerformed (ActionEvent e)
      {
      
         userEntry = txtField1.getText();
         color = solutions[num];
         
         //sets up for next piece of user input
         if (ColorComparison(userEntry,color) == true)
         {   
            
            {    
               num++;
               txtField1.setText("");
               lblEntry.setText("Enter color number " + (num+1) + ": ");
            }
            
         }
         
         //ends program and displays failure message
         if (ColorComparison(userEntry, color) == false)
         {
         
            lblEntry.setVisible(false);
            txtField1.setVisible(false);
            
            lblResult = new JLabel("Sorry - wrong color. Eat more antioxidants.");
            mainWindow.add(lblResult); 
         
         }

         //ends program and displays Congratulations message
         if (num == 5)
         {
            lblEntry.setVisible(false);
            txtField1.setVisible(false);
            lblResult = new JLabel("Congratulations - your memory is perfect!");
            mainWindow.add(lblResult);
         }
         
      }
      
   }//end of EventListener2
   
   /*************************************************************/
   /*************************************************************/
   //Boolean Color Comparison Class
   public boolean ColorComparison(String userEntry,String color)
   {
   
      boolean match = true;
      
      if (userEntry.equalsIgnoreCase(color) == true)
      {
         match = true;
      }
      
      if (userEntry.equalsIgnoreCase(color) == false)
      {
         match = false;
      }
   
      return match;
   }//end of color comparison class
  
}//end of Memory Game Class