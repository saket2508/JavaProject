import java.util.*;
import java.time.*;
class PassengerBooking extends PassengerDetails {
    double check_inBaggageAllowance, handBaggageAllowance, CancellationCharges;
    String date, dateOfDeparture, International, Business, Origin, Destination, roundTrip, flightNo, Airline, TOA, TOD,canc,MEAL;

    private void checkDate(String date) throws InvalidResponse {
        if (date.indexOf('-') == -1 && date.indexOf('/') == -1) {
            throw new InvalidResponse("Invalid response to Date Field");
        }
        String dd = date.substring(0, 2);
        String mm = date.substring(3, 5);
        String yy = date.substring(6, date.length());
        int day = Integer.parseInt(dd);
        int month = Integer.parseInt(mm);
        int year = Integer.parseInt(yy);
        if (month > 12) {
            throw new InvalidResponse("Invalid response to Date Field");
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day > 31) {
                throw new InvalidResponse("Invalid response to Date Field");
            }
        }
        if (month == 2 && year % 4 == 0) {
            if (day > 29) {
                throw new InvalidResponse("Invalid response to Date Field");
            }
        }
        if (month == 2 && year % 4 != 0) {
            if (day > 28) {
                throw new InvalidResponse("Invalid response to Date Field");
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                throw new InvalidResponse("Invalid response to Date Field");
            }
        }

    }

    private void getCancellationCharges(String DOD) {
        LocalDate obj = LocalDate.now();
        date = obj.toString();
        if (DOD.substring(6, 10).equals(date.substring(0, 4)) && DOD.substring(3, 5).equals(date.substring(5, 7))) {
            int dd = Integer.parseInt(date.substring(8, 10));
            int dD = Integer.parseInt(DOD.substring(0, 2));
            if (dD - dd >= 7) {
                CancellationCharges = 4500;
            } else if (dD - dd >= 4 && dD - dd < 7) {
                CancellationCharges = 3000;
            } else {
                CancellationCharges = 1500;
            }
        }
        else if(DOD.substring(6,10).equals(date.substring(0,4)) && !DOD.substring(3,5).equals(date.substring(5,7))){
            int mm1,mm2,dd1,dd2;
            mm1 = Integer.parseInt(date.substring(5,7));
            mm2 = Integer.parseInt(DOD.substring(3,5));
            dd1 = Integer.parseInt(date.substring(8,10));
            dd2 = Integer.parseInt(DOD.substring(0,2));
            if(mm2 == 1 || mm2 == 2 || mm2 == 4 || mm2 == 6 || mm2 == 8 || mm2 == 9 || mm2 == 11){
                int diff = 31-dd1+dd2;
                if(diff >= 7){
                    CancellationCharges = 4500;
                }
                else if(diff >= 3 && diff < 7){
                    CancellationCharges = 3000;
                }
                else{
                    CancellationCharges = 1500;
                }
            }
            else if(mm2 == 3){
                int diff = 28-dd1+dd2;
                if(diff >= 7){
                    CancellationCharges = 4500;
                }
                else if(diff >= 3 && diff < 7){
                    CancellationCharges = 3000;
                }
                else{
                    CancellationCharges = 1500;
                }
            }
            else{
                int diff = 30-dd1+dd2;
                if(diff >= 7){
                    CancellationCharges = 4500;
                }
                else if(diff >= 3 && diff < 7){
                    CancellationCharges = 3000;
                }
                else{
                    CancellationCharges = 1500;
                }
            }
        }
        else{
            int dd1 = Integer.parseInt(date.substring(8,10));
            int dd2 = Integer.parseInt(DOD.substring(0,2));
            int diff = 31-dd1+dd2;
            if(diff >= 7){
                CancellationCharges = 4500;
            }
            else if(diff >= 3 && diff < 7){
                CancellationCharges = 3000;
            }
            else{
                CancellationCharges = 1500;
            }
        }
        TotalFare = TotalFare - CancellationCharges;

    }

    private void forBusinessClass(){
        check_inBaggageAllowance = 45;
        handBaggageAllowance = 10;
    }

    private void forDomestic(){
        check_inBaggageAllowance = 18;
        handBaggageAllowance = 7.5;
    }

    private void forInternationalRoutes(){
        check_inBaggageAllowance = check_inBaggageAllowance * 1.25;
        handBaggageAllowance = handBaggageAllowance * 1.25;
    }

    private void checkTime(String time) throws InvalidResponse{
        if(time.length()!=4){
            throw new InvalidResponse("Invalid response to time field");
        }
        if(time.indexOf(":")!=-1){
            throw new InvalidResponse("Please input time in 24 hr format");
        }
        int hh = Integer.parseInt(time.substring(0,2));
        if(hh>24){
            throw new InvalidResponse("Please input time in 24 hr format");
        }
    }

    private void getBookingDetails() throws InvalidResponse {
        System.out.println("Enter the date of departure(DD-MM-YY)");
        dateOfDeparture = sc.next();
        try {
            checkDate(dateOfDeparture);
        } catch (InvalidResponse ex) {
            System.out.println(ex);
        }
        System.out.println("Enter time of departure(24hr format)");
        TOD = sc.next();
        try{
            checkTime(TOD);
        }
        catch (InvalidResponse ex){
            System.out.println(ex);
        }
        System.out.println("Enter time of arrival(24hr format)");
        TOA = sc.next();
        try{
            checkTime(TOA);
        }
        catch (InvalidResponse ex){
            System.out.println(ex);
        }
        System.out.println("Are you flying business class" + "(Y/N)");
        Business = sc.next();
        if (!Business.equals("Y") && !Business.equals("N")) {
            throw new InvalidResponse("Invalid Response");
        }
        if (Business.equals("Y")) {
            forBusinessClass();
        } else {
            forDomestic();
        }
        System.out.println("Are you flying International " + "(Y/N)");
        International = sc.next();
        if (!International.equals("Y") && !International.equals("N")) {
            throw new InvalidResponse("Invalid Response");
        }
        if (International.equals("Y")) {
            forInternationalRoutes();
        }
        System.out.println("Where are you flying from");
        sc.nextLine();
        Origin = sc.nextLine();
        System.out.println("Enter your destination");
        Destination = sc.nextLine();
        System.out.println("Will you be taking a round Trip " + "(Y/N)");
        roundTrip = sc.next();
        if (!roundTrip.equals("Y") && !roundTrip.equals("N")) {
            throw new InvalidResponse("Invalid Response");
        }
        System.out.println("Enter your flightNo(SGXXX,AIXXX,UKXXX,6EXXX)");
        flightNo = sc.next();
        if (!flightNo.startsWith("AI") && !flightNo.startsWith("SG") && !flightNo.startsWith("6E") && !flightNo.startsWith("UK")) {
            throw new InvalidResponse("Invalid FlightNo");
        }
        if (flightNo.startsWith("AI")) {
            Airline = "Air India";
        } else if (flightNo.startsWith("6E")) {
            Airline = "Indigo";
        } else if (flightNo.startsWith("UK")) {
            Airline = "Vistara";
        } else if (flightNo.startsWith("SG")) {
            Airline = "SpiceJet";
        }
        System.out.println("Enter your meal preference(VEG/NON-VEG)");
        MEAL = sc.next();
        if(!MEAL.equals("VEG") && !MEAL.equals("NON-VEG")){
            throw new InvalidResponse("Invalid Response");
        }
        System.out.println("Enter your seat no");
        seatNo = sc.nextInt();
        System.out.println("Do you want to request cancellation?(Y/N)");
        canc = sc.next();
        if (!canc.equals("Y") && !canc.equals("N")) {
            throw new InvalidResponse("Invalid Response");
        }
        System.out.println("Enter your Total Fare");
        TotalFare = sc.nextDouble();
        if (canc.equals("Y")) {
            getCancellationCharges(dateOfDeparture);
        }
        sc.close();
    }
    public String toString(){
        String pr = " ";
        if(canc.equals("N")){
            pr+= "YOUR TRAVEL ITINERARY- " + "\n";
            pr+= "NAME: " + passengerFirstName + " " + passengerLastName + "\n";
            pr+= "GENDER: " + passengerGender + " " + "AGE: " + passengerAge + "\n";
            pr+= "CONTACT NO: " + contactNo + "\n";
            pr+= "ADDRESS: "+passengerAddress+"\n";
            pr+= "EMAIL ADDRESS " + Email + "\n";
            if(studentEmail!=null){
                pr+= "STUDENT EMAIL ADDRESS: " + studentEmail + "\n";
            }
            pr+= "DATE OF DEPARTURE: " + dateOfDeparture + "\n";
            pr+= "TIME OF DEPARTURE: " + TOD + "\n";
            pr+= "TIME OF DEPARTURE: " + TOA + "\n";
            pr+= "ORIGIN: "+ Origin + "\n";
            pr+= "DESTINATION: "+Destination + "\n";
            pr+= "FLIGHT NO: "+ flightNo + "\n";
            pr+= "AIRLINE: "+ Airline + "\n";
            pr+= "SEAT-NO: "+ seatNo + "\n";
            pr+= "TOTAL FARE: "+TotalFare+ "\n";
            pr+= "MEAL PREFERENCE: "+MEAL+"\n";
            pr+= "MAX HAND BAGGAGE ALLOWANCE: "+handBaggageAllowance + " kgs"+ "\n";
            pr+= "MAX CHECK-IN BAGGAGE ALLOWANCE: "+check_inBaggageAllowance + " kgs"+"\n";
        }
        else{
            pr+= "NAME: " + passengerFirstName + " " + passengerLastName + "\n";
            pr+= "GENDER: " + passengerGender + " " + "AGE: " + passengerAge + "\n";
            pr+= "CONTACT NO: " + contactNo + "\n";
            pr+= "ADDRESS: " + passengerAddress + "\n";
            pr+= "EMAIL ADDRESS " + Email + "\n";
            if(!studentEmail.equals(null)){
                pr+= "STUDENT EMAIL ADDRESS: " + studentEmail + "\n";
            }
            pr+= "CANCELLATION CHARGES: "+ CancellationCharges + "\n";
            pr+= "NET AMOUNT PAYABLE: "+ TotalFare;
        }
        return pr;
    }
    void printItinerary(){
        System.out.println(this.toString());
    }


    PassengerBooking() {
        try {
            getBookingDetails();
        } catch (InvalidResponse msg) {
            System.out.println(msg);
        }
    }
}
