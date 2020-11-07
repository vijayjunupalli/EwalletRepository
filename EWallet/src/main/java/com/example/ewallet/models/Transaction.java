package com.example.ewallet.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private UserAccount userAccount;
	@NotNull
	private BigDecimal amount;

	/** Purpose of Transaction */
	private String details;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date transactionDate;
	@NotNull
	private Long transactionReference;
	// @Version //for concurrency
	private int version;

	public Transaction() {
	}

	public Transaction(UserAccount userAccount, BigDecimal amount, String details, Date transactionDate,
			Long transactionReference) {
		this.userAccount = userAccount;
		this.amount = amount;
		this.details = details;
		this.transactionDate = transactionDate;
		this.transactionReference = transactionReference;
	}

	public Transaction(TransactionBuilder builder) {
		id = builder.id;
		userAccount = new UserAccount(builder.userAccountId);
		amount = builder.amount;
		details = builder.details;
		transactionDate = builder.transactionDate;
		transactionReference = builder.transactionReference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(Long transactionReference) {
		this.transactionReference = transactionReference;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Transaction other = (Transaction) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	public static class TransactionBuilder {

		private Long id;
		private Long userAccountId;
		private BigDecimal amount;
		private String details;
		private Date transactionDate;
		private Long transactionReference;

		public TransactionBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public TransactionBuilder setUserAccount(Long userAccountId) {
			this.userAccountId = userAccountId;
			return this;
		}

		public TransactionBuilder setAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public TransactionBuilder setDetails(String details) {
			this.details = details;
			return this;
		}

		public TransactionBuilder setTransactionDate(Date transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		public TransactionBuilder setTransactionReference(Long transactionReference) {
			this.transactionReference = transactionReference;
			return this;
		}

		public Transaction build() {
			return new Transaction(this);
		}

	}

}