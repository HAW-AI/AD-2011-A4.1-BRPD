package com.github.haw.brpd.ad.a04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MIFile {
	private String[] content;
	public final String DELIMETER = "||";
	public static final int NUMBER_OF_COLUMNS = 3;
	

	public MIFile(String filename) {}
	
	private String[] head(String[] parse) {
		String[] result = parse[0].split(DELIMETER);
		for (int i = 0; i < result.length; i++) {
			result[i] = result[i].trim();
		}
		return result;
	}

	private String[][] body(String[] parse) {
		String[] body = Arrays.copyOfRange(parse, 1, parse.length);
		String[][] result = new String[body.length][NUMBER_OF_COLUMNS];
		for (int i = 0; i < body.length; i++) {
			result[i] = body[i].split(DELIMETER);
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = result[i][j].trim();
			}
		}
		return result;
	}

	public String[] parse(String filename) {
		if (this.content == null) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(new File(filename)));
			} catch (FileNotFoundException e) {
				// XXX: maybe we should leave the exception as it is
				throw new IllegalArgumentException(filename + " not found");
			}
			List<String> buffer = new ArrayList<String>();
			String line; 
			try {
				while ((line = reader.readLine()) != null) {
					buffer.add(line);
				}
			} catch (IOException e) {
				// to bad, no lines to read
			}
			this.content = buffer.toArray(new String[0]);
		}
		return this.content;
	}
	
}
