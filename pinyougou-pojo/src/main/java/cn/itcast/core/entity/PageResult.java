package cn.itcast.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 实现序列化接口的场景
 * 1、网络传输
 * 2、缓存
 *
 * 数据：二进制的数据
 * 1、传输效率高
 * 2、数据可以共享（不同语言开发的系统）
 * 3、反序列化：将磁盘的数据加载到内存中（容灾-备份）
 */
public class PageResult implements Serializable{
    private long total; //总条数
    private List rows;  //结果集

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
