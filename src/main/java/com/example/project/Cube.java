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

	}


	public static void main(String[] args) {
		Face front = new Face("Front","w");
		front.printFace();
		System.out.println();

		//Simulate a top rotate. The top of white should be changed to the top of green
		String[] topFront = front.getSide("L");
		String[] toAdd = {"o", "o", "o"};
		front.setSide("L", toAdd);
		front.printFace();
	}







}