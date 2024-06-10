package com.mycompany.finalbankingapp;

import java.util.Scanner;
import java.util.ArrayList;

public class FinalBankingApp {
    static String name[] = {"Katleho", "Lwando", "Mso"};
    static String surname[] = {"Gulubela", "Gulubela", "Gulubela"};
    static int pin[] = {1234, 1234, 1234};
    static int id[] = {1234, 1357, 2468};
    static double balance[] = {5000, 4000, 3000};
    
    static ArrayList<String> historySavings = new ArrayList<>();
    static ArrayList<Double> historyAmountSavings = new ArrayList<>();
    
    static ArrayList<String> historyCredit = new ArrayList<>();
    static ArrayList<Double> historyAmountCredit = new ArrayList<>();
    
    static String name1, surname1; 
    static int id1, pin1, idR, pinR, confPinR;
    static double balanceSavings = 0, amount, balanceCredit;
    static int x = 1, n, tries = 0, dec, account, option;
    
    static void intro(){
        System.out.println("Welcome to Katleho's bank. ");
        System.out.println("How may we help you?");
    }
    
    static void getDec(){
        Scanner k = new Scanner(System.in);
        
        System.out.println("Please enter (1) to login, (2) to register, or (3) to opt out of the app");
        dec = k.nextInt();
        
        tries = 0;
        
        check();
    }
    
    static void check(){
        while(x == 1){
            if(dec == 1){
                login();
            }
            else if(dec == 2){
                register();
            }
            else if(dec == 3){
                System.out.println("Thank you for using our app");
                System.exit(0);
            }
            else{
                System.out.println("Please enter a valid input");
                getDec();
            }
        }
    }
    
    static void login(){
        Scanner k = new Scanner(System.in);
        
        while(tries < 4){
                System.out.println("Please enter your id number");
                id1 = k.nextInt();
                System.out.println("Please enter your 4 digit pin");
                pin1 = k.nextInt();
                
                tries++;
                for(int i = 0; i < 5; i++){
                    if(id1 == id[i] && pin1 == pin[i]){
                        System.out.println("Would you like to use your (1) savings or (2) credit account?");
                        account = k.nextInt();
                        if(account == 1){
                            balanceSavings = balance[i];
                            choice();
                        }
                        if(account == 2){
                            choice();
                        }
                        else{
                            System.out.println("Invaild option (Default account is savings)");
                            account = 1;
                            deposit();
                        }
                        
                        x = 3;
                    }
                    else if(id1 == idR && pin1 == pinR){
                        System.out.println("How can we help you?");
                        System.out.println("Would you like to use your (1) savings or (2) credit account?");
                        account = k.nextInt();
                        if(account == 1){
                            deposit();
                        }
                        if(account == 2){
                            choice();
                        }
                        else{
                            System.out.println("Invaild option (Default account is savings)");
                            account = 1;
                            deposit();
                        }
                        tries = 4;
                        x = 3;
                    }
                    else if(tries == 4){
                       System.out.println("You have tried too many failed attempts"); 
                       getDec();
                       x = 3;
                    }
                    else {
                        System.out.println("Password or ID number was incorrect please try again");
                        
                        login();
                    }
                }
            }
        
    }
    
    static void register(){
        Scanner k = new Scanner(System.in);
        
        
            System.out.println("Please enter your name");
            name1 = k.next();
            System.out.println("Please enter your surname");
            surname1 = k.next();
            System.out.println("Please enter you ID number");
            idR = k.nextInt();
            System.out.println("Please enter a 4 digit pin");
            pinR = k.nextInt();
            System.out.println("Please confirm the 4 digit pin");
            confPinR = k.nextInt();
            
            if(pinR == confPinR){
                System.out.println("Your account has been succesfully created");
                
                System.out.println("Details: " + name1 + " " + surname1);
                System.out.println("ID number: " + idR);
                System.out.println("Current Balance: " + balanceSavings);
                
                login();
            }
            else {
                System.out.println("Pins do not match please try again");
                register();
            }
        
    }
    
    static void choice(){
        Scanner k = new Scanner(System.in);
        
        System.out.println("Enter (1) to deposit/pay your accounts, enter (2) to withdraw, enter (3) to transfer, enter (4) to check balance, enter (5) to check history, enter (6) to sign out");
        dec = k.nextInt();
        
        if(dec == 1){
            deposit();
        }
        if(dec == 2){
            withdraw();
        }
        if(dec ==3){
            transfer();
        }
        if(dec == 4){
            checkBalance();
        }
        if(dec == 5){
            checkHistory();
        }
        if(dec == 6){
           System.out.println("Thank you for using our app");
            System.exit(0);
        }
        else {
            System.out.println("Please choose a valid number");
            choice();
        }
    }
    
    static void deposit(){
        Scanner k = new Scanner(System.in);
        
        if(account == 1){
            System.out.println("How much would you like to deposit?");
            amount = k.nextDouble();

            System.out.println("You deposited R" + amount);
            balanceSavings += amount;

            historySavings.add("Deposited: R");
            historyAmountCredit.add(amount);

            choice();
        }
        else{
            System.out.println("How much would you like to deposit?");
            amount = k.nextDouble();

            System.out.println("You paid R" + amount);
            balanceCredit += amount;

            historyCredit.add("Paid: R");
            historyAmountCredit.add(amount);

            choice();  
        }
    }
    
    static void withdraw(){
        Scanner k = new Scanner(System.in);
        
        System.out.println("How much would you like to withdraw?");
        amount = k.nextDouble();
        
        if(account == 1){
            if(amount < balanceSavings){
                System.out.println("You withdrew R" + amount);

                historySavings.add("Withdrew: R");
                historyAmountSavings.add(amount);

                balanceSavings -= amount;
                choice();  
            }
            else{
                System.out.println("insufficient funds choose another amount");
                withdraw();
            }
        }
        else{
            System.out.println("You withdrew R" + amount);

                historyCredit.add("Withdrew: R");
                historyAmountCredit.add(amount);

                balanceCredit -= amount;
                choice(); 
        }
        
    }
    
    static void transfer(){
        Scanner k = new Scanner(System.in);
        
        System.out.println("Which account would you like to send money to?(0, 1, 2) or (3)pay of your credit?");
        System.out.println("Amount due: R" + balanceCredit);
        n = k.nextInt();
        
        if(account == 1){
            if(n <= 2){
                System.out.println("How much would you like to transfer?");
                amount = k.nextDouble();

                if(amount < balanceSavings){
                   System.out.println("You transfered R" + amount);

                    historySavings.add("Transaction made to " + name[n] + " amount: R");
                    historyAmountSavings.add(amount);

                   balanceSavings -= amount;
                    balance[n] += amount;
                   choice();  
               }
               else{
                   System.out.println("insufficient funds");
                   transfer();
               }
            }
            if(n == 3){
                System.out.println("How much would you like to pay?");
                amount = k.nextDouble();

                    System.out.println("You paid R" + amount);

                     historyCredit.add("Paid credit fees amount: R");
                     historyAmountCredit.add(amount);

                    balanceSavings -= amount;
                     balanceCredit += amount;
                    choice();  
            }
            else{
                System.out.println("Account not found");
                transfer();
            }
        }
        else{
            if(n == 2){
            System.out.println("How much would you like to pay?");
                amount = k.nextDouble();

                    System.out.println("You paid R" + amount);

                     historyCredit.add("Paid credit fees amount: R");
                     historyAmountCredit.add(amount);

                    balanceSavings -= amount;
                     balanceCredit += amount;
                    choice();
            }
            else
            {
                System.out.println("Sorry you are unable to transfer money with a Credit account");
                System.out.println("would you like to use yur savings account instead? (1) yes / (2) no");
                option = k.nextInt();
                
                if(option == 1){
                    login();
                }
                else if(option == 2){
                    choice();
                }
                else{
                    System.out.println("invalid option");
                    transfer();
                }
                
            }
        }
    }
    
    static void checkBalance(){
        if(account == 1){
        System.out.println("Current balance: R" + balanceSavings);
        choice();
        }
        else
        {
           System.out.println("you currently owe: R" + balanceCredit);
        choice(); 
        }
    }
    
    static void checkHistory(){
        if(account == 1){
        for(int i=0; i < historySavings.size(); i++){
            System.out.println(historySavings.get(i) + historyAmountSavings.get(i));
        }
        System.out.println("Current balance: R" + balanceSavings);
        choice();
        }
        else{
          for(int i=0; i < historyCredit.size(); i++){
            System.out.println(historyCredit.get(i) + historyAmountCredit.get(i));
        }
        System.out.println("Current balance: R" + balanceCredit);
        choice();  
        }
    }
    
    
    public static void main(String[] args) {
        intro();
        getDec();
        check();

    }
}

