/*Neven Marinkovic
3/2/2024
CSCI 152: The Cube
The goal of this assignment was to model a Rubiks cube in the command line. Specifically, to be able to make valid moves on the cube and print out the cube, as well as keeping track of the moves
needed to solve the cube. I took a class approach to this assignment, utilizing a RubiksCube and Face class. 

The Face class has a position name which is a combination of the color and respective side of that face (ex: White - Front Face or Orange - Top Face). The face class also has a 9 element string 
array that stores all 9 of the cubes. The face class implements functions like printFace, getSide, and setSide, which are discussed in greater detail below.

The RubiksCube class represents the cube as a whole and implements 6 Face objects as such. The RubiksCube class has functions like move, rotate face, print cube, and print side, which are
discussed in greater detail below.

This program has 2 modes. The first mode is with command line arguments. The user runs the program like: ...'come.example.project.Cube' D "R'" U to run those moves on the cube, the program
will make those moves on the cube, print out the cube state, and then print out the moves needed to solve the cube before exiting. The other mode is without command line arguments. The user
is prompted to enter commands to be done. These commands could be to make a move on the cube, print out the whole cube, print out a specific side, or randomize the cube.
 */

package com.example.project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Cube
{

	public static class Face{
		private String position;
		private String[] cubes = new String[9];

		//Constructor for the face object. Position is the face (front, left, back, etc.) and val is the initial color val for that face (w, y, r, etc.)
		public Face(String pos, String val)
		{
			position = pos;
			//Loop through and assign each cube of this face to be whatever val is
			for(int i = 0; i < cubes.length; i++)
			{
				cubes[i] = val;
				//cubes[i] = val + i //Utilize if i need to see the numbers again
			}
		}

		//This function prints out the name of the face before looping through each cube in the face and printing it 
		public void printFace()
		{
			System.out.println(position);
			int count = 0;
			for(int i = 0; i < cubes.length; i++)
			{
				if(count == 2)
				{
					System.out.println(cubes[i]);
					count = 0;
				}
				else
				{
					System.out.print(cubes[i] + "|");
					count ++;
				}
			}
		}

		/*getSide takes in a String side. The possible sides are T, L, R, B. This function returns the side of a face, see the diagram below:
		Face implementation:
		[
			0, 1, 2
			3, 4, 5
			6, 7, 8
		]
		[0, 1, 2] represent the top of the face. [0, 3, 6] represent the left side of the face. [2, 5, 8] represent the right side of the face. And, [6, 7, 8] represent the bottom of the face
		This function returns a string array of 3 elements which represent either the top, left, right, or bottom sides of a given face
		This function is used alongside setSide to make moves on the cube
		*/
		public String[] getSide(String side)
		{
			String[] toReturn = new String[3];
			switch(side)
			{
				//Return the top of the face
				case "T":
					toReturn[0] = cubes[0];
					toReturn[1] = cubes[1];
					toReturn[2] = cubes[2];
					break;
				case "L":
					toReturn[0] = cubes[0];
					toReturn[1] = cubes[3];
					toReturn[2] = cubes[6];
					break;
				case "R":
					toReturn[0] = cubes[2];
					toReturn[1] = cubes[5];
					toReturn[2] = cubes[8];
					break;
				case "B":
					toReturn[0] = cubes[6];
					toReturn[1] = cubes[7];
					toReturn[2] = cubes[8];
					break;
				default:
				System.out.println("Something went wrong...");
					
			}
			return toReturn;
		}

		/*setSide takes in an array of 3 string elements (a side of a face) and sets it equal to whatever side we want to change. This function is used alongside getSide to make moves on the 
		cube and adjust the cubes accordingly. 
		The boolean regular is used to verify if the move we are trying to make is a regular (cubes[0] - toAdd[0], cubes[1] - toAdd[1], cubes[2] - toAdd[2]) move or not.
		The way i implemented the rubiks cube in this program looks at each face from a forward view. With each move, one side of one face is assigned to a side on another face. 
		let's take the L' move for example. The left side of the front face moves to the left side of the top face. The left side of the top face moves to the RIGHT side of the back face,
		The right side of the back face moves to the left side of the bottom face, and the left side of the bottom face moves to the left side of the front face.
		When setting sides, your really assigning each cube of a 3 cube side to another 3 cube side. Sometimes, the cubes within the sides match up nicely (ex: the U move). What I mean is that,
		cubes 0, 1, 2 from the front side go to cubes 0, 1, 2 on the left side and so on. This is what I call a regular move. There are some moves, like the left side of the top face moving to
		the right side of the back face where the transition is not regular. Cubes 0, 3, 6 from the top side are now being set to cubes 2, 5, 8 on the back side. The first cube from the top side
		is set as the LAST cube on the back side (0 -> 8), the middle one remains constant (2 -> 5), and the last cube on the top side is set to the first cube on the back side (6 -> 2). These
		are what i classify as irregular transitions as the first cube in one side is not set to the first cube on another side. 
		 */
		public void setSide(String side, String[] toAdd, boolean regular)
		{
			if(regular)
			{
				switch(side)
				{
					case "T":
						cubes[0] = toAdd[0];
						cubes[1] = toAdd[1];
						cubes[2] = toAdd[2];
						break;
					case "L":
						cubes[0] = toAdd[0];
						cubes[3] = toAdd[1];
						cubes[6] = toAdd[2];
						break;
					case "R":
						cubes[2] = toAdd[0];
						cubes[5] = toAdd[1];
						cubes[8] = toAdd[2];
						break;
					case "B":
						cubes[6] = toAdd[0];
						cubes[7] = toAdd[1];
						cubes[8] = toAdd[2];
						break;
					default:
						System.out.println("Something went wrong...");
				}
			}
			else
			{
				switch(side)
				{
					case "T":
						cubes[0] = toAdd[2];
						cubes[1] = toAdd[1];
						cubes[2] = toAdd[0];
						break;
					case "L":
						cubes[0] = toAdd[2];
						cubes[3] = toAdd[1];
						cubes[6] = toAdd[0];
						break;
					case "R":
						cubes[2] = toAdd[2];
						cubes[5] = toAdd[1];
						cubes[8] = toAdd[0];
						break;
					case "B":
						cubes[6] = toAdd[2];
						cubes[7] = toAdd[1];
						cubes[8] = toAdd[0];
						break;
					default:
						System.out.println("Something went wrong...");
				}
			}
			
		}

	}

	public static class RubiksCube
	{
		private Face front;
		private Face left;
		private Face right;
		private Face back;
		private Face top;
		private Face bottom;
		private Face[] faces = new Face[6];

		//Constructor for the cube 
		public RubiksCube()
		{
			front = new Face("White - Front Face","w");
			left = new Face("Green - Left Face", "g");
			right = new Face("Blue - Right Face","b");
			back = new Face("Yellow - Back Face", "y");
			top = new Face("Orange - Top Face","o");
			bottom = new Face("Red - Bottom Face", "r");
			faces[0] = front;
			faces[1] = left;
			faces[2] = back;
			faces[3] = right;
			faces[4] = top;
			faces[5] = bottom;

		}

		//This function shifts the sides of a given face around depending on the direction given
		public void rotateFace(Face f, boolean clockwise)
		{
			String [] top = f.getSide("T");
			String [] left = f.getSide("L");
			String [] right = f.getSide("R");
			String [] bottom = f.getSide("B");

			if(clockwise)
			{
				f.setSide("T", left, false);
				f.setSide("R", top, true);
				f.setSide("B", right, false);
				f.setSide("L", bottom, true);

			}
			else
			{
				f.setSide("T", right, true);
				f.setSide("R", bottom, false);
				f.setSide("B", left, true);
				f.setSide("L", top, false);
			}

		}

		//This function loops through the cube's faces and prints each face
		public void printCube()
		{
			System.out.println();
			for(Face face: faces)
			{
				face.printFace();
				System.out.println();
			}
		}

		//This function takes a color argument and prints out the respective face accordingly
		public void printSide(String color)
		{
			switch(color)
			{
				case "W":
					System.out.println("White - Front Face");
					front.printFace();
					break;
				case "G":
					System.out.println("Green - Left Face");
					left.printFace();
					break;
				case "Y":
					System.out.println("Yellow - Back Face");
					back.printFace();
					break;
				case "BL":
					System.out.println("Blue - Right Face");
					right.printFace();
					break;
				case "O":
					System.out.println("Orange - Top Face");
					top.printFace();
					break;
				case "RED":
					System.out.println("Red - Bottom Face");
					bottom.printFace();
					break;
			}
		}

		/*This function takes a string move argument (D, R', F, etc.) and makes a move on the cube accordingly. Each if branch follows the same logic: Grab the desired side from one of the 
		faces and store it as a temp String[]. Then, starting with that side, assign the desired side a side from the next side in the sequence (use face.setSide and face.getSide). Do this for
		each of the faces with the last face being set equal to our temp side we initially got. Lastly, rotate the face accordingly. I utilized the diagram in the README as well as this
		https://rubikscu.be virtual cube to understand how each side is affected on each move.

		I ran out of time, but there is definitely a way to streamline this function. As I said, the logic for each of these is the same, which leads me to believe there is a much more optimized
		way to handle the moves on the cube.
		*/
		

		public void move(String move)
		{
			
			if(move == "U")
			{
				String[] topOfLeft = left.getSide("T");	

				left.setSide("T", front.getSide("T"), true);
				front.setSide("T", right.getSide("T"), true);
				right.setSide("T", back.getSide("T"), true);
				back.setSide("T", topOfLeft, true);

				rotateFace(top, true);

			}
			else if (move == "U'")
			{
				String[] topOfLeft = left.getSide("T");
				left.setSide("T", back.getSide("T"), true);
				back.setSide("T", right.getSide("T"), true);
				right.setSide("T", front.getSide("T"), true);
				front.setSide("T", topOfLeft, true);

				rotateFace(top, false);

			}
			else if(move == "D")
			{
				String [] bottomFront = front.getSide("B");
				
				front.setSide("B", left.getSide("B"), true);
				left.setSide("B", back.getSide("B"), true);
				back.setSide("B", right.getSide("B"), true);
				right.setSide("B", bottomFront, true);
				rotateFace(bottom, true);
			}
			else if(move == "D'")
			{
				String [] bottomFront = front.getSide("B");
				
				front.setSide("B", right.getSide("B"), true);
				right.setSide("B", back.getSide("B"), true);
				back.setSide("B", left.getSide("B"), true);
				left.setSide("B", bottomFront, true);
				
				rotateFace(bottom, false);
			}

			else if(move == "R")
			{
				String [] rightOfFront = front.getSide("R");

				front.setSide("R", bottom.getSide("R"), true);
				bottom.setSide("R", back.getSide("L"), false);
				back.setSide("L", top.getSide("R"), false);
				top.setSide("R", rightOfFront, true);

				rotateFace(right, true);
			}
			else if(move == "R'")
			{
				String [] rightOfFront = front.getSide("R");

				front.setSide("R", top.getSide("R"), true);
				top.setSide("R", back.getSide("L"), false);
				back.setSide("L", bottom.getSide("R"), false);
				bottom.setSide("R", rightOfFront, true);

				rotateFace(right, false);
			}
			
			else if(move == "L")
			{
				String [] leftOfFront = front.getSide("L");

				front.setSide("L", top.getSide("L"), true);
				top.setSide("L", back.getSide("R"), false);
				back.setSide("R", bottom.getSide("L"), false);
				bottom.setSide("L", leftOfFront, true);

				rotateFace(left, true);

			}
			else if(move == "L'")
			{
				String [] leftOfFront = front.getSide("L");
				front.setSide("L", bottom.getSide("L"), true);
				bottom.setSide("L", back.getSide("R"), false);
				back.setSide("R", top.getSide("L"), false);
				top.setSide("L", leftOfFront, true);
				rotateFace(left, false);
			}
			
			else if (move == "F")
			{
				String[] rightOfLeft = left.getSide("R");
				left.setSide("R", bottom.getSide("T"), true);
				bottom.setSide("T", right.getSide("L"), false);
				right.setSide("L", top.getSide("B"), true);
				top.setSide("B", rightOfLeft, false);
				rotateFace(front, true);
			}
			else if(move == "F'")
			{
				String[] rightOfLeft = left.getSide("R");
				left.setSide("R", top.getSide("B"), false);
				top.setSide("B", right.getSide("L"), true);
				right.setSide("L",bottom.getSide("T"), false);
				bottom.setSide("T", rightOfLeft, true);
				rotateFace(front, false);
			}
			else if (move == "B")
			{
				String[] rightOfRight = right.getSide("R");
				right.setSide("R", bottom.getSide("B"), false);
				bottom.setSide("B", left.getSide("L"), true);
				left.setSide("L", top.getSide("T"), false);
				top.setSide("T", rightOfRight, true);
				
				rotateFace(back, true);
			}
			else if(move == "B'")
			{
				String[] rightOfRight = right.getSide("R");
				right.setSide("R", top.getSide("T"), true);
				top.setSide("T", left.getSide("L"), false);
				left.setSide("L", bottom.getSide("B"), true);
				bottom.setSide("B", rightOfRight, false);
				rotateFace(back, false);
			}
			else
			{
				System.out.println("An incorrect move has been entered...\n");
			}
		

		}

	}

	//This function takes in a list of string (pastMoves) which are representative of the moves needed to complete the cube. Each time a move is made in the main loop, the opposite move is added
	//to a list. That list is then passed to this function which prints out all of the "opposite" moves
	static void solveCube(ArrayList<String> pastMoves)
	{
		
			System.out.println("\nMoves to complete cube: ");
			for(int i = pastMoves.size() - 1; i >= 0; i--)
			{
				System.out.println(pastMoves.get(i));
			}
			System.out.println();
		}

	//This function loops through a random number of times (1,9) and makes a random move each time. The anti-moves are gotten using the opposite function and are stored in the movesToComplete
	//list. That list is returned
	static ArrayList<String> randomizeCube(RubiksCube cube)
	{
		Random random = new Random();
		int ranNum = random.nextInt(10) + 1;
		ArrayList<String> movesToComplete = new ArrayList<>();

		String[] moves = {"U", "U'", "R", "R'", "D", "D'", "L", "L'", "F'", "F", "B", "B'"};
		for(int i = 0; i < ranNum; i++)
		{
			
			int move = random.nextInt(12);
			cube.move(moves[move]);
			movesToComplete.add(opposite(moves[move]));
		}
		return movesToComplete;
	}

	//This function takes a string move in as an argument. if that move is a prime (ex: R') return the move without the prime (R). Otherwise, add a prime to the move and return that.
	static String opposite(String s)
	{
		if(s.contains("'"))
		{
			char first = s.charAt(0);
			return Character.toString(first);
		}
		else
		{
			return s + "'";
		}
	}
	
	public static void main(String[] args) {
		//Initialize the cube and the moves list to track the anti-moves (needed to solve the cube)
		RubiksCube cube = new RubiksCube();
		ArrayList<String> moves = new ArrayList<>();

		if(args.length != 0)	//Command line inputs are being used
		{
			
				for(int i = 0; i < args.length; i++)
				{
					//For each command line argument, perform the move on the cube and add the anti-move to moves.
					String m = args[i].toUpperCase();
					switch (m)
					 {
					case "U":
						cube.move("U");
						moves.add("U'");	
						break;
					case "D":
						cube.move("D");
						moves.add("D'");
						break;
					case "R":
						cube.move("R");
						moves.add("R'");
						break;
					case "L":
						cube.move("L");
						moves.add("L'");	
						break;
					case "F":
						cube.move("F");
						moves.add("F'");
						break;
					case "B":
						cube.move("B");
						moves.add("B'");
						break;
					case "U'":
						cube.move("U'");
						moves.add("U");
						break;
					case "D'":
						cube.move("D'");
						moves.add("D");
						break;
					case "R'":
						cube.move("R'");
						moves.add("R");
						break;
					case "L'":
						cube.move("L'");
						moves.add("L");
						break;
					case "F'":
						cube.move("F'");
						moves.add("F");
						break;
					case "B'":
						cube.move("B'");
						moves.add("B");
						break;
					default:
						System.out.println("There are no matches for the move entered");
						break;
					}
				}

				//Print out the final state of the cube as well as the steps to solve the cube. Exit the program
				cube.printCube();
				solveCube(moves);
				System.exit(0);
			}
			

		else
		{
			//This block is the user input option
			Scanner scn = new Scanner(System.in);
			
			//Continue to ask the user for input until they enter x (to quit). Potential commands include any valid move on the cube, printing the whole cube, printing a side of the cube,
			//or randomizing the cube.
			while(true)
			{
				System.out.println("Please enter a move {D, U, L, R, F, B, D', U', etc.}\nEnter CUBE to see the current state of the cube, enter a color (W, G, Y, BL, O, RED) to see a specific side OR enter RANDOM to randomize the cube\nEnter X to quit.\n");
				String input = scn.nextLine();

				switch(input.toUpperCase())
				{
					case "CUBE":
						cube.printCube();
						break;
					
					case "U":
						cube.move("U");
						moves.add("U'");	
						break;
					case "D":
						cube.move("D");
						moves.add("D'");
						break;
					case "R":
						cube.move("R");
						moves.add("R'");
						break;
					case "L":
						cube.move("L");
						moves.add("L'");	
						break;
					case "F":
						cube.move("F");
						moves.add("F'");
						break;
					case "B":
						cube.move("B");
						moves.add("B'");
						break;
					case "U'":
						cube.move("U'");
						moves.add("U");
						break;
					case "D'":
						cube.move("D'");
						moves.add("D");
						break;
					case "R'":
						cube.move("R'");
						moves.add("R");
						break;
					case "L'":
						cube.move("L'");
						moves.add("L");
						break;
					case "F'":
						cube.move("F'");
						moves.add("F");
						break;
					case "B'":
						cube.move("B'");
						moves.add("B");
						break;
					case "X":
						scn.close();
						System.exit(0);
						break;
					case "W":
						cube.printSide("W");
						break;
					case "Y":
						cube.printSide("Y");
						break;
					case "G":
						cube.printSide("G");
						break;
					case "BL":
						cube.printSide("BL");
						break;
					case "O":
						cube.printSide("O");
						break;
					case "RED":
						cube.printSide("RED");
						break;
						//If random is selected, randomize the Cube. newMoves is the list of anti-moves stored from the randomizing moves. Add each of these new moves to our existing list
						//of moves
					case "RANDOM":
						ArrayList<String> newMoves = randomizeCube(cube);
						for(int j = 0; j < newMoves.size(); j++)
						{
							moves.add(newMoves.get(j));
						}
						break;
					default:
						System.out.println("Please enter a valid move");
						break;

				}
				solveCube(moves);

			}


	}
	}
}

