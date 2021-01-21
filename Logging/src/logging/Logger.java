package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Logger
{
	public static File logFile = null;
	public String date;
	public String time;
	public Long timeStep;
	public String level;
	public String message;
	public String simulationObject;

	static String timestamp = new SimpleDateFormat("dd-MM-yyyy - HH'h' mm'm' ss's'").format(new Date());

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	public Logger(Date timestamp, String level, Long timeStep, String message, String simulationObject)
	{
		this.time = timeFormat.format(timestamp);
		this.date = dateFormat.format(timestamp);
		this.timeStep = timeStep;
		this.level = level;
		this.message = message;
		this.simulationObject = simulationObject;
	}

	public void writeToTxt()
	{
		String fileSeparator = System.getProperty("file.separator"); // System neutral
//		System.out.println(fileSeparator);	

		File folder = new File("Logfiles");
		boolean exFolder = folder.mkdir();

		if (exFolder)
		{
			System.out.println("Folder created");
		} else
		{
//			System.err.println("Folder already exists");
		}

		BufferedWriter writer = null;

		try
		{
			File logFile = new File("Logfiles" + fileSeparator + timestamp + ".txt");

			if (logFile.createNewFile())
			{
				System.out.println("File created: " + logFile.getName());
			} else
			{
//				System.out.println("File already exists.");
			}

//			FileWriter(String fileName, boolean append)
			FileWriter fw = new FileWriter(logFile, true);
			writer = new BufferedWriter(fw);			

//			writer = new BufferedWriter(new FileWriter(logFile, true));
			writer.append("<" + this.time + "> <" + 
								this.date + "> <" + 
								this.timeStep + "> <" + 
								this.level + "> <" + 
								this.message + "> <" + 
								this.simulationObject + ">");
			
			writer.append(System.getProperty("line.separator")); // instead of \n

		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Error writing to the file");
		} finally
		{ // writer.close();
			try
			{
				writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void writeToConsole()
	{
		System.out.println("<" + this.time + "> <" + 
								 this.date + "> <" + 
								 this.timeStep + "> <" + 
								 this.level + "> <" +
								 this.message + "> <" + 
								 this.simulationObject + ">");
	}

}
