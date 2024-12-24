package com.submaster.submasterserver.docs.bank;

import com.submaster.submasterserver.RestDocsSupport;
import com.submaster.submasterserver.api.controller.bank.BankController;
import com.submaster.submasterserver.api.controller.bank.request.BankRequest;
import com.submaster.submasterserver.api.service.bank.BankReadService;
import com.submaster.submasterserver.api.service.bank.BankService;
import com.submaster.submasterserver.api.service.bank.request.BankServiceRequest;
import com.submaster.submasterserver.api.service.bank.response.BankFindAllResponse;
import com.submaster.submasterserver.api.service.bank.response.BankFindNameResponse;
import com.submaster.submasterserver.api.service.bank.response.BankResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankControllerDocsTest extends RestDocsSupport {
    private final BankReadService bankReadService = mock(BankReadService.class);
    private final BankService bankService = mock(BankService.class);

    @Override
    protected Object initController() {
        return new BankController(bankService, bankReadService);
    }

    @DisplayName("은행 저장 API")
    @Test
    void save() throws Exception {
        // given
        BankRequest request = BankRequest.builder()
                .bankName("kb")
                .imagePath("/test/test.jpg")
                .build();

        given(bankService.save(any(BankServiceRequest.class)))
                .willReturn(BankResponse.builder()
                        .id(1)
                        .bankName("kb")
                        .imagePath("/test/test.jpg")
                        .build()
                );

        // when // then
        mockMvc.perform(
                        post("/api/v1/bank")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("bank-save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("bankName").type(JsonFieldType.STRING)
                                        .description("은행명"),
                                fieldWithPath("imagePath").type(JsonFieldType.STRING)
                                        .description("이미지경로")
                        ),
                        responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("httpStatus").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메세지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("은행 key"),
                                fieldWithPath("data.bankName").type(JsonFieldType.STRING)
                                        .description("은행명"),
                                fieldWithPath("data.imagePath").type(JsonFieldType.STRING)
                                        .description("이미지경로")
                        )
                ));
    }

    @DisplayName("은행 전체 조회 API")
    @Test
    void findAll() throws Exception {
        // given
        given(bankReadService.findAll())
                .willReturn(BankFindAllResponse.builder()
                        .bankResponses(List.of(
                                BankResponse.builder()
                                        .id(1)
                                        .bankName("kb")
                                        .imagePath("/test/test.jpg")
                                        .build(),
                                BankResponse.builder()
                                        .id(1)
                                        .bankName("shinhan")
                                        .imagePath("/test/test.jpg")
                                        .build()))
                        .build()
                );

        // when // then
        mockMvc.perform(
                        get("/api/v1/bank")
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("bank-find",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("httpStatus").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메세지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.bankResponses[].id").type(JsonFieldType.NUMBER)
                                        .description("은행 key"),
                                fieldWithPath("data.bankResponses[].bankName").type(JsonFieldType.STRING)
                                        .description("은행명"),
                                fieldWithPath("data.bankResponses[].imagePath").type(JsonFieldType.STRING)
                                        .description("이미지경로")
                        )
                ));
    }

    @DisplayName("은행 이름 조회 API")
    @Test
    void findByName() throws Exception {
        // given
        given(bankReadService.findByBankNameContaining(any(String.class)))
                .willReturn(BankFindNameResponse.builder()
                        .bankResponses(List.of(
                                BankResponse.builder()
                                        .id(1)
                                        .bankName("kb")
                                        .imagePath("/test/test.jpg")
                                        .build(),
                                BankResponse.builder()
                                        .id(1)
                                        .bankName("shinhan")
                                        .imagePath("/test/test.jpg")
                                        .build()))
                        .build()
                );

        // when // then
        mockMvc.perform(
                        get("/api/v1/bank/{bankName}", "KB")
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("bank-findName",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("httpStatus").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메세지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.bankResponses[].id").type(JsonFieldType.NUMBER)
                                        .description("은행 key"),
                                fieldWithPath("data.bankResponses[].bankName").type(JsonFieldType.STRING)
                                        .description("은행명"),
                                fieldWithPath("data.bankResponses[].imagePath").type(JsonFieldType.STRING)
                                        .description("이미지경로")
                        )
                ));
    }
}
