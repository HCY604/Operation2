package dao;

import model.Member;

public interface MemberDao {
    void insert(Member member);
    Member login(String username, String password);
}
