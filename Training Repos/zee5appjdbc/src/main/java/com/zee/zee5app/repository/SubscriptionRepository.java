package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscriptions;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;

public interface SubscriptionRepository {
	public String addSubscription(Subscriptions subscription);
	public Optional<List<Subscriptions>> getSubscriptions() throws InvalidAmountException;
	public Optional<Subscriptions> getSubscriptionById(String id) throws IdNotFoundException, InvalidAmountException;
	public String deleteSubscriptionById(String id) throws IdNotFoundException;
	public String modifySubscriptionById(String id, Subscriptions subscription);
	
}
