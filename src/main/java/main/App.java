package main;

import java.util.Scanner;

import Application.User;
import Authentication.Auth;
import Cryptographers.RSA;
import Seeders.Seeder;
import UserInterface.StartingMenuInterface;

public class App {
    private static RSA rsa = new RSA();
    private static Scanner scanner = new Scanner(System.in);
    private static User user = null;
    private static StartingMenuInterface menu;
    public static void main(String[] Args) throws Exception
    {
    		menu = new StartingMenuInterface();
    		menu.setVisible(true);
    }
}