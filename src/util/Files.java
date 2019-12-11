package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import objects.Vehicle;

public class Files {
	public static final Logger LOGGER = Logger.getLogger(Files.class.getName());
	public static final String INVENTORYPATH = "C:\\Users\\glove\\Desktop\\CarDealership\\Dealership\\WebContent\\data\\inventory.txt";
	public static final File INVENTORYFILE = new File(INVENTORYPATH);
	public static final String LEDGERPATH = "C:\\Users\\glove\\Desktop\\CarDealership\\Dealership\\WebContent\\data\\ledger.txt";
	public static final File LEDGERFILE = new File(LEDGERPATH);
	public static final String BIDLEDGERPATH = "C:\\Users\\glove\\Desktop\\CarDealership\\Dealership\\WebContent\\data\\bidLedger.txt";
	public static final File BIDLEDGERFILE = new File(BIDLEDGERPATH);
	private static final String READFILE = "Could not read from file.";

	/**
	 * Constructor hidden as this is a utility class. Variables are constants,
	 * methods are public static for use elsewhere
	 */
	private Files() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Takes an array of string (of any length) and creates a single string with
	 * each item delimited by comma. The last item is followed by a line separator
	 * to create a new line. The new line can then be appended to on next call.
	 * 
	 * The new string is then appended to the end of a given writable file.
	 * 
	 * @param file Any file that can be written to with Buffered/FileWriter
	 * @param s    Array of strings to be appended to given file, comma separted
	 *             items on single line
	 */
	public static void appendToFile(File file, String... s) {
		StringBuilder builder = new StringBuilder();

		for (String string : s) {
			if (!string.equals(s[s.length - 1])) {
				builder.append(string + ",");
			} else {
				builder.append(string + System.getProperty("line.separator"));
			}
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.write(builder.toString());
		} catch (IOException e) {
			util.Files.LOGGER.warning(READFILE + e);
		}
	}

	/**
	 * Searches file for given token. If token is not found on each line of file,
	 * that line is appended to a StringBuilder If the token is found, the line is
	 * skipped and not added The original file is then overwritten with
	 * BufferedWriter
	 * 
	 * @param file  Andy file that can be handled by BufferedReader and Writer
	 * @param token String to be searched for in file.
	 */
	public static void removeFromFile(File file, String token) {
		String line;
		String[] items = null;
		boolean containsToken = false;
		StringBuilder builder = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				items = line.split(",");
				for (String string : items) {
					if (string.equals(token)) {
						containsToken = true;
						break;
					}
				}
				if (!containsToken) {
					builder.append(line + System.getProperty("line.separator"));
				}
				containsToken = false; // Reset token to continue search
			}
		} catch (IOException e) {
			LOGGER.severe(READFILE + e);
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
			writer.write(builder.toString());
		} catch (IOException e) {
			LOGGER.severe("Could not write to file." + e);
		}
	}

	/**
	 * While loops through given file, stores each line in 'line,' Loops through
	 * list of tokens (user can comma separate keywords), Checks if each token is in
	 * the current 'line' on loop (ignore case), Add vehicle to returnList, Return
	 * results of found vehicles.
	 */
	public static List<Vehicle> searchFileForVehicle(File file, String token) {
		String line;
		String[] tokens = token.split(",");
		ArrayList<Vehicle> returnList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				String[] items = line.split(",");
				for (String s : tokens) {
					if (line.toLowerCase().contains(s.toLowerCase())) {
						returnList.add(new Vehicle(items[0], items[1], items[2], items[3], items[4], items[5], items[6],
								items[7], items[8], items[9], items[10], items[11], items[12]));
						break;
					}
				}
			}
		} catch (IOException e) {
			LOGGER.severe(READFILE + e);
		}

		return returnList;
	}

	/**
	 * Search through file by token, if the newValue is not equal to the value at
	 * the index of the line found with token (vin for vehicle), the entry is
	 * removed from the file and the new line is added to the end
	 * 
	 * @param file     Any file compatible with Buffered Reader/Writer and
	 *                 FileWriter
	 * @param token    Token to be search for, in the case of vehicles this should
	 *                 be vin
	 * @param index    Index of item to be removed (7 to remove daysInInventory)
	 * @param newValue Value to be replaced with current value at index. Used for
	 *                 updating.
	 */
	public static void updateValue(File file, String token, int index, String newValue) {

		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				String[] items = line.split(",");
				if (items[12].equals(token) && !items[index].equals(newValue)) {
					removeFromFile(INVENTORYFILE, token);
					items[index] = newValue;
					appendToFile(INVENTORYFILE, items[0], items[1], items[2], items[3], items[4], items[5], items[6],
							items[7], items[8], items[9], items[10], items[11], items[12]);
					break;
				}
			}
		} catch (IOException e) {
			LOGGER.severe(READFILE + e);
		}
	}

	/**
	 * Searches through bid ledger to get all bids on a given VIN.
	 * 
	 * If that list is not empty, get the highest bid from the list, search the file
	 * again and purchase the car for the user who made that bid. (Remove from
	 * inventory and add to ledger.)
	 * 
	 * If the vehicle has no bids, it should remain on sale at the discounted price.
	 * 
	 * @param vin Unique identifier of vehicle to be sold to last bidder.
	 */
	public static boolean sellToLastBidder(String vin) {

		String line;

		// Create list of all bids on vin
		ArrayList<Double> bids = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(util.Files.BIDLEDGERFILE))) {
			while ((line = reader.readLine()) != null) {
				String[] items = line.split(",");
				if (items[1].equals(vin)) {
					bids.add(Double.parseDouble(items[2]));
				}
			}
		} catch (IOException e) {
			LOGGER.severe(READFILE + e);
		}

		if (!bids.isEmpty()) {
			// Get highest bid
			Collections.reverse(bids);
			String highBid = Double.toString(bids.get(0));

			// Search back through file and sell car to person with highest bid on vin
			try (BufferedReader reader = new BufferedReader(new FileReader(util.Files.BIDLEDGERFILE))) {
				while ((line = reader.readLine()) != null) {
					String[] items = line.split(",");
					if (items[1].equals(vin) && items[2].equals(highBid)) {
						removeFromFile(INVENTORYFILE, vin);
						// Skip items[2], it is bid, all else remains the same.
						appendToFile(LEDGERFILE, items[0], items[1], items[3], items[4], items[5], items[6], items[7],
								items[8], items[9], items[10]);
						return true;
					}
				}
			} catch (IOException e) {
				LOGGER.severe(READFILE + e);
			}
		}
		
		return false;
	}
}
