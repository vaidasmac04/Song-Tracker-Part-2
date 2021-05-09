package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Song;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface SongMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SONG
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SONG
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    int insert(Song record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SONG
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    Song selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SONG
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    List<Song> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SONG
     *
     * @mbg.generated Tue Apr 13 13:12:35 EEST 2021
     */
    int updateByPrimaryKey(Song record);
}