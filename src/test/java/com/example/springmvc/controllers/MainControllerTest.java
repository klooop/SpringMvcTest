package com.example.springmvc.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.controllers.dto.CreditContractRequestDTO;
import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import com.example.springmvc.repositories.CreditContractRepository;
import com.example.springmvc.repositories.CreditRepository;
import com.example.springmvc.services.CreditService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {MainController.class})
@ExtendWith(SpringExtension.class)
public class MainControllerTest {
    @MockBean
    private CreditService creditService;

    @Autowired
    private MainController mainController;

    @Test
    public void testCreateCredit() throws Exception {
        doNothing().when(this.creditService).makeRequestOnCredit((com.example.springmvc.entities.Credit) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/credits/createCredit");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credit"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/credits"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/credits"));
    }

    @Test
    public void testSignContract() {
        Credit credit = new Credit();
        credit.setWork_organization("Work organization");
        credit.setIs_married("Is married");
        credit.setSum(1);
        credit.setName("Name");
        credit.setWork_period_months(1);
        credit.setAddress("42 Main St");
        credit.setPassport_series(1);
        credit.setSurname("Doe");
        credit.setPhone(1L);
        credit.setStatus(true);
        credit.setCreditContract(new CreditContract());
        credit.setWork_position("Work position");
        credit.setMiddle_name("Middle name");
        credit.setApprovedSum(1);
        credit.setId(123L);
        credit.setDays(1);
        credit.setPassport_number(10);

        CreditContract creditContract = new CreditContract();
        creditContract.setContractId(123L);
        creditContract.setTime(LocalDate.ofEpochDay(1L));
        creditContract.setCredit(credit);
        creditContract.setCreditStatus("Credit Status");
        creditContract.setCreditSum(1);

        Credit credit1 = new Credit();
        credit1.setWork_organization("Work organization");
        credit1.setIs_married("Is married");
        credit1.setSum(1);
        credit1.setName("Name");
        credit1.setWork_period_months(1);
        credit1.setAddress("42 Main St");
        credit1.setPassport_series(1);
        credit1.setSurname("Doe");
        credit1.setPhone(1L);
        credit1.setStatus(true);
        credit1.setCreditContract(creditContract);
        credit1.setWork_position("Work position");
        credit1.setMiddle_name("Middle name");
        credit1.setApprovedSum(1);
        credit1.setId(123L);
        credit1.setDays(1);
        credit1.setPassport_number(10);

        CreditContract creditContract1 = new CreditContract();
        creditContract1.setContractId(123L);
        creditContract1.setTime(LocalDate.ofEpochDay(1L));
        creditContract1.setCredit(credit1);
        creditContract1.setCreditStatus("Credit Status");
        creditContract1.setCreditSum(1);

        Credit credit2 = new Credit();
        credit2.setWork_organization("Work organization");
        credit2.setIs_married("Is married");
        credit2.setSum(1);
        credit2.setName("Name");
        credit2.setWork_period_months(1);
        credit2.setAddress("42 Main St");
        credit2.setPassport_series(1);
        credit2.setSurname("Doe");
        credit2.setPhone(1L);
        credit2.setStatus(true);
        credit2.setCreditContract(creditContract1);
        credit2.setWork_position("Work position");
        credit2.setMiddle_name("Middle name");
        credit2.setApprovedSum(1);
        credit2.setId(123L);
        credit2.setDays(1);
        credit2.setPassport_number(10);
        CreditRepository creditRepository = mock(CreditRepository.class);
        when(creditRepository.findCreditByCreditContract((CreditContract) any())).thenReturn(credit2);

        Credit credit3 = new Credit();
        credit3.setWork_organization("Work organization");
        credit3.setIs_married("Is married");
        credit3.setSum(1);
        credit3.setName("Name");
        credit3.setWork_period_months(1);
        credit3.setAddress("42 Main St");
        credit3.setPassport_series(1);
        credit3.setSurname("Doe");
        credit3.setPhone(1L);
        credit3.setStatus(true);
        credit3.setCreditContract(new CreditContract());
        credit3.setWork_position("Work position");
        credit3.setMiddle_name("Middle name");
        credit3.setApprovedSum(1);
        credit3.setId(123L);
        credit3.setDays(1);
        credit3.setPassport_number(10);

        CreditContract creditContract2 = new CreditContract();
        creditContract2.setContractId(123L);
        creditContract2.setTime(LocalDate.ofEpochDay(1L));
        creditContract2.setCredit(credit3);
        creditContract2.setCreditStatus("Credit Status");
        creditContract2.setCreditSum(1);

        Credit credit4 = new Credit();
        credit4.setWork_organization("Work organization");
        credit4.setIs_married("Is married");
        credit4.setSum(1);
        credit4.setName("Name");
        credit4.setWork_period_months(1);
        credit4.setAddress("42 Main St");
        credit4.setPassport_series(1);
        credit4.setSurname("Doe");
        credit4.setPhone(1L);
        credit4.setStatus(true);
        credit4.setCreditContract(creditContract2);
        credit4.setWork_position("Work position");
        credit4.setMiddle_name("Middle name");
        credit4.setApprovedSum(1);
        credit4.setId(123L);
        credit4.setDays(1);
        credit4.setPassport_number(10);

        CreditContract creditContract3 = new CreditContract();
        creditContract3.setContractId(123L);
        creditContract3.setTime(LocalDate.ofEpochDay(1L));
        creditContract3.setCredit(credit4);
        creditContract3.setCreditStatus("Credit Status");
        creditContract3.setCreditSum(1);
        CreditContractRepository creditContractRepository = mock(CreditContractRepository.class);
        when(creditContractRepository.findById((Long) any())).thenReturn(Optional.<CreditContract>of(creditContract3));
        MainController mainController = new MainController(new CreditService(creditRepository, creditContractRepository));
        ConcurrentModel concurrentModel = new ConcurrentModel();
        assertEquals("signContract", mainController.signContract(123L, concurrentModel));
        verify(creditRepository).findCreditByCreditContract((CreditContract) any());
        verify(creditContractRepository).findById((Long) any());
        assertEquals("42 Main St", ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getAddress());
        assertTrue(((CreditContractRequestDTO) concurrentModel.get("creditContract")).isStatus());
        assertEquals("Work position",
                ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getWork_position());
        assertEquals(1, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getWork_period_months());
        assertEquals("Work organization",
                ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getWork_organization());
        assertEquals("Doe", ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getSurname());
        assertEquals(1, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getApprovedSum());
        assertEquals(123L,
                ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getCreditContractId().longValue());
        assertEquals("Name", ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getName());
        assertEquals(10, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getPassport_number());
        assertEquals(1, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getDays());
        assertEquals(1, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getPassport_series());
        assertEquals(1L, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getPhone().longValue());
        assertEquals("Еще не подписано",
                ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getConfirmStatus());
        assertEquals("Is married", ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getIs_married());
        assertEquals(1, ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getSum());
        assertEquals("Middle name", ((CreditContractRequestDTO) concurrentModel.get("creditContract")).getMiddle_name());
    }

    @Test
    public void testCreateCredit2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits/createCredit");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credit"))
                .andExpect(MockMvcResultMatchers.view().name("createCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("createCredits"));
    }

    @Test
    public void testCreateCredit3() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits/createCredit");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credit"))
                .andExpect(MockMvcResultMatchers.view().name("createCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("createCredits"));
    }

    @Test
    public void testShowCreditContractWithCreditStatusIsTrue() throws Exception {
        when(this.creditService.findCreditContractByCreditStatusIsTrue()).thenReturn(new ArrayList<CreditContract>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/credits/showCreditsContractsWithCreditStatusTrue");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract"))
                .andExpect(MockMvcResultMatchers.view().name("trueCreditContracts"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCreditContracts"));
    }

    @Test
    public void testShowCreditContractWithCreditStatusIsTrue2() throws Exception {
        when(this.creditService.findCreditContractByCreditStatusIsTrue()).thenReturn(new ArrayList<CreditContract>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/credits/showCreditsContractsWithCreditStatusTrue");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract"))
                .andExpect(MockMvcResultMatchers.view().name("trueCreditContracts"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCreditContracts"));
    }

    @Test
    public void testShowCredits() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCredits2() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits").param("middle_name", "foo");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits", "middle_name"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCredits3() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits").param("name", "foo");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits", "name"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCredits4() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("passport_number", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits", "passport_number"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCredits5() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("passport_series", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits", "passport_series"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCredits6() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("phone", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits", "phone"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCredits7() throws Exception {
        when(this.creditService.getAllCreditContracts()).thenReturn(new ArrayList<CreditContract>());
        when(this.creditService.getCreditsWithFiltering((org.springframework.data.jpa.domain.Specification<Credit>) any()))
                .thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits").param("surname", "foo");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(8))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract", "credits", "surname"))
                .andExpect(MockMvcResultMatchers.view().name("creditsPage"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("creditsPage"));
    }

    @Test
    public void testShowCreditsWithStatusTrue() throws Exception {
        when(this.creditService.findCreditsByStatusIsTrue()).thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits/showCreditsWithStatusTrue");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credits"))
                .andExpect(MockMvcResultMatchers.view().name("trueCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCredits"));
    }

    @Test
    public void testShowCreditsWithStatusTrue2() throws Exception {
        when(this.creditService.findCreditsByStatusIsTrue()).thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits/showCreditsWithStatusTrue");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credits"))
                .andExpect(MockMvcResultMatchers.view().name("trueCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCredits"));
    }
}

