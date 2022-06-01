package service;

import model.dao.ClubMemberDAO;
import model.entity.ClubMember;

import java.util.List;
import java.util.Objects;

public class ClubMemberService {
    public static void AddClubMember(int clubId, ClubMember member)throws RuntimeException{
        if(!ClubService.isClubExist(clubId)){
            throw new RuntimeException("���Ų�����");
        }
        if (checkClubMember(member) != 0) {
            throw new RuntimeException("��Ա��Ϣ���Ϸ�");
        }
        ClubMemberDAO.getInstance().AddClubMember(clubId,member);
    }

    //��һ���˼��뵽һ��������
    public static void AddClubMemberList(int clubId,List<ClubMember> clubMemberList)throws RuntimeException{
        if(!ClubService.isClubExist(clubId)){
            throw new RuntimeException("���Ų�����");
        }
        for(ClubMember member:clubMemberList){
            if (checkClubMember(member) != 0) {
                throw new RuntimeException("��Ա��Ϣ���Ϸ�");
            }
        }

        ClubMemberDAO.getInstance().AddClubMemberList(clubId,clubMemberList);
    }
    public static void UpdateClubMember(ClubMember member)throws RuntimeException{
        if(checkClubMember(member)!=0){
            throw new RuntimeException("��Ա��Ϣ���Ϸ�");
        }

        ClubMemberDAO.getInstance().UpdateClubMember(member);
    }

    public static List<ClubMember> QueryClubMemberByName(int clubId,String name)throws RuntimeException{
        if(ClubService.checkName(name)!=0){
            throw new RuntimeException("���ֲ��Ϸ�");
        }
        if (!ClubService.isClubExist(clubId)) {
            throw  new RuntimeException("���Ų�����");
        }
        return ClubMemberDAO.getInstance().QueryClubMemberByName(clubId,name);
    }

    public static void DeleteClubMemberById(int clubId,int id)throws RuntimeException{
        if (!ClubService.isClubExist(clubId)) {
            throw new RuntimeException("���ֲ�������");
        }
        if(isClubMemberExist(id)){
            throw new RuntimeException("���ֲ���Ա������");
        }

        ClubMemberDAO.getInstance().DeleteClubMemberById(clubId,id);
    }

    public static List<ClubMember> QueryClubMembersByClubId(int clubId)throws RuntimeException{
        if (!ClubService.isClubExist(clubId)){
            throw new RuntimeException("���ֲ�������");
        }

        return ClubMemberDAO.getInstance().QueryClubMemberByClubId(clubId);
    }

    //start to check
    private static int checkClubMember(ClubMember member){
        //TODO ���clubMember
        if (!member.checkStrLen()){
            return -1;
        }
        if(ClubService.checkName(member.getName())!=0){
            return -1;
        }
        if(!(Objects.equals(member.getSex(), "��") || Objects.equals(member.getSex(), "Ů"))){
            return -1;
        }
        return 0;
    }

    static boolean isClubMemberExist(int id){
        //TODO �ж��Ƿ����
        return !ClubMemberDAO.getInstance().IsClubMemberExist(id);
    }
}
