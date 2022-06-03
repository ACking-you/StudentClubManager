package service;

import model.dao.ClubMemberDAO;
import model.entity.ClubMember;

import java.util.List;
import java.util.Objects;

public class ClubMemberService {
    public static void AddClubMember(int clubId, ClubMember member) throws RuntimeException {
        if (ClubService.isClubExist(clubId)) {
            throw new RuntimeException("���Ų�����");
        }
        String s = checkClubMember(member);
        if (s != null) {
            throw new RuntimeException(s);
        }
        try {
            ClubMemberDAO.getInstance().AddClubMember(clubId, member);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("ѧ�Ų����ظ���");
        }
    }

    //��һ���˼��뵽һ��������
    public static void AddClubMemberList(int clubId, List<ClubMember> clubMemberList) throws RuntimeException {
        if (ClubService.isClubExist(clubId)) {
            throw new RuntimeException("���Ų�����");
        }
        for (ClubMember member : clubMemberList) {
            String s = checkClubMember(member);
            if (s != null) {
                throw new RuntimeException(s);
            }
        }
        try {
            ClubMemberDAO.getInstance().AddClubMemberList(clubId, clubMemberList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("ѧ�Ų����ظ���");
        }
    }

    public static void UpdateClubMember(ClubMember member) throws RuntimeException {
        String s = checkClubMember(member);
        if (s != null) {
            throw new RuntimeException(s);
        }
        try {
            ClubMemberDAO.getInstance().UpdateClubMember(member);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("ѧ�Ų����ظ���");
        }
    }

    public static List<ClubMember> QueryClubMemberByName(int clubId, String name) throws RuntimeException {
        if (ClubService.checkName(name) != 0) {
            throw new RuntimeException("���ֲ��Ϸ�");
        }
        if (ClubService.isClubExist(clubId)) {
            throw new RuntimeException("���Ų�����");
        }
        return ClubMemberDAO.getInstance().QueryClubMemberByName(clubId, name);
    }


    public static void DeleteClubMemberById(int clubId, int id) throws RuntimeException {
        if (ClubService.isClubExist(clubId)) {
            throw new RuntimeException("���ֲ�������");
        }
        if (isClubMemberExist(id)) {
            throw new RuntimeException("���ֲ���Ա������");
        }

        ClubMemberDAO.getInstance().DeleteClubMemberById(clubId, id);
    }

    public static List<ClubMember> QueryClubMembersByClubId(int clubId) throws RuntimeException {
        if (ClubService.isClubExist(clubId)) {
            throw new RuntimeException("���ֲ�������");
        }

        return ClubMemberDAO.getInstance().QueryClubMemberByClubId(clubId);
    }

    //start to check
    private static String checkClubMember(ClubMember member) {
        //TODO ���clubMember
        if (!member.checkStrLen()) {
            return "�ַ������ȼ�����";
        }
        if (ClubService.checkName(member.getName()) != 0) {
            return "����ֻ����дӢ�Ļ������ַ���������";
        }
        if (!(Objects.equals(member.getSex(), "��") || Objects.equals(member.getSex(), "Ů"))) {
            return "�Ա�ֻ��Ϊ��Ů";
        }
        return null;
    }

    static boolean isClubMemberExist(int id) {
        //TODO �ж��Ƿ����
        return !ClubMemberDAO.getInstance().IsClubMemberExist(id);
    }
}
