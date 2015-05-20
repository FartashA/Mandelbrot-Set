package pkg4_2;

public class complexNumber {
    double re, im;
    
    public complexNumber (double r, double i){
        //Constructs a complex number based on the real value and imaginary value entered
        re = r;
        im = i;
    }
    
    public complexNumber add (complexNumber cN2){
        double re1 = this.re;
        double im1 = this.im;
        double re2 = cN2.re;
        double im2 = cN2.im;
        
        //Adds complexNumber 1 with complexNumber 2 and returns a new complexNumber
        return new complexNumber (re1 + re2, im1 + im2);
    }
    
    public complexNumber multiply(complexNumber cN2){
        double re1 = this.re;
        double im1 = this.im;
        double re2 = cN2.re;
        double im2 = cN2.im;
        
        //Multiplies complexNumber 1 with complexNumber 2 and returns a new complexNumber
        return new complexNumber (re1*re2 - im1*im2, re1*im2 + re2*im1);
    }
    
    public complexNumber exponent(double e){
        complexNumber c = new complexNumber(this.re, this.im);
        complexNumber c2 = c;
        
        //While the exponent value is greater than 1, multiplies the new complexNumber by the original complexNumber
        while(e > 1){
            c2 = c2.multiply(c);
            //Decrements the exponent value by 1
            e--;
        }
        
        //Returns the new complexNumber
        return new complexNumber(c2.re, c2.im);
    }
    
    public int isInMandelbrotSet (){
        //Creates a complexNumber c equal to the complexNumber which the method is being applied to
        complexNumber c = new complexNumber(this.re, this.im);
        //Sets complexNumber z = to c;
        complexNumber z = c;
        //Keeps track of the amount of tries taken to see if the complexNumber is in the Mandelbrot Set
        int tries = 0;
        
        //While the number value squared of z + the coefficient value squared of z are less than 4
        //and it has not reached the maximum amount of itterations to test, it runs this loop
        while ((Math.pow(z.re, 2) + Math.pow(z.im, 2)) < 4 && tries <= mandelbrotSet.itterations){
            //Sets z equal to z^2 + c and increments the number of tries by 1
            z = z.exponent(2).add(c);
            tries++;
        }
        return tries;
    }
}
