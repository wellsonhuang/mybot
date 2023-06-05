package com.mybot.mybot.controllers;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
@LineMessageHandler
public class MessageController {
    @Autowired
    LineMessagingClient lineMessagingClient;
    @EventMapping
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent) {
        try {
            /*取回message的訊息*/
            Source source = messageEvent.getSource();
            String lineId = source.getUserId();
            String replyToken = messageEvent.getReplyToken();
            String message = messageEvent.getMessage().getText();

            /*獲得使用者的displayname*/
            String displayName = lineMessagingClient
                    .getProfile(lineId)
                    .get()
                    .getDisplayName();

            /*建立回應*/
            String answer = String.format("Hello, %s! Your message is %s", displayName, message);
            TextMessage responseMessage = new TextMessage(answer);

            /*送出回應*/
            lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, responseMessage));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}