package main;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
   public static void main (String args[]) throws Exception {

      ArrayList<ChemicalElement> chemicals = new ArrayList<ChemicalElement>();

      // Load file using BufferedReader
      BufferedReader br = new BufferedReader(new FileReader("Elements.txt"));

      // Required variables for iterations
      String line = br.readLine();
      int atomicNumber = 0;

      // Iteration loop until EoF
      while(line != null) {

         // Atomic Number iteration
         atomicNumber++;

         // Get data from loaded line
         String name = line.split(" ")[0];
         String symbol = line.split(" ")[1];

         // Convert to record instance
         ChemicalElement cr = new ChemicalElement(name, symbol, atomicNumber);

         // Add to memory
         chemicals.add(cr);

         // Next line
         line = br.readLine();
      }
      br.close();

      // Create scanner instance
      Scanner stringScanner = new Scanner(System.in);

      // Exit manual print
      System.out.println("Type 'exit' to close the program.");

      // Interface loop
      while(true) {

         // Interface printing
         System.out.print("Element's name / atomic # / symbol: ");

         // User input
         String inputElement = stringScanner.nextLine();

         // Check if exit
         if(inputElement.equalsIgnoreCase("exit")) break;

         // Check if atomic number
         int atomicNumberToSearch = -1;
         try {
            atomicNumberToSearch = Integer.parseInt(inputElement);
         } catch (Exception ignored) {
            // Not searched using atomic number
         } 

         // Flag
         boolean doesElementExist = false;

         // Enhanced for loop for linear searching
         for(ChemicalElement cr : chemicals) {

            // Tests
            if(cr.atomicNumber == atomicNumberToSearch 
            || cr.chemicalName.equalsIgnoreCase(inputElement) 
            || cr.symbol.equalsIgnoreCase(inputElement)) {

               // If found: print, mark flag, exit loop
               System.out.println(cr + "\n");
               doesElementExist = true;
               break;
            }
         }

         // If flag is false, print error
         if(!doesElementExist) System.out.println("Element does not exist. Please type in a real element.\n");
         
      }
      stringScanner.close();
   }

   record ChemicalElement(String chemicalName, String symbol, int atomicNumber) {
      @Override
      public String toString () {
         return chemicalName + " (Symbol: " + symbol + ", Mass number: " + atomicNumber + ") \n";
      }
   }
}