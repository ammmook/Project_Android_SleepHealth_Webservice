package com.springmvc.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.springmvc.conn.HibernateConnection;
import com.springmvc.bean.Sleep;

public class SleepManager {
    public String insertSleep(List<Sleep> sleeps) {
        try {
            SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            // บันทึกข้อมูลการนอนทั้งหมด
            for (Sleep sleep : sleeps) {
                session.saveOrUpdate(sleep);  // บันทึกการนอนทีละตัว
            }

            t.commit();
            session.close();
            return "successfully saved";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to save sleep data";
        }
    }

    // ดึงข้อมูลการนอนตาม username
    public List<Sleep> getSleepDataByUsername(String username) {
        try {
            SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            // ใช้ Hibernate query เพื่อดึงข้อมูลการนอนตาม username
            List<Sleep> sleeps = session.createQuery("FROM Sleep WHERE username = :username")
                    .setParameter("username", username)
                    .list();
            session.close();
            return sleeps;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Sleep> getSleepDataByDate(String date) {
        try {
            SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            // ค้นหาข้อมูลการนอนที่ตรงกับวันที่
            List<Sleep> sleeps = session.createQuery("FROM Sleep WHERE date = :date")
                    .setParameter("date", date)
                    .list();
            session.close();
            return sleeps;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean deleteSleepbyUsernameandDate(String username, String date) {
        try {
            SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            // คำสั่ง HQL สำหรับลบข้อมูลการนอนที่ตรงกับวันที่และชื่อผู้ใช้
            int deletedCount = session.createQuery("DELETE FROM Sleep WHERE username = :username AND date = :date")
                    .setParameter("username", username)
                    .setParameter("date", date)
                    .executeUpdate();

            t.commit();
            session.close();

            return deletedCount > 0;  // คืนค่า true หากลบข้อมูลสำเร็จ
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // คืนค่า false หากเกิดข้อผิดพลาด
        }
    }


}
