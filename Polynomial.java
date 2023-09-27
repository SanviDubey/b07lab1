public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] arr) {
		coefficients = arr;
	}
	
	public Polynomial add(Polynomial p) {

		double[] result;
		int len1 = p.coefficients.length;
		int len2 = coefficients.length;
		
		if (len1 > len2) {
			result = new double[len1];
			for (int i = 0; i<len2; i++) {
				result[i] = coefficients[i] + p.coefficients[i];
			}
			for (int i = len2; i<len1; i++) {
				result[i] = p.coefficients[i];
			}
		}

		else if (len1 < len2) {
			result = new double[len2];
			for (int i = 0; i<len1; i++) {
				result[i] = coefficients[i] + p.coefficients[i];
			}
			for (int i = len1; i<len2; i++) {
				result[i] = coefficients[i];
			}
		}

		else {
			result = new double[len1];
			for (int i = 0; i<len1; i++) {
				result[i] = coefficients[i] + p.coefficients[i];
			}
		}
		
		return new Polynomial(result);
	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++) {
			sum = sum + coefficients[i]*Math.pow(x, i);
		}
		return sum;
	}

	public boolean hasRoot(double value) {
		double sum = evaluate(value);
		return (sum == 0);
	}
}
