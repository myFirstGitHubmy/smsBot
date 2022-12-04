package com.service.impl

import com.dictiondary.Constant
import com.dictiondary.DictionaryWord
import com.domain.Hierarchy
import com.domain.SendMessageContext
import com.domain.TypeKeyBoard
import com.service.KeyboardService
import com.service.MessageService
import com.utils.StringUtils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
open class MessageServiceImpl : MessageService {
    private val keyBoardService: KeyboardService = KeyBoardServiceImpl()
    private val words: DictionaryWord = DictionaryWord()

    override fun startButton(sendMessage: SendMessage) {
        keyBoardService.createKeyboardRows(sendMessage,
                TypeKeyBoard.MENU_KEY, TypeKeyBoard.PROFILE_KEY)
    }

    override fun sendMsg(sendMessage: SendMessage,
                         message: Message,
                         text: String) {
        when (message.text) {
            TypeKeyBoard.START -> startButton(sendMessage)
            TypeKeyBoard.MENU_KEY -> menuButtons(sendMessage)
            else -> {

            }
        }

        sendMessage.chatId = message.chatId.toString()
        sendMessage.replyToMessageId = message.messageId
        sendMessage.text = text
    }

    override fun handlerMessage(context: SendMessageContext, receivedMessage: String?, update: Update) {
        context.command = receivedMessage
        var sendMessage = context.sendMessage
        when (receivedMessage) {
                TypeKeyBoard.START -> sendMsg(sendMessage, update.message, Constant.greeting)
                TypeKeyBoard.MENU_KEY -> sendMsg(sendMessage, update.message, "")
                TypeKeyBoard.HISTORY_KEY -> sendMsg(sendMessage, update.message, "История")
                TypeKeyBoard.SEND_SMS_KEY -> sendMsg(sendMessage, update.message, "смс")
                TypeKeyBoard.BACK_KEY -> handlerMessage(context,
                        receivedMessage = Hierarchy.hierarchyMap.get(TypeKeyBoard.PROFILE_KEY),
                        update)
                else -> {
                    if (StringUtils.contains(words.getGreeting(), receivedMessage)) {
                        sendMsg(sendMessage, update.getMessage(), words.getGreetingWord())
                    } else {
                        sendMsg(sendMessage, update.getMessage(), Constant.NO_FOUND_COMMAND)
                    }
                }
        }
        context.sendMessage = sendMessage
    }

    private fun menuButtons(sendMessage: SendMessage) {
        keyBoardService.createKeyboardRows(sendMessage,
                TypeKeyBoard.BACK_KEY, TypeKeyBoard.SEND_SMS_KEY, TypeKeyBoard.HISTORY_KEY)
    }
}
