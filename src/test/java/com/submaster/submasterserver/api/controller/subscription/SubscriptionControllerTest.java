package com.submaster.submasterserver.api.controller.subscription;

import com.submaster.submasterserver.ControllerTestSupport;
import com.submaster.submasterserver.api.controller.subcription.SubscriptionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubscriptionControllerTest extends ControllerTestSupport {

    @DisplayName("구독서비스를 저장한다.")
    @Test
    @WithMockUser("USER")
    void saveTest() throws Exception {
        //given
        SubscriptionRequest request = SubscriptionRequest.builder()
                .serviceName("Neflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/subscription")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.httpStatus").value("CREATED"))
                .andExpect(jsonPath("$.message").value("CREATED"));
    }

    @DisplayName("구독서비스를 저장할시 서비스명은 필수이다.")
    @Test
    @WithMockUser("USER")
    void saveWithoutServiceNameTest() throws Exception {
        //given
        SubscriptionRequest request = SubscriptionRequest.builder()
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/subscription")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("서비스명은 필수입니다."));
    }

    @DisplayName("구독서비스 목록을 조회한다.")
    @Test
    @WithMockUser("USER")
    void findAll() throws Exception {
        //given
        // when // then
        mockMvc.perform(
                        get("/api/v1/subscription")
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("구독서비스를 이름으로 조회한다.")
    @Test
    @WithMockUser("USER")
    void findByNameTest() throws Exception {
        //given
        // when // then
        mockMvc.perform(
                        get("/api/v1/subscription/{serviceName}", "netflix")
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
