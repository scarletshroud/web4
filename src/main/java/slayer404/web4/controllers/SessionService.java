package slayer404.web4.controllers;

import java.util.HashMap;

public class SessionService {
    public final static SessionService global = new SessionService();

    private final HashMap<String, Long> sessions = new HashMap<>();

    private SessionService() {}

    public void add(String sessionId, Long userId) {
        sessions.put(sessionId, userId);
    }

    public Long get(String sessionId) {
        return sessions.get(sessionId);
    }

    public void remove(String sessionId) {
        sessions.remove(sessionId);
    }

    public boolean contains(String sessionId) {
        return sessions.containsKey(sessionId);
    }

}
