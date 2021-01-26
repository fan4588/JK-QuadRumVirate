package com.nephew.jk.manager;

import java.util.ArrayList;
import java.util.List;

public class LazyCode {

    /**
     * 批量新增
     *
     * @return
     */
    public int insertBatch(int a) {
        List<Object> list = new ArrayList<>();
        int dataLimitNum = 200;// 限制分批条数
        int i = 0;
        while (i < list.size()) {
            int startIndex = i;
            int endIndex = i + dataLimitNum;
            if (endIndex >= list.size()) {
                endIndex = list.size();
            }
            //截取List
            List<Object> partList = list.subList(startIndex, endIndex);
            if (partList.isEmpty()) {
                break;
            }
            i = endIndex;
            //批量新增
            /*int sum = userMapper.insertBatch(partList);
            if (sum == 0) {
                //log.info("定时记录修改日志::" + JSONObject.toJSONString(ImageLogList));
                throw new BizException(MessageConstant.MESSAGE_0005.getMessage());
            }*/
        }

        //sql
        /*
        int insertBatch(List<ImageLogDO> imageLogList);

        <insert id="insertBatch" parameterType="com.sinochem.it.acs_image.domain.ImageLogDO" useGeneratedKeys="true" keyProperty="id">
                insert into image_log (id, uuid, gmt_created,
                user_created, gmt_modified, user_modified,
                deleted, version)
        values
        <foreach collection="list" item="item" index="index" separator=",">
        (
        #{item.id,jdbcType=INTEGER}, #{item.uuid,jdbcType=VARCHAR}, #{item.gmtCreated,jdbcType=TIMESTAMP},
        #{item.userCreated,jdbcType=BIGINT}, #{item.gmtModified,jdbcType=TIMESTAMP}, #{item.userModified,jdbcType=BIGINT},
        #{item.deleted,jdbcType=INTEGER}, #{item.version,jdbcType=INTEGER}
        )
        </foreach>
    </insert>*/

        return 0;
    }

}
