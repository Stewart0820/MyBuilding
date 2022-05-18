package com.stewart.building.common;

import java.util.List;

/**
 *
 * @author Stewart
 * @date 2017/5/3 0003
 * @ Description:
 */
public class R {
    //状态,成功：1，失败：0
    private int status;
    //消息
    private String msg;
    //数据
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public R() {
    }

    public R(int status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public R(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public R(ResultStatus resultStatus) {
        this.status = resultStatus.getStatus();
        this.msg = resultStatus.getMsg();
        this.data = null;
    }

    public R(ResultStatus resultStatus, Object data) {
        this.status = resultStatus.getStatus();
        this.msg = resultStatus.getMsg();
        this.data = data;
    }
    public R(ResultStatus resultStatus, String str) {
        this.status = resultStatus.getStatus();
        this.msg = resultStatus.getMsg() + "：[" + str +"]";
    }

    //具体成功信息，带数据(2个参数)
    public static R ok(ResultStatus ok, Object data) {
        //查询集合为空的统一提示处理
        if(data instanceof List){
            List list = (List)data;

        }
        return new R(ok, data);
    }
    //成功信息，不带数据。
    public static R ok() {
        return new R(ResultStatus.SUCCESS);
    }
    //具体成功信息，不带数据。
    public static R ok(ResultStatus ok) {
        return new R(ok);
    }
    //具体错误信息。
    public static R error(ResultStatus error) {
        return new R(error);
    }
    //具体错误信息(加附带信息)。
    public static R error(ResultStatus error, String str) {
        return new R(error,str);
    }

    /** 格式化占位符*/
    public static R format(ResultStatus rs, String ...value){
        StringBuffer sbMsg = new StringBuffer(rs.getMsg());
        int index = 0;
        for(int i=0,l=value.length;i<l;i++){
            String old = "{"+i+"}";
            index = sbMsg.indexOf(old,index);
            sbMsg = sbMsg.replace(index,index+old.length(),value[i]);
        }
        return new R(rs.getStatus(),sbMsg.toString());
    }
    /** 首部添加信息*/
    public static R appendBegin(R r, String addMsg){
        r.setMsg(addMsg+","+r.getMsg());
        return r;
    }
    /** 尾部添加信息*/
    public static R appendEnd(R r, String addMsg){
        r.setMsg(r.getMsg()+","+addMsg);
        return r;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
