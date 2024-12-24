package com.submaster.submasterserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submaster.submasterserver.api.controller.account.AccountController;
import com.submaster.submasterserver.api.controller.bank.BankController;
import com.submaster.submasterserver.api.controller.login.LoginController;
import com.submaster.submasterserver.api.controller.subcription.SubscriptionController;
import com.submaster.submasterserver.api.controller.subscriptionPrice.SubscriptionPriceController;
import com.submaster.submasterserver.api.controller.user.UserController;
import com.submaster.submasterserver.api.service.account.AccountReadService;
import com.submaster.submasterserver.api.service.account.AccountService;
import com.submaster.submasterserver.api.service.bank.BankReadService;
import com.submaster.submasterserver.api.service.bank.BankService;
import com.submaster.submasterserver.api.service.login.LoginService;
import com.submaster.submasterserver.api.service.subscription.SubscriptionReadService;
import com.submaster.submasterserver.api.service.subscription.SubscriptionService;
import com.submaster.submasterserver.api.service.subscriptionPrice.SubscriptionPriceReadService;
import com.submaster.submasterserver.api.service.subscriptionPrice.SubscriptionPriceService;
import com.submaster.submasterserver.api.service.user.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(controllers = {
        SubscriptionController.class,
        SubscriptionPriceController.class,
        BankController.class,
        AccountController.class,
        LoginController.class,
        UserController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected EntityManager entityManager;

    @MockBean
    protected SubscriptionService subscriptionService;

    @MockBean
    protected SubscriptionReadService subscriptionReadService;

    @MockBean
    protected SubscriptionPriceService subscriptionPriceService;

    @MockBean
    protected SubscriptionPriceReadService subscriptionPriceReadService;

    @MockBean
    protected BankService bankService;

    @MockBean
    protected BankReadService bankReadService;

    @MockBean
    protected AccountReadService accountReadService;

    @MockBean
    protected AccountService accountService;

    @MockBean
    protected LoginService loginService;

    @MockBean
    protected UserService userService;

}
