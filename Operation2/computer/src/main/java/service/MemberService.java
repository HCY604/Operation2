package service;

import model.Member;

public interface MemberService {
    void insert(Member member);
    Member login(String username, String password);
}