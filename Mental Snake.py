import turtle, time

draw = False
move = 'Start'
easy = 5
medium = 9
hard = 13

moves = ['Start', 'Start', 'Start']

def draw(boolean):
    if boolean == False:
        turtle.pen(pendown=False)
    elif boolean == True:
        turtle.pen(pendown=True)
    return True

def startup():
    global moves
    diff = str(input("Enter a difficulty level:   "))
    if diff == "Easy" or diff == "easy" or diff == "Medium" or diff == "medium" or diff == "Hard" or diff == "hard":
        print("Starting game at", diff, "difficulty...")
        time.sleep(3)
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
        print("Input Left, Right, Up or Down")
        getMoves(move)

    else:
        print("Please enter Easy, Medium, or Hard")
        time.sleep(1)
        startup()

def getMoves(move):
    global moves
    x = True
    i = 1
    num_moves = 0
    while x == True:
        if moves[i] == "left" and moves[i+1] == "right":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        elif moves[i] == "up" and moves[i+1] == "down":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        elif moves[i] == "down" and moves[i+1] == "up":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        elif moves[i] == "right" and moves[i+1] == "left":
            print("You crashed yourself!")
            print("Game Over!")
            time.sleep(2)
            endGame(num_moves)
        move = input("Enter:  ")
        if move == "Right" or move == "right":
            moves.append('right')
            i += 1
            num_moves += 1
        elif move == "Left" or move == "left":
            moves.append('left')
            i += 1
            num_moves += 1
        elif move == "Up" or move == "up":
            moves.append('up')
            i += 1
            num_moves += 1
        elif move == "Down" or move == "down":
            moves.append('down')
            i += 1
            num_moves += 1
        elif move == "Finish" or move == "finish":
            x = False
            time.sleep(1)
            drawGrid(moves, num_moves)

    
def drawGrid(moves, num_moves):
    a = 1
    print("Drawind Grid...")
    moves.append('Finish')
    turtle.color("black", "black")
    draw(False)
    turtle.forward(1)
    turtle.back(1)
    draw(True)
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
        elif direction == "down":
            turtle.right(90)
            turtle.forward(50)
            turtle.stamp()
            turtle.left(90)
        elif direction == "left":
            turtle.right(180)
            turtle.forward(50)
            turtle.stamp()
            turtle.right(180)
        elif direction == "right":
            turtle.forward(50)
            turtle.stamp()
    print("Finished drawing grid...")
    return True

    
def endGame(num_moves):
    print("You took", num_moves, "goes")
    return False

startup()
