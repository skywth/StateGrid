package com.example.stategrid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;

/**
 * @author Li Ming
 * @e-mail liming.school@qq.com
 * @create 2022-10-15 1:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice {
    private Integer id;
    private String uuid;
    private Date date;
    private String notice_img_url;
    private String title;
    private String describe;
    private String detail;
    private Integer push_object;
}
