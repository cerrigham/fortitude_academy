package it.proactivity.utility;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.security.auth.login.Configuration;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class Utility {

    public boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static Object findObjectFromLong(Session session, Long id, String className) throws NoSuchElementException{

        if (session == null) {
            return null;
        }
        if (id == null || className == null || className.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        final String selectObjectFromId = "SELECT o FROM " + className + " o WHERE o.id = :id";
        Query<Object> query = session.createQuery(selectObjectFromId);
        query.setParameter("id", id);

        List<Object> objectList = query.getResultList();
        if (objectList.isEmpty() || objectList == null) {
            return null;
        }else if(objectList.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }else {
            return objectList.get(0);
        }
    }


    public static List<Object> findObjectFromString(Session session, String attributeName, String attributeValue, String className) {
        if (session == null) {
            return null;
        }

        if (attributeName == null || attributeName.isEmpty() || className == null || className.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        final String selectObjectFromName = "SELECT o FROM " + className + " o WHERE o." + attributeName + " = :name";
        Query<Object> query = session.createQuery(selectObjectFromName);
        query.setParameter("name", attributeValue);

        List<Object> objectList = query.getResultList();
        if (objectList.isEmpty() || objectList == null) {
            return null;
        }else {
            return objectList;
        }
    }

    public static List<Object> findObjectsFromDate(Session session,String attributeName,  String date, String className) {
        if (session == null) {
            return null;
        }
        if (date == null || date.isEmpty() || className == null || className.isEmpty() || attributeName == null || attributeName.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        LocalDate parsedDate = ParsingUtility.parseStringIntoDate(date);
        if (parsedDate == null) {
            SessionUtility.endSession(session);
            return null;
        }
        final String selectObjectFromDate = "SELECT o FROM " + className + " o WHERE o." + attributeName + " = :date";

        Query<Object> query = session.createQuery(selectObjectFromDate);
        query.setParameter("date", parsedDate);

        List<Object> objects = query.getResultList();
        if (objects == null || objects.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }
        SessionUtility.endSession(session);
        return objects;
    }

    public static List<Object> findObjectFromObject(Session session, String attributeName, Object object, String className) {
        if (session == null) {
            return null;
        }
        if (attributeName == null || attributeName.isEmpty() || object == null || className == null || className.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        final String selectObjectFromObject = "SELECT o FROM " + className + " o WHERE o." + attributeName + " = :object";

        Query<Object> query = session.createQuery(selectObjectFromObject);
        query.setParameter("object", object);

        List<Object> objects = query.getResultList();
        if (objects == null || objects.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }
        SessionUtility.endSession(session);
        return objects;
    }

    public static Object findObjectFromLongWithCriteria(Session session, Long id , Class c) throws NoSuchElementException {
        if (session == null) {
            return null;
        }

        if (id == null || id.equals(0l)) {
            return null;
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(c);
        Root<Object> root = cq.from(c);

        cq.select(root).where(cb.equal(root.get("id"),id));

        Query query = session.createQuery(cq);
        List<Object> objects = query.getResultList();
        if (objects == null || objects.isEmpty()) {
            return null;
        }else if (objects.size() > 1) {
            throw new NoSuchElementException();
        }else {
            return objects.get(0);
        }
    }

    public static List<Object> findObjectsFromStringWithCriteria(Session session, String attributeName, String attributeValue, Class c) {
        if (session == null) {
            return null;
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(c);
        Root<Object> root = cq.from(c);
        cq.select(root).where(cb.equal(root.get(attributeName),attributeValue));


        Query query = session.createQuery(cq);
        List<Object> objects = query.getResultList();

        if (objects == null || objects.isEmpty()) {
            return null;
        }
        return objects;
    }

    public static List<Object> findObjectFromDateWithCriteria(Session session, String attributeName, String date, Class c) {
        if (session == null) {
            return null;
        }
        LocalDate parsedDate = ParsingUtility.parseStringIntoDate(date);
        if (parsedDate == null) {
            return null;
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(c);
        Root<Object> root = cq.from(c);
        cq.select(root).where(cb.equal(root.get(attributeName),parsedDate));


        Query query = session.createQuery(cq);
        List<Object> objects = query.getResultList();

        if (objects == null || objects.isEmpty()) {
            return null;
        }
        return objects;
    }

    public static List<Object> findObjectFromObjectWithCriteria(Session session, String attributeName, Object o, Class c) {
        if (session == null || o == null) {
            return null;
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(c);
        Root<Object> root = cq.from(c);
        cq.select(root).where(cb.equal(root.get(attributeName),o));

        Query query = session.createQuery(cq);
        List<Object> objects = query.getResultList();

        if (objects == null || objects.isEmpty()) {
            return null;
        }
        return objects;

    }

}
