package com.zee.zee5app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.naming.NameNotFoundException;

import com.google.protobuf.Service;
import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.exception.LocationNotFound;
import com.zee.zee5app.service.EpisodeService;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.impl.EpisodeServiceImpl;
import com.zee.zee5app.service.impl.LoginServiceImpl;
import com.zee.zee5app.service.impl.MovieServiceImpl;
import com.zee.zee5app.service.impl.SeriesServiceImpl;
import com.zee.zee5app.service.impl.SubscriptionServiceImpl;
import com.zee.zee5app.service.impl.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserService service = null;
//		try {
//			Register register = new Register("rush0010", "rushik10", "kumar10", "rushik@gmail.com10", "12345610");
//			
//			register.setContactNumber(new BigDecimal("1234567810"));
//			
//			service = UserServiceImpl.getInstance();
//			
//			String result = service.addUser(register);
//			System.out.println("result --> " +result);
//			
//		} catch (InvalidIdLengthException | InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//getting user by ID
//		try {
//			service = UserServiceImpl.getInstance();
//			Optional<Register> optional = service.getUserById("rush002");
//			System.out.println(optional);
//		} catch (InvalidNameException | IdNotFoundException | InvalidIdLengthException | InvalidEmailException
//				| InvalidPasswordException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//get all user details
//		try {
//			service = UserServiceImpl.getInstance();
//			Optional<List<Register>> optional = service.getAllUserDetails();
//			if(optional.isEmpty()) {
//				System.out.println("There are no records");
//			}
//			else {
//				optional.get().forEach(e -> System.out.println(e));
//			}
//		} catch (InvalidNameException | InvalidIdLengthException | InvalidEmailException
//				| InvalidPasswordException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//Updating ROLE
//		try {
//			LoginService service = LoginServiceImpl.getInstance();
//			System.out.println(service.changeRole("rushik@gmail.com10", ROLE.ROLE_ADMIN));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//updating register
//		try {
//			Register register = new Register("rush0010", "rushik10U", "kumar10U", "rushik@gmail.com10", "12345610");
//			register.setContactNumber(new BigDecimal("1234567810"));
//			UserService service = UserServiceImpl.getInstance();
//			System.out.println(service.updateUser(register.getId(), register));
//		}
//		catch (InvalidIdLengthException | InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//--------------------------Movies Transactions------------------------------------------
		//Inserting data
//		try {
//			Movies movie = new Movies("sanju", "Drama", "2019-02-12", "/location/06", "Hindhi", "Ranbir", 128, "mov006", 13);
//			MovieService service = MovieServiceImpl.getInstance();
//			System.out.println(service.addMovie(movie));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Getting all movies
//		try {
//			MovieService service = MovieServiceImpl.getInstance();
//			Optional<List<Movies>> optional = service.getMovies();
//			if(optional.isEmpty()) {
//				System.out.println("There are no records");
//			}
//			else {
//				optional.get().forEach(e -> System.out.println(e));
//			}
//		} catch (IOException | NameNotFoundException | LocationNotFound e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//getting a movie by id
//		try {
//			MovieService service = MovieServiceImpl.getInstance();
//			Optional<Movies> optional = service.getMovieById("mov0002");
//			System.out.println(optional);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LocationNotFound e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//Updating Movie record
//		try {
//			Movies movie = new Movies("sanjuU", "DramaU", "2019-02-12", "/location/06U", "Hindhi", "Ranbir", 128, "mov006", 13);
//			MovieService service = MovieServiceImpl.getInstance();
//			System.out.println(service.modifyMovieById("mov006", movie));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//------------------------------------------Subscriptions transactions--------------------------------------
				//Inserting data
//				try {
//					Subscriptions subscription = new Subscriptions("2022-01-30", "online", "quaterly", "credit", false, "2022-02-10", "sub0006", 500, "rush007");
//					SubscriptionService service = SubscriptionServiceImpl.getInstance();
//					System.out.println(service.addSubscription(subscription));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		//getting subscriptions
//		try {
//			SubscriptionService service = SubscriptionServiceImpl.getInstance();
//			Optional<List<Subscriptions>> optional = service.getSubscriptions();
//			if(optional.isEmpty()) {
//				System.out.println("There are no records");
//			}
//			else {
//				optional.get().forEach(e -> System.out.println(e));
//			}
//		} catch (IOException | InvalidAmountException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//getting  subscription by id
//		try {
//			SubscriptionService service = SubscriptionServiceImpl.getInstance();
//			Optional<Subscriptions> optional = service.getSubscriptionById("sub0003");
//			System.out.println(optional);
//		}catch (IOException | InvalidAmountException | IdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//updating subscription
//		try {
//			Subscriptions subscription = new Subscriptions("2022-02-01", "online", "quaterly", "credit", false, "2022-02-10", "sub0006", 500, "rush007");
//			SubscriptionService service = SubscriptionServiceImpl.getInstance();
//			System.out.println(service.modifySubscriptionById("sub0006", subscription));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//-------------------------------------Series transactions-----------------------------------------
		//Inserting data
//		try {
//			Series series = new Series("seriename6", 10, "2022-01-30", 127, "cast6", "Thriller", "ser006", 13, "english");
//			SeriesService service = SeriesServiceImpl.getInstance();
//			System.out.println(service.addSeries(series));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//getting all Series
//		try {
//			SeriesService service = SeriesServiceImpl.getInstance();
//			Optional<List<Series>> optional = service.getSeries();
//			if(optional.isEmpty()) {
//				System.out.println("There are no records");
//			}
//			else {
//				optional.get().forEach(e -> System.out.println(e));
//			}
//		} catch (IOException | NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//getting series by id
//		try {
//			SeriesService service = SeriesServiceImpl.getInstance();
//			Optional<Series> optional = service.getSeriesById("ser003");
//			System.out.println(optional);
//		}catch (IOException | IdNotFoundException | NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//updating series
//		try {
//			Series series = new Series("seriename6U", 10, "2022-01-30", 127, "cast6U", "ThrillerU", "ser006", 13, "englishU");
//			SeriesService service = SeriesServiceImpl.getInstance();
//			System.out.println(service.modifySeriesById("ser006", series));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//-----------------------------------Episodes transactions-------------------------------------
		//inserting data
//		try {
//			Episodes episode = new Episodes("epiId6", "ser006", "episode6", (float)45.06, "location/06");
//			EpisodeService service = EpisodeServiceImpl.getInstance();
//			System.out.println(service.addEpisode(episode));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//get all episodes
//		try {
//			EpisodeService service = EpisodeServiceImpl.getInstance();
//			Optional<List<Episodes>> optional = service.getEpisodes();
//			if(optional.isEmpty()) {
//				System.out.println("There are no records");
//			}
//			else {
//				optional.get().forEach(e -> System.out.println(e));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//getting episode by ID
//		try {
//			EpisodeService service = EpisodeServiceImpl.getInstance();
//			Optional<Episodes> optional = service.getEpisodeById("epiId3");
//			System.out.println(optional);
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//Modifying Episode by ID
//		try {
//			Episodes episode = new Episodes("epiId6", "ser006", "episode6U", (float)45.16, "location/06U");
//			EpisodeService service = EpisodeServiceImpl.getInstance();
//			System.out.println(service.modifyEpisodeById("epiId6", episode));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
