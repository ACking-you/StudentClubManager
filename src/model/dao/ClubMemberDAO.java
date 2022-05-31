package model.dao;

import model.MybatisUtil;
import model.entity.ClubMember;
import model.mapper.ClubMapper;
import model.mapper.ClubMemberMapper;
import model.mapper.ClubRelationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubMemberDAO {
    private final SqlSession sqlSession;
    private final ClubMemberMapper clubMemberMapper;
    private final ClubRelationMapper clubRelationMapper;
    private final ClubMapper clubMapper;
    private ClubMemberDAO(boolean autoCommit){
        sqlSession = MybatisUtil.getSession(autoCommit);
        clubMemberMapper = sqlSession.getMapper(ClubMemberMapper.class);
        clubRelationMapper = sqlSession.getMapper(ClubRelationMapper.class);
        clubMapper = sqlSession.getMapper(ClubMapper.class);
    }
    public static ClubMemberDAO getInstance(){
        return new ClubMemberDAO(false);
    }
    public void AddClubMember(int clubId, ClubMember clubMember){
        //��ӳ�Ա
        clubMemberMapper.addClubMember(clubMember);
        //�õ��ó�Ա��id
        int id = clubMemberMapper.queryIdFromStudentId(clubMember.getStudentId());
        //��Ӷ�Զ��ϵ
        clubRelationMapper.addRelations(clubId,id);
        //�������ŵ�count
        clubMapper.plusMemberCountById(clubId);
        sqlSession.commit();
    }
    public void UpdateClubMember(ClubMember clubMember){
        clubMemberMapper.updateClubMember(clubMember);
        sqlSession.commit();
    }

    //�����������������ֽ���ģ����ѯ
    public List<ClubMember> QueryClubMemberByName(int clubId, String name){
        return clubMemberMapper.queryClubMemberByName(clubId,name);
    }

    //��ѯ���ŵ�ȫ����Ա
    public List<ClubMember> QueryClubMemberByClubId(int clubId){
        return clubMemberMapper.queryClubMemberByClubId(clubId);
    }

    //ɾ�����ų�Ա��ͬʱ��ɾ����Ӧ�����Ź�ϵ
    public void DeleteClubMemberById(int clubId,int memberId){
        clubRelationMapper.deleteRelationsByMemberId(memberId);
        clubMapper.minusMemberCountById(clubId);
        clubMemberMapper.deleteClubMemberById(memberId);
        sqlSession.commit();
    }

    public boolean IsClubMemberExist(int id){
        return clubMemberMapper.isClubMemBerExistById(id)>0;
    }
}
