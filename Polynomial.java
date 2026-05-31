import java.io.File;
import java.util.Scanner;

public class Polynomial {

    private double[] coefficients;
    private int[] exponents;

    public Polynomial(){
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }

    public Polynomial(double[] input){
        int nonzero_count = 0;
        for(int i = 0; i < input.length; i++){
            if(input[i] != 0){
                nonzero_count++;
            }
        }

        this.coefficients = new double[nonzero_count];
        this.exponents = new int[nonzero_count];

        int added_count = 0;
        for(int i = 0; i < input.length; i++){
            if(input[i] != 0){
                this.coefficients[added_count] = input[i];
                this.exponents[added_count] = i;
                added_count++;
            }
        }
    }

    public Polynomial(double[] coeff, int[] exp){
        coefficients = coeff;
        exponents = exp;
    }

    public Polynomial(File file){
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        String[] terms;
    }

    public Polynomial add(Polynomial other){
        int total_exp_count = 0;
        boolean cont = true;
        int p = 0, q = 0; // p is the location of current coefficient on this, q is location of the current coefficient on other

        while(cont){
            if((p >= this.coefficients.length) && (q >= other.coefficients.length)){
                cont = false;
            } else if(p >= this.coefficients.length/* p is too long, but we havent reached the end of q */){
                q++;
                total_exp_count++;
            } else if( q >= this.coefficients.length/* same for q */){
                p++;
                total_exp_count++;
            } else if (this.coefficients[p] == other.coefficients[q] /*coefficients[p] is equal to coefficients[q]*/){
                p++;
                q++;
                total_exp_count++;
            } else if (this.coefficients[p] < other.coefficients[q] /*coefficients[p] is less than coefficients[q]*/){
                p++;
                total_exp_count++;
            } else if (this.coefficients[p] > other.coefficients[q] /*coefficients[p] is greater than coefficients[q]*/){
                q++;
                total_exp_count++;
            }

        }

        int[] final_coeff = new int[total_exp_count];
        int[] final_exp = new int[total_exp_count];

        p = 0; 
        q = 0;

        
        for(int i = 0; i < total_exp_count; i++){
            //if q is out of bounds
            if(q >= other.exponents.length){
                final_exp[i] = this.exponents[p];
                final_coeff[i] = this.coefficients[p];
                p++;
            } 
            //if p is out of bounds
            else if(p >= this.exponents.length){
                final_exp[i] = other.exponents[q];
                final_coeff[i] = other.coefficients[q];
                q++;
            } else if (this.exponents[p] == other.exponents[q]){
                final_exp[i] = this.exponents[p]
                final_coeff[i] = this.coefficients[p] + other.coefficients[q]
                p++;
                q++;
            } else if (this.exponents[p] < other.exponents[q]){
                final_exp[i] = this.exponents[p];
                final_coeff[i] = this.coefficients[p];
                p++;
            } else{
                final_exp[i] = other.exponents[q];
                final_coeff[i] = other.coefficients[q];
                q++;
            }
        }

        return new Polynomial(final_coeff, final_exp);

    }

    public Polynomial multiply(Polynomial other){
        // get an array in the original format, with one long array, then convert into the new format
        // find highest polynomial value in multiplication

        int max_polynomial = this.exponents[this.exponents.length - 1] * other.exponents[other.exponents.length - 1];

        int[] polynomial_arr = new int[max_polynomial + 1];
        int exponent_value = 0;

        // for int i in range(this.exponents.length)
        for(int i = 0; i < this.exponents.length; i++){
            // for int j in range(other.exponents.length)
            for(int j = 0; j < other.exponents.length; j++){
                exponent_value = this.exponents[i] + other.exponents[j];
                polynomial_arr[exponent_value] += this.coefficients[i] * other.coefficients[j];
            }
        }
        return new Polynomial(polynomial_arr);
    }

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < this.coefficients.length; i++){
            sum += this.coefficients[i] * (Math.pow(x, i));
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}