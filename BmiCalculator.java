import java.util.Scanner;
import java.util.Locale;
import javax.swing.JOptionPane;
public class BmiCalculator{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.US);
		
		char repeat = 0;
		
		//Modification
		JOptionPane.showMessageDialog(null, "Welcome to BMI Calculator!", "BMI Calculator", JOptionPane.INFORMATION_MESSAGE);
		
		
		do{
			int unitChoice = getUnitChoice(scanner);
			
			double weight = (unitChoice == 1) ? getValidInput(scanner, "Enter your weight in kilograms:", 10, 600)
			
					: getValidInput(scanner, "Enter your weight in pounds", 22, 1300);
			
			double height = (unitChoice == 1) ? getValidInput(scanner, "Enter your height in meters:", 0.5, 2.5)
			
					: getValidInput(scanner, "Enter your height in inches", 20, 100);
					
			double bmi = CalculateBMI(unitChoice, weight, height);
			
			System.out.println("Your BMI is:" + bmi);
			System.out.println(determineBMICategory(bmi));
			
			repeat = askToReport(scanner);
			System.out.println();
		} while (repeat == 'Y'|| repeat == 'y');
		JOptionPane.showMessageDialog(null, "Thank you for using BMI Calculator!", "BMI Calculator", JOptionPane.INFORMATION_MESSAGE);
	}
	//Unit - Metric and Imperial
	public static int getUnitChoice(Scanner scanner){
		int choice;
		
		while(true){
			System.out.println("Select a prefered unit:\n"
								+ "1. Metric (kg, m)\n "
								+ "2. Imperial(lbs, in)\n"
								+ "Please select either option 1 or option 2");
			if(scanner.hasNextInt()){
				choice = scanner.nextInt();
				if(choice == 1 || choice == 2){
					break;
				} else {
					System.out.println("Invalid choice. Please enter either 1 or 2");
				} 
			} else {
				System.out.println("Invalid input. Please enter number (1 or 2) ");
				scanner.next();
			}
			
		}
		
		return choice;
	}
	public static double getValidInput(Scanner scanner, String prompt, double min, double max){
		double value;
		
		while(true){
			System.out.println(prompt);
			
			if(scanner.hasNextDouble()){
				value = scanner.nextDouble();
				if(value >= min && value <= max){
					break;
				} else {
					System.out.printf("Please enter a value between %.1f and %.1f", min, max);
				}
			} else {
				System.out.println("Invalid input. Please enter a value");
				scanner.next();
			}
		}
		
		return value;
	}
	public static double CalculateBMI(int unitChoice, double weight, double height){
		double totalBMI;
		
		if(unitChoice == 1){
			totalBMI = weight/(height*height);
		}else{
			totalBMI = (703 * weight)/(height*height);
		}			
		return totalBMI;
	}
	//Modidification of the BMI Categories
	public static String determineBMICategory(double bmi){
		if(bmi < 18.5){
			return "Underweight";
		}else if(bmi < 25){
			return "NormalWeight";
		}else if(bmi < 30){
			return "Overweight";
		}else if(bmi < 35){
			return "Obese";
		}else{
			return "SeverlyObese";
			
		}
		
		
	}
	public static char askToReport(Scanner scanner){
		System.out.println("Would you like to calculate again? (Y/N):");
		return scanner.next().charAt(0);
	}
}