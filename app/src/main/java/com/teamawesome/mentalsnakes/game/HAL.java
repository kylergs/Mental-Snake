package com.teamawesome.mentalsnakes.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Ben on 21/01/2016.
 * The HAL class will contain the ability to act as an AI playing against the User.
 */
public class HAL extends User
{
    private ArrayList<Direction> memory = new ArrayList<Direction>();

    public HAL (int red, int green, int blue)
    {
        super(red, green, blue);
    }

    public HAL ()
    {
        this(40, 40, 40);
    }

    /*
     * The logic for HAL's move
     */
    public void makeMove(GameScreen screen) throws CloneNotSupportedException {
        int gridDimension = screen.getGridDimension();
        Grid memoryGrid = new Grid(0, 0, 10, gridDimension, 2, 10); //Choice of integers is arbitary
        memoryGrid.setSnakeCoordinate((gridDimension - 1)/2, (gridDimension - 1)/2);
        for(int i = 0; i < memory.size(); i++)
        {
            Direction directionI = memory.get(i);
            memoryGrid.moveSnake(directionI, this);
        }
        ArrayList<Integer> snakeCoordinate = memoryGrid.getSnakeCoordinate();
        ArrayList<Direction> moves = new ArrayList<>();
        moves = initialMoveElimination(moves, gridDimension, snakeCoordinate); //HAL removes the obviously implausible moves
        //If there are no moves left, there is no point cycling, and he chooses a move at random
        ArrayList<Direction> availableMoves = new ArrayList<Direction>();
        if(moves.size() == 0)
        {
            screen.gridMoveSnake(Direction.UP, this);
            remember(Direction.UP);
            screen.setLastDirection(Direction.UP);
        }
//        If there are moves that HAL can do without crashing into an edge, he will then think more.
        else
            for (int i = 0; i < moves.size(); i++) {
//                We create a clone of the grid that HAL remembers
                Grid testGrid = new Grid();
                testGrid.clone(memoryGrid);
//                We then cycle through the directions left available to the user, testing each one with a dummy user.
                Direction directionTest = moves.get(i);
                User dummyUser = new User();
                testGrid.setSnakeCoordinate(snakeCoordinate.get(0), snakeCoordinate.get(1));
                testGrid.moveSnake(directionTest, dummyUser);
                if (!testGrid.getCrash()) {
                    availableMoves.add(directionTest);
                }
                this.setCrash(false);
            }
//        We then (provided there are plausible moves) choose the first move
        if(availableMoves.size() == 0)
        {
            screen.gridMoveSnake(Direction.UP, this);
            remember(Direction.UP);
        }
        else if(availableMoves.size() == 1)
        {
            Direction directionChosen = randomMove(availableMoves);
            screen.gridMoveSnake(directionChosen, this);
            remember(directionChosen);
            screen.setLastDirection(directionChosen);
        }
//        If HAL can carry out more than one possible move, we will add further stages of thinking.
        else
        {
//            If we find a move that traps the future user, then we wish to choose this move.
            ArrayList<Direction> winMoves = new ArrayList<Direction>();
//            We wish to cycle through all the moves that are still available to HAL
            for (int i = 0; i < availableMoves.size(); i++)
            {
//                For each available move, we wish to test with a series of second moves.
//                We loop through our available directions, and for each loop through our secondDirecions
                Direction firstMove = availableMoves.get(i);
                User dummyUser = new User();
//                For each direction, we wish to compute a further move, so construct an ArrayList of 4 moves
//                We will assign the ith move a rank, based on how many subsequent directions the user crashes in.
                ArrayList<Direction> secondMoves = new ArrayList<Direction>();
                secondMoves = allMoveConstructor(secondMoves);
                ArrayList<Integer> directionCrashes = new ArrayList<Integer>();
                directionCrashes = integerArrayList(0, 4);
//                We create a clone of the grid that HAL remembers
                for(int j = 0; j < secondMoves.size(); j++)
                {
                    Grid testGrid = new Grid();
                    testGrid.clone(memoryGrid);
                    testGrid.setSnakeCoordinate(snakeCoordinate.get(0), snakeCoordinate.get(1));
                    Direction secondMove = secondMoves.get(j);
                    testGrid.moveSnake(firstMove, dummyUser);
                    testGrid.moveSnake(secondMove, dummyUser);
                    if (testGrid.getCrash())
                    {
                        directionCrashes.set(i, directionCrashes.get(i) + 1);
                    }
                    this.setCrash(false);
                    testGrid.recreate(testGrid.getGridDimension());
                }
//                If the user crashes no matter what direction he moves next, then we have trapped him.
                for(int k = 0; k < directionCrashes.size(); k++)
                {
                    if(directionCrashes.get(i) == 4)
                    {
                        winMoves.add(0, secondMoves.get(k));
                        winMoves.add(0, firstMove);
                    }
                }
            }
//            If there is a direction in which we can trap the user, HAL should move in that direction.
//            Otherwise, HAL will select a move at random s.t. the user cannot predict his moves.
            if(winMoves.size() != 0)
            {
                Direction directionChosen = winMoves.get(0);
                screen.gridMoveSnake(directionChosen, this);
                remember(directionChosen);
                screen.setLastDirection(directionChosen);
            }
            else
            {
                Direction directionChosen = randomMove(availableMoves);
                screen.gridMoveSnake(directionChosen, this);
                remember(directionChosen);
                screen.setLastDirection(directionChosen);
            }
        }
        screen.increaseUserPlayerNumber(1);
    }

    /*
     * HAL has the capacity to remember moves made by himself and the user.
     * This method takes a direction, and enables him to remember it.
     */
    public void remember(Direction directionToRemember)
    {
        memory.add(directionToRemember);
    }

    public void rememberStart(ArrayList<Integer> startCoordinates)
    {

    }

    /*
     * HAL must have the capacity to forget the moves from the previous game that have been played
     */
    public void clearMemory()
    {
        memory.clear();
    }

    /*
     * HAL becomes too predictable, so he evolves the ability to choose a move at random from the list
     * of moves that are available to him
     */
    public Direction randomMove(ArrayList<Direction> availableMoves)
    {
        Random rand = new Random();
        int randomInt = rand.nextInt(availableMoves.size());
        return availableMoves.get(randomInt);
    }

    /*
     * This cumbersome method reverses the directions, in order for the moves to reverse engineered by HAL
     */
    private Direction directionReverse(Direction directionToReverse)
    {
        if (directionToReverse == Direction.UP)
        {
            directionToReverse = Direction.DOWN;
        }
        else if (directionToReverse == Direction.LEFT)
        {
            directionToReverse = Direction.RIGHT;
        }
        else if (directionToReverse == Direction.DOWN)
        {
            directionToReverse = Direction.UP;
        }
        else if (directionToReverse == Direction.RIGHT)
        {
            directionToReverse = Direction.LEFT;
        }
        return directionToReverse;
    }

    public ArrayList<Direction> initialMoveElimination(ArrayList<Direction> moves, int gridDimension, ArrayList<Integer> snakeCoordinate)
    {
        moves = allMoveConstructor(moves);
        if(snakeCoordinate.get(0) == 0)
        {
            moves.remove(Direction.LEFT);
        }
        if(snakeCoordinate.get(0) == gridDimension)
        {
            moves.remove(Direction.RIGHT);
        }
        if(snakeCoordinate.get(1) == 0)
        {
            moves.remove(Direction.UP);
        }
        if(snakeCoordinate.get(1) == gridDimension)
        {
            moves.remove(Direction.DOWN);
        }
        return moves;
    }

    public ArrayList<Direction> allMoveConstructor(ArrayList<Direction> moves)
    {
        moves.add(Direction.UP);
        moves.add(Direction.LEFT);
        moves.add(Direction.RIGHT);
        moves.add(Direction.DOWN);
        return moves;
    }

    public ArrayList<Integer> integerArrayList(int intToStore, int numElements)
    {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        for(int i = 0; i < numElements; i++)
        {
            integerArrayList.add(0, intToStore);
        }
        return integerArrayList;
    }
}
