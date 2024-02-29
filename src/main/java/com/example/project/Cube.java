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
					cubes[0] = toAdd[2];
					cubes[3] = toAdd[5];
					cubes[6] = toAdd[8];
					break;
				case "B":
					cubes[0] = toAdd[6];
					cubes[3] = toAdd[7];
					cubes[6] = toAdd[8];
					break;
				default:
					System.out.println("Something went wrong...");
			}
		}

		/*
		public void rotateFace(Face F, boolean clockwise)
		{
			if(clockwise)
			{
				String[] sides = 
			}
		}
		*/

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
			faces[2] = right;
			faces[3] = back;
			faces[4] = top;
			faces[5] = bottom;

		}

		public void printCube()
		{
			for(Face face: faces)
			{
				face.printFace();
				System.out.println();
			}
		}

		//Add some get/set functions for each face
	}


	public static void main(String[] args) {
		RubiksCube cube = new RubiksCube();
		cube.printCube();

	}







}