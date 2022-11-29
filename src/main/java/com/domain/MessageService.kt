package com.domain

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

interface MessageService {
    fun initDefaultKeyboardButton(sendMessage: SendMessage)
    fun sendMsg(sendMessage: SendMessage, message: Message, text: String)
}