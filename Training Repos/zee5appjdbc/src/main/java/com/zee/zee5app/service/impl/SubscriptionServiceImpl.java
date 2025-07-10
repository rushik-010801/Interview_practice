package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.impl.SubscriptionRepositoryImpl;
import com.zee.zee5app.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
	
	private SubscriptionRepository repository;
	
	public SubscriptionServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addSubscription(Subscriptions subscription) {
		return this.repository.addSubscription(subscription);
	}
	
	@Override
	public Optional<Subscriptions> getSubscriptionById(String id) throws IdNotFoundException, InvalidAmountException {
		return this.repository.getSubscriptionById(id);
	}
	
	@Override
	public Optional<List<Subscriptions>> getSubscriptions() throws InvalidAmountException {
		return repository.getSubscriptions();
	}
	@Override
	public String modifySubscriptionById(String id, Subscriptions subscription) {
		return repository.modifySubscriptionById(id,subscription);
	}
	
	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		return repository.deleteSubscriptionById(id);
	}
}
