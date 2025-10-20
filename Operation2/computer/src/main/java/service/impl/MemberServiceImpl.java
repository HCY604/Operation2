package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao = new MemberDaoImpl();

    @Override
    public void insert(Member member) {
        memberDao.insert(member);
    }

    @Override
    public Member login(String username, String password) {
        return memberDao.login(username, password);
    }
}
