import java.util.*;

public class demoDet {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int dim = in.nextInt();

        ArrayList<ArrayList<Integer>> coeff;
        ArrayList<Integer> constants;

        coeff = new ArrayList<ArrayList<Integer>>();
        constants = new ArrayList<Integer>();

        for(int i=0;i<dim;i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j=0;j<dim;j++) {
                temp.add(in.nextInt());
            }
            constants.add(in.nextInt());
            coeff.add(temp);
        }

        /*for(int i=0;i<dim;i++) {
            for(int j=0;j<dim;j++) {
                System.out.print(coeff.get(i).get(j)+" ");
            }
            System.out.println(constants.get(i));
        }*/

        setOfEquation cramerSolver = new setOfEquation(coeff,constants,dim);

        cramerSolver.showSolution();

    }
}
