package com.finance.tracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class ReadBankCSV
	{
		private String folderPath;

		public ReadBankCSV(String folderPath)
			{
				this.folderPath = folderPath;
			}

		public String findCSVName() throws IOException
			{
				File folderName = new File(this.folderPath);
				String[] fileName = folderName.list();
				if (fileName == null)
					{
						throw new IOException("The directory <" + folderPath + "> could not be found");
					}
				if (fileName.length == 0)
					{
						throw new IOException("No statement found in folder <" + folderPath + ">");
					}
				String onlyFileName = fileName[0];
				return this.folderPath + "/" + onlyFileName;
			}

		public String findAccountType() throws IOException
			{

				String accountType = "";
				if (findCSVName().toLowerCase().contains("savings"))
					{
						accountType = "Savings";
					}
				else if (findCSVName().toLowerCase().contains("checking"))
					{
						accountType = "Checking";
					}
				else if (findCSVName().toLowerCase().contains("credit"))
					{
						accountType = "Credit";
					}
				else
					{
						accountType = "Null";
					}
				return accountType;
			}

		public ArrayList<Transaction> collectTransactions() throws IOException
			{

				ArrayList<Transaction> transactionData = new ArrayList<Transaction>();
				Scanner fileReader = new Scanner(new File(findCSVName()));
				while (fileReader.hasNextLine())
					{
						String data = fileReader.nextLine();
						String[] dataSplit = data.split(",");
						String date = dataSplit[0].substring(5, 7) + "/" + dataSplit[0].substring(8) + "/"
								+ dataSplit[0].substring(0, 4);
						String info = "";
						if (dataSplit[1].length() < 20)
							{
								info = dataSplit[1].substring(6);
							}
						else
							{
								info = dataSplit[1].substring(6, 20);
							}
						double amount = Double.parseDouble(dataSplit[2].substring(1));

						if (dataSplit[2].contains("-"))
							{
								transactionData.add(new Transaction(date, info, amount, findAccountType()));
							}

					}
				
				Collections.reverse(transactionData);
				fileReader.close();
				return transactionData;
			}

	}
