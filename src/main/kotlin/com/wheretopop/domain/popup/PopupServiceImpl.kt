package com.wheretopop.domain.popup

import org.springframework.stereotype.Service

@Service
class PopupServiceImpl(
    private val popupStore: PopupStore
): PopupService {
    override fun savePopup(popup: Popup) {
        popupStore.save(popup)
    }
}