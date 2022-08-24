package main;

import java.util.*;

import javax.print.event.PrintEvent;

import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
   public static void main (String args[]) throws Exception {

      //ArrayList to call elements of text file to memory
      ArrayList<ChemicalElement> chemicals = new ArrayList<ChemicalElement>();
      BufferedReader br = new BufferedReader(new FileReader("Elements.txt"));

      int count = 0;

      String line = br.readLine();
      while(line != null) {
         count++;
         String cn = line.split(" ")[0];
         String sb = line.split(" ")[1];
         float an = count;
         ChemicalElement cr = new ChemicalElement(cn,sb,an);
         chemicals.add(cr);
         line = br.readLine();
      }
      br.close();

      Scanner stringScanner = new Scanner(System.in);
      while(true) {
         System.out.println("Please type in an element's name. Type 'exit' to close the program. \n");

         //user input is recorded in inputElement
         String inputElement = stringScanner.next();

         if(inputElement.equalsIgnoreCase("exit")) {
            break;
         }

         float an = 0;
         try {
            an = Float.parseFloat(inputElement);
         } catch (Exception e) {
            //TODO: handle exception
         }
         Boolean doesElementExist = false;
         for(ChemicalElement cr : chemicals) {
            if(cr.atomicNumber == an || cr.chemicalName.equalsIgnoreCase(inputElement) || cr.symbol.equalsIgnoreCase(inputElement)) {
               System.out.println(cr);
               doesElementExist = true;
               break;
            }
         }
         if(!doesElementExist) {
            System.out.println("Element does not exist. Please type in a real element. \n");
         }
         
      }
      //close scanner to prevent leaks
      stringScanner.close();

      return;
   }

   record ChemicalElement(String chemicalName, String symbol, float atomicNumber) {
      @Override
      public String toString () {
         return chemicalName + " (Symbol: " + symbol + ", Mass number: " + atomicNumber + ") \n";
      }
   }
}