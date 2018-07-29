package net.reflxction.impuritybot.core.others;

import net.dv8tion.jda.core.entities.Member;

public class BanInfo {

    private String reason = null;
    private boolean reasonProvided = false;

    private Member executor;
    private Member target;

    private int delDays;

    public BanInfo(Member executor, Member target, int delDays, String reason) {
        if (reason != null) {
            this.reason = reason;
            this.reasonProvided = true;
        }
        this.executor = executor;
        this.target = target;
        this.delDays = delDays;
    }

    public BanInfo(Member executor, Member target, int delDays) {
        this(executor, target, delDays, null);
    }

    public String getReason() {
        return reason;
    }

    public int getDelDays() {
        return delDays;
    }

    public Member getExecutor() {
        return executor;
    }

    public Member getTarget() {
        return target;
    }

    public boolean reasonProvided() {
        return reasonProvided;
    }

}
