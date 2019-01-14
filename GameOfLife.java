import java.util.Random;

/**
 * Conway's game of life.
 * 
 * @author Annaliese 
 * @version 1.0 2015-04-23
 */
public class GameOfLife
{
    //constant fields
    private static final String ALIVE = "*";
    private static final String DEAD = ".";
    private static final int HEIGHT = 10;
    private static final int WIDTH = 20;

    //instance fields
    private static int aliveCount;
    private static int generationCount;
    private static int neighbours;

    //arrays
    private static Cell[][] currentGrid = new Cell[HEIGHT][WIDTH];
    private static Cell[][] futureGrid = new Cell[HEIGHT][WIDTH];
    
    /*
     * main method
     */
    
    /**
     * Prints the game of life and each generation.
     * 
     * @param args argument not used
     */
    public static void main(String[] args) throws InterruptedException
    {
        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                currentGrid[x][y] = new Cell(false);
                futureGrid[x][y] = new Cell(false);
            }//end of  for (int y = 0; y < WIDTH; y++)
        }//end of  for (int x = 0; x < HEIGHT; x++)

        currentGrid[4][4].setAlive();
        currentGrid[4][5].setAlive();
        currentGrid[4][6].setAlive();
        currentGrid[5][4].setAlive();
        currentGrid[5][6].setAlive();
        currentGrid[6][4].setAlive();
        currentGrid[6][5].setAlive();
        currentGrid[6][6].setAlive();

        randomStart();   

        do
        {            
            printGeneration();
            neighbourCheck();
            Thread.sleep(750);
        }
        while(aliveCount != 0);
    }//end of main (String[] args) throws InterruptedException

    // -- private methods --

    private static int check(int x, int y)
    {
        try
        {                
            if(currentGrid[x-1][y-1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
        }

        try
        {                
            if(currentGrid[x+1][y-1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }

        try
        {                
            if(currentGrid[x-1][y+1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }

        try
        {                
            if(currentGrid[x][y+1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }

        try
        {                
            if(currentGrid[x+1][y+1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }

        try
        {                
            if(currentGrid[x-1][y].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }

        try
        {                
            if(currentGrid[x][y-1].getCellState() == true)
            {
                neighbours++;
            }
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }

        try
        {                
            if(currentGrid[x+1][y].getCellState() == true)
            {
                neighbours++;
            }//end of if(currentGrid[x+1][y].getCellState() == true)
        }
        catch(ArrayIndexOutOfBoundsException  e)
        {
        }
        
        return neighbours;
    }//end of method check()

    private static void neighbourCheck() 
    {
        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                neighbours = 0;
                if (currentGrid[x][y].getCellState() == true)
                {
                    check(x, y);
                    
                    if (neighbours == 2 || neighbours == 3)
                    {
                        futureGrid[x][y].setAlive();
                    }
                    else if (neighbours < 2 || neighbours > 3)
                    {
                        futureGrid[x][y].setDead();
                    }//end of if (neighbours == 2 || neighbours == 3)
                }
                else
                {
                    check(x, y);

                    if (neighbours == 3)
                    {
                        futureGrid[x][y].setAlive();
                    } //end of if (neighbours == 3)                                  
                }//end of if (currentGrid[x][y].getCellState() == true)                              
            }//end of for (int x = 0; x < WIDTH; x++)
        } //end of for (int y = 0; y < HEIGHT; y++) 
    }//end of method neighbourCheck()

    private static void printGeneration()
    {
        System.out.print("\f");
        aliveCount = 0;
        generationCount++;
        for (int x = 0; x < HEIGHT; x++)
        {
            for (int y = 0; y < WIDTH; y++)
            {
                if (generationCount > 1)
                {
                    currentGrid[x][y] = futureGrid[x][y];
                    if (currentGrid[x][y].getCellState() == true)
                    {
                        aliveCount++;
                        if (y < WIDTH - 1)
                        {
                            System.out.print("*");
                        }
                        else 
                        {
                            System.out.println("*");
                        }//end of if(y < WIDTH - 1)
                    }
                    else 
                    {
                        if (y < WIDTH - 1)
                        {
                            System.out.print(".");
                        }
                        else 
                        {
                            System.out.println(".");
                        }//end of if (y < WIDTH - 1)
                    }//end of if (currentGrid[x][y].getCellState() == true)
                }
                else
                {
                    if (currentGrid[x][y].getCellState() == true)
                    {
                        aliveCount++;
                        if (y < WIDTH - 1)
                        {
                            System.out.print(ALIVE);
                        }
                        else 
                        {
                            System.out.println(ALIVE);
                        }//end of if(y < WIDTH - 1)
                    }
                    else 
                    {
                        if (y < WIDTH - 1)
                        {
                            System.out.print(DEAD);
                        }
                        else 
                        {
                            System.out.println(DEAD);
                        }//end of if (y < WIDTH - 1)
                    }//end of if (currentGrid[x][y].getCellState() == true)
                }//end of if (generationCount < 1)
            }//end of (int y = 0; y < WIDTH; y++)
        }//end of for (int x = 0; x < HEIGHT; x++)

        System.out.println("\nGeneration: " + generationCount);
    }//end of method printGeneration()    

    private static void randomStart()
    {
        Random randomGenerator = new Random();

        int randomHeight = randomGenerator.nextInt(HEIGHT);
        int randomWidth = randomGenerator.nextInt(WIDTH);

        try
        {
            currentGrid[randomHeight][randomWidth].setAlive();
            currentGrid[randomHeight+1][randomWidth].setAlive();
            currentGrid[randomHeight-1][randomWidth].setAlive();
            currentGrid[randomHeight+1][randomWidth+1].setAlive();
            currentGrid[randomHeight-1][randomWidth-1].setAlive();        
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
        }
    }//end of randomStart(
}//end of class GameOfLife