package model;

import model.dao.ClubMemberDAO;
import model.entity.ClubMember;

public class TestDb {

    public static void main(String[] args) {
//        Club club = new Club().setName("����").setCategory("ѧϰ��").setMemberCount(100).setId(2);
//        ClubDAO.getInstance().UpdateClub(club);
        ClubMember m = new ClubMember().
                setName("���").setAge(10).
                setMajor("�ҵ�רҵ").setInterest("�ҵ���Ȥ").
                setSex("Ů").setStudentId("B31").setId(1);
        System.out.println(ClubMemberDAO.getInstance().IsClubMemberExist(2));
    }

}
