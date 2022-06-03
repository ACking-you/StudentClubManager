package model.dao;

import model.MybatisUtil;
import model.entity.Club;
import model.mapper.ClubMapper;
import model.mapper.ClubRelationMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubDAO {
    static ClubDAO dao = null;
    private final SqlSession sqlSession;
    private final ClubMapper clubMapper;
    private final ClubRelationMapper clubRelationMapper;

    private ClubDAO(boolean autoCommit) {
        sqlSession = MybatisUtil.getSession(autoCommit);
        clubMapper = sqlSession.getMapper(ClubMapper.class);
        clubRelationMapper = sqlSession.getMapper(ClubRelationMapper.class);
    }

    public static ClubDAO getInstance() {
        if (dao == null) {
            dao = new ClubDAO(false);
        }
        return dao;
    }

    public void AddClub(Club club) {
        clubMapper.addClub(club);
        sqlSession.commit();
    }

    public void AddClubList(List<Club> clubList) {
        for (Club club : clubList) {
            clubMapper.addClub(club);
        }
        sqlSession.commit();
    }

    public void UpdateClub(Club club) {
        clubMapper.updateClub(club);
        sqlSession.commit();
    }

    //ɾ�����ţ�������Ӧ�Ĺ�ϵ��
    public void DeleteClub(int clubId) {
        clubRelationMapper.deleteRelationsByClubId(clubId);
        clubMapper.deleteClub(clubId);
        sqlSession.commit();
    }

    //��������ģ����ѯ
    public List<Club> QueryClubsByName(String name) {
        return clubMapper.queryClubsByName(name);
    }

    //���ݳ�Աid������вμӵ�club
    public List<Club> QueryClubByMemberId(int memberId) {
        return clubMapper.queryClubsByMemberId(memberId);
    }

    public List<Club> QueryAllClub() {
        return clubMapper.queryAllClub();
    }

    public boolean IsClubExist(int id) {
        return clubMapper.isClubExistById(id) > 0;
    }

    public boolean IsClubExist(String name) {
        return clubMapper.isExistByName(name) > 0;
    }

    //����id��name�жϵ�ǰ�����Ƿ���ڣ�������ڣ���˵���������ظ�nameδ�����ģ���Ϊ�������ֶζ���Ψһ��
    //����ǲ����ڣ���˵��name���˸��ģ���ʱ����Ҫ����name����
    public boolean IsSelfDuplicate(int id, String name) {
        return clubMapper.countByDuplicate(id, name) > 0;
    }
}
