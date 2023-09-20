import java.lang.Math;

public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = [0];
	}

	public Polynomial(double[] arr) {
		int len = arr.length;
		for (int i = 0; i<len; i = i+1) {
			coefficients[i] = arr[i];
		}
	}

	public Polynomial add(Polynomial p) {
		Polynomial result = new Polynomial();
		for (int i = 0; i<p.length; i = i+1) {
			result.coefficients[i] = this.coefficients[i] + p.coefficients[i];
		}
		return result;
	}

	public int evaluate(double x) {
		int result = 0;
		for (int i = 0; i<coefficients.length; i = i+1) {
			result = result + coefficients[i] * Math.pow(x, i);
		}
		return result;
	}

	public boolean hasRoot(double root) {
		int sum = Polynomial.evaluate(root);
		return sum == 0;
	}
}