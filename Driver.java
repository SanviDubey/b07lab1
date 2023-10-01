import java.util.Arrays;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

	// Testing Polynomial constructors

        Polynomial p0 = new Polynomial();
        double[] coeff0 = {3,5};
        int[] expo0 = {3,7};
	p0.coefficients = coeff0;
	p0.exponents = expo0;
	System.out.println("Polynomial p0 contains coefficients: " + Arrays.toString(p0.coefficients) + " and exponents: " + Arrays.toString(p0.exponents));

        double[] coeff1 = {2, 8};
        int[] expo1 = {2, 3};
        Polynomial p1 = new Polynomial(coeff1, expo1);
	System.out.println("Polynomial p1 contains coefficients: " + Arrays.toString(p1.coefficients) + " and exponents: " + Arrays.toString(p1.exponents));

	try{
		File file = new File ("C:\\Users\\sidhartha.dubey\\Desktop\\Polynomials.txt");
		Polynomial p2 = new Polynomial(file);
		System.out.println("Polynomial p2 contains coefficients: " + Arrays.toString(p2.coefficients) + " and exponents: " + Arrays.toString(p2.exponents));
			
	} catch (IOException e) {
		e.printStackTrace();
	}

	// Testing method add
        Polynomial sum = p1.add(p0);
	System.out.println("Sum of p1 and p0 contains coefficients: " + Arrays.toString(sum.coefficients) + " and exponents: " + Arrays.toString(sum.exponents));

	//Testing method multiply
	Polynomial product = p1.multiply(p0);
	System.out.println("Product of p1 and p0 contains coefficients: " + Arrays.toString(product.coefficients) + " and exponents: " + Arrays.toString(product.exponents));

	//Testing method saveToFile
	double[] coeff3 = {5, 6, 9, 7};
	int[] expo3 = {1, 2, 3, 0};
	Polynomial p3 = new Polynomial(coeff3, expo3);
	System.out.println("Polynomial p3 contains coefficients: " + Arrays.toString(p3.coefficients) + " and exponents: " + Arrays.toString(p3.exponents));
	
	try{
		p3.saveToFile("C:\\Users\\sidhartha.dubey\\Desktop\\SavedPolynomial.txt");
		System.out.println("Polynomial saved to file");
	} catch (IOException e){
		e.printStackTrace();
	}

	//Testing method evaluate
	double result = p3.evaluate(2);
	System.out.println("Result of evaluating p3 with x = 2 is: " + result);

	//Testing method hasRoot
	if (p1.hasRoot(0) == true) {
		System.out.println("p1 has root = 0!");
	} else {
		System.out.println("p1 does not have root = 0 - check again!");
	}
        
    }
}
