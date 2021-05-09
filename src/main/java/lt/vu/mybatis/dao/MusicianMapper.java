package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Musician;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface MusicianMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.MUSICIAN
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.MUSICIAN
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    int insert(Musician record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.MUSICIAN
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    Musician selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.MUSICIAN
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    List<Musician> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.MUSICIAN
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    int updateByPrimaryKey(Musician record);
}