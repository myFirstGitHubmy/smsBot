package com.service

import com.domain.SendMessageContext
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface MessageService {
    fun startButton(sendMessage: SendMessage)
    fun sendMsg(sendMessage: SendMessage, message: Message, text: String)
    fun handlerMessage(context: SendMessageContext, receivedMessage: String?, update: Update)
}