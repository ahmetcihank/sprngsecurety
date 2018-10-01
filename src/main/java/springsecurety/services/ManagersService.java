package springsecurety.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsecurety.model.Employee;
import springsecurety.model.Managers;
import springsecurety.repository.EmployeeRepo;
import springsecurety.repository.ManagerRepo;

import java.util.List;

/**
 * Created by SOFTWARE02 on 01.09.2018.
 */

@Service
public class ManagersService {

    @Autowired
    ManagerRepo managerRepo;


    public List<Managers> getAllManagers(){

        return  managerRepo.findAll();
    }

    public List<Managers> deleteManagers(Integer id){

        managerRepo.deleteById(id);
        return  managerRepo.findAll();
    }

    public List<Managers> addManagers(Managers managers){

        managerRepo.save(managers);
        return  managerRepo.findAll();

    }


}
