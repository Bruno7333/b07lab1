public class Polynomial {

    private double[] coefficients;

    public Polynomial(){
        coefficients = new double[]{0};
    }

    public Polynomial(double[] input){
        this.coefficients - new double[input.length];
        for(int i = 0; i < input.length; i++){
            this.coefficients[i] = input[i];
        }
    }

    public Polynomial add(Polynomial other){
        if(this.coefficients.length >= other.coefficients.length){
            for(int i = 0; i < other.coefficients.length; i++){
                this.coefficients[i] += other.coefficients[i];
            }
        } else{
            double[] new_arr = new double[other.coefficients.length];
            for( int i = 0; i < new_arr.length; i++){
                new_arr[i] = other.coefficients[i];
                if(i < this.coefficients.length){
                    new_arr[i] += this.coefficients[i];
                }
            }
            return new Polynomial(new_arr);
        }
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