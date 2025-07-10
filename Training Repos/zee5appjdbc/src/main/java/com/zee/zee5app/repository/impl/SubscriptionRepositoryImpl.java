package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository{
	
	@Autowired
	DataSource datasource;
	//DBUtils dbutils = DBUtils.getInstance();
	
	public SubscriptionRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addSubscription(Subscriptions subscription) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//insert statement
		String inputstatement = "insert into subscription"
				+ "(id, dop, expiry, amount, paymentmode, status, type, autorenewal, regId) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(inputstatement);
			preparedstatement.setString(1, subscription.getId());
			preparedstatement.setString(2, subscription.getDateOfPurchase());
			preparedstatement.setString(3, subscription.getExpiryDate());
			preparedstatement.setInt(4, subscription.getAmount());
			preparedstatement.setString(5, subscription.getPaymentMode());
			preparedstatement.setString(6, subscription.getStatus());
			preparedstatement.setString(7, subscription.getType());
			preparedstatement.setString(8, String.valueOf(subscription.isAutoRenewal()));
			preparedstatement.setString(9, subscription.getRegId());

			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "success";
			}
			else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		}
	}
	@Override
	public Optional<List<Subscriptions>> getSubscriptions() throws InvalidAmountException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		List<Subscriptions> arrayList = new ArrayList<>();
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from subscription";
		try {
			preparedstatment = connection.prepareStatement(query);			
			resultset = preparedstatment.executeQuery();
			
			while(resultset.next()) {
				Subscriptions subscription = new Subscriptions();
				subscription.setId(resultset.getString("id"));
				subscription.setDateOfPurchase(resultset.getString("dop"));
				subscription.setExpiryDate(resultset.getString("expiry"));
				subscription.setAmount(resultset.getInt("amount"));
				subscription.setAutoRenewal(Boolean.parseBoolean(resultset.getString("autorenewal")));
				subscription.setPaymentMode(resultset.getString("paymentmode"));
				subscription.setStatus(resultset.getString("status"));
				subscription.setType(resultset.getString("type"));
				subscription.setRegId(resultset.getString("regId"));
				arrayList.add(subscription);
			}
			return Optional.ofNullable(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	@Override
	public Optional<Subscriptions> getSubscriptionById(String id) throws IdNotFoundException, InvalidAmountException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedstatment = null;
		ResultSet resultset = null;
		
		//select query
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "select * from subscription where id = ?";
		try {
			preparedstatment = connection.prepareStatement(query);
			preparedstatment.setString(1, id);
			
			resultset = preparedstatment.executeQuery();
			
			if(resultset.next()) {
				Subscriptions subscription = new Subscriptions();
				subscription.setId(resultset.getString("id"));
				subscription.setDateOfPurchase(resultset.getString("dop"));
				subscription.setExpiryDate(resultset.getString("expiry"));
				subscription.setAmount(resultset.getInt("amount"));
				subscription.setAutoRenewal(Boolean.parseBoolean(resultset.getString("autorenewal")));
				subscription.setPaymentMode(resultset.getString("paymentmode"));
				subscription.setStatus(resultset.getString("status"));
				subscription.setType(resultset.getString("type"));
				subscription.setRegId(resultset.getString("regId"));
				return Optional.of(subscription);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//delete statement
		String deletestatement = "delete from subscription where id = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(deletestatement);
			preparedstatement.setString(1, id);
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				//deleting in Login table
				
				return "success";
			}
			else {
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return "fail";
		}
		
	}
	@Override
	public String modifySubscriptionById(String id, Subscriptions subscription) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement  preparedstatement = null;
		//update statement
		String updatestatement = "update subscription set dop = ?, expiry = ?, amount = ?, paymentmode = ?, status = ?, type = ?, autorenewal = ?, regId = ? where id = ?";
		
		//preparing statement
		try {
			connection = datasource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			preparedstatement = connection.prepareStatement(updatestatement);
			preparedstatement.setString(9, subscription.getId());
			preparedstatement.setString(1, subscription.getDateOfPurchase());
			preparedstatement.setString(2, subscription.getExpiryDate());
			preparedstatement.setInt(3, subscription.getAmount());
			preparedstatement.setString(4, subscription.getPaymentMode());
			preparedstatement.setString(5, subscription.getStatus());
			preparedstatement.setString(6, subscription.getType());
			preparedstatement.setString(7, String.valueOf(subscription.isAutoRenewal()));
			preparedstatement.setString(8, subscription.getRegId());
			
			int result = preparedstatement.executeUpdate();
			if(result > 0) {
				connection.commit();
				return "success";
			}
			else {
				connection.rollback();
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		}
	}
	
		
}
