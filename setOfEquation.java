import java.lang.reflect.Array;
import java.util.*;

public class setOfEquation {
    private int dimension;
    private ArrayList<ArrayList<Integer>> coeffMatrice;
    private ArrayList<Integer> constants;
    private ArrayDeque<ArrayList<ArrayList<Integer>>> cramerMatrices;
    private ArrayDeque<Double> solution;


    setOfEquation(ArrayList<ArrayList<Integer>> cf,ArrayList<Integer> t,int dim) {
        dimension = dim;
        coeffMatrice = cf;
        constants = t;

        solution = new ArrayDeque<>();
        cramerMatrices = new ArrayDeque<ArrayList<ArrayList<Integer>>>();

        for(int i=0;i<dimension;i++) {
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
            for(int j=0;j<dimension;j++) {
                ArrayList<Integer> gar = new ArrayList<Integer>();
                for(int k=0;k<dimension;k++) {
                    if(i == k) {
                        gar.add(constants.get(j));
                    }
                    else{
                        gar.add(coeffMatrice.get(j).get(k));
                    }
                }
                temp.add(gar);
            }
            cramerMatrices.addLast(temp);
        }
    }


    private void solve() {
        double denominator =(double) determinentValue(coeffMatrice,dimension);
        System.out.println(denominator);
        if(denominator == 0) {
            solution = null;
        }
        else {
            ArrayList<ArrayList<Integer>> numeratorMatrice;
            while(!cramerMatrices.isEmpty()) {
                numeratorMatrice = cramerMatrices.pollFirst();
                solution.add((double)determinentValue(numeratorMatrice,dimension) / denominator);
            }
        }
    }

    private int determinentValue(ArrayList<ArrayList<Integer>> targetMat,int d) {
        int sum = 0;
        int sign = 1;

        if(d == 1) {
            return targetMat.get(0).get(0);
        }

        for(int i=0;i<d;i++) {
            ArrayList<ArrayList<Integer>> cofactor = new ArrayList<ArrayList<Integer>>();
            for(int j = 1;j<d;j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for(int k=0;k<d;k++) {
                    if(i != k){
                        temp.add(targetMat.get(j).get(k));
                    }
                }
                cofactor.add(temp);
            }
            sum += targetMat.get(0).get(i)*sign*determinentValue(cofactor,d-1);
            sign *= -1;
        }

        return sum;
    }

    public void showSolution() {
        solve();
        if(solution == null) {
            System.out.println("No Solution Exists.");
        }
        else {
            for (Double d : solution) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }

}
