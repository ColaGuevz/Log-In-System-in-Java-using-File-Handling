package account;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Account 
{
    private String email;
    private String userName;
    private String password;
    private int confirmationNumber;
    private String otp;
    
    //creating account
    public Account()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your email: ");
        this.email = sc.next();

        System.out.println("Enter your user name: ");
        this.userName = sc.next();

        System.out.println("Enter your password: ");
        this.password = sc.next();

       // createFile(this.userName, this.email, this.password);
        sendConfirmation();
        confirmAccount();
    }

    public Account (Account a)
    {
        this.email = a.email;
        this.userName = a.userName;
        this.password = a.password;
    }

    public void changeUserName(Account a)
    {
        Scanner x = new Scanner(System.in);

        System.out.println("\nEnter your new user name: ");
        a.userName = x.next();

        System.out.println("\nSuccessfully changed user name!!!");
    }

    public void changePassword(Account a)
    {
        Scanner x = new Scanner(System.in);

        System.out.println("\nEnter your new Password: ");
        a.password = x.next();

        System.out.println("\nSuccessfully changed password!!!");

    }

    public String getUserName()
    {
        return this.userName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    private void confirmAccount()
    {
        Scanner c = new Scanner(System.in);
        System.out.println("\nEnter confirmation number: ");
        this.otp = c.next();

        readOTP(this.userName+"OTP.txt");

        if(this.otp.equals(readOTP(this.userName+"OTP.txt")))
        {
            System.out.println("\nACCOUNT SUCCESSFULLY CREATED");
            createFile(this.userName, this.email, this.password);
        }
        else
        {
            System.out.println("""
                               
                               Confirmation number not matched!!
                               ACCOUNT NOT CREATED!!""");

            this.email = null;
            this.userName = null;
            this.password = null;
        }
    }

    private String readOTP(String fileName)
    {	
        char[] array = new char[100];
        //file reader
        try
        {
            FileReader input = new FileReader(fileName + "OTP.txt");

            input.read(array);
            System.out.println("Data in the file: ");
            System.out.println(array);

            input.close();
        }

        catch(IOException e) 
        {
            e.getStackTrace();
        }
        this.otp = String.valueOf(array);
        return this.otp;
    }

    private void sendConfirmation()
    {
        Random a = new Random();
        this.confirmationNumber = a.nextInt(999999) + 100000;

        File file = new File(this.userName + "OTP.txt");

        //file creator
        try 
        {
        // trying to create a file based on the object
                boolean value = file.createNewFile();
            if (value) 
            {
                   System.out.println("Confirmation sent!!!");
            }
        }
        catch(IOException e)
        {
            e.getStackTrace();
        }

    //file writer
    String str = Integer.toString(this.confirmationNumber);
        try
        {
            FileWriter output = new FileWriter(this.userName + "OTP.txt");

            output.write(str);

            output.close();
        }
        catch(IOException e)
        {
            e.getStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "\n";
    }

    private String userEmail;
    private String userPassword;
    public void logIn(Account a)
    {
        Scanner b = new Scanner(System.in);
        System.out.println("\nEnter your email address: ");
        a.userEmail = b.next();

        Scanner c = new Scanner(System.in);
        System.out.println("\nEnter your password: ");
        a.userPassword = c.next();


        if(a.userEmail.equals(this.email) && a.userPassword.equals(this.password))
        {
                System.out.println("\nSuccesfully logged in!");
        }
        else
        {
                System.out.println("\nLog in not recognized!!");
        }
    }

    private void createFile(String name, String mail, String passcode)
    {
        File file = new File(name + ".txt");
        //file creator
        try
        {
        // trying to create a file based on the object
                boolean value = file.createNewFile();
            if (value) 
            {
                   System.out.println("The new file is created.");
            }
            else 
            {
              System.out.println("The file already exists.");
            }
        }
        catch(IOException e)
        {
            e.getStackTrace();
        }

    //file writer
        try
        {
            FileWriter output = new FileWriter(name + ".txt");
            output.write(mail + "\n" + name + "\n" + passcode);

            output.close();
        }
        catch(IOException e)
        {
            e.getStackTrace();
        }  
    }
}
