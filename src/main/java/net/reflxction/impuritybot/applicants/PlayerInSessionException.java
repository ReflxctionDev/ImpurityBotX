package net.reflxction.impuritybot.applicants;

public class PlayerInSessionException extends Exception {

    public PlayerInSessionException(String msg) {
        super(msg);
    }

    public PlayerInSessionException() {
        super();
    }

}