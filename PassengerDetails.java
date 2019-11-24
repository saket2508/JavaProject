import java.util.*;
class InvalidResponse extends Exception{
    InvalidResponse(String msg){
        super(msg);
    }
}
class PassengerDetails{
    Scanner sc = new Scanner(System.in);
    String passengerFirstName,passengerLastName, passengerGender,passengerAddress, contactNo, Email, studentEmail;
    int passengerAge,seatNo;
    double TotalFare;
    String student;


    void getPassengerDetails() throws InvalidResponse {
        System.out.println("Enter your First Name");
        passengerFirstName = sc.next();
        System.out.println("Enter your Last Name");
        passengerLastName = sc.next();
        System.out.println("Enter your age and Gender(M/F/Others)");
        passengerAge = sc.nextInt();
        passengerGender = sc.next();
        if (!passengerGender.equals("M") && !passengerGender.equals("F") && !passengerGender.equals("Others")) {
            throw new InvalidResponse("Invalid Response to Gender Field");
        }
        System.out.println("Enter your address");
        sc.nextLine();
        passengerAddress = sc.nextLine();
        System.out.println("Enter your Contact No");
        contactNo = sc.next();
        if (contactNo.length() != 10) {
            throw new InvalidResponse("PLease enter a valid contact no");
        }
        System.out.println("Enter your email address");
        Email = sc.next();
        if (Email.indexOf('@') == -1 || Email.indexOf(".com") == -1) {
            throw new InvalidResponse("Please enter a valid email id");
        }
        System.out.println("Are you a student(Y/N)");
        student = sc.next();
        if (!student.equals("Y") && !student.equals("N")) {
            throw new InvalidResponse("Invalid Response");
        }
        if (student.equals("Y")) {
            System.out.println("Enter your student Email ID");
            studentEmail = sc.next();
            if(studentEmail.equals(Email)){
                throw new InvalidResponse("Please enter a different email id");
            }
            if (studentEmail.indexOf('@') == -1 || studentEmail.indexOf(".com") == -1) {
                throw new InvalidResponse("Please enter a valid email id");
            }
        }
    }

    PassengerDetails() {
        try{
            getPassengerDetails();
        }
        catch(InvalidResponse msg){
            System.out.println(msg);
        }
    }
}