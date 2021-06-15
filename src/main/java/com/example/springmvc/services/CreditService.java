package com.example.springmvc.services;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import com.example.springmvc.exception.CreditContractNotFoundException;
import com.example.springmvc.repositories.CreditContractRepository;
import com.example.springmvc.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final CreditContractRepository creditContractRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository, CreditContractRepository creditContractRepository) {
        this.creditRepository = creditRepository;
        this.creditContractRepository = creditContractRepository;
    }
    public List<CreditContract> findCreditContractByCreditStatusIsTrue(){
        return creditContractRepository.getCreditContractsByStatus();
    }


    public List<Credit> getAllCredits() {
        return (List<Credit>) creditRepository.findAll();
    }
    public List<CreditContract> getAllCreditContracts() {
        return (List<CreditContract>) creditContractRepository.findAll();
    }

    public CreditContract getCreditContractById(Long id) {
        return creditContractRepository.findById(id)
                .orElseThrow(()-> new CreditContractNotFoundException("Unable to find exception with id :"+id));
    }

    public List<Credit> getCreditsWithFiltering(Specification<Credit> specification) {
            return creditRepository.findAll(specification);
    }
    public Credit findByName(String name){
        return creditRepository.findByName(name);
    }
    public List<Credit> findCreditsByStatusIsTrue(){
       return   creditRepository.findCreditsByStatusIsTrue();
    }


    public Credit getCreditByCreditContract(CreditContract creditContract) {
        return creditRepository.findCreditByCreditContract(creditContract);
    }

    public void signContract(CreditContract creditContract, String confirmStatus, LocalDate time){
        if (confirmStatus.equals("on")) {
            creditContract.setCreditStatus("Подписано");
            creditContract.setTime(time);
        }
        else creditContract.setCreditStatus("Еще не подписано");
        creditContractRepository.save(creditContract);
    }


    public void makeRequestOnCredit(Credit credit) {
        Random random = new Random();
        boolean status=random.nextBoolean();
        credit.setStatus(status);
        credit.setDays(randomValue(30, 365));
        if (status){
            int approvedSum = randomValue(1, credit.getSum());
            credit.setApprovedSum(approvedSum);
            CreditContract creditContract = new CreditContract();
            creditContract.setCreditSum(approvedSum);
            credit.setCreditContract(creditContract);
            creditContract.setCreditStatus("Еще не подписано");
            creditContractRepository.save(creditContract);

        }

        else credit.setApprovedSum(0);
        creditRepository.save(credit);
    }
    private static int randomValue(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
