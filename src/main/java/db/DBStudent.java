package db;

import models.Course;
import models.Mentor;
import models.Student;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DBStudent {

    private static Session session;

    public static Course getCourseFromStudent(Student student){
        session = HibernateUtil.getSessionFactory().openSession();
        Course course = null;

        try{
            Criteria cr = session.createCriteria(Course.class);
            cr.createAlias("students", "student");
            cr.add(Restrictions.eq("student.id", student.getId()));
            course = (Course) cr.uniqueResult();
        }
        catch (HibernateException ex){
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return course;
    }

    public static Mentor getMentorForStudent(Student student){
        session = HibernateUtil.getSessionFactory().openSession();
        Mentor mentor = null;

        try{
            Criteria cr = session.createCriteria(Mentor.class);
            cr.add(Restrictions.eq("student", student));
            mentor = (Mentor) cr.uniqueResult();
        }
        catch (HibernateException ex){
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return mentor;
    }

}
