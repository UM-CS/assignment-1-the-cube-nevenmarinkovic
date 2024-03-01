package com.example.project;

import java.util.ArrayList;

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
				cubes[i] = val + i;
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

		//If the rotation is clockwise, left side -> top side OR right side -> bottom side (top[0] = left [2], top[1] = left[1], top[2] = left[0])
		//if the rotation is counterclockwise, top side -> left side OR bottom side -> right side (left[0] = top[2], left[1] = top[1], left[2] = top[0])
		private void rotateSetSide(String side, String[] add, boolean clockwise)
		{
			switch(side)
			{
				case "T":
					if(clockwise)
					{
						cubes[0] = add[2];
						cubes[1] = add[1];
						cubes[2] = add[0];
					}
					else
					{
						cubes[0] = add[0];
						cubes[1] = add[1];
						cubes[2] = add[2];
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
			front = new Face("Front","w");
			left = new Face("Left", "g");
			right = new Face("Right","b");
			back = new Face("Back", "y");
			top = new Face("Top","o");
			bottom = new Face("Bottom", "r");
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
			/*
			System.out.println("Top Cubes: " + top[0] + top[1] + top[2]);
			System.out.println("Left Cubes: " + left[0]+ left[1] + left[2]);
			System.out.println("Right Cubes: " + right[0] + right[1] + right[2]);
			System.out.println("Bottom Cubes: " + bottom[0] + bottom[1] + bottom[2]);
			*/
			if(clockwise)
			{
				f.setSide("T", left, false);
				f.setSide("R", top, true);
				f.setSide("B", right, false);
				f.setSide("L", bottom, true);
				/* 
				String [] newTop = f.getSide("T");
				String [] newLeft = f.getSide("L");
				String [] newRight = f.getSide("R");
				String [] newBottom = f.getSide("B");
				System.out.println("Top Cubes: " + newTop[0] + newTop[1] + newTop[2]);
				System.out.println("Left Cubes: " + newLeft[0]+ newLeft[1] + newLeft[2]);
				System.out.println("Right Cubes: " + newRight[0] + newRight[1] + newRight[2]);
				System.out.println("Bottom Cubes: " + newBottom[0] + newBottom[1] + newBottom[2]);
				*/
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
			for(Face face: faces)
			{
				face.printFace();
				System.out.println();
			}
		}

		public void move(String move)
		{
			//U move and D moves can use the regular set.side as its 0-0, 1-1, and 2-2. Other moves, such as r, will have to use some 0-2, 1-1, 2-0
			
			if(move == "u")
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
			else if(move == "d")
			{
				String [] bottomFront = front.getSide("B");
				
				front.setSide("B", left.getSide("B"), true);
				left.setSide("B", back.getSide("B"), true);
				back.setSide("B", right.getSide("B"), true);
				right.setSide("B", bottomFront, true);
				

				rotateFace(bottom, true);

			}

			else if(move == "r")
			{
				String [] rightOfFront = front.getSide("R");

				front.setSide("R", bottom.getSide("R"), true);
				bottom.setSide("R", back.getSide("L"), false);
				back.setSide("L", top.getSide("R"), false);
				top.setSide("R", rightOfFront, true);

				rotateFace(right, true);
			}
			
			else if(move == "l")
			{
				String [] leftOfFront = front.getSide("L");

				front.setSide("L", top.getSide("L"), true);
				top.setSide("L", back.getSide("R"), false);
				back.setSide("R", bottom.getSide("L"), false);
				bottom.setSide("L", leftOfFront, true);

				rotateFace(left, true);

			}
			/*
			else if (move == "f")
			{
				String[] rightOfLeft = left.getSide("R");
				left.setSide("R", bottom.getSide("T"));
				bottom.setSide("T", right.getSide("L"));
				right.setSide("L", top.getSide("B"));
				top.setSide("B", rightOfLeft);
				

				rotateFace(front, true);
			}
			*/

		}

		//Add some get/set functions for each face
	}


	public static void main(String[] args) {
		RubiksCube cube = new RubiksCube();
		cube.printCube();
		//System.out.println("-----------------------------------------------------------");
		cube.move("l");
		//cube.printCube();
		//System.out.println("-----------------------------------------------------------");
		cube.move("d");
		//cube.printCube();
		System.out.println("-----------------------------------------------------------");
		cube.move("r");
		cube.printCube();



	}






ArrayList<String> moves = new ArrayList<>();
}

/*

static void solveCube(ArrayList<String> pastMoves)
{
	for(int i = pastMoves.size - 1; i >= 0; i--)
	{
		System.out.println(pastMoves.get(i));
	}
	System.out.println(;)
}

ArrayList<String> moves = new ArrayList<>();
 while(true)
 {
	System.out.println("Please enter a move {D, U, L, R, F, B}");
	String input = scn.nextLine();
	switch(input.toUpperCase())
	{
		case "U":
			moves.add("U'")	//add the opposite move to our moves list, that way we know how to solve the cube
			break;
		case "D":
			break;
		case "R":
			break;
		case "L":
			break;
		case "F":
			break;
		case "B":
			break;
		case "U'":
			break;
		case "D'":
			break;
		case "R'":
			break;
		case "L'":
			break;
		case "F'":
			break;
		case "B'":
			break;
		default:
			System.out.println("Please enter a valid move")

	}
 }
 */