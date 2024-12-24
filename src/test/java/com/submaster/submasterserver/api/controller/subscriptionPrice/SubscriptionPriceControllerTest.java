package com.submaster.submasterserver.api.controller.subscriptionPrice;

import com.submaster.submasterserver.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SubscriptionPriceControllerTest extends ControllerTestSupport {

    @DisplayName("구독서비스의 가격목록을 조회한다.")
    @Test
    @WithMockUser("USER")
    void findBySubscriptionIdTest() throws Exception {
        //given
        // when // then
        mockMvc.perform(
                        get("/api/v1/subscriptionPrice/{subscriptionId}", 1)
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }
}
