package com.traillermovie.repository.loginRepository;

import com.traillermovie.model.AccountUser;

import java.util.List;

public interface ILoginRepository {
    List<AccountUser> getListAccountUser();
    AccountUser checkAccountUser(String username, String password);
    int checkUsernameRetrive(String name);
    void saveAccountRegister(String name, String password);
    int deleteUser (int id);
    List<AccountUser> getListAccountUserInAdmin();
}
