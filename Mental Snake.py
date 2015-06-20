#-- Import modules
import turtle, time, random
#-- Set variables
draw = False
move = 'Start'
easy = 5
medium = 9
hard = 13
#-- Set lists
moves = ['Start', 'Start', 'Start']
directions = ['left', 'right', 'up', 'down']

#-- Define functions

#--- Function to control whether the turtle (robot) should draw
def draw(boolean):
    if boolean == False:
        turtle.pen(pendown=False)
    elif boolean == True:
        turtle.pen(pendown=True)
    return True

#--- Function to get difficulty level (First to be executed)
def startup():
    global moves, diff, easy, medium, hards, grid
    diff = str(input("Enter a difficulty level:   "))
    if diff == "Easy" or diff == "easy":
        diff = "easy"
    elif diff == "Medium" or diff == "medium":
        diff = "medium"
    elif diff == "Hard" or diff == "hard":
        diff = "hard"
    if diff == "easy":
        grid = easy
    elif diff == "medium":
        grid = medium
    elif diff == "hard":
        grid = hard
    if diff == "Easy" or diff == "easy": 
        print("Starting game at Easy difficulty...")
        time.sleep(3)
        print("\nInput Left, Right, Up or Down")
        getMoves()
    elif diff == "Medium" or diff == "medium":
        print("Starting game at Medium difficulty")
        time.sleep(3)
        print("\nInput Left, Right, Up or Down")
        getMoves()
    elif diff == "Hard" or diff == "hard":
        print("Starting game at Hard difficulty")
        time.sleep(3)
        print("\nInput Left, Right, Up or Down")
        getMoves()

    else:
        print("Please enter Easy, Medium, or Hard")
        time.sleep(1)
        startup()
        
#--- Function to get moves from user and computer, and draw the invisble map
def getMoves():
    #Calls the variable 'moves', and the list 'directions' for use
    global moves
    x = True #Used in the 'While' loop
    i = 1  #Callable variable
    num_moves = 0  #Number of moves variable
    while x == True: #While loop (repeats until certain criteria are set)
        #First map tracking, if the user goes back on themselves or the computer
        #The game ends
        if moves[i] == "left" and moves[i-1] == "right":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        elif moves[i] == "up" and moves[i-1] == "down":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        elif moves[i] == "down" and moves[i-1] == "up":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        elif moves[i] == "right" and moves[i-1] == "left":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        move = input("Enter:  ")
        if move == "Right" or move == "right":
            moves.append('right')
            i += 1
            num_moves += 1
            compMove(moves, i+1)
        elif move == "Left" or move == "left":
            moves.append('left')
            i += 1
            num_moves += 1
            compMove(moves, i+1)
        elif move == "Up" or move == "up":
            moves.append('up')
            i += 1
            num_moves += 1
            compMove(moves, i+1)
        elif move == "Down" or move == "down":
            moves.append('down')
            i += 1
            num_moves += 1
            compMove(moves, i+1)
        elif move == "Finish" or move == "finish":
            x = False
            time.sleep(1)
            drawGrid(moves, num_moves)
        else:
            print("Invalid Input...")
            
def compMove(moves, i):
    comp_move = random.choice(directions)
    if moves[i] == "left" and comp_move == "right":
        compMove(moves, i)
    elif moves[i] == "up" and comp_move == "down":
        compMove(moves, i)
    elif moves[i] == "down" and comp_move == "up":
        compMove(moves, i)
    elif moves[i] == "right" and comp_move == "left":
        compMove(moves, i)
    moves.append(comp_move)
    print("The computer moved", comp_move)
    time.sleep(1)
    getMoves()
    
    

#--- Function to draw the moves onto the grid
def drawGrid(moves, num_moves):
    global diff, grid
    a = 1
    print("Drawing Grid...")
    moves.append('Finish')
    turtle.reset()
    if grid == medium: #If the grid is medium sized
        turtle.speed(5)  #set the speed of the turtle to 5 (faster)
    elif grid == hard: #If the grid is hard sized
        turtle.speed(8)  #set the speed of the turtle to 8 (a lot faster)
    turtle.color("black", "black")
    draw(False)
    turtle.left(90)
    turtle.forward(25*grid)
    turtle.right(90)
    turtle.back(25*grid)
    draw(True)
    #Draws out the grid itself
    for i in range(0, 4):
        turtle.forward(50*grid)
        turtle.right(90)
    for i in range(0, grid):
        turtle.forward(50)
        turtle.right(90)
        turtle.forward(50*grid)
        turtle.back(50*grid)
        turtle.left(90)
    turtle.back(50*grid)
    for i in range(0, grid):
        turtle.right(90)
        turtle.forward(50)
        turtle.left(90)
        turtle.forward(50*grid)
        turtle.back(50*grid)
    draw(False)
    turtle.left(90)
    turtle.forward(25*grid)
    turtle.right(90)
    turtle.forward(25*grid)
    draw(True)
    turtle.speed(3)
    for direction in moves:
        if direction == 'Start':
            a += 1
        elif direction == 'Finish':
            endGame(num_moves)
        elif direction == "up":
            turtle.left(90)
            turtle.forward(50)
            turtle.stamp()
            turtle.right(90)
            num_moves += 1
        elif direction == "down":
            turtle.right(90)
            turtle.forward(50)
            turtle.stamp()
            turtle.left(90)
            num_moves += 1
        elif direction == "left":
            turtle.right(180)
            turtle.forward(50)
            turtle.stamp()
            turtle.right(180)
            num_moves += 1
        elif direction == "right":
            turtle.forward(50)
            turtle.stamp()
            num_moves += 1
    print("Finished drawing grid...")
    return True

    
def endGame(num_moves):
    print("You took", num_moves+1, "goes")
    return False

startup()
