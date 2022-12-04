package com.domain;

import com.service.MessageService;
import com.service.impl.MessageServiceImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SmsBot extends TelegramLongPollingBot {


    private final MessageService messageService = new MessageServiceImpl();

    @Override
    public String getBotUsername() {
        return "cookieSMSBot";
    }

    @Override
    public String getBotToken() {
        return "5902717178:AAGIFM_JvhWNdu_XIPQZoU_Wwo39a-gFWcU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() == null) {
            return;
        }

        if (update.getMessage().hasText()) {
            String receivedMessage = update.getMessage().getText();
            SendMessageContext context = new SendMessageContext(new SendMessage(), receivedMessage);
            messageService.handlerMessage(context, receivedMessage, update);
            try {
                execute(context.getSendMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
