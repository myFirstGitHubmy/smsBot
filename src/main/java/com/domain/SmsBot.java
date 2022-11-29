package com.domain;

import com.dictiondary.DictionaryWord;
import com.utils.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SmsBot extends TelegramLongPollingBot {
    DictionaryWord words = new DictionaryWord();

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
            String receivedMessage = update.getMessage().getText().toLowerCase();
            SendMessage sendMessage = new SendMessage();
            switch (receivedMessage) {
                case "/start" -> messageService.sendMsg(sendMessage, update.getMessage(), "123456");
                case "история" -> messageService.sendMsg(sendMessage, update.getMessage(), "История");
                case "отправить смс на номер" -> messageService.sendMsg(sendMessage, update.getMessage(), "смс");
                default -> {
                    if (StringUtils.contains(words.getGreeting(), receivedMessage)) {
                        messageService.sendMsg(sendMessage, update.getMessage(), words.getGreetingWord());
                    } else {
                        messageService.sendMsg(sendMessage, update.getMessage(), "команда не распознана");
                    }
                }
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
