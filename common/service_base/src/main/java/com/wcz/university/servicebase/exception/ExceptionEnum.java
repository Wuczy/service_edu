package com.wcz.university.servicebase.exception;

public enum ExceptionEnum {

    FILE_UPLOAD_ERROR_01(1001,"文件上传失败！"),
    FILE_UPLOAD_ERROR_02(1002,"上传文件为空！"),
    FILE_UPLOAD_ERROR_03(1003,"文件名字为空！"),
    FILE_UPLOAD_ERROR_04(1004,"文件类型错误，请上传'jpg','png','gif'文件！"),
    EXCEL_UPLOAD_ERROR_01(2001,"excel表格名字为空！"),
    EXCEL_UPLOAD_ERROR_02(2002,"excel表格内容为空！"),
    EXCEL_UPLOAD_ERROR_03(2003,"文件类型错误，请上传'xlsx','xls'文件！"),
    SUBJECT_ADD_ERROR_01(3001,"添加课程失败，请稍后再试！"),
    SUBJECT_ADD_ERROR_02(3002,"一级课程必填项未填，请填写必填项！"),
    SUBJECT_ADD_ERROR_03(3002,"二级课程必填项未填，请填写必填项！")

    ;
    private final Integer code;
    private final String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
