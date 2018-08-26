package com.programmer.igoodie.utils.time;

import java.util.TimerTask;

public class Timer {

	private static final java.util.Timer TIMER = new java.util.Timer();
	
	public static TimerTask schedule(final Runnable r, long delay) {
		final TimerTask task = generateTask(r);
		TIMER.schedule(task, delay);
		return task;
	}
	
	public static TimerTask setInterval(final Runnable r, long period) {
		final TimerTask task = generateTask(r);
		TIMER.scheduleAtFixedRate(task, 0, period);
		return task;
	}
	
	private static TimerTask generateTask(final Runnable r) {
		return new TimerTask() {
			@Override
			public void run() { r.run(); }
		};
	}

}
