package com.service

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface KeyboardService {
    fun createKeyboardRows(sendMessage: SendMessage, vararg nameKeys: String)
}