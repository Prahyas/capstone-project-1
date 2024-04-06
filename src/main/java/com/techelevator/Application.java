package com.techelevator;

import java.util.Scanner;

public class Application {

	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu(){
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");

			try{
				int input = scanner.nextInt();

				switch(input){
					case 1:
						System.out.println("run display method");
						//display();
						return;
					case 2:
						System.out.println("run purchase method");
						purchase();
						return;
					case 3:
						System.out.println("Closing program.");
						keepRunning = false;
						break;
				}
			}catch(Exception e){
				System.out.println("invalid input");
			}
		}
	}

	private static void purchase(){
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		System.out.println("(4) Main Menu");

		try{
			int input = scanner.nextInt();

			switch(input){
				case 1:
					System.out.println("run feed money method");
					//feedMoney();
					return;
				case 2:
					System.out.println("run select product method");
					//selectProduct();
					return;
				case 3:
					System.out.println("run finish transaction method");
					//finishTransaction();
					return;
				case 4:
					System.out.println("Returning to Main Menu.");
					mainMenu();
			}
		}catch(Exception e){
			System.out.println("invalid input");
		}
	}

}
