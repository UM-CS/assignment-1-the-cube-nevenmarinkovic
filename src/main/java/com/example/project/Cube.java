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

		public String[] getSide(String side)
		{
			String[] toReturn = new String[3];
			switch(side)
			{
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

		public void printCube()
		{
			System.out.println();
			for(Face face: faces)
			{
				face.printFace();
				System.out.println();
			}
		}

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

		

		public void move(String move)
		{
			//U move and D moves can use the regular set.side as its 0-0, 1-1, and 2-2. Other moves, such as r, will have to use some 0-2, 1-1, 2-0
			if(move == "U")
			{
				String[] topOfLeft = left.getSide("T");	//Need one of the sides to be temp

				//.out.println("")
				left.setSide("T", front.getSide("T"), true);
				front.setSide("T", right.getSide("T"), true);
				right.setSide("T", back.getSide("T"), true);
				back.setSide("T", topOfLeft, true);

				//Rotate the top
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

	static void solveCube(ArrayList<String> pastMoves)
	{
		
			System.out.println("\nMoves to complete cube: ");
			for(int i = pastMoves.size() - 1; i >= 0; i--)
			{
				System.out.println(pastMoves.get(i));
			}
			System.out.println();
		}

	static ArrayList<String> randomizeCube(RubiksCube cube)
	{
		Random random = new Random();
		int ranNum = random.nextInt(10);
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
		RubiksCube cube = new RubiksCube();
		ArrayList<String> moves = new ArrayList<>();

		if(args.length != 0)	//Command line inputs are being used
		{
			
				for(int i = 0; i < args.length; i++)
				{
					String m = args[i].toUpperCase();
					switch (m)
					 {
					case "U":
						cube.move("U");
						moves.add("U'");	//add the opposite move to our moves list, that way we know how to solve the cube
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

				cube.printCube();
				solveCube(moves);
				System.exit(0);
			}
			

		else
		{
			Scanner scn = new Scanner(System.in);
			
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
						moves.add("U'");	//add the opposite move to our moves list, that way we know how to solve the cube
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

