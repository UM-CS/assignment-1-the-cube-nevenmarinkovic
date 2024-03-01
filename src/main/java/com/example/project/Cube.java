package com.example.project;

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
			}
		}

		public void printFace()
		{
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

		public void setSide(String side, String[] toAdd)
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
			if(clockwise)
			{
				f.setSide("T", left);
				f.setSide("R", top);
				f.setSide("B", right);
				f.setSide("L", bottom);
			}
			else
			{
				f.setSide("T", right);
				f.setSide("R", bottom);
				f.setSide("B", left);
				f.setSide("L", top);
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
			
			
			if(move == "u")
			{
				String[] topOfLeft = left.getSide("T");	//Need one of the sides to be temp

				//.out.println("")
				left.setSide("T", front.getSide("T"));
				front.setSide("T", right.getSide("T"));
				right.setSide("T", back.getSide("T"));
				back.setSide("T", topOfLeft);

				//Rotate the top
				rotateFace(top, true);

			}
			else if(move == "d")
			{
				String [] bottomFront = front.getSide("B");
				
				front.setSide("B", left.getSide("B"));
				left.setSide("B", back.getSide("B"));
				back.setSide("B", right.getSide("B"));
				right.setSide("B", bottomFront);

				rotateFace(bottom, true);

			}

			else if(move == "r")
			{
				String [] rightOfFront = front.getSide("R");

				front.setSide("R", bottom.getSide("R"));
				bottom.setSide("R", back.getSide("R"));
				back.setSide("L", top.getSide("R"));
				top.setSide("R", rightOfFront);

				rotateFace(right, true);
			}

			else if(move == "l")
			{
				String [] leftOfFront = front.getSide("L");

				front.setSide("L", top.getSide("L"));
				top.setSide("L", back.getSide("R"));
				back.setSide("R", bottom.getSide("L"));
				bottom.setSide("L", leftOfFront);

				rotateFace(left, true);

			}

		}

		//Add some get/set functions for each face
	}


	public static void main(String[] args) {
		RubiksCube cube = new RubiksCube();
		cube.printCube();
		System.out.println("-----------------------------------------------------------");
		cube.move("r");
		//cube.printCube();
		//System.out.println("-----------------------------------------------------------");
		cube.move("r");
		//cube.printCube();
		//System.out.println("-----------------------------------------------------------");
		cube.move("d");
		cube.printCube();



	}







}