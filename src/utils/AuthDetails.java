package utils;

import java.util.Calendar;

public class AuthDetails {
    private int attempts;
    private Calendar timestamp;
    private boolean isBlocked;

    public AuthDetails() {
        this.attempts = 1;
        this.timestamp = Calendar.getInstance();
        this.isBlocked = false;
    }

    public boolean getIsBlocked() {return isBlocked;}

    public void setIsBlocked(boolean b) {isBlocked = b;}

    public int getAttemps() {return attempts;}

    public Calendar getTimestamp() {return timestamp;}

    public void increaseAttemps() {
        this.attempts += 1;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setTimestamp(int delta) {
        Calendar blockTime = Calendar.getInstance();
        blockTime.add(Calendar.MINUTE, delta);
        this.timestamp = blockTime;
    }

}
