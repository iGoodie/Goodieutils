package com.programmer.igoodie.utils.io;

import java.util.HashMap;
import java.util.HashSet;

public class CommandLineArgs {

	private String[] rawArgs;
	private HashSet<String> flags;
	private HashMap<String, String> args;

	public CommandLineArgs(String[] rawArgs, String... valueSeperators) {
		this.rawArgs = rawArgs;
		this.flags = new HashSet<>();
		this.args = new HashMap<>();

		// Parse args
		for (String arg : rawArgs) {
			if (arg.startsWith("--")) // --someflag
				this.flags.add(arg.substring(2));
			
			else if (arg.startsWith("-")) { // -key:value
				for(String valueSeperator : valueSeperators) {
					if(arg.contains(valueSeperator)) {						
						String[] pair = arg.substring(1).split(valueSeperator, 2);
						this.args.put(pair[0], pair[1]);
						break;
					}
				}
			}
		}
	}

	public CommandLineArgs(String[] rawArgs) {
		this(rawArgs, ":", "=");
	}

	public int getFlagCount() {
		return flags.size();
	}

	public int getSubargumentCount() {
		return args.size();
	}

	public int getArgumentCount() {
		return rawArgs.length;
	}

	public boolean containsFlag(String flag) {
		return flags.contains(flag);
	}

	public boolean containsArgument(String arg) {
		return args.containsKey(arg);
	}

	public String getArgument(String argName) {
		return args.get(argName);
	}

	public String getArgument(String argName, String defaultValue) {
		String arg = args.get(argName);
		return arg == null ? defaultValue : arg;
	}
	
}
