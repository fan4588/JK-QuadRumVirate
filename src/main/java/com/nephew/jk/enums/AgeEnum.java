package com.nephew.jk.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AgeEnum {

    ONE(1,"1"),
    TWO(2,"2");

    private Integer code;
    private String desc;

    AgeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static AgeEnum getByCode(Integer code){
        for(AgeEnum ageEnum: AgeEnum.values()){
            if (code == ageEnum.getCode()){
                return ageEnum;
            }
        }
        return null;
    }

}
