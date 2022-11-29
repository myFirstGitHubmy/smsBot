package com.domain

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.ArrayList

@Service
open class MessageServiceImpl : MessageService {

    override fun initDefaultKeyboardButton(sendMessage: SendMessage) {
        sendMessage.enableMarkdown(true)

        var replyKeyboardMarkup = ReplyKeyboardMarkup()
        sendMessage.replyMarkup = replyKeyboardMarkup
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false


        var keyboardRow = KeyboardRow()
        keyboardRow.add("Отправить смс на номер")
        keyboardRow.add("История")
        var keyboard = ArrayList<KeyboardRow>()
        keyboard.add(keyboardRow)

        replyKeyboardMarkup.keyboard = keyboard
    }

    override fun sendMsg(sendMessage: SendMessage,
                         message: Message,
                         text: String) {
        initDefaultKeyboardButton(sendMessage)
        sendMessage.chatId = message.chatId.toString()
        sendMessage.replyToMessageId = message.messageId
        sendMessage.text = text
    }
}