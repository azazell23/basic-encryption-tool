package main;

import java.util.Scanner;

import Application.User;
import Authentication.Auth;
import Cryptographers.RSA;
import Seeders.Seeder;

public class App {
    private static RSA rsa = new RSA();
    private static Scanner scanner = new Scanner(System.in);
    private static User user = null;
    public static void main(String[] Args) throws Exception
    {
    		String username, password;
    		while (user == null)
    		{
    			try {
		    		System.out.print("Input your username: ");
		    		username = scanner.nextLine();
		    		
		    		System.out.print("Input your password: ");
		    		password = scanner.nextLine();
		    		
		    		user = Auth.attempt(username, password);
		    		System.out.println("Login Success");
    			} catch (Exception e) {
    				System.out.println("Login Failed: " + e.getMessage());
    			}
    		}
    }
}