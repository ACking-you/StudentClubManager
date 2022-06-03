package service;

import model.dao.ClubDAO;
import model.entity.Club;
import util.CharacterUtil;

import java.util.Comparator;
import java.util.List;

public class ClubService {
    public static void AddClub(Club club) throws RuntimeException {
        if (checkClub(club) != 0) {
            throw new RuntimeException("club����������");
        }
        if (ClubDAO.getInstance().IsClubExist(club.getName())) {
            throw new RuntimeException("���������Ѿ�����");
        }

        ClubDAO.getInstance().AddClub(club);
    }

    public static void AddClubList(List<Club> clubList) throws RuntimeException {
        if (clubList == null || clubList.isEmpty()) {
            throw new RuntimeException("clubList����Ϊ��");
        }
        ClubDAO.getInstance().AddClubList(clubList);
    }

    public static void UpdateClub(Club club) throws RuntimeException {
        if (checkClub(club) != 0) {
            throw new RuntimeException("club����������");
        }
        if (isClubExist(club.getId())) {
            throw new RuntimeException("club������");
        }
        //�������������ظ�������£�����ʱ�������ظ����򷵻ش���
        if (!ClubDAO.getInstance().IsSelfDuplicate(club.getId(), club.getName()) && ClubDAO.getInstance().IsClubExist(club.getName())) {
            throw new RuntimeException("club�����ظ�");
        }
        ClubDAO.getInstance().UpdateClub(club);
    }

    public static void DeleteClub(Club club) throws RuntimeException {
        if (checkClub(club) != 0) {
            throw new RuntimeException("club����������");
        }
        if (isClubExist(club.getId())) {
            throw new RuntimeException("club������");
        }
        ClubDAO.getInstance().DeleteClub(club.getId());
    }

    public static List<Club> QueryClubsByName(String name) throws RuntimeException {
        if (checkName(name) != 0) {
            throw new RuntimeException("���ֲ���������");
        }
        return ClubDAO.getInstance().QueryClubsByName(name);
    }

    public static List<Club> QueryClubByMemberId(int memberId) throws RuntimeException {
        if (ClubMemberService.isClubMemberExist(memberId)) {
            throw new RuntimeException("memberId������");
        }
        return ClubDAO.getInstance().QueryClubByMemberId(memberId);
    }

    public static List<Club> QueryAllClub() {
        return ClubDAO.getInstance().QueryAllClub();
    }

    //����orderֵ���ذ�memberCount����Ľ����true˳��false����
    public static List<Club> QueryAllClubByOrder(boolean order) {
        List<Club> list = QueryAllClub();
        if (order) {
            list.sort(Comparator.comparingInt(Club::getMemberCount));
        } else {
            list.sort((a, b) -> b.getMemberCount() - a.getMemberCount());
        }
        return list;
    }

    //start to check
    public static int checkName(String name) {
        //TODO �������ֻ�ܺ��к��ֻ���Ӣ���ַ�
        if (!CharacterUtil.isValidNameString(name)) {
            return -1;
        }
        return 0;
    }

    private static int checkClub(Club club) {
        //TODO �Ϸ�����0������ֵ���Ϸ�
        if (!club.checkStrLen()) {
            return -1;
        }
        if (checkName(club.getName()) != 0) {
            return -1;
        }
        return 0;
    }

    static boolean isClubExist(int id) {
        //TODO ����id�ж����club�Ƿ����
        return !ClubDAO.getInstance().IsClubExist(id);
    }
}
