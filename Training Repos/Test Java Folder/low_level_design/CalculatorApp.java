package low_level_design;

/**
 * Question : System Overview.
 *
 * The system comprises a set of calculators, each designed to compute points
 * based on specified parameters and mathematical formulas.
 *
 * Component.
 *
 * 1. Calculator. • - Each calculator consists of multiple parameters. • - It
 * employs a mathematical formula to calculate points based on these parameters.
 * • -The calculator should provide. ○ - A detailed list of parameters used in
 * the calculation. ○ - The values of these parameters. ○ - The calculated
 * points.
 *
 * #### 2. Parameter. • - Each parameter has specific definitions, including. ○
 * - Parameter name. ○ - Description. • - Parameters can derive their values
 * from various sources, including. ○ - API calls. ○ - Database queries. ○ -
 * Constant values. • - Parameters can be composed of multiple other parameters
 * similar to calculator. • - Each parameter has associated validation logic
 * that can be reused across multiple parameters.
 *
 * ### Example. #### Calculator. - **Parameters**: `param1`, `param2`, `param3.
 * - **Formula**: `param1 * param2 + param3.
 *
 * #### Parameter. • - **Param1**:. ○ - Source: API. ○ - Value: 3 • -
 * **Param2**:. ○ - Source: Database. ○ - Value: 4 • - **Param3**:. ○ - Source:
 * Constant. ○ - Value: 1
 *
 * #### Result. • - **Points Calculated**: 13. • - **Parameters Used**. ○ -
 * `Param1 = 3. ○ - `Param2 = 4. ○ - `Param3 = 1. ○ - **Formula**: `param1 *
 * param2 + param3.
 */

import static low_level_design.Constants.PI;
import static low_level_design.Constants.RADIUS;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Summary : - User can define a custom formula.(Not be available in this code)
 * - Formulas are stored and available for selection. - User selects a formula
 * and provides necessary parameter values. - System evaluates the formula
 * dynamically and returns results.
 *
 *
 * Pre-Identified Entities : Calculator, Parameter, Calculator App (Client),
 * FetchType -> (APIFetchType, DBFetchType, ConstantFetchType),
 */
enum FetchType {
	CONSTANTS, API, DATABASE
}

class Constants {
	public static final String PI = "PI";
	public static final String RADIUS = "Radius";
}

class Parameter {
	private String name;
	private String description;
	private FetchType fetchType;

	public Parameter(String name, String description, FetchType fetchType) {
		this.name = name;
		this.description = description;
		this.fetchType = fetchType;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public FetchType getFetchType() {
		return fetchType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Parameter{");
		sb.append("name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", fetchType=").append(fetchType);
		sb.append('}');
		return sb.toString();
	}
}

abstract class Calculator {
	protected Map<String, Parameter> paramsMap;

	abstract double calculate(Map<String, Double> paramsValueMap);

	abstract String getAbout();

	abstract void defineParams();

}

class PerimeterCircle extends Calculator {

	private static final String name = "Perimeter of Circle";
	private static final String description = "This is calculates the perimeter of Circle";
	private static Calculator instance;

	public PerimeterCircle() {
		defineParams();
	}

	@Override
	double calculate(Map<String, Double> paramsValueMap) {
		if (paramsValueMap.containsKey(PI) && paramsValueMap.containsKey(RADIUS))
			return 2 * paramsValueMap.get(PI) * paramsValueMap.get(RADIUS);
		else
			throw new CalculatorAppException("Expected Parameter Value doesn't exist.");
	}

	@Override
	String getAbout() {
		StringBuffer str = new StringBuffer();
		str.append("Name : " + name + "\n");
		str.append("Description : " + description + "\n");
		str.append("Required Parameter : \n");
		for (Map.Entry param : paramsMap.entrySet()) {
			str.append(param.getValue().toString());
			str.append("\n");
		}
		return str.toString();
	}

	@Override
	void defineParams() {
		paramsMap = new HashMap<>();
		// Param 1 : PI constant
		Parameter p1 = new Parameter(PI, "Constant", FetchType.CONSTANTS);
		paramsMap.put(p1.getName(), p1);

		// Param 2 : Radius
		Parameter p2 = new Parameter(RADIUS, "Radius of the circle", FetchType.DATABASE);
		paramsMap.put(p2.getName(), p2);
	}

	static Calculator getInstance() {
		if (instance == null) {
			instance = new PerimeterCircle();
		}

		return instance;
	}
}

class CalculatorFactory {

	public Calculator process(int option) {
		if (option == 1) {
			return PerimeterCircle.getInstance();
		}
		throw new CalculatorAppException("Invalid Option selected.");
	}
}

class CalculatorHelper {

	private static CalculatorFactory calculatorFactory;
	private static FetchHelper fetchHelper;

	public CalculatorHelper() {
		// Here we can use spring framework to inject beans
		calculatorFactory = new CalculatorFactory();
		fetchHelper = new FetchHelper();
	}

	public void process(int option) {
		try {
			// get the calculator object using factory and singleton design pattern
			Calculator calculator = calculatorFactory.process(option);

			// print the descirption of calculator
			System.out.println("\n" + calculator.getAbout());

			// send the required params map to Fetch Param helper
			// and then send them to calculator's calculate function
			double result = calculator.calculate(fetchHelper.fetchParams(calculator.paramsMap));

			// print the result
			System.out.println("Result : " + result);

		} catch (Exception e) {
			System.out.println("Unexpected Exception : " + e.getMessage());
		}

	}
}

class FetchHelper {

	static Map<FetchType, Function<Parameter, Double>> fetchToFunctionMapper = Map.of(FetchType.API, apiParam(),
			FetchType.DATABASE, databaseParam(), FetchType.CONSTANTS, constantParam());
	private static FetchServiceResolver fetchServiceResolver;

	public FetchHelper() {
		this.fetchServiceResolver = new FetchServiceResolverImpl();
	}

	Map<String, Double> fetchParams(Map<String, Parameter> paramMap) {
		Map<String, Double> paramValueMap = new HashMap<>();

		for (Map.Entry<String, Parameter> param : paramMap.entrySet()) {
			paramValueMap.put(param.getKey(),
					fetchToFunctionMapper.get(param.getValue().getFetchType()).apply(param.getValue()));
		}

		return paramValueMap;
	}

	private static Function<Parameter, Double> apiParam() {
		return (param) -> {
			System.out.println("\n(Please Provide me following Details)\n1.POST connection Endpoint");
			String url = new Scanner(System.in).nextLine();
			return fetchServiceResolver.resolveFetchService(FetchType.API).fetchParam(new ApiDetails(url));
		};
	}

	private static Function<Parameter, Double> databaseParam() {
		return (param) -> {
			System.out.println("\n(Please Provide me following Details)\n1. Table name\n2. Id");
			String db = new Scanner(System.in).nextLine();
			String id = new Scanner(System.in).nextLine();
			return fetchServiceResolver.resolveFetchService(FetchType.DATABASE).fetchParam(new DatabaseDetails(db, id));
		};
	}

	private static Function<Parameter, Double> constantParam() {
		return (param) -> fetchServiceResolver.resolveFetchService(FetchType.CONSTANTS).fetchParam(param.getName());
	}
}

//This is another type of implementation of factory design pattern
interface FetchServiceResolver<T, R extends FetchServiceHandler> {

	R resolveFetchService(T t);
}

class FetchServiceResolverImpl implements FetchServiceResolver<FetchType, FetchServiceHandler> {

	@Override
	public FetchServiceHandler resolveFetchService(FetchType fetchType) {
		return switch (fetchType) {
		case API -> ApiFetchServiceHandler.getInstance();
		case CONSTANTS -> ConstantFetchServiceHandler.getInstance();
		case DATABASE -> DatabaseFetchServiceHandler.getInstance();
		};
	}
}

interface FetchServiceHandler<E> {

	double fetchParam(E e);
}

class ApiDetails {
	private String url;

	public ApiDetails(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

class DatabaseDetails {
	private String tableName;
	private String id;

	public DatabaseDetails(String tableName, String id) {
		this.tableName = tableName;
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

class ApiFetchServiceHandler implements FetchServiceHandler<ApiDetails> {

	private static FetchServiceHandler instance;

	@Override
	public double fetchParam(ApiDetails apiDetails) {
		return 3;
	}

	public static FetchServiceHandler getInstance() {
		if (instance == null) {
			instance = new ApiFetchServiceHandler();
		}

		return instance;
	}

}

class DatabaseFetchServiceHandler implements FetchServiceHandler<DatabaseDetails> {
	private static FetchServiceHandler instance;

	@Override
	public double fetchParam(DatabaseDetails databaseDetails) {
		return 4;
	}

	public static FetchServiceHandler getInstance() {
		if (instance == null) {
			instance = new DatabaseFetchServiceHandler();
		}

		return instance;
	}
}

class ConstantFetchServiceHandler implements FetchServiceHandler<String> {
	private static FetchServiceHandler instance;

	private static final Map<String, Double> constantsMap = Map.of(PI, 3.14);

	@Override
	public double fetchParam(String constantName) {
		return constantsMap.get(constantName);
	}

	public static FetchServiceHandler getInstance() {
		if (instance == null) {
			instance = new ConstantFetchServiceHandler();
		}
		return instance;
	}
}

public class CalculatorApp {

	public static void main(String[] args) throws CalculatorAppException {
		Scanner sc = new Scanner(System.in);
		CalculatorHelper calculatorHelper = calculatorHelper = new CalculatorHelper();
		System.out.println("Welcome to custom Calculator : ");
		while (true) {
			System.out.println(
					"\nAvailable Calculators : " + "\n1. Perimeter of Circle" + "\n(Please select the option)");
			int option = sc.nextInt();
			calculatorHelper.process(option);
		}
	}
}

class CalculatorAppException extends RuntimeException {
	CalculatorAppException(String msg) {
		super(msg);
	}
}
