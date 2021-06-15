package com.example.springmvc.controllers;

import com.example.springmvc.controllers.dto.CreditContractRequestDTO;
import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import com.example.springmvc.repositories.specifications.CreditSpecs;
import com.example.springmvc.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/credits")
public class MainController {

    private final CreditService creditService;

    @Autowired
    public MainController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public String showCredits(Model model,
                              @RequestParam(value = "phone", required = false) Long phone,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              @RequestParam(value = "middle_name", required = false) String middle_name,
                              @RequestParam(value = "passport_series", required = false) Integer passport_series,
                              @RequestParam(value = "passport_number", required = false) Integer passport_number) {

        Specification<Credit> filter = Specification.where(null);
        if (phone != null) filter = filter.and(CreditSpecs.phoneContains(phone));
        if (name != null) filter = filter.and(CreditSpecs.nameContains(name));
        if (surname != null) filter = filter.and(CreditSpecs.surnameContains(surname));
        if (middle_name != null) filter = filter.and(CreditSpecs.middleNameContains(middle_name));
        if (passport_series != null) filter = filter.and(CreditSpecs.passportSeriesContains(passport_series));
        if (passport_number != null) filter = filter.and(CreditSpecs.passportNumberContains(passport_number));
        List<Credit> credits = creditService.getCreditsWithFiltering(filter);
        model.addAttribute("credits", credits);
        model.addAttribute("creditContract", creditService.getAllCreditContracts());
        model.addAttribute("phone", phone);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("middle_name", middle_name);
        model.addAttribute("passport_series", passport_series);
        model.addAttribute("passport_number", passport_number);
        return "creditsPage";


    }

    @GetMapping("/createCredit")
    public String createCredit(Model model) {
        Credit credit = new Credit();
        model.addAttribute("credit", credit);
        return "createCredits";
    }

    @PostMapping("/createCredit")
    public String createCredit(@ModelAttribute(value = "credit") Credit credit) {
        creditService.makeRequestOnCredit(credit);
        return "redirect:/credits";
    }

    @GetMapping("/signContract/{contractId}")
    public String signContract(@PathVariable(name = "contractId") Long contractId,
                               Model model) {
        CreditContract creditContract = creditService.getCreditContractById(contractId);
        Credit credit = creditService.getCreditByCreditContract(creditContract);
        CreditContractRequestDTO creditContractRequestDTO = new CreditContractRequestDTO(credit, contractId);

        model.addAttribute("creditContract", creditContractRequestDTO);
        return "signContract";
    }

    @PostMapping("/signContract")
    public String signContract(@ModelAttribute(name = "creditContract") CreditContractRequestDTO creditContract) {

        CreditContract updatedCreditContract = creditService.getCreditContractById(creditContract.getCreditContractId());
        creditService.signContract(updatedCreditContract, creditContract.getConfirmStatus(), creditContract.getTime());
        return "redirect:/credits";
    }

    @GetMapping("/showCreditsWithStatusTrue")
    public String showCreditsWithStatusTrue(Model model) {

        model.addAttribute("credits", creditService.findCreditsByStatusIsTrue());
        return "trueCredits";
    }

    @GetMapping("/showCreditsContractsWithCreditStatusTrue")
    public String showCreditContractWithCreditStatusIsTrue(Model model) {

        model.addAttribute("creditContract", creditService.findCreditContractByCreditStatusIsTrue());
        return "trueCreditContracts";
    }
}
