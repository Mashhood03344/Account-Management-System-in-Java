package com.company;
import java.util.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Parent Class Accounts (Bank Accounts)

//Customer class
class Customer {

    public String name;
    public String address;
    public int phone_number;

    public Customer(){
        name="";
        address="";
        phone_number=-1;
    }

    public Customer(String n, String a, int p){
        name=n;
        address=a;
        phone_number=p;
    }

    public void setName(String n){
        name = n;
    }

    public void setAddress(String a){
        address = a;
    }

    public void setPhone_number(int p){
        phone_number=p;
    }

    public String getName(){
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone_number(){
        return phone_number;
    }
}

class Accounts{
    public int account_number;
    public float balance;
    public String date_created;
    static int count=0;
    Customer cust= new Customer();

    Transaction tranct;

    public Accounts(){
        count++;
        account_number=count;
        balance = 0;
        date_created="";

    }

    public Accounts(String n,String a, String d, float b, int p){
        count++;
        account_number=count;
        balance=b;
        date_created=d;

        cust.setName(n);
        cust.setAddress(a);
        cust.setPhone_number(p);
    }

    public void display(){
        System.out.println("Account Number: "+account_number);
        System.out.println("Balance: "+balance);
        System.out.println("Date Created: "+date_created);
        System.out.println();
    }

    public void setbalance(float b){
        balance=b;
    }

    public float getbalance(){
        return balance;
    }

    public void setDateCreated(String d){
        date_created=d;
    }

    public String getDateCreated(){
        return date_created;
    }

    public void checkBalance(){
        System.out.println("Customer Name: "+cust.name);
        System.out.println("Account Balance: "+balance);
    }

    public void printStatement(){

        System.out.println("Customer Name: "+cust.name);
        System.out.println("Customer Address: "+cust.address);
        System.out.println("Customer Phone Number: "+cust.phone_number);
        System.out.println("Customer Account Number: "+account_number);
        System.out.println("Remaining Balance: "+balance);
    }

    public void transferAmount(float amount, Checking_Account check, int index){

        if (index==-1){

            if (balance>=amount){
                balance-=amount;

                System.out.println("The Bank Account Number is not in our Bank but the amount has been transfered Successfully");
            }

            else{
                System.out.println("You do not have enough balance in your account to make this transfer");
            }
        }

        else{
            if(balance>=amount){
                balance-=amount;

                check.balance+=amount;
            }

            else{
                System.out.println("You do not have enough balance in your account to make this transfer");
            }
        }

    }

    public void calculateZakat(){
        double zakat=0;

        zakat = (balance*2.5)/100;

        System.out.println("ZAKAT IS: "+zakat);
    }
}

//Checking Account class
class Checking_Account extends Accounts{

    int transaction_count;

    public Checking_Account(){
        transaction_count=0;
    }

    public Checking_Account(String n, String a, String d, float b, int p){

        super(n,a,d,b,p);

        transaction_count=0;
    }

    public void makeDeposit(float amount){

        int tranction_fees=0;

        if(transaction_count>2){
            Accounts.this.balance-=10;

            tranction_fees=(transaction_count-2)*10;
        }

        balance+=amount;


        System.out.println("All the transaction fees Paid: "+transaction_fees);
    }

    public void makeWithdrawal(float amount){

        int tranction_fees=0;

        if (balance>=amount+10){
            Accounts.this.balance-=amount;

            if(transaction_count>2){
                Accounts.this.balance-=10;
            }

            tranction_fees=(transaction_count-2)*10;

            System.out.println("All the transaction fees Paid: "+transaction_fees);
        }

        else{
            System.out.println("You do not have enough balance in your account to make this transaction");
        }
    }
}


//Saving Account class
class Saving_Account extends Accounts{

    double interest_rate;
    public Saving_Account(){
        interest_rate=0.01;
    }

    public Saving_Account(String n, String a, String d, float b, int p){
        super(n,a,d,b,p);
    }

    public void setInterest_rate(double i){
        interest_rate=i;
    }
    public void calculatelnterest(){

        double interest_calculated=0;

        interest_calculated=Accounts.this.balance*interest_rate;

        System.out.println("Interest Calculated: "+interest_calculated);
    }

    public void makeDeposit(float amount){
        balance+=amount;
    }

    public void makeWithdrawal(float amount){

        if (balance>=amount){
            Accounts.this.balance-=amount;
        }

        else{
            System.out.println("You do not have enough balance in your account to make this transaction");
        }
    }
}

class Transaction{

    String transaction_type;
    double transaction_amount;

    public Transaction(){
        transaction_type="";
        transaction_amount=0;
    }

    public Transaction(String tt, double ta){
        transaction_amount=ta;
        transaction_type=tt;
    }

    public void setTransaction_type(String tt){
        transaction_type=tt;
    }

    public void setTransaction_amount(double ta){
        transaction_amount=ta;
    }

    public String getTransaction_type(){
        return transaction_type;
    }

    public double getTransaction_amount(){
        return transaction_amount;
    }

}
//Main starts from here
public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scan = new Scanner(System.in);
    //ArrayList <Checking_Account> checking_account_array = new ArrayList<Checking_Account>();
    Saving_Account saving_account_array[] = new Saving_Account[100];

    //variables for keeping the track of the number of Checking and Saving accounts opened
        int checking_account_count=0;
        int saving_account_count=0;

    char choice;

    //Displaying the menu
        System.out.println("MENU");
        System.out.println("Press 1 to open an account");
        System.out.println("Press e to end the program");

        //Inputting the choice from the user
        System.out.println("Enter your choice: ");
        choice = scan.next().charAt(0);
        //Building the functionalities of the System
        while(choice!='e'){

            if(choice == '1'){
                int account_choice;
                System.out.println("Enter the Type of Account you want to open ( Press 1 for Checking or 2 for Saving): ");
                account_choice=scan.nextInt();
                if (account_choice==1){
                    //Taking accuont information such as name, address, phone number, balance
                    String name;
                    String address;
                    int phoneNumber;
                    float balance=0f;
                    String dateOfCreation;

                    System.out.println("Please Enter your name: ");
                    name=scan.next();

                    System.out.println("Please Enter your address: ");
                    address = scan.next();

                    System.out.println("Please Enter your Phone Number: ");
                    phoneNumber= scan.nextInt();

                    System.out.println("Please Enter the Initail amount balance you want to deposit in the account: ");
                    balance=scan.nextFloat();

                    System.out.println("Please Enter the date of the Account creation: ");
                    dateOfCreation=scan.next();


                    /*checking_account_array.set(checking_account_count,name,address,dateOfCreation,balance,phoneNumber);

                    for (int i = 0; i < checking_account_array.size(); i++) {
                        System.out.println(checking_account_array.get(i));
                    }*/


                    System.out.println("Account Successfully Created");
                }

                //Inputting the choice from the user
                System.out.println("Enter your choice: ");
                choice = scan.next().charAt(0);
            }
        }

        //Main ends here///
    }
}
