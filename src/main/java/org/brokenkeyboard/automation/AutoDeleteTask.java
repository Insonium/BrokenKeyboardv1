package org.brokenkeyboard.automation;

import net.dv8tion.jda.api.entities.Message;

public class AutoDeleteTask implements Runnable {
    private final Message message;
    private final long waitTime;

    public AutoDeleteTask(Message message, long waitTime) {
        this.message = message;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        message.delete().queue();
    }
}