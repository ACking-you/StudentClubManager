package model.dao;

import model.MybatisUtil;
import model.entity.Club;
import model.mapper.ClubMapper;
import model.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubDAO {
    private final SqlSession sqlSession;
    private final ClubMapper clubMapper;

    private ClubDAO(boolean autoCommit) {
        sqlSession = MybatisUtil.getSession(autoCommit);
        clubMapper = sqlSession.getMapper(ClubMapper.class);
    }
    public static ClubDAO getInstance() {
        return  new ClubDAO(false);
    }
    public void AddClub(Club club){
        clubMapper.addClub(club);
        sqlSession.commit();
    }
    public void UpdateClub(Club club){
        clubMapper.updateClub(club);
        sqlSession.commit();
    }

    //��������ģ����ѯ
    public List<Club> QueryClubsByName(String name){
       return clubMapper.queryClubsByName(name);
    }

    //���ݳ�Աid������вμӵ�club
    public List<Club> QueryClubByMemberId(int memberId){
        return clubMapper.queryClubsByMemberId(memberId);
    }

    public boolean IsClubExist(int id){
        return clubMapper.isClubExistById(id)>0;
    }
}
