package low_level_design;

/*
    Question : Add, Subtract and Multiply two set of polynomials.

    Requirements/Usecases :
    - Two set of polynomials will be in the following input format.
        - 3 (Number of terms)
        - 1 3 5 (Degree of term)
        - 1 2 -4 (Coefficient of term)
    - Option will be given which indicates : 1 -> Add, 2 -> subtract, 3 -> Multiply
    - Print the Final output of both polynomials

    Entities : Polynomial(List of terms), Term (degree and coeficient)
    going with very simple approach
 */

import java.util.*;

class Polynomial {
    private Map<Integer, Integer> terms;

    public Polynomial() {
        this.terms = new LinkedHashMap<>();
    }

    public void addTerm(int degree, int coef) {
        terms.put(degree, terms.getOrDefault(degree, 0) + coef);
    }

    public Map<Integer, Integer> getTerms() {
        return terms;
    }
}

interface Operation {
    Polynomial evaluate(Polynomial p1, Polynomial p2);
}

class Addition implements Operation {

    @Override
    public Polynomial evaluate(Polynomial p1, Polynomial p2) {
        Iterator<Map.Entry<Integer, Integer>> i1 = p1.getTerms().entrySet().iterator();
        Iterator<Map.Entry<Integer, Integer>> i2 = p2.getTerms().entrySet().iterator();
        Polynomial ans = new Polynomial();
        boolean nextFlag1 = true, nextFlag2 = true;
        Map.Entry<Integer, Integer> e1 = null, e2 = null;

        while (i1.hasNext() && i2.hasNext()) {
            e1 = nextFlag1 ? i1.next() : e1;
            e2 = nextFlag2 ? i2.next() : e2;

            if ((int) e1.getKey() == e2.getKey()) {
                ans.addTerm(e1.getKey(), e1.getValue() + e2.getValue());
                nextFlag1 = true;
                nextFlag2 = true;
            } else if (e1.getKey() < e2.getKey()) {
                ans.addTerm(e1.getKey(), e1.getValue());
                nextFlag1 = true;
                nextFlag2 = false;
            } else {
                ans.addTerm(e2.getKey(), e2.getValue());
                nextFlag1 = false;
                nextFlag2 = true;
            }
        }

        if (i1.hasNext()) i1.forEachRemaining(x -> ans.addTerm(x.getKey(), x.getValue()));
        if (i2.hasNext()) i2.forEachRemaining(x -> ans.addTerm(x.getKey(), x.getValue()));
        return ans;
    }
}

class Subtraction implements Operation {


    @Override
    public Polynomial evaluate(Polynomial p1, Polynomial p2) {
        Iterator<Map.Entry<Integer, Integer>> i1 = p1.getTerms().entrySet().iterator();
        Iterator<Map.Entry<Integer, Integer>> i2 = p2.getTerms().entrySet().iterator();
        Polynomial ans = new Polynomial();
        boolean nextFlag1 = true, nextFlag2 = true;
        Map.Entry<Integer, Integer> e1 = null, e2 = null;

        while (i1.hasNext() && i2.hasNext()) {
            e1 = nextFlag1 ? i1.next() : e1;
            e2 = nextFlag2 ? i2.next() : e2;

            if ((int) e1.getKey() == e2.getKey()) {
                ans.addTerm(e1.getKey(), e1.getValue() - e2.getValue());
                nextFlag1 = true;
                nextFlag2 = true;
            } else if (e1.getKey() < e2.getKey()) {
                ans.addTerm(e1.getKey(), e1.getValue());
                nextFlag2 = false;
                nextFlag1 = true;
            } else {
                ans.addTerm(e2.getKey(), -1 * e2.getValue());
                nextFlag1 = false;
                nextFlag2 = true;
            }
        }

        if (i1.hasNext()) i1.forEachRemaining(x -> ans.addTerm(x.getKey(), x.getValue()));
        if (i2.hasNext()) i2.forEachRemaining(x -> ans.addTerm(x.getKey(), -1 * x.getValue()));
        return ans;
    }
}

class Multiplication implements Operation {


    @Override
    public Polynomial evaluate(Polynomial p1, Polynomial p2) {
        Iterator<Map.Entry<Integer, Integer>> i1 = p1.getTerms().entrySet().iterator();
        Iterator<Map.Entry<Integer, Integer>> i2 = null;
        Polynomial ans = new Polynomial();

        while (i1.hasNext()) {
            Map.Entry<Integer, Integer> e1 = i1.next();
            i2 = p2.getTerms().entrySet().iterator();
            while (i2.hasNext()) {
                Map.Entry<Integer, Integer> e2 = i2.next();
                ans.addTerm(e1.getKey() + e2.getKey(), e1.getValue() * e2.getValue());
            }
        }

        return ans;
    }
}

class OperationFactory {
    private Operation addition;
    private Operation subtraction;
    private Operation multiplication;

    OperationFactory() {
        addition = new Addition();
        subtraction = new Subtraction();
        multiplication = new Multiplication();
    }

    public Operation getOperation(int option) throws UnidentifiedOperationException {
        switch (option) {
            case 1:
                return addition;
            case 2:
                return subtraction;
            case 3:
                return multiplication;
            default:
                throw new UnidentifiedOperationException("Not a Identified Operation.");
        }
    }
}

public class PolynomialLLD {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        OperationFactory opf = new OperationFactory();

        int len1 = sc.nextInt();
        Polynomial p1 = new Polynomial();
        for (int i = 0; i < len1; i++) {
            int degree = sc.nextInt();
            int coef = sc.nextInt();
            p1.addTerm(degree, coef);
        }

        int len2 = sc.nextInt();
        Polynomial p2 = new Polynomial();
        for (int i = 0; i < len2; i++) {
            int degree = sc.nextInt();
            int coef = sc.nextInt();
            p2.addTerm(degree, coef);
        }

        int option = sc.nextInt();
        try {
            Polynomial ans = opf.getOperation(option).evaluate(p1, p2);
            PolynomialPrinterHelper.getInstance().printPolynomial(ans);
        } catch (UnidentifiedOperationException e) {
            System.err.println(e.getMessage());
        }


    }
}

class PolynomialPrinterHelper {

    public static final String X = "x";
    public static final String POWER = "^";
    public static final String SPACE = " ";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    private static PolynomialPrinterHelper singletonObj;

    private PolynomialPrinterHelper() {

    }

    public void printPolynomial(Polynomial p) {
        Map<Integer, Integer> terms = p.getTerms();
        Set<Integer> keySet = terms.keySet();
        StringBuilder ans = new StringBuilder();
        for (int degree : keySet) {
            int coef = terms.get(degree);
            if (degree == 0) {
                ans.append(coef).append(SPACE);
            } else {
                if (coef == 0) continue;
                else if (coef > 0)
                    ans.append(PLUS).append(SPACE);
                else {
                    ans.append(MINUS).append(SPACE);
                    coef = Math.abs(coef);
                }
                ans.append(coef).append(X).append(POWER).append(degree).append(SPACE);
            }
        }
        System.out.println(ans.toString().trim());
    }

    public static PolynomialPrinterHelper getInstance() {
        if (singletonObj == null) {
            singletonObj = new PolynomialPrinterHelper();
        }
        return singletonObj;
    }

}

class UnidentifiedOperationException extends RuntimeException {
    public UnidentifiedOperationException(String message) {
        super(message);
    }
}
