import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

/**
 * A simple implentation of Conway's Game Of Life.
 * 
 * @author Annaliese Tan
 * @version 1.0 2015-04-30
 */
public class GOLGUI
{
    private final int HEIGHT = 20;
    private final int WIDTH = 20;

    //instance fields
    private int aliveCount;
    private int generationCount;
    private int neighbours;
    private JFrame frame;
    private JLabel generationStatus;    
    private JPanel buttonPanel; 
    private Container contentPane;

    private boolean quit;
    private boolean endGamePressed = false;

    //arrays
    private Cell[][] currentGrid = new Cell[HEIGHT][WIDTH];
    private Cell[][] futureGrid = new Cell[HEIGHT][WIDTH];

    /**
     * Constructs a GUI version of Game Of Life.
     */
    public GOLGUI() throws InterruptedException
    {
        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                currentGrid[x][y] = new Cell(false, x, y);
                futureGrid[x][y] = new Cell(false, x, y);
            }//end of  for (int y = 0; y < WIDTH; y++)
        }//end of  for (int x = 0; x < HEIGHT; x++)

        randomStart();
        makeFrame();

        do
        {            
            setRules();
            printGeneration();
            Thread.sleep(400);
        }
        while(aliveCount > 0);
    } //end of constructor GameOfLifeGUI() throws InterruptedException                

    private void makeFrame()
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        frame = new JFrame("Game Of Life");

        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());  

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(HEIGHT, WIDTH));

        JPanel messagePanel = new JPanel();
        generationStatus = new JLabel("Generation: ");
        messagePanel.add(generationStatus);

        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                buttonPanel.add(currentGrid[x][y].getButton());
            }//end of 
        }        

        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(messagePanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu controlMenu = new JMenu("Game Options");
        menuBar.add(controlMenu);

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
        newGameItem.addActionListener(new NewGameListener());
        controlMenu.add(newGameItem);

        JMenuItem endGameItem = new JMenuItem("End Game");
        endGameItem.addActionListener(new EndGameListener());
        controlMenu.add(endGameItem);

        frame.pack();
        frame.setVisible(true);
    }//end of method makeFrame()

    private  int neighbourCheck(int x, int y)
    {
        neighbours = 0;

        try
        {                
            if(currentGrid[x-1][y-1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException exception)
        {
        }

        try
        {                
            if(currentGrid[x+1][y-1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        try
        {                
            if(currentGrid[x-1][y+1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        try
        {                
            if(currentGrid[x][y+1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        try
        {                
            if(currentGrid[x+1][y+1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        try
        {                
            if(currentGrid[x-1][y].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        try
        {                
            if(currentGrid[x][y-1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        try
        {                
            if(currentGrid[x+1][y].getCellState() == true)
            {
                neighbours++;
            }//end of if(currentGrid[x+1][y].getCellState() == true)
        }
        catch(ArrayIndexOutOfBoundsException  exception)
        {
        }

        return neighbours;
    }//end of method neighbourCheck()

    /**
     * Sets the state according to the rules of Game of Life. 
     */
    private void setRules() 
    {
        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                if (currentGrid[x][y].getCellState() == true)
                {
                    neighbourCheck(x, y);

                    if (neighbours == 2 || neighbours == 3)
                    {
                        futureGrid[x][y].setCellState(true);
                    }
                    else if (neighbours < 2 || neighbours > 3)
                    {
                        futureGrid[x][y].setCellState(false);
                    }//end of if (neighbours == 2 || neighbours == 3)
                }
                else
                {
                    neighbourCheck(x, y);

                    if (neighbours == 3)
                    {
                        futureGrid[x][y].setCellState(true);
                    } //end of if (neighbours == 3)                                  
                }//end of if (currentGrid[x][y].getCellState() == true)                              
            }//end of for (int x = 0; x < WIDTH; x++)
        } //end of for (int y = 0; y < HEIGHT; y++) 
    }//end of method setRules()

    private void printGeneration()
    {
        aliveCount = 0;
        generationStatus.setText("Generation: " + ++generationCount);

        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {                
                currentGrid[x][y].setCellState(futureGrid[x][y].getCellState());

                if (currentGrid[x][y].getCellState() == true)
                {
                    aliveCount++;
                }

                buttonPanel.add(currentGrid[x][y].getButton());
            }//end of (int y = 0; y < WIDTH; y++)            
        }//end of for (int x = 0; x < HEIGHT; x++)
    }//end of method printGeneration()

    private void randomStart()
    {
        Random randomGenerator = new Random();

        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                currentGrid[x][y].setCellState(randomGenerator.nextBoolean());
            }//end of for (int x = 0; x < HEIGHT; x++)
        }//end of for (int y = 0; y < WIDTH; y++)
    }//end of method randomStart()

    private void endGameDialog()
    {
        final String MESSAGE = "Do you want to exit the game?";

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, MESSAGE,"Game Option", dialogButton);

        if(dialogResult == 1) //NO Option
        {
            randomStart();
        }
        else
        {            
            quit = true;
            System.exit(0);
        }
    }//end of method endGameDialog()

    private class NewGameListener implements ActionListener
    {
        /**
         * Handles about new game menu items.
         * 
         * @param event event objects generated from about items 
         */
        public void actionPerformed(ActionEvent event)
        {           
            randomStart();
        }//end of actionPerformed(ActionEvent event)    
    }//end of class NewGameListener implements ActionListener

    private class EndGameListener implements ActionListener
    {
        /**
         * Handles about end game menu items.
         * 
         * @param event event objects generated from about items 
         */
        public void actionPerformed(ActionEvent event)
        {
            endGamePressed = true;

            for (int x = 0; x < HEIGHT; x++)
            {
                for (int y = 0; y < WIDTH; y++)
                {
                    currentGrid[x][y].setCellState(false);
                    futureGrid[x][y].setCellState(false);                    
                }   
            }

            endGameDialog();
        }//end of actionPerformed(ActionEvent event) 
    }//end of class EndGameListener implements ActionListener
}//end of class GOLGUI()