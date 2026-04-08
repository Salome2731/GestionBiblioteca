package com.ottersal.gestionbiblioteca.repository;

import org.springframework.stereotype.Component;

@Component
public class ClientRepositoryHelper {

    public String listClients(){
        return " select id, first_name, last_name, dni, email, phone, status from client ";
    }

    public String insertClient(){
        return " insert into client (id, first_name, last_name, dni, email, phone, status) values (?, ?, ?, ?, ?,?,?) ";
    }

    public String updateClient(){
        return " update client set first_name = ?, last_name = ?, dni = ?, email = ?, phone = ?, status = ? where id = ? ";
    }

    public String getStudentById(){
        return " Select id, first_name, last_ame, dni, email, phone, status from client where id = ? ";
    }

    public String deleteClient(){
        return " Delete from client where id = ? ";
    }


}