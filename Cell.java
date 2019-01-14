import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A cell that knows its state and position.
 * 
 * @author Annaliese Tan
 * @version 1.0 2015-04-23
 */
public class Cell implements ActionListener
{
    //instance fields
    private boolean cellState;
    private JButton buttonCell;
    private int xCoordinate;
    private int yCoordinate;

    /*
     * constructors 
     */

    /**
     * Constructor for objects of class Cell
     * 
     * @param cellState state of this cell
     * @param xCoordinate x coordinate of this cell
     * @param yCoordinate y coordinate of this cell
     */
    public Cell(boolean state, int xCoordinate, int yCoordinate)
    {
        buttonCell = new JButton();
        buttonCell.addActionListener(this);
        buttonCell.setForeground(Color.BLACK);
        if (state == true)
        {
            buttonCell.setBackground(Color.YELLOW);
        }
        else
        {
            buttonCell.setBackground(Color.LIGHT_GRAY);
        }
        cellState = state;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }//end of constructor Cell(boolean cellState)

    /*
     * accessors
     */

    /**
     * Returns a button with the correct color coding (yellow = alive, grey = dead)
     *
     * @return a button with the correct color coding (yellow = alive, grey = dead)
     */
    public JButton getButton()
    {
        return buttonCell;   
    }//end of accessor getXCoordinate()

    /**
     * Returns the x coordinate of this cell.
     *
     * @return x coordinate of this cell
     */
    public int getXCoordinate()
    {
        return xCoordinate;   
    }//end of accessor getXCoordinate()

    /**
     * Returns the y coordinate of this cell.
     *
     * @return y coordinate of this cell 
     */
    public int getYCoordinate()
    {
        return yCoordinate;   
    }//end of accessor getYCoordinate()

    /**
     * Returns the state of this cell.
     *
     * @return state of this cell 
     */
    public boolean getCellState()
    {
        return cellState;   
    }//end of accessor getCellState()

    /*
     * mutators
     */

    /**
     * Sets this cell as alive or dead.
     * 
     * @param state state of this cell
     */
    public void setCellState(boolean state)
    {
        cellState = state;

        if(cellState)
        {
            buttonCell.setBackground(Color.YELLOW);
        }
        else
        {
            buttonCell.setBackground(Color.LIGHT_GRAY);
        }            
    } //end of method setAlive()

    /**
     * Sets the x coordinate of this cell.
     * 
     * @param xCoordinate x coordinate of this cell
     */
    public void setXCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }//end of method setXCoordinate(int xCoordinate)

    /**
     * Sets the y coordinate of this cell.
     * 
     * @param yCoordinate y coordinate of this cell
     */
    public void setYCoordinate(int yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    } //end of method setYCoordinate(int yCoordinate)  

    /**
     * Handles about button items.
     * 
     * @param event event objects generated from about items 
     */
    public void actionPerformed(ActionEvent event)
    {
        setCellState(true);
    }//end of actionPerformed(ActionEvent event)   
}//end of class Cell