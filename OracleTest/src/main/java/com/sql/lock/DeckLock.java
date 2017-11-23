package com.sql.lock;
/**
 * 
 * @author sonic
 * reentrance lock
 * only the current thread holds the lock can unlock it
 */
public class DeckLock {
	private boolean isLocked = false;
	private Thread lockedBy = null;
	private int lockedCount = 0;

	public synchronized void lock() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while (isLocked && lockedBy != callingThread) { // is locked by non-callingThread then wait
			wait();
		}
		isLocked = true;
		lockedCount++;
		lockedBy = callingThread;
	}

	public synchronized void unlock() {
		if (Thread.currentThread() == this.lockedBy) {
			lockedCount--;

			if (lockedCount == 0) {
				isLocked = false;
				notify();
			}
		}
	}

}
