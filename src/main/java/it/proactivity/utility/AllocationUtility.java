package it.proactivity.utility;

import it.proactivity.model.Allocation;
import it.proactivity.model.Company;
import it.proactivity.model.HumanResource;
import it.proactivity.model.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class AllocationUtility {

    public static Allocation createAnAllocation(Long humanResourceId, Long projectId) {
        if (humanResourceId == null || projectId == null) {

        }
    }

    public static Boolean deleteAnAllocation(Session session, Long id){
        if (session == null || id == null) {
            return false;
        }
        checkSession(session);

        final String query = "SELECT a " +
                "FROM Allocation a " +
                "WHERE a.id = :id";
        Query<Allocation> allocationQuery = session.createQuery(query).setParameter("id", id);
        List<Allocation> allocationList = allocationQuery.getResultList();
        if (allocationList.size() > 1 || allocationList == null) {
            endSession(session);
            return false;
        }
        session.delete(allocationList.get(0));
        endSession(session);
        return true;
    }
}
