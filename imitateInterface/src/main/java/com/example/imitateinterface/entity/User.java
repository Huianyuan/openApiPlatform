package com.example.imitateinterface.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 *
 */
@Data
public class User implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;
}