package com.biz.memo.domain;

import lombok.Builder;

@Builder
public class MemoVO {

    private String m_date;
    private String m_time;
    private String m_text;

    public MemoVO() {
    }

    public MemoVO(String m_date, String m_time, String m_text) {
        this.m_date = m_date;
        this.m_time = m_time;
        this.m_text = m_text;
    }

    public String getM_date() {
        return m_date;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public String getM_time() {
        return m_time;
    }

    public void setM_time(String m_time) {
        this.m_time = m_time;
    }

    public String getM_text() {
        return m_text;
    }

    public void setM_text(String m_text) {
        this.m_text = m_text;
    }

    @Override
    public String toString() {
        return "MemoVO{" +
                "m_date='" + m_date + '\'' +
                ", m_time='" + m_time + '\'' +
                ", m_text='" + m_text + '\'' +
                '}';
    }
}
