/**
 * Paul Petelski
 * COMP 271
 *
 * Plot a sine function using characters and a 2D array
 *
 */

import java.util.Scanner;

public class Plots {

    public static double toX,fromX, xStep, yStep;
    public static int rows, columns;
    public static String plotCharacter = "*",
            horizontalAxis="-",
            verticalAxis="|",
            intersection="+",
            spaceCharacter=" ";
    public static double minY=Double.MAX_VALUE, maxY=Double.MIN_VALUE; // initialize min and max value
    public static String [][] screen;
    private static int convertedY;

    // calculate sin function
    public static double f(double x){
        return Math.sin(x);
    }

    public static void collectData(){
        // setup scanner for user input
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to your 1980s plotter!\n");
        System.out.printf("What range of x do you want?\nFrom x = ");
        fromX = s.nextDouble();
        System.out.printf("to x = ");
        toX = s.nextDouble();
        System.out.printf("\nwhat resolution do you want?\nHow many rows? ");
        rows = s.nextInt();
        System.out.printf("and how many columns? ");
        columns = s.nextInt();
        // derive steps
        xStep = Math.abs(fromX-toX)/(double) columns;
        // find the min and max
        findMinMax();
        // find yStep after est. min and max values for function
        yStep= Math.abs(maxY-minY)/((double) rows);
        //report summary of computations
        System.out.printf("We'll plot the function from %.3f to %.3f on a %d x %d grid\n", fromX, toX, rows, columns);
        System.out.printf("\nThe dx is %.4f. The dy is %.4f with min = %.3f and max = %.3f", xStep, yStep, minY, maxY);

    } // method collectData

    // how to switch to int in loop
    public static void findMinMax(){
        for (double x = fromX; x < toX; x += xStep){
            double y = f(x);
            minY = (y<minY) ? y : minY; // ternary operator --- same as if statement
            maxY = (y>maxY) ? y : maxY;
        }
    } // method findMinMax

    public static void plot(){
        // initialize 2D array
        screen = new String[rows][columns];
        System.out.println();
        // starting x value --- taken from user input
        double x=fromX;


        // for loop each column
        for (int j=0; j<columns;j++){
            // calculate sine function starting with x which is equal to fromX
            double y=f(x);
            // add the x step to x for the next calculation of sine
            x = x + xStep;
            //convert y into int
            convertedY = (int) ((rows-1)*((maxY-y)/(maxY-minY)));
            // for loop for each row of each column
            for (int i=0; i<rows; i++){
                // if y value is equal to the row number print a star *
                // if not then print a space " "
                if (convertedY==i) {
                    screen[i][j] = "*";
                } else {
                    screen[i][j] = " ";
                }
            }

            // loop of if's to make lines
            for (int i=0; i<columns;i++) {
                // if minY is less than 0, maxY is greater than 0
                // and fromX is greater than or equal to 0
                if ((minY < 0 && maxY > 0) && (fromX>=0 && toX >0)) {
                    // make the horizontal dash line in the middle
                    // need to be in the middle so divide number of rows in half
                    screen[rows / 2][i]="-";
                    screen[rows/2][columns-1]=">";  // add the arrow at the end -->
                    // make the vertical line
                    // needs to be on the leftmost of screen so use column 0
                    for (int c=0; c<rows; c++){
                        screen[c][0]="|";
                    }
                    // draw top arrow
                    screen[0][0]="^";
                    // draw bottom arrow
                    screen[rows-1][0]="v";

                }

                // if minY is less than 0 and maxY is greater than 0
                // and fromX is less than 0 and toX is greater than 0
                if ((minY < 0 && maxY > 0)&&(fromX<0 && toX>0)) {
                    // make the horizontal dash line in the middle
                    // need to be in the middle so divide number of rows in half
                    screen[rows / 2][i]="-";
                    screen[rows/2][columns-1]=">";  // add the arrow at the end -->
                    screen[rows/2][0]="<";          // add arrow at end on left <--
                    // make vertical line
                    // needs to be in the middle so divide number of columns in half
                    for (int c=0; c<rows; c++){
                        screen[c][columns/2]="|";
                    }
                    // add top arrow
                    screen[0][columns/2]="^";
                    // add bottom arrow
                    screen[rows-1][columns/2]="v";
                }

                // if minY and maxY are both greater than or equal to 0
                // and fromX and toX are greater than or equal to 0
                if ((minY>=0&&maxY>=0)&&(fromX>=0&&toX>=0)){
                    // make the horizontal dash line in the middle
                    // need to be on the bottom so use last row (rows-1) bc starts with 0
                    screen[rows-1][i]="-";
                    screen[rows-1][columns-1]=">";  // add the arrow at the end -->
                    // make vertical line
                    // needs to be on the left
                    for (int c=0; c<rows; c++){
                        screen[c][0]="|";
                    }
                    // top arrow
                    screen[0][0]="^";
                }

                // if minY and maxY are both less than 0
                // and fromX and toX are greater than or equal to 0
                if ((minY<=0&&maxY<=0)&&(fromX>=0&&toX>=0)){
                    // make the horizontal dash line in the middle
                    // need to be on the top so use row 0 and all columns (i)
                    screen[0][i]="-";
                    screen[0][columns-1]=">";  // add the arrow at the end -->
                    // make vertical line
                    // needs to be on the left
                    for (int c=0; c<rows; c++){
                        screen[c][0]="|";
                    }

                }

                // if minY and maxY are both less than 0
                // and fromX and toX are less than to 0
                if ((minY<=0&&maxY<=0)&&(fromX<=0&&toX<=0)){
                    // make the horizontal dash line in the middle
                    // need to be on the top so use row 0 and all columns (i)
                    screen[0][i]="-";
                    screen[0][0]=">";  // add the arrow at the end -->
                    // make vertical line
                    // needs to be on the right so columns-1
                    for (int c=0; c<rows; c++){
                        screen[c][columns-1]="|";
                    }
                    // left arrow
                    screen[0][0]="<";
                    // bottom arrow
                    screen[rows-1][columns-1]="v";
                }

                // if minY and maxY is greater than 0
                // and fromX and toX are less than 0
                if ((minY>=0 && maxY>= 0) && (fromX<0 && toX<0)) {
                    // make the horizontal dash line in the middle
                    // need to be on the bottom so use last row (rows-1) bc starts with 0
                    screen[rows-1][i]="-";
                    // make the vertical line
                    // needs to be on the right so columns-1
                    for (int c=0; c<rows; c++){
                        screen[c][columns-1]="|";
                    }
                    // left arrow
                    screen[rows-1][0]="<";
                    // top arrow
                    screen[0][columns-1]="^";
                }

                // if minY is less than 0, maxY is greater than 0
                // and fromX and toX are less than 0
                if ((minY< 0 && maxY >0) && (fromX<0 && toX<0)) {
                    // make the horizontal dash line in the middle
                    // need to be in the middle so divide number of rows in half
                    screen[rows / 2][i]="-";
                    screen[rows/2][0]="<";  // add the arrow at the end <--
                    // make the vertical line
                    // needs to be on the rightmost of screen so use column 0
                    for (int c=0; c<rows; c++){
                        screen[c][columns-1]="|";
                    }
                    // bottom and top arrows
                    screen[0][columns-1]="^";
                    screen[rows-1][columns-1]="v";

                }

            } // end loop of if's

        }
    } // method plot

    // print out the array/plot
    public static void drawPlot(){
        // print min Y and max Y
        System.out.println(minY);
        System.out.println(maxY);
        // start in first row and go over all columns to print what is in the array
        for (int i=0; i <rows; i++){
            for (int j=0; j<columns; j++){
                System.out.print(screen[i][j]);
            }
            System.out.println();
        }
    } // method drawPlot

    public static void main(String[] args) {
        collectData();
        plot();
        drawPlot();
    } // method main
} // end of class