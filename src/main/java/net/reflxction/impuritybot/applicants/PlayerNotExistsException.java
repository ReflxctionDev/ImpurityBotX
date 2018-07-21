package net.reflxction.impuritybot.applicants;

public class PlayerNotExistsException extends Exception {

    public PlayerNotExistsException(String msg) {
        super(msg);
    }

    public PlayerNotExistsException() {
        super();
    }

}