package com.example.stategrid.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonPage {

    private long currentPage;
    private long pageCount;
    private long total;
    private long pageSize;
}

