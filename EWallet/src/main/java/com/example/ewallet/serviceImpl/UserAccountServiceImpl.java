package com.example.ewallet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ewallet.dataaccessrepository.UserAccountRepository;
import com.example.ewallet.exceptions.UserNotFoundException;
import com.example.ewallet.models.UserAccount;
import com.example.ewallet.service.UserAccountService;
import com.google.common.collect.Lists;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserAccount userAccountByPK(Long userAccountId) throws UserNotFoundException {
		return userAccountRepository.findById(userAccountId).orElseThrow(
				() -> new UserNotFoundException(String.format("userAccount with '%d' not found ", userAccountId)));
	}

	/**
	 * this operations registers a user and creates and userAccount for him/her with
	 * minimal details
	 */
	@Override
	@Transactional
	public UserAccount save(UserAccount userAccount) throws Exception {
		if (userAccount.getUserName() != null) {
			if (userAccount.getUserName().length() < 5) {
				throw new Exception("user name is should be 5 characters of more");
			}
			return userAccountRepository.save(userAccount);
		}
		throw new Exception("user name is mandatory");
	}

	/**
	 * this operation updates a users userAccount information and checks for
	 * concurrent user modification
	 */
	@Override
	@Transactional
	public UserAccount update(UserAccount userAccount, Long userAccountId) throws Exception {
		if (userAccount.getUserName() != null) {
			userAccount.setId(userAccountId);
			try {
				return userAccountRepository.save(userAccount);
			} catch (Exception e) {
				throw new Exception("Try again");
			}
		}
		throw new Exception("user name is mandatory");

	}

	/**
	 * this operation gets all userAccount lists and their respective transaction
	 * transactions
	 */
	@Override
	public List<UserAccount> getList() {
		return Lists.newArrayList(userAccountRepository.findAll());
	}

}