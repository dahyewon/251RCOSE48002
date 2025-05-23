package com.wheretopop.infrastructure.chat.prompt

import com.wheretopop.interfaces.popup.PopupToolRegistry
import mu.KotlinLogging
import org.springframework.ai.model.tool.ToolCallingChatOptions
import org.springframework.ai.support.ToolCallbacks
import org.springframework.stereotype.Component

/**
 * 팝업 스토어 정보 조회에 특화된 전략 구현체
 * 사용자가 팝업 스토어 정보를 요청할 때 적절한 Tool Calling을 설정하여 응답합니다.
 */
@Component
class PopupQueryStrategy(
    private val popupToolRegistry: PopupToolRegistry
) : BaseChatPromptStrategy() {

    private val logger = KotlinLogging.logger {}

    /**
     * 전략 타입을 반환합니다.
     */
    override fun getType(): StrategyType {
        return StrategyType.POPUP_QUERY
    }

    /**
     * 팝업 스토어 정보 조회에 특화된 추가 프롬프트를 반환합니다.
     */
    override fun getAdditionalSystemPrompt(): String {
        return SystemPrompt.POPUP_QUERY_PROMPT
    }
    
    /**
     * Tool Calling을 위한 채팅 옵션을 설정합니다.
     * 팝업 스토어 관련 도구들을 등록하고 AI가 이를 사용할 수 있도록 합니다.
     */
    override fun getToolCallingChatOptions(): ToolCallingChatOptions {
        val toolCallbackChatOptions = ToolCallingChatOptions.builder()
            .toolCallbacks(*ToolCallbacks.from(popupToolRegistry))
            .internalToolExecutionEnabled(false)
            .temperature(0.2)
            .build()
        return toolCallbackChatOptions
    }
} 