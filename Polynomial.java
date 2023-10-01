import java.util.Arrays;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		coefficients = new double[]{0};
		exponents = new int[]{0};
	}
	
	public Polynomial(double[] c, int[] e) {
		coefficients = c;
		exponents = e;
	}


	public int findMaxPower(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		int max = -1;
		for (int i = 0; i<len1; i++) {
			if (arr1[i] > max) {
				max = arr1[i];
			}
		}
		
		for (int j = 0; j<len2; j++) {
			if (arr2[j] > max) {
				max = arr2[j];
			}
		}

		return max;
	}
	
	
	public Polynomial add(Polynomial p) {

		int[] e1 = this.exponents;
		int[] e2 = p.exponents;
		int maxPower = findMaxPower(e1, e2);
		int[] eNew = new int[maxPower+1];
		for (int i = 0; i<maxPower+1; i++) {
			eNew[i] = i;
		}
		for (int i = 0; i<maxPower+1; i++) {
		}
		double[] c1 = new double[maxPower+1];
		Arrays.fill(c1, 0);
		double[] c2 = new double[maxPower+1];
		Arrays.fill(c2, 0);
		for (int i = 0; i<maxPower+1; i++) {
			for (int j = 0; j<e1.length; j++) {
				if (e1[j] == i) {
					c1[e1[j]] = this.coefficients[j];
				}
			}
			for (int j = 0; j<e2.length; j++) {
				if (e2[j] == i) {
					c2[e2[j]] = p.coefficients[j];
				}
			}
		}

		double[] cNew = new double[maxPower+1];
		for (int i = 0; i<maxPower+1; i++) {
			cNew[i] = c1[i] + c2[i];
		}

		int len = 0;
		for (int i = 0; i<maxPower+1; i++) {
			if (cNew[i] != 0) {
				len = len + 1;
			}
		}

		double[] cleanC = new double[len];
		int[] cleanE = new int[len];

		for (int i = 0, j = 0; i<maxPower+1; i++) {
			if (cNew[i] != 0) {
				cleanC[j] = cNew[i];
				cleanE[j] = eNew[i];
				j++;
			}
		}
		
		
		return new Polynomial(cleanC, cleanE);
	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++) {
			sum = sum + coefficients[i]*Math.pow(x, exponents[i]);
		}
		return sum;
	}

	public boolean hasRoot(double value) {
		double sum = evaluate(value);
		return (sum == 0);
	}

	public Polynomial multiply(Polynomial p) {
		int maxPower = -1;
		for (int i = 0; i<exponents.length; i++) {
			for (int j = 0; j<p.exponents.length; j++) {
				if (exponents[i] + p.exponents[j] > maxPower) {
					maxPower = exponents[i] + p.exponents[j];
				}
			}
		}

		double[] cNew = new double[maxPower + 1];
		Arrays.fill(cNew, 0);
		for (int i = 0; i<exponents.length; i++) {
			for (int j = 0; j<p.exponents.length; j++) {
				cNew[exponents[i] + p.exponents[j]] += coefficients[i] * p.coefficients[j];
			}
		}

		int len = 0;
		for (int i = 0; i<maxPower+1; i++) {
			if (cNew[i] != 0) {
				len = len + 1;
			}
		}

		double[] cleanC = new double[len];
		int[] cleanE = new int[len];
		for (int i = 0, j = 0; i<maxPower+1; i++) {
			if (cNew[i] != 0) {
				cleanC[j] = cNew[i];
				cleanE[j] = i;
				j++;
			}
		}
		
		
		return new Polynomial(cleanC, cleanE);		

	
	}

	public Polynomial(File file) throws IOException {
	
	BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		reader.close();
		line = line.replace("-", "+-");
		String[] p = line.split("\\+");
		int len = p.length;
		double[] c = new double[len];
		int[] e = new int[len];
		for (int i = 0; i<len; i++) {
			if (p[i].contains("x")) {
				String[] term = p[i].split("x");
				c[i] = Double.parseDouble(term[0]);
				e[i] = Integer.parseInt(term[term.length-1]);	
			} else {
				c[i] = Double.parseDouble(p[i]);
				e[i] = 0;	
			}
		}

		coefficients = c;
		exponents = e;
	
	}

	public void saveToFile(String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		for (int i = 0; i<coefficients.length; i++) {
			if (coefficients[i] > 0 && i > 0) {
				writer.write("+");
			}
			if (exponents[i] == 0) {
				writer.write(Double.toString(coefficients[i]));
			} else {
				writer.write(Double.toString(coefficients[i]));
				writer.write("x");
				writer.write(Integer.toString(exponents[i]));
			}
		}
		writer.close();
	}
}