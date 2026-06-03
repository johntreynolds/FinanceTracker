package com.finance.tracker;

public class Transaction
	{
		private String date;
		private String info;
		private double amount;
		private String accountType;

		public Transaction(String date, String info, double amount, String accountType)
			{
				this.date = date;
				this.info = info;
				this.amount = amount;
				this.accountType = accountType;
			}

		public String getDate()
			{
				return date;
			}

		public void setDate(String date)
			{
				this.date = date;
			}

		public double getAmount()
			{
				return amount;
			}

		public void setAmount(double amount)
			{
				this.amount = amount;
			}

		public String getInfo()
			{
				return info;
			}

		public void setInfo(String info)
			{
				this.info = info;
			}

		public String getAccountType()
			{
				return accountType;
			}

		public void setAccountType(String accountType)
			{
				this.accountType = accountType;
			}
		
		
		
	}
