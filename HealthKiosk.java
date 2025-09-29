import java.util.Scanner;

public class HealthKiosk {
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Ashesi Health Kiosk");

        System.out.print("Enter service code (P=Pharmacy, L=Lab, T=Triage, C=Counseling): ");
        String serviceInput = sc.next();                
        String serviceUpper = serviceInput.toUpperCase(); 
        char serviceCode = serviceUpper.charAt(0);        

        String serviceName = "";
        if (serviceCode == 'P' || serviceCode == 'L' || serviceCode == 'T' || serviceCode == 'C') {
        
        } else {
            System.out.println("Invalid service code. Exiting.");
            sc.close();
            return;
        }

        switch (serviceCode) {
            case 'P':
                serviceName = "Pharmacy";
                System.out.println("Go to: Pharmacy Desk");
                break;
            case 'L':
                serviceName = "Lab";
                System.out.println("Go to: Lab Desk");
                break;
            case 'T':
                serviceName = "Triage";
                System.out.println("Go to: Triage Desk");
                break;
            case 'C':
                serviceName = "Counseling";
                System.out.println("Go to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code. Exiting.");
                sc.close();
                return;
        }

        System.out.print("Choose a metric (1=BMI, 2=Dosage round-up, 3=Trig helper): ");
        int metricChoice;
        if (sc.hasNextInt()) {
            metricChoice = sc.nextInt();
        } else {
        
            System.out.println("Not a number. Defaulting to 2 (Dosage).");
            sc.next(); 
            metricChoice = 2;
        }

    
        //  Option 1: BMI 
        if (metricChoice == 1) {
            System.out.print("Enter weight in kg: ");
            double weightKg = sc.nextDouble();

            System.out.print("Enter height in meters: ");
            double heightM = sc.nextDouble();

            double bmi = weightKg / (heightM * heightM);

            double bmiTimes10 = bmi * 10;
            long bmiRoundedLong = Math.round(bmiTimes10);
            bmiRounded1dp = bmiRoundedLong / 10.0;

             if (bmi < 18.5) {
                bmiCategory = "Underweight";
            } else if (bmi < 25.0) {
                bmiCategory = "Normal";
            } else if (bmi < 30.0) {
                bmiCategory = "Overweight";
            } else {
                bmiCategory = "Obese";
            }

            System.out.println("BMI = " + bmiRounded1dp + " | Category = " + bmiCategory);

            long bmiNearestWholeLong = Math.round(bmi);
            metricInt = (int) bmiNearestWholeLong;
        }

        if (metricChoice == 2) {
            System.out.print("Enter required dosage in mg (e.g., 760): ");
            double requiredMg = sc.nextDouble();

            double tabletsExact = requiredMg / 250.0;
            double tabletsCeilDouble = Math.ceil(tabletsExact); 
            int tabletsCount = (int) tabletsCeilDouble;

            System.out.println("Tablets to dispense: " + tabletsCount);

            metricInt = tabletsCount; 
        }

        // Option 3: Trig helper (sin & cos)
        if (metricChoice == 3) {
            System.out.print("Enter angle in degrees: ");
            double degrees = sc.nextDouble();

            double radians = Math.toRadians(degrees);
            double sinVal = Math.sin(radians);
            double cosVal = Math.cos(radians);

            double sinTimes1000 = sinVal * 1000;
            long sinRoundedLong = Math.round(sinTimes1000);
            double sinRounded3dp = sinRoundedLong / 1000.0;

            double cosTimes1000 = cosVal * 1000;
            long cosRoundedLong = Math.round(cosTimes1000);
            double cosRounded3dp = cosRoundedLong / 1000.0;

            System.out.println("sin = " + sinRounded3dp + " , cos = " + cosRounded3dp);

            long sinTimes100Rounded = Math.round(sinVal * 100);
            metricInt = (int) sinTimes100Rounded;
        }

        if (metricChoice != 1 && metricChoice != 2 && metricChoice != 3) {
            System.out.println("Unknown metric choice. Setting metric to 0.");
            metricInt = 0;
        }

        Random rand = new Random();

        int letterOffset = rand.nextInt(26);     // 0 to 25
        char firstChar = (char) ('A' + letterOffset);

        int d1 = 3 + rand.nextInt(7);
        int d2 = 3 + rand.nextInt(7); 
        int d3 = 3 + rand.nextInt(7); 
        int d4 = 3 + rand.nextInt(7); 

        String shortId = "" + firstChar + d1 + d2 + d3 + d4;
        System.out.println("Generated ID: " + shortId);

        boolean idOk = true;

        if (shortId.length() != 5) {
            System.out.println("Invalid ID: wrong length.");
            idOk = false;
        }

        char first = shortId.charAt(0);
        if (!Character.isLetter(first)) {
            System.out.println("Invalid ID: first character is not a letter.");
            idOk = false;
        }

        char c1 = shortId.charAt(1);
        char c2 = shortId.charAt(2);
        char c3 = shortId.charAt(3);
        char c4 = shortId.charAt(4);

        if (!Character.isDigit(c1) || !Character.isDigit(c2) || !Character.isDigit(c3) || !Character.isDigit(c4)) {
            System.out.println("Invalid ID: last four must be digits.");
            idOk = false;
        }

        if (idOk) {
            System.out.println("ID OK");
        }

        // ====== Task 4: "Secure" Display Code
        System.out.print("Enter your first name: ");
        String firstName = sc.next();

        char nameFirstUpper = Character.toUpperCase(firstName.charAt(0));

        int alphabetIndex = nameFirstUpper - 'A'; 
        int shiftedIndex = alphabetIndex + 2;
        if (shiftedIndex >= 26) {
            shiftedIndex = shiftedIndex - 26; 
        }
        char shiftedLetter = (char) ('A' + shiftedIndex);

        char idThird = shortId.charAt(3);
        char idFourth = shortId.charAt(4);
        String lastTwo = "" + idThird + idFourth;

        String displayCode = "" + shiftedLetter + lastTwo + "-" + metricInt;
        System.out.println("Display Code: " + displayCode);

        String summary = serviceName.toUpperCase() + " | ID=" + shortId + " | Code=" + displayCode;

        if (serviceName.equals("Triage") && metricChoice == 1) {
            summary = summary + " | BMI=" + bmiRounded1dp;
        }

        System.out.println("Summary: " + summary);

        sc.close();
    }
}

