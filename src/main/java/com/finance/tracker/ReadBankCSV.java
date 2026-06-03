package com.finance.tracker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

		public ArrayList<Transaction> readTextFile() throws IOException
			{
				ArrayList <Transaction> transactionData = new ArrayList <Transaction>();
				String fileName = findCSVName();
				String accountType = "";
				if (fileName.toLowerCase().contains("savings"))
					{
						accountType = "Savings";
					}
				else if (fileName.toLowerCase().contains("checking"))
					{
						accountType = "Checking";
					}
				else if (fileName.toLowerCase().contains("credit"))
					{
						accountType = "Credit";
					}
				else
					{
						accountType = "Null";
					}
				
				Scanner fileReader = new Scanner(new File(fileName));
				while (fileReader.hasNextLine())
					{
						String data = fileReader.nextLine();
						String[] dataSplit = data.split(",");
						String date = dataSplit[0].substring(1 , 9);
						String info = "";
						if (dataSplit[1].length() < 20)
							{
								info = dataSplit[1].substring(6);
							}
						else
							{
								info = dataSplit[1].substring(6, 20);
							}
						double amount = Double.parseDouble(dataSplit[3].substring(1));
						if (!dataSplit[3].contains("+"))
							{
								transactionData.add(new Transaction(date, info, amount, accountType));
							}
						
						
					}
				
				fileReader.close();
				return transactionData;
			}

	}
