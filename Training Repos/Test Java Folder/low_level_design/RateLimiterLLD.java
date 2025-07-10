package low_level_design;

/*
Question: Rate Limiter for API Requests
System Overview
The system is designed to control the rate of incoming requests to prevent abuse and ensure fair usage across multiple clients. It should enforce rate limits based on configurable policies.

Components

Rate Limiter

Enforces limits on API requests per user.
Supports different rate-limiting strategies:
Fixed Window (e.g., max 100 requests per minute)
Sliding Window (e.g., distribute requests evenly over time)
Token Bucket (e.g., replenish tokens at a fixed rate)
Rejects requests exceeding the limit with an error response.
Client Identification

Identifies users based on an API key or IP address.
Maintains usage statistics for each user.
Storage

Keeps track of request timestamps/counts for users.
Could use in-memory storage (Redis, HashMap) for fast access.
Example
Scenario 1: Fixed Window Rate Limiting
Rule: Max 10 requests per minute per user.
User A sends 10 requests within 1 minute → Allowed.
User A sends the 11th request within the same minute → Rejected (HTTP 429 Too Many Requests).
After 1 minute → Quota resets, and requests are allowed again.
Scenario 2: Token Bucket
Rule: Bucket holds 5 tokens, refills 1 token per second.
User B sends 5 requests instantly → Allowed.
User B sends the 6th request immediately → Rejected (Bucket is empty).
After 2 seconds → 2 tokens added → 2 requests allowed.
 */

/*

Summary : System should be defined with set of API endpoints and type of Rate Limiter algo it uses
            and required params

Pre Identified Classes : Rate Limiter(Interface)  -> (Fixed Window, sliding window, token Bucket)
                         UserRequestTracker, APIDetails, 
		Fixed window : "limit": 10, "timeWindowSeconds": 60
		Token Bucket : "bucketSize": 5, "refillRatePerSecond": 1
		Sliding window : "limit": 3, "timeWindowSeconds": 10
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

enum RequestType {
	GET, POST, PUT
}

enum RateLimiterType {
	FIXED_WINDOW, SLIDING_WINDOW, TOKEN_BUCKET
}

abstract class RateLimiterDetails {

}

class FixedWindowDetails extends RateLimiterDetails {
	private int limit;
	private int timeWindowSeconds;

	public FixedWindowDetails(int limit, int timeWindowSeconds) {
		this.limit = limit;
		this.timeWindowSeconds = timeWindowSeconds;
	}

	public int getLimit() {
		return limit;
	}

	public int getTimeWindowSeconds() {
		return timeWindowSeconds;
	}
}

class SlidingWindowDetails extends RateLimiterDetails {
	private int limit;
	private int timeWindowSeconds;

	public SlidingWindowDetails(int limit, int timeWindowSeconds) {
		this.limit = limit;
		this.timeWindowSeconds = timeWindowSeconds;
	}

	public int getLimit() {
		return limit;
	}

	public int getTimeWindowSeconds() {
		return timeWindowSeconds;
	}
}

class TokenBucketDetails extends RateLimiterDetails {
	private int bucketSize;
	private int refillRatePerSecond;

	public TokenBucketDetails(int bucketSize, int refillRatePerSecond) {
		this.bucketSize = bucketSize;
		this.refillRatePerSecond = refillRatePerSecond;
	}

	public int getBucketSize() {
		return bucketSize;
	}

	public int getRefillRatePerSecond() {
		return refillRatePerSecond;
	}
}

class Api {
	private String apiName;
	private String apiEndpoint;
	private RequestType apiRequestType;
	private RateLimiterType rateLimiterType;
	private RateLimiterDetails rateLimiterDetails;

	public Api(String apiName, String apiEndpoint, RequestType apiRequestType, RateLimiterType rateLimiterType,
			RateLimiterDetails rateLimiterDetails) {
		this.apiName = apiName;
		this.apiEndpoint = apiEndpoint;
		this.apiRequestType = apiRequestType;
		this.rateLimiterType = rateLimiterType;
		this.rateLimiterDetails = rateLimiterDetails;
	}

	public String getApiName() {
		return apiName;
	}

	public String getApiEndpoint() {
		return apiEndpoint;
	}

	public RequestType getApiRequestType() {
		return apiRequestType;
	}

	public RateLimiterType getRateLimiterType() {
		return rateLimiterType;
	}

	public RateLimiterDetails getRateLimiterDetails() {
		return rateLimiterDetails;
	}

	@Override
	public int hashCode() {
		return new String(apiEndpoint).hashCode();
	}
}

class UserRequestDetails {
	String uid;
	String ip;
	String apiEndpoint;
	RequestType requestType;

	public UserRequestDetails(String uid, String ip, String apiEndpoint, RequestType requestType) {
		this.uid = uid;
		this.ip = ip;
		this.apiEndpoint = apiEndpoint;
		this.requestType = requestType;
	}

	public String getUid() {
		return uid;
	}

	public String getIp() {
		return ip;
	}

	public String getApiEndpoint() {
		return apiEndpoint;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	@Override
	public int hashCode() {
		return new String(apiEndpoint).hashCode();
	}
}

interface RateLimiter {
	boolean allowRequest();
}

class FixedWindowRateLimiter implements RateLimiter {

	private final FixedWindow fw;

	public FixedWindowRateLimiter(FixedWindowDetails rateLimiterDetails) {
		this.fw = new FixedWindow(rateLimiterDetails.getLimit(), rateLimiterDetails.getTimeWindowSeconds());
		fw.start();
	}

	private class FixedWindow extends Thread {
		private int limitCounter;
		private int confiuredLimit;
		private int configuredTimeinMin;

		FixedWindow(int confiuredLimit, int configuredTimeinMin) {
			this.confiuredLimit = confiuredLimit;
			this.configuredTimeinMin = configuredTimeinMin;
		}

		@Override
		public void run() {
			while (true) {
				try {
					synchronized (this) {
						limitCounter = confiuredLimit;
					}
					Thread.sleep(configuredTimeinMin);
				} catch (Exception e) {
					System.out.println("Exception Occured : " + e.getMessage());
				}
			}
		}

		public synchronized int getRequest() {
			if (limitCounter == 0)
				return -1;
			return --limitCounter;
		}
	}

	@Override
	public boolean allowRequest() {
		int request = fw.getRequest();
		return request > 0;
	}
}

class SlidingWindowRateLimiter implements RateLimiter {

	@Override
	public boolean allowRequest() {
		return false;
	}
}

class TokenBucketRateLimiter implements RateLimiter {

	@Override
	public boolean allowRequest() {
		return false;
	}
}

class RateLimiterResolver {
	public RateLimiter resolve(RateLimiterType type, RateLimiterDetails rateLimiterDetails) {
		switch (type) {
		case FIXED_WINDOW:
			FixedWindowDetails fwd = (FixedWindowDetails) rateLimiterDetails;
			return new FixedWindowRateLimiter(fwd);
		case SLIDING_WINDOW:
			return new SlidingWindowRateLimiter();
		case TOKEN_BUCKET:
			return new TokenBucketRateLimiter();
		default:
			throw new IllegalArgumentException("Unknown Rate Limiter Type");
		}
	}
}

class RateLimiterProcessor {
	private Map<String, RateLimiter> rateLimiters = new HashMap<>();
	private RateLimiterResolver resolver = new RateLimiterResolver();

	public void configureApi(Api apiDetails) {
		RateLimiter rateLimiter = resolver.resolve(apiDetails.getRateLimiterType(), apiDetails.getRateLimiterDetails());
		rateLimiters.put(apiDetails.getApiEndpoint(), rateLimiter);
	}

	public void checkRequest(String[] apis) {
		for (String api : apis) {
			RateLimiter rateLimiter = rateLimiters.get(api);
			if (rateLimiter != null && rateLimiter.allowRequest()) {
				System.out.println("Request accepted for API: " + api);
			} else {
				System.out.println("Request not accepted for API: " + api);
			}
		}
	}
}

public class RateLimiterLLD {

	public static void main(String args[]) {
		Api userDataApi = new Api("UserData", "/api/v1/user/data", RequestType.GET, RateLimiterType.FIXED_WINDOW,
				new FixedWindowDetails(10, 60000) // 10 requests per 60 seconds
		);
		RateLimiterProcessor processor = new RateLimiterProcessor();
		processor.configureApi(userDataApi);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Rate Limiter Client - Enter API name to simulate request (UserData or Orders).");

		while (true) {
			System.out.println("\nEnter API Name ");
			String input[] = scanner.nextLine().trim().split(" ");
			processor.checkRequest(input);
		}
	}
}
