package utils;

import java.util.Calendar;
import java.util.Hashtable;
import model.Authenticator;
import utils.AuthDetails;

public class LockUser {
	
    public static int ALLOWED_LOGINS = 3;
    public static int BLOCK_TIME = 30;
    public static int LOCK_COUNTER_MEMORY = 60;
	    // Class's only instance
	    private static LockUser instance = null;

	    // Hash table containing rogue login information
	    // {IP : [#attemps, timestamp]}
	    private Hashtable<String, AuthDetails> hashtable = new Hashtable<String, AuthDetails>();

	    // Eliminate instantiation of LoginCache
	    private LockUser() {}

	    // Get cache singleton
	    public static LockUser getInstance() {
	        if (instance == null)
	            instance = new LockUser();
	        return instance;
	    }

	    public boolean isHostBlocked(String ipAddr) {
	        AuthDetails current = this.hashtable.get(ipAddr);
	        if (current == null)
	            return false;

	        checkReset(ipAddr);
	        if (current.getIsBlocked())
	            current.setAttempts(0);
	        return (current.getAttemps() == ALLOWED_LOGINS &&
	                current.getTimestamp().after(Calendar.getInstance())) || current.getIsBlocked();
	        //return current.getAttemps() == ALLOWED_LOGINS;
	    }

	    private void checkReset(String ipAddr) {
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.MINUTE, -BLOCK_TIME);
	        AuthDetails currentLogin = this.hashtable.get(ipAddr);
	        System.out.println("Inside checkReset: isBlocked: " + currentLogin.getIsBlocked());
//	        System.out.println("is " + currentLogin.getTimestamp() + " before " + now + "? "  + currentLogin.getTimestamp().before(now));
	        if (currentLogin.getTimestamp().before(now)) {
	            currentLogin.setIsBlocked(false);
	            currentLogin.setAttempts(0);
	            currentLogin.setTimestamp(0);
	        }
	    }

	    public void clearFailedLogins(String ipAddr) {
	        this.hashtable.remove(ipAddr);
	    }

	    public void insertFailedLogin(String ipAddr) {
	        // If ip exists in hashtable
	        if (hashtable.containsKey(ipAddr)) {
	            AuthDetails current = hashtable.get(ipAddr);
	            current.increaseAttemps();
	            // and it reached the maximum amount of failed logins allowerd
	            if (current.getAttemps() == ALLOWED_LOGINS) {
	                // block the user, set the timestamp of the blockage.
	                current.setIsBlocked(true);
	                current.setTimestamp(BLOCK_TIME);
	            }  else {
	                // Check if 5 minutes have passed, if so, update the timestamp and reset the failed logins counter.
	                Calendar now = Calendar.getInstance();
	                now.add(Calendar.MINUTE, -LOCK_COUNTER_MEMORY);

	                if (current.getTimestamp().before(now)) {
	                	
	                    // 5 minutes have passed, reset the counter and set the new date.
	                    current.setAttempts(1);
	                    current.setTimestamp(BLOCK_TIME);
	                }
	            }
	            // increase user failed logins attempts

	        } else {
	            // First failed attempt, set the time.
	            AuthDetails authDetails = new AuthDetails();
	            hashtable.put(ipAddr, authDetails);
	            // set the timestamp for the first failed login
	            hashtable.get(ipAddr).setTimestamp(0);

	        }
	    }

	    public int getAttempts(String ipAddr) {
	        return hashtable.get(ipAddr).getAttemps();
	    }

	    public Calendar getBlockTime(String ipAddr) {
	        if (hashtable.get(ipAddr) != null && hashtable.get(ipAddr).getTimestamp() != null)
	            return hashtable.get(ipAddr).getTimestamp();
	        return null;
	    }
}
