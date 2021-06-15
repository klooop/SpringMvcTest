package com.example.springmvc.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
@Data
@Getter
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "passport_series")
    private int passport_series;

    @Column(name = "passport_number")
    private int passport_number;

    @Column(name = "is_married")
    private String is_married;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "work_period_months")
    private int work_period_months;

    @Column(name = "work_position")
    private String work_position;

    @Column(name = "work_organization")
    private String work_organization;

    @Column(name = "sum")
    private int sum;

    @Column(name = "status")
    private boolean status;

    @Column(name = "answer_days")
    private int days;

    @Column(name = "approved_sum")
    private int approvedSum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_decision_id", referencedColumnName = "contract_id")
    private CreditContract creditContract;
}
