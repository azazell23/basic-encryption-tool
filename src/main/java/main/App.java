package main;

import java.util.Scanner;

import javax.swing.JFrame;

import Cryptographers.RSA;
import Model.User;
import Seeders.Seeder;
import UserInterface.StartingMenuInterface;

public class App {
    private static RSA rsa;
    private static Seeder seeder;
    public static void main(String[] Args) throws Exception
    {
    		seeder = new Seeder();
    		rsa = new RSA();
    		JFrame frame = new StartingMenuInterface(seeder, rsa);
    		frame.setVisible(true);
    }
}