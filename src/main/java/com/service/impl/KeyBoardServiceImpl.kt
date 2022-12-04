package com.service.impl

import com.service.KeyboardService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.*

@Service
class KeyBoardServiceImpl : KeyboardService {
    private fun setSettingReplyKeyboardMarkup(replyKeyboardMarkup: ReplyKeyboardMarkup) {
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.oneTimeKeyboard = false
    }

    private fun createReplyKeyboardMarkup(sendMessage: SendMessage,
                                          vararg nameKeys: String) {
        val keyBoardRows = ArrayList<KeyboardRow>()
        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        sendMessage.replyMarkup = replyKeyboardMarkup
        setSettingReplyKeyboardMarkup(replyKeyboardMarkup)
        for (nameKey in nameKeys) {
            val keyboard = createKeyboardRow(nameKey)
            keyBoardRows.add(keyboard)
        }
        replyKeyboardMarkup.keyboard = keyBoardRows
    }

    private fun createKeyboardRow(nameKeyBoard: String ): KeyboardRow{
        val keyboardRow = KeyboardRow()
        keyboardRow.add(nameKeyBoard)
        return keyboardRow
    }

    override fun createKeyboardRows(sendMessage: SendMessage,
                                    vararg nameKeys: String) {
        sendMessage.enableMarkdown(true)
        createReplyKeyboardMarkup(sendMessage, nameKeys = nameKeys)
    }
}